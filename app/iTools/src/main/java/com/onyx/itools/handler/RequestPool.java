package com.onyx.itools.handler;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 12345 on 2017/3/25.
 */

public class RequestPool {
    private List<BaseRequest> requestList;
    public RequestPool(){
        init();
    }

    private void init() {
        requestList = Collections.synchronizedList(new ArrayList<BaseRequest>());
    }

    private void addRequest(final BaseRequest request) {
        synchronized (requestList) {
            requestList.add(request);
        }
    }

    private void removeRequest(final BaseRequest request) {
        synchronized (requestList) {
            requestList.remove(request);
        }
    }

    public void abortAllRequests(boolean abortNormalJob, boolean abortLongTermJob) {
        synchronized (requestList) {
            for (BaseRequest request : requestList) {
                if (!request.isAbort()) {
                    continue;
                }
                if ((abortLongTermJob && request.mLongTermJob) || (abortNormalJob && !request.mLongTermJob)) {
                    request.setAbort(true);
                }
            }
        }
    }

    public boolean sendRequest(Context context, final BaseRequest request, final RequestCallback callback) {
        if (request == null) {
            if (callback != null) {
                callback.done(null, Exception.nullRequest());
            }
            return false;
        }
        request.context = context;
        request.callback = callback;
        final ContentProviderBase provider = getProvider(request);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final ObjectHolder<Exception> exceptionHolder = new ObjectHolder();
                try {
                    invokeCallbackStart(request);
                    provider.processRequest(request, ContentBrowser.this);
                } catch (Exception exception) {
                    exceptionHolder.add(exception);
                } catch (java.lang.Exception exception) {
                    exceptionHolder.add(Exception.createException(exception));
                } finally {
                    invokeCallbackDone(request, exceptionHolder.remove());
                }
            }
        };

        if (request.isAbortPendingTasks()) {
            abortAllRequests(!request.mLongTermJob, request.mLongTermJob);
        }
        addRequest(request);

        if (!request.isRunInBackground()) {
            runnable.run();
        } else {
            provider.submit(runnable, request);
        }
        return true;
    }
}
