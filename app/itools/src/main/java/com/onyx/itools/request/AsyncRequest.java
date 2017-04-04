package com.onyx.itools.request;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.onyx.itools.net.NetWorks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 12345 on 2017/3/25.
 */
public class AsyncRequest<T> {
    public final static int DEFAULT_REQUEST = 0;
    public final static int NATIVE_DATA_REQUEST = 1;
    public final static int REMOTE_DATA_REQUEST = 2;

    private static Handler handler;
    private  RequestCallback<T> callback;
    private  int  requestType = 0;
    private static ExecutorService executorService = Executors.newFixedThreadPool(15);

    public AsyncRequest(int requestType, RequestCallback<T> callback){
        this.requestType = requestType;
        this.callback = callback;
    }

    public void execute() {
        callback.onStart();
        run();
    }

    private void run() {
        if(requestType == 2){
            NetWorks.verfacationCodeGet("15910435235", "123456",null);
        } else{
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Message message = Message.obtain();
                    message.obj = new ResultData<T>(AsyncRequest.this, callback.onDoInBackground());
                    getHandler().sendMessage(message);
                }
            });
        }

    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    private static class MyHandler<T> extends Handler {

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ResultData<T> resultData = (ResultData<T>) msg.obj;
            resultData.asyncRequest.callback.onResult(resultData.data);
        }
    }

    private static Handler getHandler() {
        if (handler == null) {
            synchronized (MyHandler.class) {
                if (handler == null) {
                    handler = new MyHandler(Looper.getMainLooper());
                }
            }
        }
        return handler;
    }



    private static class ResultData<T> {
        AsyncRequest asyncRequest;
        T data;

        public ResultData(AsyncRequest asyncRequest, T data) {
            this.asyncRequest = asyncRequest;
            this.data = data;
        }
    }



}
