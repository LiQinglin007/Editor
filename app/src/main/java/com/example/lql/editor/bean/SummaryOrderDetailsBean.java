package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/8.
 */

public class SummaryOrderDetailsBean extends  BaseBean {
    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * ProductId : 5290
         * serviceName : 江重数量
         * serviceImg : /Upload/img/20161230/2016123010065205.png
         * totalMoney : 5.0
         * ServerState : 进行中
         * Title : null
         * CheckType : null
         * Professional :
         * AuthorName : null
         * Schooling :
         * PublicationTime :
         * UrlName : null
         * CreateTime : 2016-12-30 09:47:14
         * time : [{"serviceTime":"2016-12-30 09:47:14","ServerName":"订单提交"}]
         * progress : 正在等待小编确认
         */

        private int ProductId;
        private String serviceName;
        private String serviceImg;
        private double totalMoney;
        private String ServerState;
        private String Title;
        private String CheckType;
        private String Professional;
        private String AuthorName;
        private String Schooling;
        private String PublicationTime;
        private String UrlName;
        private String CreateTime;
        private String progress;
        private List<TimeBean> time;

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getServiceImg() {
            return serviceImg;
        }

        public void setServiceImg(String serviceImg) {
            this.serviceImg = serviceImg;
        }

        public double getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(double totalMoney) {
            this.totalMoney = totalMoney;
        }

        public String getServerState() {
            return ServerState;
        }

        public void setServerState(String ServerState) {
            this.ServerState = ServerState;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getCheckType() {
            return CheckType;
        }

        public void setCheckType(String CheckType) {
            this.CheckType = CheckType;
        }

        public String getProfessional() {
            return Professional;
        }

        public void setProfessional(String Professional) {
            this.Professional = Professional;
        }

        public String getAuthorName() {
            return AuthorName;
        }

        public void setAuthorName(String AuthorName) {
            this.AuthorName = AuthorName;
        }

        public String getSchooling() {
            return Schooling;
        }

        public void setSchooling(String Schooling) {
            this.Schooling = Schooling;
        }

        public String getPublicationTime() {
            return PublicationTime;
        }

        public void setPublicationTime(String PublicationTime) {
            this.PublicationTime = PublicationTime;
        }

        public String getUrlName() {
            return UrlName;
        }

        public void setUrlName(String UrlName) {
            this.UrlName = UrlName;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public List<TimeBean> getTime() {
            return time;
        }

        public void setTime(List<TimeBean> time) {
            this.time = time;
        }

        public static class TimeBean {
            /**
             * serviceTime : 2016-12-30 09:47:14
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
//         * ProductId : 4211
//         * serviceName : 降重2
//         * serviceImg : /Upload/img/20161221/CP20161221195802601.jpg
//         * totalMoney : 200
//         * ServerState : 进行中
//         * Title : null
//         * CheckType : null
//         * Professional :
//         * AuthorName : null
//         * Schooling :
//         * PublicationTime :
//         * UrlName : null
//         * CreateTime : 2016-12-22 10:20:56
//         * time : [{"serviceTime":"2016-12-22 10:21:00","ServerName":"订单提交"}]
//         * progress : 正在等待小编确认
//         * Direction : null
//         */
//
//        private int ProductId;
//        private String serviceName;
//        private String serviceImg;
//        private int totalMoney;
//        private String ServerState;
//        private String Title;
//        private String CheckType;
//        private String Professional;
//        private String AuthorName;
//        private String Schooling;
//        private String PublicationTime;
//        private String UrlName;
//        private String CreateTime;
//        private String progress;
//        private String Direction;
//        private List<TimeBean> time;
//
//        public int getProductId() {
//            return ProductId;
//        }
//
//        public void setProductId(int ProductId) {
//            this.ProductId = ProductId;
//        }
//
//        public String getServiceName() {
//            return serviceName;
//        }
//
//        public void setServiceName(String serviceName) {
//            this.serviceName = serviceName;
//        }
//
//        public String getServiceImg() {
//            return serviceImg;
//        }
//
//        public void setServiceImg(String serviceImg) {
//            this.serviceImg = serviceImg;
//        }
//
//        public int getTotalMoney() {
//            return totalMoney;
//        }
//
//        public void setTotalMoney(int totalMoney) {
//            this.totalMoney = totalMoney;
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
//        public String getTitle() {
//            return Title;
//        }
//
//        public void setTitle(String Title) {
//            this.Title = Title;
//        }
//
//        public String getCheckType() {
//            return CheckType;
//        }
//
//        public void setCheckType(String CheckType) {
//            this.CheckType = CheckType;
//        }
//
//        public String getProfessional() {
//            return Professional;
//        }
//
//        public void setProfessional(String Professional) {
//            this.Professional = Professional;
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
//        public String getSchooling() {
//            return Schooling;
//        }
//
//        public void setSchooling(String Schooling) {
//            this.Schooling = Schooling;
//        }
//
//        public String getPublicationTime() {
//            return PublicationTime;
//        }
//
//        public void setPublicationTime(String PublicationTime) {
//            this.PublicationTime = PublicationTime;
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
//        public String getCreateTime() {
//            return CreateTime;
//        }
//
//        public void setCreateTime(String CreateTime) {
//            this.CreateTime = CreateTime;
//        }
//
//        public String getProgress() {
//            return progress;
//        }
//
//        public void setProgress(String progress) {
//            this.progress = progress;
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
//             * serviceTime : 2016-12-22 10:21:00
//             * ServerName : 订单提交
//             */
//
//            private String serviceTime;
//            private String ServerName;
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
//
//
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
//         * ProductId : 1183
//         * serviceName : 22
//         * serviceImg : /Upload/img/20161123/CP20161123172455209.jpg
//         * totalMoney : 0
//         * ServerState : 已完成
//         * Title :
//         * CheckType :
//         * Professional :
//         * AuthorName :
//         * Schooling :
//         * PublicationTime :
//         * UrlName :
//         * CreateTime : 2016-12-05 10:48:11
//         * time : [{"Servertime":"2016-12-05 02:08:00","ServerName":"检测完成"}]
//         * progress : 订单完成
//         */
//
//        private int ProductId;
//        private String serviceName="";
//        private String serviceImg="";
//        private int totalMoney=0;
//        private String ServerState="";
//        private String Title="";
//        private String CheckType="";
//        private String Professional="";
//        private String AuthorName="";
//        private String Schooling="";
//        private String PublicationTime="";
//        private String UrlName="";
//        private String CreateTime="";
//        private String progress="";
//        private List<TimeBean> time;
//
//        public int getProductId() {
//            return ProductId;
//        }
//
//        public void setProductId(int ProductId) {
//            this.ProductId = ProductId;
//        }
//
//        public String getServiceName() {
//            return serviceName;
//        }
//
//        public void setServiceName(String serviceName) {
//            this.serviceName = serviceName;
//        }
//
//        public String getServiceImg() {
//            return serviceImg;
//        }
//
//        public void setServiceImg(String serviceImg) {
//            this.serviceImg = serviceImg;
//        }
//
//        public int getTotalMoney() {
//            return totalMoney;
//        }
//
//        public void setTotalMoney(int totalMoney) {
//            this.totalMoney = totalMoney;
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
//        public String getTitle() {
//            return Title;
//        }
//
//        public void setTitle(String Title) {
//            this.Title = Title;
//        }
//
//        public String getCheckType() {
//            return CheckType;
//        }
//
//        public void setCheckType(String CheckType) {
//            this.CheckType = CheckType;
//        }
//
//        public String getProfessional() {
//            return Professional;
//        }
//
//        public void setProfessional(String Professional) {
//            this.Professional = Professional;
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
//        public String getSchooling() {
//            return Schooling;
//        }
//
//        public void setSchooling(String Schooling) {
//            this.Schooling = Schooling;
//        }
//
//        public String getPublicationTime() {
//            return PublicationTime;
//        }
//
//        public void setPublicationTime(String PublicationTime) {
//            this.PublicationTime = PublicationTime;
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
//        public String getCreateTime() {
//            return CreateTime;
//        }
//
//        public void setCreateTime(String CreateTime) {
//            this.CreateTime = CreateTime;
//        }
//
//        public String getProgress() {
//            return progress;
//        }
//
//        public void setProgress(String progress) {
//            this.progress = progress;
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
//            public String getServertime() {
//                return serviceTime;
//            }
//
//            public void setServertime(String Servertime) {
//                this.serviceTime = Servertime;
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
