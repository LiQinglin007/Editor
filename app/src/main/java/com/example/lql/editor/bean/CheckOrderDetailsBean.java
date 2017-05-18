package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/8.
 */

public class CheckOrderDetailsBean extends  BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * servicePicture : /Upload/img/20161230/2016123011282703.png
         * serviceTitle : 木子查重3
         * Money : 10.99
         * ServerState : 等待检测
         * createTime : 2016-12-30 13:41:05
         * Title : jjk
         * Direction : 金融
         * AuthorName : kkk
         * UrlName : ID20161229010427_测试.doc
         * time : [{"serviceTime":"2016-12-30 13:41:05","ServerName":"订单提交"}]
         * avg1 : 0
         * howlong : 0
         * DetectionResult :
         */

        private String servicePicture;
        private String serviceTitle;
        private double Money;
        private String ServerState;
        private String createTime;
        private String Title;
        private String Direction;
        private String AuthorName;
        private String UrlName;
        private int avg1;
        private int howlong;
        private String DetectionResult;
        private List<TimeBean> time;

        public String getServicePicture() {
            return servicePicture;
        }

        public void setServicePicture(String servicePicture) {
            this.servicePicture = servicePicture;
        }

        public String getServiceTitle() {
            return serviceTitle;
        }

        public void setServiceTitle(String serviceTitle) {
            this.serviceTitle = serviceTitle;
        }

        public double getMoney() {
            return Money;
        }

        public void setMoney(double Money) {
            this.Money = Money;
        }

        public String getServerState() {
            return ServerState;
        }

        public void setServerState(String ServerState) {
            this.ServerState = ServerState;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getDirection() {
            return Direction;
        }

        public void setDirection(String Direction) {
            this.Direction = Direction;
        }

        public String getAuthorName() {
            return AuthorName;
        }

        public void setAuthorName(String AuthorName) {
            this.AuthorName = AuthorName;
        }

        public String getUrlName() {
            return UrlName;
        }

        public void setUrlName(String UrlName) {
            this.UrlName = UrlName;
        }

        public int getAvg1() {
            return avg1;
        }

        public void setAvg1(int avg1) {
            this.avg1 = avg1;
        }

        public int getHowlong() {
            return howlong;
        }

        public void setHowlong(int howlong) {
            this.howlong = howlong;
        }

        public String getDetectionResult() {
            return DetectionResult;
        }

        public void setDetectionResult(String DetectionResult) {
            this.DetectionResult = DetectionResult;
        }

        public List<TimeBean> getTime() {
            return time;
        }

        public void setTime(List<TimeBean> time) {
            this.time = time;
        }

        public static class TimeBean {
            /**
             * serviceTime : 2016-12-30 13:41:05
             * ServerName : 订单提交
             */

            private String serviceTime;
            private String ServerName;

            public String getServiceTime() {
                return serviceTime;
            }

            public void setServiceTime(String serviceTime) {
                this.serviceTime = serviceTime;
            }

            public String getServerName() {
                return ServerName;
            }

            public void setServerName(String ServerName) {
                this.ServerName = ServerName;
            }
        }
    }


//    private List<DataBean> Data;
//
//    public List<DataBean> getData() {
//        return Data;
//    }
//
//    public void setData(List<DataBean> Data) {
//        this.Data = Data;
//    }
//
//    public static class DataBean {
//        /**
//         * servicePicture : /Upload/img/20161123/CP20161123171341215.jpg
//         * serviceTitle : 122
//         * Money : 0
//         * ServerState : 已完成
//         * createTime : 2016-12-01 10:43:05
//         * Title : 我
//         * Direction : 金融
//         * AuthorName : 我
//         * UrlName : 工作室模块细化.doc
//         * time : [{"Servertime":"2016-12-05 02:08:00","ServerName":"检测完成"}]
//         * avg1 : 8
//         * howlong : 5965
//         * DetectionResult : null
//         */
//
//        private String servicePicture="";
//        private String serviceTitle="";
//        private int Money=0;
//        private String ServerState="";
//        private String createTime="";
//        private String Title="";
//        private String Direction="";
//        private String AuthorName="";
//        private String UrlName="";
//        private int avg1=8;
//        private int howlong=0;
//        private String DetectionResult="";
//        private List<TimeBean> time;
//
//        public String getServicePicture() {
//            return servicePicture;
//        }
//
//        public void setServicePicture(String servicePicture) {
//            this.servicePicture = servicePicture;
//        }
//
//        public String getServiceTitle() {
//            return serviceTitle;
//        }
//
//        public void setServiceTitle(String serviceTitle) {
//            this.serviceTitle = serviceTitle;
//        }
//
//        public int getMoney() {
//            return Money;
//        }
//
//        public void setMoney(int Money) {
//            this.Money = Money;
//        }
//
//        public String getServerState() {
//            return ServerState;
//        }
//
//        public void setServerState(String ServerState) {
//            this.ServerState = ServerState;
//        }
//
//        public String getCreateTime() {
//            return createTime;
//        }
//
//        public void setCreateTime(String createTime) {
//            this.createTime = createTime;
//        }
//
//        public String getTitle() {
//            return Title;
//        }
//
//        public void setTitle(String Title) {
//            this.Title = Title;
//        }
//
//        public String getDirection() {
//            return Direction;
//        }
//
//        public void setDirection(String Direction) {
//            this.Direction = Direction;
//        }
//
//        public String getAuthorName() {
//            return AuthorName;
//        }
//
//        public void setAuthorName(String AuthorName) {
//            this.AuthorName = AuthorName;
//        }
//
//        public String getUrlName() {
//            return UrlName;
//        }
//
//        public void setUrlName(String UrlName) {
//            this.UrlName = UrlName;
//        }
//
//        public int getAvg1() {
//            return avg1;
//        }
//
//        public void setAvg1(int avg1) {
//            this.avg1 = avg1;
//        }
//
//        public int getHowlong() {
//            return howlong;
//        }
//
//        public void setHowlong(int howlong) {
//            this.howlong = howlong;
//        }
//
//        public String getDetectionResult() {
//            return DetectionResult;
//        }
//
//        public void setDetectionResult(String DetectionResult) {
//            this.DetectionResult = DetectionResult;
//        }
//
//        public List<TimeBean> getTime() {
//            return time;
//        }
//
//        public void setTime(List<TimeBean> time) {
//            this.time = time;
//        }
//
//        public static class TimeBean {
//            /**
//             * Servertime : 2016-12-05 02:08:00
//             * ServerName : 检测完成
//             */
//
//            private String serviceTime="";
//            private String ServerName="";
//
//            public String getServiceTime() {
//                return serviceTime;
//            }
//
//            public void setServiceTime(String serviceTime) {
//                this.serviceTime = serviceTime;
//            }
//
//            public String getServerName() {
//                return ServerName;
//            }
//
//            public void setServerName(String ServerName) {
//                this.ServerName = ServerName;
//            }
//        }
//    }
}
