package com.onyx.itools.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/25 0025,2:26
 * @Version: V1.0
 * @Description: TODO
 */
public class ActivityUtil {
    public static void startActivity(Activity activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        activity.finish();
    }


    public static void startActivity(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);

        if (intent != null) {
            context.startActivity(intent);
        }
    }
}
