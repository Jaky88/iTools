package com.onyx.itools.config;

import android.content.Context;

import com.onyx.itools.data.JsonObject;
import com.onyx.itools.utils.RawResourceUtil;

import java.util.Locale;

/**
 * Created by 12345 on 2017/3/25.
 */

public class AppConfig {
    static private AppConfig mInstance;
    static private String TAG = AppConfig.class.getSimpleName();
    static private Locale currentLocale = null;
    private JsonObject mJson;

    private AppConfig(Context context) {
        mJson = jsonFromRawResource(context, "config");
    }

    static public AppConfig getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AppConfig(context);
        }
        currentLocale = context.getResources().getConfiguration().locale;
        return mInstance;
    }

    private JsonObject jsonFromRawResource(Context context, final String name) {
        JsonObject object = null;
        try {
            int resId = context.getResources().getIdentifier(name.toLowerCase(), "raw", context.getPackageName());
            object = RawResourceUtil.jsonFromRawResource(context, resId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return object;
        }
    }

    public int getSplashDelay() {
        return mJson.getInt(ConstantValue.SPLASH_DELAY);
    }
}
