package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/1.
 */

public class ServiceBean extends  BaseBean {
    /**
     * Data : [{"Id":5295,"Picture":"/Upload/img/20161230/2016123011394704.png","ServiceName":"木子降重3","OriginalCost":30.329999923706055,"ServicePrice":"20.11","count":3,"credit":0,"human":0},{"Id":5290,"Picture":"/Upload/img/20161230/2016123010065205.png","ServiceName":"江重数量","OriginalCost":10,"ServicePrice":"5","count":8,"credit":1,"human":0},{"Id":5288,"Picture":"/Upload/img/20161230/2016123010070506.png","ServiceName":"降重服务验收1228","OriginalCost":25.75,"ServicePrice":"10.20","count":2,"credit":1,"human":1},{"Id":5285,"Picture":"/Upload/img/20161230/2016123010072407.png","ServiceName":"木子降重2","OriginalCost":300,"ServicePrice":"200","count":4,"credit":0,"human":2},{"Id":5282,"Picture":"/Upload/img/20161230/2016123010075408.png","ServiceName":"木子降重1","OriginalCost":200,"ServicePrice":"100","count":0,"credit":0,"human":0}]
     * count : -15
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
         * Id : 5295
         * Picture : /Upload/img/20161230/2016123011394704.png
         * ServiceName : 木子降重3
         * OriginalCost : 30.329999923706055
         * ServicePrice : 20.11
         * count : 3
         * credit : 0
         * human : 0
         */

        private int Id;
        private String Picture;
        private String ServiceName;
        private double OriginalCost;
        private String ServicePrice;
        private int count;
        private int credit;
        private int human;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getPicture() {
            return Picture;
        }

        public void setPicture(String Picture) {
            this.Picture = Picture;
        }

        public String getServiceName() {
            return ServiceName;
        }

        public void setServiceName(String ServiceName) {
            this.ServiceName = ServiceName;
        }

        public double getOriginalCost() {
            return OriginalCost;
        }

        public void setOriginalCost(double OriginalCost) {
            this.OriginalCost = OriginalCost;
        }

        public String getServicePrice() {
            return ServicePrice;
        }

        public void setServicePrice(String ServicePrice) {
            this.ServicePrice = ServicePrice;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getHuman() {
            return human;
        }

        public void setHuman(int human) {
            this.human = human;
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
//         * Id : 1187
//         * Picture :
//         * ServiceName :
//         * OriginalCost : 0
//         * ServicePrice : t7
//         * count : 5
//         * credit : 0
//         * human : 0
//         */
//
//        private int Id;
//        private String Picture="";
//        private String ServiceName="";
//        private int OriginalCost=0;
//        private String ServicePrice="";
//        private int count=0;
//        private int credit=0;
//        private int human=0;
//
//        public int getId() {
//            return Id;
//        }
//
//        public void setId(int Id) {
//            this.Id = Id;
//        }
//
//        public String getPicture() {
//            return Picture;
//        }
//
//        public void setPicture(String Picture) {
//            this.Picture = Picture;
//        }
//
//        public String getServiceName() {
//            return ServiceName;
//        }
//
//        public void setServiceName(String ServiceName) {
//            this.ServiceName = ServiceName;
//        }
//
//        public int getOriginalCost() {
//            return OriginalCost;
//        }
//
//        public void setOriginalCost(int OriginalCost) {
//            this.OriginalCost = OriginalCost;
//        }
//
//        public String getServicePrice() {
//            return ServicePrice;
//        }
//
//        public void setServicePrice(String ServicePrice) {
//            this.ServicePrice = ServicePrice;
//        }
//
//        public int getCount() {
//            return count;
//        }
//
//        public void setCount(int count) {
//            this.count = count;
//        }
//
//        public int getCredit() {
//            return credit;
//        }
//
//        public void setCredit(int credit) {
//            this.credit = credit;
//        }
//
//        public int getHuman() {
//            return human;
//        }
//
//        public void setHuman(int human) {
//            this.human = human;
//        }
//    }
}
