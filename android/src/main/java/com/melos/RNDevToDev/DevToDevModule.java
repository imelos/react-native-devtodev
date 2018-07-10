package com.melos.RNDevToDev;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;

import java.lang.Exception;

import org.json.JSONObject;

import com.devtodev.core.DevToDev;

public class DevToDevModule extends ReactContextBaseJavaModule {
    final static String ModuleName = "DevToDev";

    public DevToDevModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return ModuleName;
    }

    @ReactMethod
    public void init(String key, String secret) {
        DevToDev.init(getReactApplicationContext().getApplicationContext(), key, secret);
    }

    @ReactMethod
    public void setCurrentLevel(int currentLevel) {
        DevToDev.setCurrentLevel(currentLevel);
    }

    @ReactMethod
    public void setUserId(String activeUserId) {
        DevToDev.setUserId(activeUserId);
    }
}
