package com.onyx.itools.utils;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.Log;

import com.onyx.itools.entity.DeviceInfoBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.TimeZone;


/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/5 0005,21:24
 * @Version: V1.0
 * @Description: TODO
 */
public class DeviceInfoProvider {

    private static final String TAG = DeviceInfoProvider.class.getSimpleName();

    public static List<DeviceInfoBean> getAllDeviceInfo(Context context, int type) {
        return null;
    }

    public static DeviceInfoBean.SystemInfo getSystemInfo(Context context) {
        DeviceInfoBean bean=new DeviceInfoBean();
        TimeZone tz = TimeZone.getDefault();
        bean.systemInfo.osName = System.getProperty("");
        bean.systemInfo.osVersion=Build.VERSION.RELEASE;
        bean.systemInfo.osVersionNum=Build.VERSION.SDK_INT;
        bean.systemInfo.sdkVersion =Build.VERSION.SDK;
        bean.systemInfo.timeZone = tz.getID();
        bean.systemInfo.bootVersion = Build.BOOTLOADER;
        bean.systemInfo.kernelVersion = getKernelVersion();
        String javaVersion;
        String javaClassVersion;
        String javaClassPath;
        String javaHome;
        return bean.systemInfo;
    }

    public static DeviceInfoBean getDeviceInfo(Context context) {
        String mobileName = Build.MODEL;
        String osVersion = Build.VERSION.RELEASE;
        String modelAlias;
        String branch=Build.BRAND;
        String vendor;
        String serialNum;
        String deviceID = Build.ID;
        String simSerialNum;
        String board=Build.BOARD;

        DeviceInfoBean dInfo = new DeviceInfoBean();
        dInfo.deviceInfo.setModel(Build.DEVICE);

        return null;
    }

    public static DeviceInfoBean getDisplayInfo(Context context) {
        return null;
    }

    public static DeviceInfoBean.CpuInfo getCpuInfo(Context context) {
        DeviceInfoBean bean=new DeviceInfoBean();
        String name;
        String arch;
        String core;
        String abi=Build.CPU_ABI;
        String accelerator;
        String frequency;
        String useRate;
        String openGL;

        String str1 = "/proc/cpuinfo";
        String str2="";
        String[] cpuInfo={"",""};
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bean.cpuInfo;
    }

    public static List<DeviceInfoBean.BatteryInfo> getBatteryInfo(Context context) {
        return null;
    }

    public static List<DeviceInfoBean.MemoryInfo> getMemoryInfo(Context context) {
        return null;
    }

    public static List<DeviceInfoBean.NetInfo> getNetInfo(Context context) {
        return null;
    }

    public static List<DeviceInfoBean.WifiInfo> getWifiInfo(Context context) {
        return null;
    }


    public static void getTotalMemory() {
        String str1 = "/proc/meminfo";
        String str2 = "";
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                Log.i(TAG, "---" + str2);
            }
        } catch (IOException e) {
        }
    }

    public static long getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return mi.availMem;
    }

    public long[] getRomMemroy() {
        long[] romInfo = new long[2];
        //Total rom memory  
        romInfo[0] = getTotalInternalMemorySize();

        //Available rom memory  
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        romInfo[1] = blockSize * availableBlocks;
        getVersion();
        return romInfo;
    }

    public long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    public String[] getVersion(){
        String[] version={"null","null","null","null"};
        String str1 = "/proc/version";
        String str2;
        String[] arrayOfString;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            version[0]=arrayOfString[2];//KernelVersion
            localBufferedReader.close();
        } catch (IOException e) {
        }
        version[1] = Build.VERSION.RELEASE;// firmware version
        version[2]=Build.MODEL;//model
        version[3]=Build.DISPLAY;//system version
        return version;
    }

    public long[] getSDCardMemory() {
        long[] sdCardInfo = new long[2];
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(sdcardDir.getPath());
            long bSize = sf.getBlockSize();
            long bCount = sf.getBlockCount();
            long availBlocks = sf.getAvailableBlocks();

            sdCardInfo[0] = bSize * bCount;//总大小
            sdCardInfo[1] = bSize * availBlocks;//可用大小
        }
        return sdCardInfo;
    }

    private BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            //  level加%就是当前电量了
        }
    };


    public String[] getOtherInfo(Context context){
        String[] other={"null","null"};
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if(wifiInfo.getMacAddress()!=null){
            other[0]=wifiInfo.getMacAddress();
        } else {
            other[0] = "Fail";
        }
        other[1] = getTimes(context);
        return other;
    }
    private String getTimes(Context context) {
        long ut = SystemClock.elapsedRealtime() / 1000;
        if (ut == 0) {
            ut = 1;
        }
        int m = (int) ((ut / 60) % 60);
        int h = (int) ((ut / 3600));
        return h + " " + "info_times_hour" + m + " "
                + "info_times_minute";
    }

    public String formatSize(long size) {
        String suffix = null;
        float fSize=0;

        if (size >= 1024) {
            suffix = "KB";
            fSize=size / 1024;
            if (fSize >= 1024) {
                suffix = "MB";
                fSize /= 1024;
            }
            if (fSize >= 1024) {
                suffix = "GB";
                fSize /= 1024;
            }
        } else {
            fSize = size;
        }
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        StringBuilder resultBuffer = new StringBuilder(df.format(fSize));
        if (suffix != null)
            resultBuffer.append(suffix);
        return resultBuffer.toString();
    }


    public static String getKernelVersion() {
        Process process = null;
        String kernelVersion ="";

        try {
            process = Runtime.getRuntime().exec("cat /proc/version");
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8 * 1024);
        String result ="";
        String info;

        try {
            while ((info = bufferedReader.readLine()) != null) {
                result += info;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (result != "") {
                String keyword = "version" ;
                int index = result.indexOf(keyword);
                info = result.substring(index + keyword.length());
                index = info.indexOf("");
                kernelVersion = info.substring(0, index);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return kernelVersion;
    }
}
