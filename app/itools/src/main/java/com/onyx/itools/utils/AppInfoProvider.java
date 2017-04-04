package com.onyx.itools.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.onyx.itools.entity.AppInfoBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/4 0004,14:52
 * @Version: V1.0
 * @Description: TODO
 */
public class AppInfoProvider {
    public static final int FILTER_ALL_APP = 0;
    public static final int FILTER_SYSTEM_APP = 1;
    public static final int FILTER_THIRD_APP = 2;
    public static final int FILTER_SDCARD_APP = 3;

    public static List<AppInfoBean> getInstallAppsInfo(Context context, int type) {
        PackageManager pm =  context.getPackageManager();
        List<ApplicationInfo> applicationInfos = pm
                .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        Collections.sort(applicationInfos, new ApplicationInfo.DisplayNameComparator(pm));
        List<AppInfoBean> list = new ArrayList<AppInfoBean>();
        switch (type) {
            case FILTER_ALL_APP:
                for (ApplicationInfo applicationInfo : applicationInfos) {
                    list.add(getAppInfo(pm, applicationInfo));
                }
                break;
            case FILTER_SYSTEM_APP:
                for (ApplicationInfo applicationInfo : applicationInfos) {
                    if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        list.add(getAppInfo(pm, applicationInfo));
                    }
                }
            case FILTER_THIRD_APP:

                for (ApplicationInfo applicationInfo : applicationInfos) {
                    if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        list.add(getAppInfo(pm, applicationInfo));
                    }
                    else if ((applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                        list.add(getAppInfo(pm, applicationInfo));
                    }
                }
            case FILTER_SDCARD_APP:
                for (ApplicationInfo applicationInfo : applicationInfos) {
                    if (applicationInfo.flags == ApplicationInfo.FLAG_SYSTEM) {
                        list.add(getAppInfo(pm, applicationInfo));
                    }
                }
            default:
                break;
        }
        return list;
    }

    public static AppInfoBean getAppInfo(PackageManager pm, ApplicationInfo applicationInfo) {
        Drawable icon = applicationInfo.loadIcon(pm);
        String name = applicationInfo.loadLabel(pm).toString();
        AppInfoBean info = new AppInfoBean(icon, name, applicationInfo.packageName);
        return info;
    }

}
