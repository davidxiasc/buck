/*
 * Copyright 2014-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.apple;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSString;
import com.dd.plist.PropertyListParser;
import com.facebook.buck.log.Logger;
import com.facebook.buck.util.VersionStringComparator;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

/**
 * Utility class to discover the location of SDKs contained inside an Xcode
 * installation.
 */
public class AppleSdkDiscovery {

  private static final Logger LOG = Logger.get(AppleSdkDiscovery.class);

  private static final Ordering<AppleSdk> APPLE_SDK_VERSION_ORDERING =
    Ordering
        .from(new VersionStringComparator())
        .onResultOf(new Function<AppleSdk, String>() {
            @Override
            public String apply(AppleSdk appleSdk) {
                return appleSdk.version();
            }
        });

  // Utility class; do not instantiate.
  private AppleSdkDiscovery() { }

  /**
   * Given a path to an Xcode developer directory, walks through the
   * platforms and builds a map of ({@link AppleSdk}: {@link AppleSdkPaths}) objects
   * describing the paths to the SDKs inside.
   *
   * The {@link AppleSdk#name()} strings match the ones displayed by {@code xcodebuild -showsdks}
   * and look like {@code macosx10.9}, {@code iphoneos8.0}, {@code iphonesimulator8.0},
   * etc.
   */
  public static ImmutableMap<AppleSdk, AppleSdkPaths> discoverAppleSdkPaths(Path xcodeDir)
      throws IOException {
    LOG.debug("Searching for Xcode platforms under %s", xcodeDir);

    ImmutableMap.Builder<AppleSdk, AppleSdkPaths> appleSdkPathsBuilder = ImmutableMap.builder();
    Path platforms = xcodeDir.resolve("Platforms");

    if (!Files.exists(platforms)) {
      return appleSdkPathsBuilder.build();
    }

    // We need to find the most recent SDK for each platform so we can
    // make the fall-back SDKs with no version number in their name
    // ("macosx", "iphonesimulator", "iphoneos").
    //
    // To do this, we store a map of (platform: [sdk1, sdk2, ...])
    // pairs where the SDKs for each platform are ordered by version.
    TreeMultimap<ApplePlatform, ImmutableAppleSdk> orderedSdksForPlatform =
        TreeMultimap.create(
            Ordering.natural(),
            APPLE_SDK_VERSION_ORDERING);

    try (DirectoryStream<Path> platformStream = Files.newDirectoryStream(
        platforms,
             "*.platform")) {
      for (Path platformDir : platformStream) {
        LOG.debug("Searching for Xcode SDKs under %s", platformDir);
        try (DirectoryStream<Path> sdkStream = Files.newDirectoryStream(
                 platformDir.resolve("Developer/SDKs"),
                 "*.sdk")) {
          for (Path sdkDir : sdkStream) {
            LOG.debug("Fetching SDK name for %s", sdkDir);
            if (Files.isSymbolicLink(sdkDir)) {
              continue;
            }

            ImmutableAppleSdk.Builder sdkBuilder = ImmutableAppleSdk.builder();
            if (buildSdkFromPath(sdkDir, sdkBuilder)) {
              ImmutableAppleSdk sdk = sdkBuilder.build();
              LOG.debug("Found SDK %s", sdk);
              ImmutableAppleSdkPaths xcodePaths = ImmutableAppleSdkPaths.builder()
                  .toolchainPath(xcodeDir.resolve("Toolchains/XcodeDefault.xctoolchain"))
                  .platformDeveloperPath(platformDir.resolve("Developer"))
                  .sdkPath(sdkDir)
                  .build();
              appleSdkPathsBuilder.put(sdk, xcodePaths);
              orderedSdksForPlatform.put(sdk.applePlatform(), sdk);
            }
          }
        }
      }
    }

    // Get a snapshot of what's in appleSdkPathsBuilder, then for each
    // ApplePlatform, add to appleSdkPathsBuilder the most recent
    // SDK with an unversioned name.
    ImmutableMap<AppleSdk, AppleSdkPaths> discoveredSdkPaths = appleSdkPathsBuilder.build();

    for (ApplePlatform platform : orderedSdksForPlatform.keySet()) {
      ImmutableAppleSdk mostRecentSdkForPlatform = orderedSdksForPlatform.get(platform).last();
      appleSdkPathsBuilder.put(
          mostRecentSdkForPlatform.withName(platform.toString()),
          discoveredSdkPaths.get(mostRecentSdkForPlatform));
    }

    // This includes both the discovered SDKs with versions in their names, as well as
    // the unversioned aliases added just above.
    return appleSdkPathsBuilder.build();
  }

  private static void addArchitecturesForPlatform(
      ImmutableAppleSdk.Builder sdkBuilder,
      ApplePlatform applePlatform) {
    // TODO(user): These need to be read from the SDK, not hard-coded.
    switch (applePlatform) {
      case MACOSX:
        // Fall through.
      case IPHONESIMULATOR:
        sdkBuilder.addArchitectures("i386", "x86_64");
        break;
      case IPHONEOS:
        sdkBuilder.addArchitectures("armv7", "arm64");
        break;
    }
  }

  private static boolean buildSdkFromPath(
        Path sdkDir,
        ImmutableAppleSdk.Builder sdkBuilder) throws IOException {
    try (InputStream sdkSettingsPlist = Files.newInputStream(sdkDir.resolve("SDKSettings.plist"));
         BufferedInputStream bufferedSdkSettingsPlist = new BufferedInputStream(sdkSettingsPlist)) {
      NSDictionary sdkSettings;
      try {
        sdkSettings = (NSDictionary) PropertyListParser.parse(bufferedSdkSettingsPlist);
      } catch (Exception e) {
        throw new IOException(e);
      }
      String name = ((NSString) sdkSettings.objectForKey("CanonicalName")).toString();
      String version = ((NSString) sdkSettings.objectForKey("Version")).toString();
      NSDictionary defaultProperties = (NSDictionary) sdkSettings.objectForKey("DefaultProperties");
      NSString platformName = (NSString) defaultProperties.objectForKey("PLATFORM_NAME");
      ApplePlatform applePlatform =
          ApplePlatform.valueOf(platformName.toString().toUpperCase(Locale.US));
      sdkBuilder.name(name).version(version).applePlatform(applePlatform);
      addArchitecturesForPlatform(sdkBuilder, applePlatform);
      return true;
    } catch (FileNotFoundException e) {
      LOG.error(e, "No SDKSettings.plist found under SDK path %s", sdkDir);
      return false;
    }
  }
}
