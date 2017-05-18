package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/7.
 */

public class OtherAccountBoundBean extends  BaseBean {

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * QQSatate : 未绑定
         * WXSatate : 未绑定
         * WBSatate : 未绑定
         */

        private String QQSatate="未绑定";
        private String WXSatate="未绑定";
        private String WBSatate="未绑定";

        public String getQQSatate() {
            return QQSatate;
        }

        public void setQQSatate(String QQSatate) {
            this.QQSatate = QQSatate;
        }

        public String getWXSatate() {
            return WXSatate;
        }

        public void setWXSatate(String WXSatate) {
            this.WXSatate = WXSatate;
        }

        public String getWBSatate() {
            return WBSatate;
        }

        public void setWBSatate(String WBSatate) {
            this.WBSatate = WBSatate;
        }
    }
}
