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

package com.facebook.buck.rules.coercer;

import com.facebook.buck.core.cell.nameresolver.CellNameResolver;
import com.facebook.buck.core.model.TargetConfiguration;
import com.facebook.buck.core.path.ForwardRelativePath;
import com.facebook.buck.io.filesystem.ProjectFilesystem;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import java.util.Collection;
import java.util.SortedSet;

public class SortedSetTypeCoercer<T extends Comparable<? super T>>
    extends CollectionTypeCoercer<ImmutableSortedSet<T>, T> {

  private final TypeCoercer<Object, T> elementTypeCoercer;
  private final SortedSetConcatable<T> concatable = new SortedSetConcatable<>();
  private final TypeToken<ImmutableSortedSet<T>> typeToken;

  public SortedSetTypeCoercer(TypeCoercer<Object, T> elementTypeCoercer) {
    super(elementTypeCoercer);
    this.elementTypeCoercer = elementTypeCoercer;
    this.typeToken =
        new TypeToken<ImmutableSortedSet<T>>() {}.where(
            new TypeParameter<T>() {}, elementTypeCoercer.getOutputType());
  }

  @Override
  public TypeToken<ImmutableSortedSet<T>> getOutputType() {
    return typeToken;
  }

  protected void fillSortedSet(
      CellNameResolver cellNameResolver,
      ProjectFilesystem filesystem,
      ForwardRelativePath pathRelativeToProjectRoot,
      TargetConfiguration targetConfiguration,
      TargetConfiguration hostConfiguration,
      SortedSet<T> builder,
      Object object)
      throws CoerceFailedException {

    if (object instanceof Collection) {
      for (Object element : (Iterable<?>) object) {
        // if any element failed, the entire collection fails
        T coercedElement =
            elementTypeCoercer.coerce(
                cellNameResolver,
                filesystem,
                pathRelativeToProjectRoot,
                targetConfiguration,
                hostConfiguration,
                element);
        boolean alreadyExists = !builder.add(coercedElement);
        if (alreadyExists) {
          throw new CoerceFailedException(
              String.format("duplicate element \"%s\"", coercedElement));
        }
      }
    } else {
      throw CoerceFailedException.simple(object, getOutputType());
    }
  }

  @Override
  public ImmutableSortedSet<T> coerce(
      CellNameResolver cellRoots,
      ProjectFilesystem filesystem,
      ForwardRelativePath pathRelativeToProjectRoot,
      TargetConfiguration targetConfiguration,
      TargetConfiguration hostConfiguration,
      Object object)
      throws CoerceFailedException {
    ImmutableSortedSet.Builder<T> builder = ImmutableSortedSet.naturalOrder();
    fill(
        cellRoots,
        filesystem,
        pathRelativeToProjectRoot,
        targetConfiguration,
        hostConfiguration,
        builder,
        object);
    return builder.build();
  }

  @Override
  public ImmutableSortedSet<T> concat(Iterable<ImmutableSortedSet<T>> elements) {
    return concatable.concat(elements);
  }
}
