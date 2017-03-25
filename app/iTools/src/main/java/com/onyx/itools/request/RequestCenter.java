package com.onyx.itools.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 12345 on 2017/3/25.
 */

public class RequestCenter {
    private List<BaseRequest> mRequestPool;
    public RequestCenter(){
        initRequestPool();
    }

    private void initRequestPool() {
        mRequestPool = Collections.synchronizedList(new ArrayList<BaseRequest>());
    }

    public boolean addRequest(final BaseRequest request) {
        if (request == null) {
            return false;
        }
        synchronized (mRequestPool) {
            mRequestPool.add(request);
        }
        submitRequest(request);
        return true;
    }

    private void submitRequest(final BaseRequest request) {
        request.mCallback.onPreExecute(request);
        if (request.isAbortPendingTasks()) {
            abortAllRequests(!request.mLongTermJob, request.mLongTermJob);
        }
        RequestHandler.getInstance().dispatchTask(request.isRunInBackground(), new Runnable() {
            @Override
            public void run() {
                try {
                    request.mCallback.onProcess(request);
                    RequestHandler.getInstance().handle(request, RequestCenter.this);
                } catch (Exception ex) {
                    request.mCallback.onCompleted(request, ex);
                }
            }
        });
    }

    private void removeRequest(final BaseRequest request) {
        synchronized (mRequestPool) {
            mRequestPool.remove(request);
        }
    }

    public void abortAllRequests(boolean abortNormalJob, boolean abortLongTermJob) {
        synchronized (mRequestPool) {
            for (BaseRequest request : mRequestPool) {
                if (!request.isAbort()) {
                    continue;
                }
                if ((abortLongTermJob && request.mLongTermJob) || (abortNormalJob && !request.mLongTermJob)) {
                    request.setAbort(true);
                }
            }
        }
    }
}
