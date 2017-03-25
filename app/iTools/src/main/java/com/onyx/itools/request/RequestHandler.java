package com.onyx.itools.request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 12345 on 2017/3/25.
 */

public class RequestHandler {
    private static final String TAG = RequestHandler.class.getSimpleName();

    public static RequestHandler mInstance = null;
    private ExecutorService mSingleThreadPool = null;

    public RequestHandler(){

    }
    public static RequestHandler getInstance(){
        if(mInstance == null){
            mInstance = new RequestHandler();
        }
        return mInstance;
    }

    public void dispatchTask(boolean isRunInBackground, Runnable task) {
        if (isRunInBackground) {
            asyncProcess(task);
        } else {
            syncProcess(task);
        }
    }

    public void asyncProcess(Runnable task) {
        if (mSingleThreadPool == null) {
            mSingleThreadPool = Executors.newSingleThreadExecutor();
        }
        mSingleThreadPool.submit(task);
    }

    public void syncProcess(Runnable task) {
        task.run();
    }

    public void handle(final BaseRequest request, RequestCenter center) throws Exception {
        if (request.isAbort()) {
            return;
        }
        String type = request.getRequestType();
        switch (type) {
            case RequestType.REQUEST_START:
                processStartRequest(request);
                break;
        }
    }

    private void processStartRequest(BaseRequest request) {

    }
}
