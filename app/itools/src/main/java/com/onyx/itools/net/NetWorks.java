package com.onyx.itools.net;

import com.onyx.itools.net.bean.MenuBean;
import com.onyx.itools.net.bean.Verification;
import com.onyx.itools.utils.RetrofitUtils;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/1 0001,1:42
 * @Version: V1.0
 * @Description: TODO
 */
public class NetWorks extends RetrofitUtils {
    protected static final NetService service = getRetrofit(null).create(NetService.class);

    //POST请求
    public static void verfacationCodePost(String tel, String pass,Observer<Verification> observer){
        setSubscribe(service.getVerfcationCodePost(tel, pass),observer);
    }


    //POST请求参数以map传入
    public static void verfacationCodePostMap(Map<String, String> map,Observer<Verification> observer) {
        setSubscribe(service.getVerfcationCodePostMap(map),observer);
    }

    //Get请求设置缓存
    public static void verfacationCodeGetCache(String tel, String pass,Observer<Verification> observer) {
        setSubscribe(service.getVerfcationGetCache(tel, pass),observer);
    }

    //Get请求
    public static void verfacationCodeGet(String tel, String pass,Observer<Verification> observer) {
        setSubscribe(service.getVerfcationGet(tel, pass),observer);
    }

    //Get请求
    public static void verfacationCodeGetsub(String tel, String pass, Observer<Verification> observer) {
        setSubscribe(service.getVerfcationGet(tel, pass),observer);
    }

    //Get请求
    public static void Getcache( Observer<MenuBean> observer) {
        setSubscribe(service.getMainMenu(),observer);
    }

    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
}
