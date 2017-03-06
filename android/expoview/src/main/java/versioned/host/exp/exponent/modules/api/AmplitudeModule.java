// Copyright 2015-present 650 Industries. All rights reserved.

package versioned.host.exp.exponent.modules.api;

import com.amplitude.api.AmplitudeClient;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import host.exp.exponent.analytics.Analytics;
import host.exp.exponent.utils.JSONBundleConverter;
import host.exp.exponent.utils.ScopedContext;
import versioned.host.exp.exponent.ReadableObjectUtils;
import versioned.host.exp.exponent.ScopedReactApplicationContext;

public class AmplitudeModule extends ReactContextBaseJavaModule {

  private ScopedReactApplicationContext mReactApplicationContext;
  private AmplitudeClient mClient;

  public AmplitudeModule(ScopedReactApplicationContext reactContext) {
    super(reactContext);
    mReactApplicationContext = reactContext;
  }

  @Override
  public String getName() {
    return "ExponentAmplitude";
  }

  @ReactMethod
  public void initialize(final String apiKey) {
    Analytics.resetAmplitudeDatabaseHelper();
    mClient = new AmplitudeClient();
    mClient.initialize(mReactApplicationContext, apiKey);
  }

  @ReactMethod
  public void setUserId(final String userId) {
    if (mClient != null) {
      mClient.setUserId(userId);
    }
  }

  @ReactMethod
  public void setUserProperties(final ReadableMap properties) {
    if (mClient != null) {
      mClient.setUserProperties(ReadableObjectUtils.readableMapToJson(properties));
    }
  }

  @ReactMethod
  public void clearUserProperties() {
    if (mClient != null) {
      mClient.clearUserProperties();
    }
  }

  @ReactMethod
  public void logEvent(final String eventName) {
    if (mClient != null) {
      mClient.logEvent(eventName);
    }
  }

  @ReactMethod
  public void logEventWithProperties(final String eventName, final ReadableMap properties) {
    if (mClient != null) {
      mClient.logEvent(eventName, ReadableObjectUtils.readableMapToJson(properties));
    }
  }

  @ReactMethod
  public void setGroup(final String groupType, final ReadableArray groupNames) {
    if (mClient != null) {
      mClient.setGroup(groupType, ReadableObjectUtils.readableArrayToJson(groupNames));
    }
  }
}