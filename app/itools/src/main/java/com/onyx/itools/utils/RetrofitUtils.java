package com.onyx.itools.utils;

import android.content.Context;

import com.onyx.itools.config.ConfigCenter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/1 0001,1:31
 * @Version: V1.0
 * @Description: 封装一个retrofit集成0kHttp3的抽象基类
 */
public class RetrofitUtils {

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    protected static Retrofit getRetrofit(Context context) {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ConfigCenter.getInstance(context).getSeverUrl() + "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }

        return mRetrofit;
    }
}
