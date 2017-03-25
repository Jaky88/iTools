package com.onyx.itools.request;

import android.content.Context;

/**
 * Created by 12345 on 2017/3/25.
 */
public class BaseRequest {
    public Context mContext;
    private String mRequestType;
    private volatile int mRequestId;
    private boolean mRunInBackground = false;
    static private volatile int mGlobalRequestId;
    public boolean mLongTermJob = false;
    public RequestCallback mCallback;
    private volatile boolean mAbort = false;
    private volatile boolean mAbortPendingTasks = true;

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

    public boolean isAbortPendingTasks() {
        return mAbortPendingTasks;
    }
}
