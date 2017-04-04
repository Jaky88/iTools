package com.onyx.itools.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.onyx.itools.R;
import com.onyx.itools.config.ConfigCenter;
import com.onyx.itools.net.NetWorks;
import com.onyx.itools.net.bean.MenuBean;
import com.onyx.itools.net.bean.Verification;
import com.onyx.itools.utils.ActivityUtil;
import com.onyx.itools.utils.SharedPrefUtil;

import butterknife.ButterKnife;
import rx.Observer;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/25 0025,2:04
 * @Version: V1.0
 * @Description: 启动Logo页
 */
public class SplashActivity extends AppCompatActivity {
    boolean isFirstOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirstOpen = SharedPrefUtil.getBoolean(this, SharedPrefUtil.FIRST_OPEN, true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFirstOpen) {
                    ActivityUtil.startActivity(SplashActivity.this, GuideActivity.class);
                }else{
                    ActivityUtil.startActivity(SplashActivity.this, MainActivity.class);
                }
            }
        }, ConfigCenter.getInstance(this).getSplashDelay());

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
//        verfacationCode();
    }

    private void verfacationCode() {
        NetWorks.verfacationCodePost("15910435235", "123456", new Observer<Verification>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
//                mTv.setText(e.getLocalizedMessage());
                Log.e("MAIN2",e.getLocalizedMessage()+"--"+e.getMessage());
            }

            @Override
            public void onNext(Verification verification) {
//                mTv.setText(verification.getUser().toString());
            }
        });

        NetWorks.Getcache(new Observer<MenuBean>() {
            @Override
            public void onCompleted() {
                //完成
            }

            @Override
            public void onError(Throwable e) {
                //异常
//                mTv2.setText(e.getLocalizedMessage());
                Log.e("MAIN2",e.getLocalizedMessage()+"--"+e.getMessage());
            }

            @Override
            public void onNext(MenuBean baseBean) {
                //成功
//                mTv2.setText(baseBean.toString());
            }
        });
    }

}
