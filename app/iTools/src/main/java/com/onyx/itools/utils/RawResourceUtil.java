package com.onyx.itools.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.onyx.itools.data.JsonObject;

/**
 * Created by 12345 on 2017/3/25.
 */

public class RawResourceUtil {
    public static String readStringFromRawResource(Context context, int rawResourceId) {
        BufferedReader br = null;
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().openRawResource(rawResourceId);
            br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
        finally {
            closeQuietly(br);
            closeQuietly(inputStream);
        }
        return null;
    }

    public static Map<String, List<Integer>> integerMapFromRawResource(Context context, int rawResourceId) {
        String strJson = readStringFromRawResource(context, rawResourceId);
        return JSON.parseObject(strJson, new TypeReference<Map<String, List<Integer>>>(){});
    }

    public static JsonObject jsonFromRawResource(Context context, int rawResourceId) {
        String jsonStr = readStringFromRawResource(context, rawResourceId);
        try {
            Map<String, Object> map = JSON.parseObject(jsonStr);
            if (map == null) {
                return null;
            }
            JsonObject json = new JsonObject();
            for(Map.Entry<String, Object> entry : map.entrySet()) {
                json.putObject(entry.getKey(), entry.getValue());
            }
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
