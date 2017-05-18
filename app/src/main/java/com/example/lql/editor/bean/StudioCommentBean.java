package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/6.
 */

public class StudioCommentBean extends  BaseBean {

    private List<DataBean> Data;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Id : 5213
         * CreateTime : 2016/12/6 星期二 10:57:57
         * ComContent : 1111
         * ServiceId : 2188
         * Grades : 1
         * Person : 1
         * HeadImg : /Upload/img/20161206/20161206091727master.jpg
         */

        private int Id;
        private String CreateTime="";
        private String ComContent="";
        private int ServiceId=0;
        private int Grades=0;
        private String Person="";
        private String HeadImg="";

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getComContent() {
            return ComContent;
        }

        public void setComContent(String ComContent) {
            this.ComContent = ComContent;
        }

        public int getServiceId() {
            return ServiceId;
        }

        public void setServiceId(int ServiceId) {
            this.ServiceId = ServiceId;
        }

        public int getGrades() {
            return Grades;
        }

        public void setGrades(int Grades) {
            this.Grades = Grades;
        }

        public String getPerson() {
            return Person;
        }

        public void setPerson(String Person) {
            this.Person = Person;
        }

        public String getHeadImg() {
            return HeadImg;
        }

        public void setHeadImg(String HeadImg) {
            this.HeadImg = HeadImg;
        }
    }
}
