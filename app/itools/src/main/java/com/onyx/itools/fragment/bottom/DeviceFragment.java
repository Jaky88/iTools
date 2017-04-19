package com.onyx.itools.fragment.bottom;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onyx.itools.R;
import com.onyx.itools.base.BaseFragment;
import com.onyx.itools.entity.DeviceInfoBean;
import com.onyx.itools.request.AsyncRequest;
import com.onyx.itools.request.RequestCallback;
import com.onyx.itools.utils.DeviceInfoProvider;

import java.util.Map;

/**
 * Created by 12345 on 2017/3/25.
 */

public class DeviceFragment extends BaseFragment {
    private GridLayout mGlDevice;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_device;
    }

    @Override
    protected void initView(View view) {
        initGridLayout(view);
    }

    private void initGridLayout(View parentView) {
        mGlDevice = (GridLayout) parentView.findViewById(R.id.gl_device);
        mGlDevice.removeAllViews();
        mGlDevice.setColumnCount(1);
        loadDeviceInfo();
    }

    @SuppressWarnings("unchecked")
    private void loadDeviceInfo() {
        new AsyncRequest(AsyncRequest.DEFAULT_REQUEST, new RequestCallback<Map<String,String>>() {
            @Override
            public Map<String,String> onDoInBackground() {
                Map<String,String> map = DeviceInfoProvider.getSystemInfo(getActivity());
                return map;
            }

            @Override
            public void onResult(Map<String,String> map) {
                if (map == null) {
                    return;
                }
                int i = 0;
                for (String key : map.keySet()) {
                    LinearLayout itemView = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_device_info, null, false);
                    TextView tvKey = (TextView) itemView.findViewById(R.id.tv_info_key);
                    TextView tvValue = (TextView) itemView.findViewById(R.id.tv_info_value);
                    tvKey.setText(key+":");
                    tvValue.setText(map.get(key));
                    GridLayout.Spec rowSpec = GridLayout.spec(i++);
                    GridLayout.Spec columnSpec = GridLayout.spec(0);
                    GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
                    mGlDevice.addView(itemView, params);
                }
            }
        }).execute();
    }
}
