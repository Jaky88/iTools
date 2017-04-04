package com.onyx.itools.entity;

import android.graphics.drawable.Drawable;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/4 0004,14:07
 * @Version: V1.0
 * @Description: TODO
 */
public class AppInfoBean {
    public Drawable appIcon;
    public String appName;
    public String appPackageName;

    public AppInfoBean(Drawable appIcon, String appName, String appPackageName) {
        this.appIcon = appIcon;
        this.appName = appName;
        this.appPackageName = appPackageName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }
}
