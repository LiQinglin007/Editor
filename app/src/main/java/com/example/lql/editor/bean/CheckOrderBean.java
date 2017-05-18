package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/7.
 */

public class CheckOrderBean extends  BaseBean {
    /**
     * Data : [{"ProductId":5294,"MyServiceId_":5411,"ShopId":5072,"shopImg":"/Upload/img/20161230/CP20161230101431820.png","shopName":"木子柠檬","serviceImg":"/Upload/img/20161230/2016123011282703.png","serviceName":"木子查重3","UrlName":"ID20161229010427_测试.doc","Money":10.99,"ServerState":"等待检测","DownLoadUrl":"","avg":0}]
     * count : -19
     */

    private int count;
    private List<DataBean> Data;

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
         * ProductId : 5294
         * MyServiceId_ : 5411
         * ShopId : 5072
         * shopImg : /Upload/img/20161230/CP20161230101431820.png
         * shopName : 木子柠檬
         * serviceImg : /Upload/img/20161230/2016123011282703.png
         * serviceName : 木子查重3
         * UrlName : ID20161229010427_测试.doc
         * Money : 10.99
         * ServerState : 等待检测
         * DownLoadUrl :
         * avg : 0
         */

        private int ProductId;
        private int MyServiceId_;
        private int ShopId;
        private String shopImg;
        private String shopName;
        private String serviceImg;
        private String serviceName;
        private String UrlName;
        private double Money;
        private String ServerState;
        private String DownLoadUrl;
        private int avg;

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public int getMyServiceId_() {
            return MyServiceId_;
        }

        public void setMyServiceId_(int MyServiceId_) {
            this.MyServiceId_ = MyServiceId_;
        }

        public int getShopId() {
            return ShopId;
        }

        public void setShopId(int ShopId) {
            this.ShopId = ShopId;
        }

        public String getShopImg() {
            return shopImg;
        }

        public void setShopImg(String shopImg) {
            this.shopImg = shopImg;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getServiceImg() {
            return serviceImg;
        }

        public void setServiceImg(String serviceImg) {
            this.serviceImg = serviceImg;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getUrlName() {
            return UrlName;
        }

        public void setUrlName(String UrlName) {
            this.UrlName = UrlName;
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

        public String getDownLoadUrl() {
            return DownLoadUrl;
        }

        public void setDownLoadUrl(String DownLoadUrl) {
            this.DownLoadUrl = DownLoadUrl;
        }

        public int getAvg() {
            return avg;
        }

        public void setAvg(int avg) {
            this.avg = avg;
        }
    }


//    private List<DataBean> Data;
//    private int count;
//
//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
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
//         * ProductId : 1179
//         * MyServiceId_ : 2064
//         * ShopId : 1
//         * shopImg :
//         * shopName : 77
//         * serviceImg : /Upload/img/20161123/CP20161123171341215.jpg
//         * serviceName : 122
//         * UrlName : ID_20161205025528小米_lql.docx
//         * Money : 50
//         * ServerState : 等待检测
//         * DownLoadUrl :
//         * avg : 8
//         */
//
//        private int ProductId;
//        private int MyServiceId_;
//        private int ShopId;
//        private String shopImg="";
//        private String shopName="";
//        private String serviceImg="";
//        private String serviceName="";
//        private String UrlName="";
//        private int Money=0;
//        private String ServerState="";
//        private String DownLoadUrl="";
//        private int avg=0;
//
//        public int getProductId() {
//            return ProductId;
//        }
//
//        public void setProductId(int ProductId) {
//            this.ProductId = ProductId;
//        }
//
//        public int getMyServiceId_() {
//            return MyServiceId_;
//        }
//
//        public void setMyServiceId_(int MyServiceId_) {
//            this.MyServiceId_ = MyServiceId_;
//        }
//
//        public int getShopId() {
//            return ShopId;
//        }
//
//        public void setShopId(int ShopId) {
//            this.ShopId = ShopId;
//        }
//
//        public String getShopImg() {
//            return shopImg;
//        }
//
//        public void setShopImg(String shopImg) {
//            this.shopImg = shopImg;
//        }
//
//        public String getShopName() {
//            return shopName;
//        }
//
//        public void setShopName(String shopName) {
//            this.shopName = shopName;
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
//        public String getServiceName() {
//            return serviceName;
//        }
//
//        public void setServiceName(String serviceName) {
//            this.serviceName = serviceName;
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
//        public String getDownLoadUrl() {
//            return DownLoadUrl;
//        }
//
//        public void setDownLoadUrl(String DownLoadUrl) {
//            this.DownLoadUrl = DownLoadUrl;
//        }
//
//        public int getAvg() {
//            return avg;
//        }
//
//        public void setAvg(int avg) {
//            this.avg = avg;
//        }
//    }
}
