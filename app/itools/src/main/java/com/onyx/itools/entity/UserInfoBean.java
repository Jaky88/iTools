package com.onyx.itools.entity;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/4 0004,23:46
 * @Version: V1.0
 * @Description: TODO
 */
public class UserInfoBean {
    private String userName;
    private String userPwd;
    private String userEmail;
    private String verfacationCode;

    public UserInfoBean() {}

    public UserInfoBean(String userName, String userPwd, String userEmail, String verfacationCode) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.verfacationCode = verfacationCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getVerfacationCode() {
        return verfacationCode;
    }

    public void setVerfacationCode(String verfacationCode) {
        this.verfacationCode = verfacationCode;
    }
}
