package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/7.
 */

public class CollectionServiceBean extends BaseBean {

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
         * Favoriteid : 7482
         * Id : 1178
         * Imgurl : /Upload/img/20161123/CP20161123164316402.jpg
         * Name : dsf
         * Price : 55
         * count : 3
         * Comment : 0
         * type : 3
         */

        private int Favoriteid;
        private int Id;
        private String Imgurl="";
        private String Name="";
        private String Price="";
        private int count=1;
        private int Comment=0;
        private int type=3;

        public int getFavoriteid() {
            return Favoriteid;
        }

        public void setFavoriteid(int Favoriteid) {
            this.Favoriteid = Favoriteid;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getImgurl() {
            return Imgurl;
        }

        public void setImgurl(String Imgurl) {
            this.Imgurl = Imgurl;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getComment() {
            return Comment;
        }

        public void setComment(int Comment) {
            this.Comment = Comment;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
