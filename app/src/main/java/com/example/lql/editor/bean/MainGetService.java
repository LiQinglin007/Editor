package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/1.
 */

public class MainGetService extends BaseBean{

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Id : 1179
         * ServiceName : 122
         * Picture : /Upload/img/20161123/CP20161123171341215.jpg
         * Type : 1
         * OriginalCost : 122
         * ServicePrice : 1
         * count : 7
         */

        private int Id;
        private String ServiceName="";
        private String Picture="";
        private int Type=1;
        private double OriginalCost=0;
        private String ServicePrice="";
        private int count=0;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getServiceName() {
            return ServiceName;
        }

        public void setServiceName(String ServiceName) {
            this.ServiceName = ServiceName;
        }

        public String getPicture() {
            return Picture;
        }

        public void setPicture(String Picture) {
            this.Picture = Picture;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
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
    }
}
