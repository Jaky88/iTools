package com.onyx.itools.handler;

/**
 * Created by 12345 on 2017/3/25.
 */
public class BaseRequest {
    private String mRequestType;
    private volatile int mRequestId;
    private boolean mRunInBackground = false;
    static private volatile int mGlobalRequestId;
    private volatile boolean mAbort = false;
    public boolean mLongTermJob = false;

    public BaseRequest(String type) {
        mRequestType = type;
        mRunInBackground = true;
        mRequestId = generateRequestId();
    }

    static private int generateRequestId() {
        mGlobalRequestId += 1;
        return mGlobalRequestId;
    }

    public boolean isRunInBackground() {
        return mRunInBackground;
    }

    public int getRequestId() {
        return mRequestId;
    }

    public String getRequestType() {
        return mRequestType;
    }

    public boolean isAbort() {
        return mAbort;
    }

    public void setAbort(boolean abort) {
        mAbort = abort;
    }
}
