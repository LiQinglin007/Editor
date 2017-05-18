package com.example.lql.editor.bean;

/**
 * Created by LQL on 2016/12/20.
 */

public class GetNameBean extends  BaseBean {


    /**
     * Data : {"userName":"小米","img":"/Upload/img/20161229/20161229111728.jpg","Balance":9975.45,"real":1}
     */

    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * userName : 小米
         * img : /Upload/img/20161229/20161229111728.jpg
         * Balance : 9975.45
         * real : 1
         */

        private String userName;
        private String img;
        private double Balance;
        private int real;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public double getBalance() {
            return Balance;
        }

        public void setBalance(double Balance) {
            this.Balance = Balance;
        }

        public int getReal() {
            return real;
        }

        public void setReal(int real) {
            this.real = real;
        }
    }
}
