package com.onyx.itools.request;

import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

/**
 * Created by 12345 on 2017/3/25.
 */
public abstract class RequestCallback<I,O> {
    @MainThread
    public void onStart(I i) {
    }

    @WorkerThread
    public abstract O onDoInBackground(I i);

    @MainThread
    public void onResult(O o) {
    }
}
