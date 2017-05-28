package com.onyx.itools.fragment.bottom;

import android.view.View;
import android.widget.TextView;

import com.onyx.itools.R;
import com.onyx.itools.base.BaseFragment;

/**
 * Created by 12345 on 2017/3/25.
 */

public class TechFragment extends BaseFragment {
    private TextView textView;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_tech;
    }

    @Override
    protected void initView(View view) {
        textView = (TextView) view.findViewById(R.id.menu_text);
        if(getArguments() !=null){
            String text = getArguments().getString("text");
            textView.setText(text);
        }
    }

    @Override
    protected void intData() {

    }

    @Override
    protected void intEvent() {

    }
}
