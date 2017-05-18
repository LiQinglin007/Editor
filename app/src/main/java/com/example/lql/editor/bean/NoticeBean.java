package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/6.
 */

public class NoticeBean extends  BaseBean {

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * NoticeId : 1
         * Notice : 220
         * Content : 22
         * CreateTime : 2016-12-06
         */

        private int NoticeId;
        private String Notice="";
        private String Content="";
        private String CreateTime="";

        public int getNoticeId() {
            return NoticeId;
        }

        public void setNoticeId(int NoticeId) {
            this.NoticeId = NoticeId;
        }

        public String getNotice() {
            return Notice;
        }

        public void setNotice(String Notice) {
            this.Notice = Notice;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }
}
