package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/7.
 */

public class DeclineOrderBean extends  BaseBean {
    /**
     * Data : [{"ProductId":5290,"MyServiceId_":5398,"ShopId":5072,"shopName":"木子柠檬","shopImg":"/Upload/img/20161230/CP20161230101431820.png","serviceName":"江重数量","serviceImg":"/Upload/img/20161230/2016123010065205.png","UrlName":null,"Money":5,"ServerState":"进行中"},{"ProductId":5290,"MyServiceId_":5377,"ShopId":5072,"shopName":"木子柠檬","shopImg":"/Upload/img/20161230/CP20161230101431820.png","serviceName":"江重数量","serviceImg":"/Upload/img/20161230/2016123010065205.png","UrlName":null,"Money":10,"ServerState":"进行中"},{"ProductId":5290,"MyServiceId_":5376,"ShopId":5072,"shopName":"木子柠檬","shopImg":"/Upload/img/20161230/CP20161230101431820.png","serviceName":"江重数量","serviceImg":"/Upload/img/20161230/2016123010065205.png","UrlName":null,"Money":10,"ServerState":"进行中"}]
     * count : -7
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
         * ProductId : 5290
         * MyServiceId_ : 5398
         * ShopId : 5072
         * shopName : 木子柠檬
         * shopImg : /Upload/img/20161230/CP20161230101431820.png
         * serviceName : 江重数量
         * serviceImg : /Upload/img/20161230/2016123010065205.png
         * UrlName : null
         * Money : 5.0
         * ServerState : 进行中
         */

        private int ProductId;
        private int MyServiceId_;
        private int ShopId;
        private String shopName;
        private String shopImg;
        private String serviceName;
        private String serviceImg;
        private String UrlName;
        private double Money;
        private String ServerState;

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

        public String getServerState() {
            return ServerState;
        }

        public void setServerState(String ServerState) {
            this.ServerState = ServerState;
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
//         * ProductId : 1184
//         * MyServiceId_ : 2066
//         * ShopId : 1
//         * shopName : 77
//         * shopImg :
//         * serviceName :
//         * serviceImg : /Upload/img/20161123/CP20161123174322499.jpg
//         * UrlName : null
//         * Money : 4
//         * ServerState : 进行中
//         */
//
//        private int ProductId;
//        private int MyServiceId_;
//        private int ShopId;
//        private String shopName="";
//        private String shopImg="";
//        private String serviceName="";
//        private String serviceImg="";
//        private String UrlName="";
//        private int Money=0;
//        private String ServerState="进行中";
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
//        public String getServerState() {
//            return ServerState;
//        }
//
//        public void setServerState(String ServerState) {
//            this.ServerState = ServerState;
//        }
//    }






}
