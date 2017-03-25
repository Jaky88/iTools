package com.onyx.itools.request;

/**
 * Created by 12345 on 2017/3/25.
 */
public abstract class RequestCallback {
    public void onPreExecute(BaseRequest request) {
    }

    public void onProcess(BaseRequest request) {
    }

    public abstract void onCompleted(BaseRequest request, Exception e);
}
