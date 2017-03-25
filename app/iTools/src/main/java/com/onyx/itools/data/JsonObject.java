package com.onyx.itools.data;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by 12345 on 2017/3/25.
 */

public class JsonObject {
    private JSONObject mJson = new JSONObject();

    public JsonObject() {
        super();
    }

    public JsonObject(JSONObject json) {
        mJson = json;
    }

    public boolean hasKey(final String key) {
        if (isNull()){
            return false;
        }
        return mJson.containsKey(key);
    }

    public JSONObject getJson(){
        return mJson;
    }

    public void setJson(final JSONObject object) {
        mJson = object;
    }

    public JsonObject setNull() {
        mJson = null;
        return this;
    }
    public boolean isNull() {
        return (mJson == null);
    }

    public String getString(final String key)  {
        if (isNull()){
            return null;
        }
        if (mJson.containsKey(key)) {
            return mJson.getString(key);
        }
        return null;
    }

    public boolean putString(final String key, final String value) {
        if (isNull()){
            return false;
        }
        mJson.put(key, value);
//        invokeCallback(key);
        return true;
    }

    public boolean putObject(final String key, Object value) {
        if (isNull()){
            return false;
        }

        mJson.put(key, value);
//        invokeCallback(key);
        return true;
    }

    public Object getObject(final String key) {
        if (isNull()){
            return null;
        }
        return mJson.get(key);
    }

    public int getInt(final String key)  {
        if (isNull()){
            return -1;
        }
        return mJson.getInteger(key);
    }

    public boolean putInt(final String key, int value){
        if (isNull()){
            return false;
        }

        mJson.put(key, value);
//        invokeCallback(key);
        return true;
    }

    public float getFloat(final String key) {
        if (isNull()){
            return Float.NEGATIVE_INFINITY;
        }

        return mJson.getFloat(key);
    }

    public boolean putFloat(final String key, float value) {
        if (isNull()){
            return false;
        }

        mJson.put(key, value);
//        invokeCallback(key);
        return true;
    }
}
