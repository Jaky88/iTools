package com.onyx.itools.config;

import android.content.Context;

import com.onyx.itools.utils.RawResourceUtil;

import java.util.List;
import java.util.Locale;

/**
 * Created by 12345 on 2017/3/25.
 */

public class ConfigCenter {
    static private ConfigCenter mInstance;
    static private String TAG = ConfigCenter.class.getSimpleName();
    static private Locale currentLocale = null;
    private JsonObject mJsonObject;

    private ConfigCenter(Context context) {
        mJsonObject = jsonFromRawResource(context, "config");
    }

    static public ConfigCenter getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ConfigCenter(context);
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
        return mJsonObject.getInt(ConfigConstant.SPLASH_DELAY);
    }

    public List<String> getAppFilters() {
        if(mJsonObject.hasKey(ConfigConstant.APP_LIST_FILTERS)){
            return mJsonObject.getList(ConfigConstant.APP_LIST_FILTERS);
        }
        return null;
    }

    public String getSeverUrl() {
        return mJsonObject.getString(ConfigConstant.SERVER_URL);
    }
}
