package com.onyx.itools.Presenter;

import com.onyx.itools.entity.LoginResultBean;
import com.onyx.itools.entity.UserInfoBean;
import com.onyx.itools.request.AsyncRequest;
import com.onyx.itools.request.RequestCallback;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/4 0004,23:40
 * @Version: V1.0
 * @Description: TODO
 */
public class LoginPresenter {
    private UserInfoBean userInfoBean;

    public LoginPresenter() {
        this.userInfoBean = new UserInfoBean();
    }

    @SuppressWarnings("unchecked")
    public void userLogin(final String userName, final String password) {

        new AsyncRequest(AsyncRequest.REMOTE_DATA_REQUEST, new RequestCallback<LoginResultBean>() {
                    @Override
                    public void onStart() {
                        userInfoBean.setUserName(userName);
                        userInfoBean.setUserPwd(password);
                        userInfoBean.setVerfacationCode("");
                    }

                    @Override
                    public LoginResultBean onDoInBackground() {
                        return null;
                    }

                    @Override
                    public void onResult(LoginResultBean resultBean) {
                        switch (resultBean.getResponseCode()) {
                            case 200:
                                break;
                            case 500:
                                break;
                            case 404:
                                break;
                            default:
                                break;
                        }
                    }
                }).execute();

    }
}
