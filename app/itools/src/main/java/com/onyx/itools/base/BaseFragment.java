package com.onyx.itools.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/30 0030,0:40
 * @Version: V1.0
 * @Description: TODO
 */
public abstract class BaseFragment  extends Fragment {
    protected abstract int getFragmentLayoutId();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayoutId(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract void initView(View view);
}
