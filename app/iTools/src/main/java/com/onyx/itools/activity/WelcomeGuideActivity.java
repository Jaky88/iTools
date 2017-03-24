package com.onyx.itools.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.onyx.itools.R;
import com.onyx.itools.adapter.GuideViewPagerAdapter;
import com.onyx.itools.util.SharedPrefUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/25 0025,2:09
 * @Version: V1.0
 * @Description: TODO
 */
public class WelcomeGuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private GuideViewPagerAdapter mPagerAdapter;
    private List<View> mViewList;
    private Button mStartBtn;
    private static final int[] mPics = { R.layout.guide_view1,R.layout.guide_view2, R.layout.guide_view3};
    private ImageView[] mDots;
    private int mCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mViewList = new ArrayList<View>();
        for (int i = 0; i < mPics.length; i++) {
            View view = LayoutInflater.from(this).inflate(mPics[i], null);

            if (i == mPics.length - 1) {
                mStartBtn = (Button) view.findViewById(R.id.btn_enter);
                mStartBtn.setTag("enter");
                mStartBtn.setOnClickListener(this);
            }

            mViewList.add(view);
        }

        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        mPagerAdapter = new GuideViewPagerAdapter(this, mViewList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new PageChangeListener());

        initDots();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPrefUtil.putBoolean(WelcomeGuideActivity.this, SharedPrefUtil.FIRST_OPEN, false);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.dot_container);
        mDots = new ImageView[mPics.length];

        for (int i = 0; i < mPics.length; i++) {
            mDots[i] = (ImageView) ll.getChildAt(i);
            mDots[i].setEnabled(false);
            mDots[i].setOnClickListener(this);
            mDots[i].setTag(i);
        }

        mCurrentIndex = 0;
        mDots[mCurrentIndex].setEnabled(true);

    }

    private void setCurView(int position) {
        if (position < 0 || position >= mPics.length) {
            return;
        }
        mViewPager.setCurrentItem(position);
    }

    private void setCurDot(int position) {
        if (position < 0 || position > mPics.length || mCurrentIndex == position) {
            return;
        }
        mDots[position].setEnabled(true);
        mDots[mCurrentIndex].setEnabled(false);
        mCurrentIndex = position;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals("enter")) {
            enterMainActivity();
            return;
        }

        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }

    private void enterMainActivity() {
        Intent intent = new Intent(WelcomeGuideActivity.this,
                WelcomeActivity.class);
        startActivity(intent);
        SharedPrefUtil.putBoolean(WelcomeGuideActivity.this, SharedPrefUtil.FIRST_OPEN, false);
        finish();
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int position) {

        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            setCurDot(position);
        }
    }
}
