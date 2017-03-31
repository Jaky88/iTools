package com.onyx.itools;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/25 0025,2:03
 * @Version: V1.0
 * @Description: TODO
 */
public class IToolsApplication extends Application{
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private static IToolsApplication sInstance =null;
    public  Handler getHandler() {
        return mHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
    public static IToolsApplication getInstance(){
        return sInstance;
    }
}
