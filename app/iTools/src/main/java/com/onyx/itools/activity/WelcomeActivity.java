package com.onyx.itools.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.onyx.itools.R;
import com.onyx.itools.util.ActivityUtil;
import com.onyx.itools.util.SharedPrefUtil;

import butterknife.ButterKnife;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/25 0025,2:04
 * @Version: V1.0
 * @Description:
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isFirstOpen = SharedPrefUtil.getBoolean(this, SharedPrefUtil.FIRST_OPEN, true);
        if (isFirstOpen) {
            startGuideActivity();
            return;
        }

        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        startMainActivity();
    }

    private void startGuideActivity() {
        ActivityUtil.startActivity(this, MainActivity.class);
    }

    private void startMainActivity() {
        ActivityUtil.startActivity(this, WelcomeGuideActivity.class);
    }


}
