package com.onyx.itools.net;

import com.onyx.itools.net.bean.MenuBean;
import com.onyx.itools.net.bean.Verification;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/4 0004,12:11
 * @Version: V1.0
 * @Description: TODO
 */
public interface NetService {
    //设缓存有效期为1天
    public static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";


    //POST请求
    @FormUrlEncoded
    @POST("bjws/app.user/login")
    Observable<Verification> getVerfcationCodePost(@Field("tel") String tel, @Field("password") String pass);

    //POST请求
    @FormUrlEncoded
    @POST("bjws/app.user/login")
    Observable<Verification> getVerfcationCodePostMap(@FieldMap Map<String, String> map);

    //GET请求
    @GET("bjws/app.user/login")
    Observable<Verification> getVerfcationGet(@Query("tel") String tel, @Query("password") String pass);


    //GET请求，设置缓存
    @Headers("Cache-Control: public," + CACHE_CONTROL_CACHE)
    @GET("bjws/app.user/login")
    Observable<Verification> getVerfcationGetCache(@Query("tel") String tel, @Query("password") String pass);


    @Headers("Cache-Control: public," + CACHE_CONTROL_NETWORK)
    @GET("bjws/app.menu/getMenu")
    Observable<MenuBean> getMainMenu();

}
