package com.onyx.itools.request;

import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

/**
 * Created by 12345 on 2017/3/25.
 */
public abstract class RequestCallback<ResultData> {
    @MainThread
    public void onStart() {
    }

    @WorkerThread
    public abstract ResultData onDoInBackground();

    @MainThread
    public void onResult(ResultData resultData) {
    }
}
