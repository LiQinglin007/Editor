package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/7.
 */

public class MyEvaluateBean extends  BaseBean {

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
         * Id : 4195
         * Grades : 0
         * CreateTime : 2016/10/25 8:48:24
         * ComContent : adsa dasdasa
         * ServiceId : 125
         * ServiceName : null
         */

        private int Id;
        private int Grades;
        private String CreateTime="";
        private String ComContent="";
        private int ServiceId;
        private String ServiceName="";
        private String ServicePic="";
        private int Type;

        public int getType() {
            return Type;
        }

        public void setType(int type) {
            Type = type;
        }

        public String getServicePic() {
            return ServicePic;
        }

        public void setServicePic(String servicePic) {
            ServicePic = servicePic;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getGrades() {
            return Grades;
        }

        public void setGrades(int Grades) {
            this.Grades = Grades;
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

        public String getServiceName() {
            return ServiceName;
        }

        public void setServiceName(String ServiceName) {
            this.ServiceName = ServiceName;
        }
    }
}
