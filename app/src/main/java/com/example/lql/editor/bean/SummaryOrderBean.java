package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/7.
 */

public class SummaryOrderBean extends  BaseBean {
    /**
     * Data : [{"ProductId":5289,"MyServiceId_":5394,"ShopId":5072,"shopName":"木子柠檬","shopImg":"/Upload/img/20161230/CP20161230101431820.png","ServerState":"等待初审","serviceName":"速审服务验收1228","serviceImg":"/Upload/img/20161230/2016123010081209.png","UrlName":"ID20161229010427_测试.doc","Money":0,"SecondMoney":0},{"ProductId":5289,"MyServiceId_":5393,"ShopId":5072,"shopName":"木子柠檬","shopImg":"/Upload/img/20161230/CP20161230101431820.png","ServerState":"等待初审","serviceName":"速审服务验收1228","serviceImg":"/Upload/img/20161230/2016123010081209.png","UrlName":"ID20161229010427_测试.doc","Money":0,"SecondMoney":0}]
     * count : -8
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
         * ProductId : 5289
         * MyServiceId_ : 5394
         * ShopId : 5072
         * shopName : 木子柠檬
         * shopImg : /Upload/img/20161230/CP20161230101431820.png
         * ServerState : 等待初审
         * serviceName : 速审服务验收1228
         * serviceImg : /Upload/img/20161230/2016123010081209.png
         * UrlName : ID20161229010427_测试.doc
         * Money : 0.0
         * SecondMoney : 0.0
         */

        private int ProductId;
        private int MyServiceId_;
        private int ShopId;
        private String shopName;
        private String shopImg;
        private String ServerState;
        private String serviceName;
        private String serviceImg;
        private String UrlName;
        private double Money;
        private double SecondMoney;

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

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopImg() {
            return shopImg;
        }

        public void setShopImg(String shopImg) {
            this.shopImg = shopImg;
        }

        public String getServerState() {
            return ServerState;
        }

        public void setServerState(String ServerState) {
            this.ServerState = ServerState;
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

        public double getSecondMoney() {
            return SecondMoney;
        }

        public void setSecondMoney(double SecondMoney) {
            this.SecondMoney = SecondMoney;
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
//         * ProductId : 1178
//         * MyServiceId_ : 2029
//         * ShopId : 1
//         * shopName : 77
//         * shopImg :
//         * ServerState : 等待初审
//         * serviceName : dsf
//         * serviceImg : /Upload/img/20161123/CP20161123164316402.jpg
//         * UrlName : ID_20161205025528小米_lql0007.docx
//         * Money : 0
//         * SecondMoney : 0
//         */
//
//        private int ProductId;
//        private int MyServiceId_;
//        private int ShopId;
//        private String shopName="";
//        private String shopImg="";
//        private String ServerState="";
//        private String serviceName="";
//        private String serviceImg="";
//        private String UrlName="";
//        private int Money=0;
//        private int SecondMoney=0;
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
//        public String getShopName() {
//            return shopName;
//        }
//
//        public void setShopName(String shopName) {
//            this.shopName = shopName;
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
//        public String getServerState() {
//            return ServerState;
//        }
//
//        public void setServerState(String ServerState) {
//            this.ServerState = ServerState;
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
//        public int getSecondMoney() {
//            return SecondMoney;
//        }
//
//        public void setSecondMoney(int SecondMoney) {
//            this.SecondMoney = SecondMoney;
//        }
//    }
}
