package com.onyx.itools.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.onyx.itools.R;
import com.onyx.itools.config.AppConfig;
import com.onyx.itools.utils.ActivityUtil;
import com.onyx.itools.utils.SharedPrefUtil;

import butterknife.ButterKnife;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/25 0025,2:04
 * @Version: V1.0
 * @Description:
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
        },AppConfig.getInstance(this).getSplashDelay());

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

}
