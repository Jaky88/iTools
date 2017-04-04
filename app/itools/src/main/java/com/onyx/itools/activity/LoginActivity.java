package com.onyx.itools.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.onyx.itools.Presenter.LoginPresenter;
import com.onyx.itools.R;
import com.onyx.itools.utils.NetworkUtils;
import com.onyx.itools.utils.StringUtils;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/4 0004,23:18
 * @Version: V1.0
 * @Description: TODO
 */
public class LoginActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassword;
    private Button btnLogin;
    private LoginPresenter userPresenter;
    private CheckBox cbHidePassword;
    private String username;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        cbHidePassword = (CheckBox) findViewById(R.id.cb_hide_password);
        userPresenter = new LoginPresenter();
    }

    private void initData() {
        cbHidePassword.setChecked(true);
        etPassword.setTransformationMethod(PasswordTransformationMethod
                .getInstance());
    }

    private void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtils.isNetworkConnected(LoginActivity.this)) {

                    return;
                }
                loginUp();
            }
        });

        cbHidePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPassword.setTransformationMethod(PasswordTransformationMethod
                            .getInstance());
                } else {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());
                }

                CharSequence charSequence = etPassword.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText,
                            charSequence.length());
                }
            }
        });
    }

    private void loginUp() {
        username = etUserName.getText().toString().trim();
        pwd = etPassword.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(pwd)) {

            return;
        }
        userPresenter.userLogin(username, pwd);
    }
}
