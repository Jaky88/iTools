package com.onyx.itools.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.onyx.itools.R;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/30 0030,0:40
 * @Version: V1.0
 * @Description: TODO
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected BaseFragment mCurrentFragment = null;
    protected abstract int getContentViewId();
    protected abstract int getFragmentContentId();
    protected abstract void setDefaultFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    protected abstract void initEvent();

    protected abstract void initData();

    protected abstract void initView();

    protected void switchFragment(BaseFragment fragment) {
        if (fragment != mCurrentFragment) {
            if (!fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction()
                        .hide(mCurrentFragment)
                        .add(getFragmentContentId(), fragment,fragment.getClass().getSimpleName())
                        .addToBackStack(fragment.getClass().getSimpleName())
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .hide(mCurrentFragment)
                        .show(fragment)
                        .commit();
            }
            mCurrentFragment = fragment;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
