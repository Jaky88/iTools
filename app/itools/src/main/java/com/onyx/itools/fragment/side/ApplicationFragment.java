package com.onyx.itools.fragment.side;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onyx.itools.R;
import com.onyx.itools.base.BaseFragment;
import com.onyx.itools.entity.AppInfoBean;
import com.onyx.itools.request.AsyncRequest;
import com.onyx.itools.request.RequestCallback;
import com.onyx.itools.utils.ActivityUtil;
import com.onyx.itools.utils.AppInfoProvider;
import com.onyx.itools.utils.ScreenUtils;

import java.util.List;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/3/30 0030,0:47
 * @Version: V1.0
 * @Description: TODO
 */
public class ApplicationFragment extends BaseFragment {
    private TextView textView;
    private GridLayout mGlApp;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_application;
    }

    @Override
    protected void initView(View view) {
        initGridLayout(view);


    }

    private void initGridLayout(View parentView) {
        mGlApp = (GridLayout) parentView.findViewById(R.id.gl_app);
        mGlApp.removeAllViews();
        mGlApp.setColumnCount(4);
        loadInstalledApps();
    }

    @SuppressWarnings("unchecked")
    private void loadInstalledApps() {
        new AsyncRequest(AsyncRequest.DEFAULT_REQUEST,new Object(), new RequestCallback<Object, List<AppInfoBean>>() {
            @Override
            public List<AppInfoBean> onDoInBackground(Object o) {
                return AppInfoProvider.getInstallAppsInfo(getActivity(), AppInfoProvider.FILTER_THIRD_APP);
            }

            @Override
            public void onResult(List<AppInfoBean> apps) {
                if (apps == null || apps.size() == 0) {
                    return;
                }

                int count = 0;
                for (int i = 0; i < apps.size() / mGlApp.getColumnCount() + 1; i++) {
                    for (int j = 0; j < mGlApp.getColumnCount(); j++) {
                        if (count >= apps.size()) {
                            break;
                        }
                        AppInfoBean app = apps.get(count);
                        LinearLayout itemView = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_app_list, null, false);
                        ImageView ivIcon = (ImageView) itemView.findViewById(R.id.iv_app_icon);
                        TextView tvName = (TextView) itemView.findViewById(R.id.tv_app_name);
                        ivIcon.setImageDrawable(app.getAppIcon());
                        tvName.setText(app.getAppName());
                        final String packageName = app.getAppPackageName();
                        itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityUtil.startActivity(getActivity(), packageName);
                            }
                        });
                        count++;
                        GridLayout.Spec rowSpec = GridLayout.spec(i);
                        GridLayout.Spec columnSpec = GridLayout.spec(j);
                        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
                        params.setGravity(Gravity.END);
                        params.width = ScreenUtils.getScreenWidth(getActivity()) / mGlApp.getColumnCount();
                        mGlApp.addView(itemView, params);
                    }
                }
            }
        }).execute();
    }


}
