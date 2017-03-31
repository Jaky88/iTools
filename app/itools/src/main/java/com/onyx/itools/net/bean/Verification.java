package com.onyx.itools.net.bean;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/1 0001,1:56
 * @Version: V1.0
 * @Description: TODO
 */
public class Verification {
    /**
     * message : success
     * user : {"id":"4028900b54a29c1d0154a29d66d90000","name":"","nickname":"","tel":"18611891941","createTime":1463017105000}
     * message_code : 10000
     */


    /**
     * id : 4028900b54a29c1d0154a29d66d90000
     * name :
     * nickname :
     * tel : 18611891941
     * createTime : 1463017105000
     */

    private UserEntity user;

    public void setUser(UserEntity user) {
        this.user = user;
    }


    public UserEntity getUser() {
        return user;
    }


    public static class UserEntity {
        private String id;
        private String name;
        private String nickname;
        private String tel;


        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }


        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getNickname() {
            return nickname;
        }

        public String getTel() {
            return tel;
        }

        @Override
        public String toString() {
            return "UserEntity{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", tel='" + tel + '\'' +
                    '}';
        }
    }
}
