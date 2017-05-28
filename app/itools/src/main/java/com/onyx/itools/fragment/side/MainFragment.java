package com.onyx.itools.fragment.side;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.onyx.itools.R;
import com.onyx.itools.adapter.MyFragmentPagerAdapter;
import com.onyx.itools.base.BaseFragment;
import com.onyx.itools.fragment.FragmentFactory;

import java.util.ArrayList;

/**
 * Created by 12345 on 2017/3/25.
 */

public class MainFragment extends BaseFragment {
    private TextView textView;
    private ViewPager mVpMain;
    private RadioGroup mRgMain;
    private ArrayList<Fragment> mFragList;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view) {
        textView = (TextView) view.findViewById(R.id.menu_text);
        String text = getArguments().getString("text");
        textView.setText(text);
        initViewPager(view);
        initRadioGroup(view);
    }

    @Override
    protected void intData() {

    }

    @Override
    protected void intEvent() {

    }

    private void initViewPager(View parentView) {
        mVpMain = (ViewPager) parentView.findViewById(R.id.vp_main);
        mFragList = new ArrayList<Fragment>();
        mFragList.add(FragmentFactory.newFragment(FragmentFactory.HOME_FRAGMENT,null));
        mFragList.add(FragmentFactory.newFragment(FragmentFactory.TECH_FRAGMENT,null));
        mFragList.add(FragmentFactory.newFragment(FragmentFactory.ITOOLS_FRAGMENT,null));
        mFragList.add(FragmentFactory.newFragment(FragmentFactory.DEVICE_FRAGMENT,null));
        mFragList.add(FragmentFactory.newFragment(FragmentFactory.MINE_FRAGMENT,null));

        mVpMain.setAdapter(new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), mFragList));
        mVpMain.setCurrentItem(0);
        mVpMain.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initRadioGroup(View parentView) {
        mRgMain = (RadioGroup) parentView.findViewById(R.id.rg_bottom_navigation);
        mRgMain.check(mRgMain.getChildAt(0).getId());
        mRgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mRgMain.check(mRgMain.getChildAt(position).getId());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int item = 0;
            switch (checkedId){
                case R.id.rb_tab_1:
                    item = 0;
                    break;
                case R.id.rb_tab_2:
                    item = 1;
                    break;
                case R.id.rb_tab_3:
                    item = 2;
                    break;
                case R.id.rb_tab_4:
                    item = 3;
                    break;
                case R.id.rb_tab_5:
                    item = 4;
                    break;
            }
            mVpMain.setCurrentItem(item);
        }
    }
}
