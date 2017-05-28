package com.onyx.itools.entity;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/5 0005,20:51
 * @Version: V1.0
 * @Description: TODO
 */
public class DeviceInfoBean {
    public SystemInfo systemInfo;
    public DeviceInfo deviceInfo;
    public DisplayInfo displayInfo;
    public CpuInfo cpuInfo;
    public BatteryInfo batteryInfo;
    public MemoryInfo memoryInfo;
    public NetInfo netInfo;
    public WifiInfo wifiInfo;

    public class SystemInfo{
        public String osName;
        public String osVersion;
        public int osVersionNum;
        public String sdkVersion;
        public String timeZone;
        public String bootVersion;
        public String kernelVersion;
//        public String javaVersion;
//        public String javaClassVersion;
//        public String javaClassPath;
//        public String javaHome;
    }

    public class DeviceInfo{
        public String model;
        public String modelAlias;
        public String branch;
        public String vendor;
        public String serialNum;
        public String deviceID;
        public String simSerialNum;
        public String board;

        public DeviceInfo() {
        }

        public DeviceInfo(String model, String modelAlias, String branch, String vendor, String serialNum, String deviceID, String simSerialNum) {
            this.model = model;
            this.modelAlias = modelAlias;
            this.branch = branch;
            this.vendor = vendor;
            this.serialNum = serialNum;
            this.deviceID = deviceID;
            this.simSerialNum = simSerialNum;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getModelAlias() {
            return modelAlias;
        }

        public void setModelAlias(String modelAlias) {
            this.modelAlias = modelAlias;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getSerialNum() {
            return serialNum;
        }

        public void setSerialNum(String serialNum) {
            this.serialNum = serialNum;
        }

        public String getDeviceID() {
            return deviceID;
        }

        public void setDeviceID(String deviceID) {
            this.deviceID = deviceID;
        }

        public String getSimSerialNum() {
            return simSerialNum;
        }

        public void setSimSerialNum(String simSerialNum) {
            this.simSerialNum = simSerialNum;
        }
    }

    class DisplayInfo{
        public String resolution;
        public String screenDensity;
        public String format;
        public String smallWidth;
        public String frameRate;
    }

    public class CpuInfo{
        public String name;
        public String arch;
        public String core;
        public String abi;
        public String accelerator;
        public String frequency;
        public String useRate;
        public String openGL;
    }

    public class BatteryInfo{
        public String type;
        public String temperature;
        public String voltage;
        public String capacity;
        public String status;
    }

    public class MemoryInfo{
        public String totalRAM;
        public String freeRAM;
        public String totalIn;
        public String freeIn;
        public String totalEx;
        public String freeEx;
        public String cache;
    }

    public class NetInfo{
        public String netType;
        public String netStatus;
        public String ipV4;
        public String ipV6;
        public String currTcp;
        public String freeTcp;
    }

    public class WifiInfo{
        public String wifiName;
        public String ip;
        public String bssID;
        public String sign;
        public String dns;
        public String gateway;
        public String mask;
    }
}
