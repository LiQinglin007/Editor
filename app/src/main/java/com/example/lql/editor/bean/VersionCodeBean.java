package com.example.lql.editor.bean;

/**
 * Created by LQL on 2016/12/13.
 */

public class VersionCodeBean extends BaseBean {

    /**
     * Data : {"VersionId":2,"VersionNum":"第二版","VersionUrl":"/Upload/img/20161213/201612130253271.apk","VersionReason":"dsfg","Type":1}
     */

    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * VersionId : 2
         * VersionNum : 第二版
         * VersionUrl : /Upload/img/20161213/201612130253271.apk
         * VersionReason : dsfg
         * Type : 1
         */

        private int VersionId=1;
        private String VersionNum="1.0";
        private String VersionUrl="";
        private String VersionReason="";
        private int Type=0;

        public int getVersionId() {
            return VersionId;
        }

        public void setVersionId(int VersionId) {
            this.VersionId = VersionId;
        }

        public String getVersionNum() {
            return VersionNum;
        }

        public void setVersionNum(String VersionNum) {
            this.VersionNum = VersionNum;
        }

        public String getVersionUrl() {
            return VersionUrl;
        }

        public void setVersionUrl(String VersionUrl) {
            this.VersionUrl = VersionUrl;
        }

        public String getVersionReason() {
            return VersionReason;
        }

        public void setVersionReason(String VersionReason) {
            this.VersionReason = VersionReason;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }
    }
}
