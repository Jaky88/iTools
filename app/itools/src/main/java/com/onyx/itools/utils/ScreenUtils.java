package com.onyx.itools.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/4 0004,19:53
 * @Version: V1.0
 * @Description: TODO
 */
public class ScreenUtils {
    public static int getScreenHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return  wm.getDefaultDisplay().getHeight();
    }

    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return  wm.getDefaultDisplay().getWidth();
    }
}
