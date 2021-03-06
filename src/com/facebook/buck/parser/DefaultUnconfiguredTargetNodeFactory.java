/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.facebook.buck.parser;

import com.facebook.buck.core.cell.Cell;
import com.facebook.buck.core.cell.Cells;
import com.facebook.buck.core.description.BaseDescription;
import com.facebook.buck.core.exceptions.DependencyStack;
import com.facebook.buck.core.exceptions.HumanReadableException;
import com.facebook.buck.core.model.CellRelativePath;
import com.facebook.buck.core.model.UnconfiguredBuildTarget;
import com.facebook.buck.core.model.targetgraph.impl.ImmutableUnconfiguredTargetNode;
import com.facebook.buck.core.model.targetgraph.impl.Package;
import com.facebook.buck.core.model.targetgraph.raw.UnconfiguredTargetNode;
import com.facebook.buck.core.rules.knowntypes.KnownRuleTypes;
import com.facebook.buck.core.rules.knowntypes.RuleDescriptor;
import com.facebook.buck.core.rules.knowntypes.provider.KnownRuleTypesProvider;
import com.facebook.buck.core.select.SelectorList;
import com.facebook.buck.core.select.impl.SelectorListFactory;
import com.facebook.buck.parser.api.ProjectBuildFileParser;
import com.facebook.buck.parser.function.BuckPyFunction;
import com.facebook.buck.parser.syntax.ListWithSelects;
import com.facebook.buck.rules.coercer.CoerceFailedException;
import com.facebook.buck.rules.coercer.DataTransferObjectDescriptor;
import com.facebook.buck.rules.coercer.ParamInfo;
import com.facebook.buck.rules.coercer.TypeCoercerFactory;
import com.facebook.buck.rules.visibility.VisibilityAttributes;
import com.facebook.buck.rules.visibility.VisibilityPattern;
import com.facebook.buck.rules.visibility.parser.VisibilityPatterns;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

/**
 * Creates {@link UnconfiguredTargetNode} instances from raw data coming in form the {@link
 * ProjectBuildFileParser}.
 */
public class DefaultUnconfiguredTargetNodeFactory implements UnconfiguredTargetNodeFactory {

  private final KnownRuleTypesProvider knownRuleTypesProvider;
  private final BuiltTargetVerifier builtTargetVerifier;
  private final Cells cells;
  private final SelectorListFactory selectorListFactory;
  private final TypeCoercerFactory typeCoercerFactory;

  public DefaultUnconfiguredTargetNodeFactory(
      KnownRuleTypesProvider knownRuleTypesProvider,
      BuiltTargetVerifier builtTargetVerifier,
      Cells cells,
      SelectorListFactory selectorListFactory,
      TypeCoercerFactory typeCoercerFactory) {
    this.knownRuleTypesProvider = knownRuleTypesProvider;
    this.builtTargetVerifier = builtTargetVerifier;
    this.cells = cells;
    this.selectorListFactory = selectorListFactory;
    this.typeCoercerFactory = typeCoercerFactory;
  }

  private ImmutableMap<String, Object> convertSelects(
      UnconfiguredBuildTarget target,
      RuleDescriptor<?> descriptor,
      Map<String, Object> attrs,
      CellRelativePath pathRelativeToProjectRoot,
      DependencyStack dependencyStack) {
    ImmutableMap.Builder<String, Object> result = ImmutableMap.builder();
    DataTransferObjectDescriptor<?> constructorDescriptor =
        descriptor.dataTransferObjectDescriptor(typeCoercerFactory);
    for (Map.Entry<String, Object> attr : attrs.entrySet()) {
      if (attr.getKey().startsWith("buck.")
          || attr.getKey().equals(VisibilityAttributes.VISIBILITY)
          || attr.getKey().equals(VisibilityAttributes.WITHIN_VIEW)) {
        continue;
      }
      ParamInfo<?> paramInfo = constructorDescriptor.getParamInfos().get(attr.getKey());
      Preconditions.checkNotNull(paramInfo, "attr %s", attr.getKey());
      result.put(
          attr.getKey(),
          convertSelectorListInAttrValue(
              target,
              paramInfo,
              attr.getKey(),
              attr.getValue(),
              pathRelativeToProjectRoot,
              dependencyStack));
    }
    return result.build();
  }

  /**
   * Convert attr value {@link ListWithSelects} to {@link SelectorList} or keep it as is otherwise
   */
  private Object convertSelectorListInAttrValue(
      UnconfiguredBuildTarget buildTarget,
      ParamInfo<?> paramInfo,
      String attrName,
      Object attrValue,
      CellRelativePath pathRelativeToProjectRoot,
      DependencyStack dependencyStack) {
    if (attrValue instanceof ListWithSelects) {
      if (!paramInfo.isConfigurable()) {
        throw new HumanReadableException(
            dependencyStack,
            "%s: attribute '%s' cannot be configured using select",
            buildTarget,
            attrName);
      }

      try {
        return selectorListFactory.create(
            cells.getCell(pathRelativeToProjectRoot.getCellName()).getCellNameResolver(),
            pathRelativeToProjectRoot.getPath(),
            (ListWithSelects) attrValue);
      } catch (CoerceFailedException e) {
        throw new HumanReadableException(
            e,
            dependencyStack,
            "failed to coerce list with selects for attr %s: %s",
            attrName,
            e.getMessage());
      }
    } else {
      return attrValue;
    }
  }

  @Override
  public UnconfiguredTargetNode create(
      Cell cell,
      Path buildFile,
      UnconfiguredBuildTarget target,
      DependencyStack dependencyStack,
      Map<String, Object> rawAttributes,
      Package pkg) {
    KnownRuleTypes knownRuleTypes = knownRuleTypesProvider.get(cell);
    RuleDescriptor<?> descriptor = parseRuleTypeFromRawRule(knownRuleTypes, rawAttributes);

    // Because of the way that the parser works, we know this can never return null.
    BaseDescription<?> description = descriptor.getDescription();

    builtTargetVerifier.verifyBuildTarget(
        cell, descriptor.getRuleType(), buildFile, target, description, rawAttributes);

    String visibilityDefinerDescription = target.getFullyQualifiedName();

    ImmutableSet<VisibilityPattern> visibilityPatterns =
        VisibilityPatterns.createFromStringList(
            cell.getCellPathResolver(),
            VisibilityAttributes.VISIBILITY,
            rawAttributes.get(VisibilityAttributes.VISIBILITY),
            buildFile,
            () -> visibilityDefinerDescription);

    if (visibilityPatterns.isEmpty()) {
      visibilityPatterns = pkg.getVisibilityPatterns();
    }

    ImmutableSet<VisibilityPattern> withinViewPatterns =
        VisibilityPatterns.createFromStringList(
            cell.getCellPathResolver(),
            VisibilityAttributes.WITHIN_VIEW,
            rawAttributes.get(VisibilityAttributes.WITHIN_VIEW),
            buildFile,
            () -> visibilityDefinerDescription);

    if (withinViewPatterns.isEmpty()) {
      withinViewPatterns = pkg.getWithinViewPatterns();
    }

    ImmutableMap<String, Object> withSelects =
        convertSelects(
            target, descriptor, rawAttributes, target.getCellRelativeBasePath(), dependencyStack);

    return ImmutableUnconfiguredTargetNode.of(
        target, descriptor.getRuleType(), withSelects, visibilityPatterns, withinViewPatterns);
  }

  private static RuleDescriptor<?> parseRuleTypeFromRawRule(
      KnownRuleTypes knownRuleTypes, Map<String, Object> attributes) {
    String type =
        (String) Objects.requireNonNull(attributes.get(BuckPyFunction.TYPE_PROPERTY_NAME));
    return knownRuleTypes.getDescriptorByName(type);
  }
}
