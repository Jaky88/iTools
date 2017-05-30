package com.onyx.itools.fragment.bottom;

import android.content.Context;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.onyx.itools.R;
import com.onyx.itools.base.BaseFragment;
import com.onyx.itools.utils.ActivityUtil;

/**
 * Created by 12345 on 2017/3/25.
 */

public class IToolsFragment extends BaseFragment implements View.OnClickListener {
    private TextView textView;
    private Button btnOpenAdb;
    private Button btnOpenSettings;
    private Button btnOpenDevSettings;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_itools;
    }

    @Override
    protected void initView(View view) {
        textView = (TextView) view.findViewById(R.id.menu_text);
        if (getArguments() != null) {
            String text = getArguments().getString("text");
            textView.setText(text);
        }
        btnOpenAdb = (Button) view.findViewById(R.id.btn_open_adb);
        btnOpenSettings = (Button) view.findViewById(R.id.btn_open_settings);
        btnOpenDevSettings = (Button) view.findViewById(R.id.btn_open_dev_settings);

    }

    @Override
    protected void intData() {

    }

    @Override
    protected void intEvent() {
        btnOpenAdb.setOnClickListener(this);
        btnOpenSettings.setOnClickListener(this);
        btnOpenDevSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_adb:
                try {
                    openAdb(getActivity());
                }catch (Exception e){
                    toast("非系统权限，无法打开USB调试！");
                }
                break;
            case R.id.btn_open_settings:
                ActivityUtil.startComponent(getActivity(), "com.android.settings",
                        "com.android.settings.Settings");
                break;
            case R.id.btn_open_dev_settings:
                ActivityUtil.startComponent(getActivity(), "com.android.settings",
                        "com.android.settings.DevelopmentSettings");
                break;
        }
    }

    private void openAdb(Context context) {
        boolean enableAdb = (Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ADB_ENABLED, 0) > 0);
        if (!enableAdb) {
            Settings.Secure.putInt(context.getContentResolver(), Settings.Secure.ADB_ENABLED, 1);
        }
        int res = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ADB_ENABLED, 1);
        toast("ADB_ENABLED: "+res);
    }
}
