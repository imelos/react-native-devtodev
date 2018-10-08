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
import com.devtodev.core.data.metrics.aggregated.events.CustomEventParams;

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

    @ReactMethod
    public void levelUp(int level) {
        DevToDev.levelUp(level);
    }

    @ReactMethod
    public void customEvent(String eventName, ReadableMap eventParams) {
        if (eventName == null) {
            return;
        }
        CustomEventParams params = new CustomEventParams();
        if (eventParams != null) {
            ReadableMapKeySetIterator iterator = eventParams.keySetIterator();
            while (iterator.hasNextKey()) {
                String paramName = iterator.nextKey();
                try {
                    switch (eventParams.getType(paramName)) {
                        case Number:
                            params.putDouble(paramName,eventParams.getDouble(paramName));
                            break;
                        case String:
                            params.putString(paramName,eventParams.getString(paramName));
                            break;
                        default:
                            break;
                    }
                } catch (Exception ex) {
                    Log.d(ModuleName, "put CustomEventParams fail: " + ex);
                }
            }
        }

        DevToDev.customEvent(eventName, params);
    }
}
