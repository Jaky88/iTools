package com.onyx.itools.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onyx.itools.R;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/27 0027,23:50
 * @Version: V1.0
 * @Description: TODO
 */
public class MenuFragment extends Fragment {
    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        textView = (TextView) view.findViewById(R.id.menu_text);
        String text = getArguments().getString("text");
        textView.setText(text);

        return view;
    }
}
