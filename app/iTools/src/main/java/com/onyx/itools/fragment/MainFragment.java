package com.onyx.itools.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onyx.itools.R;

/**
 * Created by 12345 on 2017/3/25.
 */

public class MainFragment extends Fragment {
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        textView = (TextView) view.findViewById(R.id.main_text);

//        String text = getArguments().getString("text");
        textView.setText("MainFragment");
        return view;
    }

}
