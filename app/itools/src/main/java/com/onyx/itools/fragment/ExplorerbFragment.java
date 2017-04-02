package com.onyx.itools.fragment;

import android.view.View;
import android.widget.TextView;

import com.onyx.itools.R;
import com.onyx.itools.base.BaseFragment;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/30 0030,0:48
 * @Version: V1.0
 * @Description: TODO
 */
public class ExplorerbFragment extends BaseFragment {
    private TextView textView;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void initView(View view) {
        textView = (TextView) view.findViewById(R.id.menu_text);
        String text = getArguments().getString("text");
        textView.setText(text);
    }
}
