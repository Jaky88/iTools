package com.onyx.itools.net.bean;

import java.util.List;

/**
 * @Copyright: Copyright © 2017 Onyx International Inc. All rights reserved.
 * @Project: iTools
 * @Author: Jack
 * @Date: 2017/4/1 0001,1:54
 * @Version: V1.0
 * @Description: TODO
 */
public class MenuBean extends BaseBean {
    /**
     * id : 4028900b54a7a7de0154a7a7e0270000
     * msgTitle :
     * msgContent :
     * msgDate : 1463101677606
     * msgType : 志愿者服务
     * parent : null
     */
    private List<MenuXBean> list;

    public List<MenuXBean> getList() {
        return list;
    }

    public void setList(List<MenuXBean> list) {
        this.list = list;
    }

    public static class MenuXBean {
        private String id;
        private String msgTitle;
        private String msgContent;
        private long msgDate;
        private String msgType;
        private Object parent;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMsgTitle() {
            return msgTitle;
        }

        public void setMsgTitle(String msgTitle) {
            this.msgTitle = msgTitle;
        }

        public String getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }

        public long getMsgDate() {
            return msgDate;
        }

        public void setMsgDate(long msgDate) {
            this.msgDate = msgDate;
        }

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public Object getParent() {
            return parent;
        }

        public void setParent(Object parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "MenuXBean{" +
                    "id='" + id + '\'' +
                    ", msgTitle='" + msgTitle + '\'' +
                    ", msgContent='" + msgContent + '\'' +
                    ", msgDate=" + msgDate +
                    ", msgType='" + msgType + '\'' +
                    ", parent=" + parent +
                    '}';
        }
    }
}
