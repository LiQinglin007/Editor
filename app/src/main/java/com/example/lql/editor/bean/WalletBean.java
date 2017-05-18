package com.example.lql.editor.bean;

/**
 * Created by LQL on 2016/12/7.
 */

public class WalletBean extends  BaseBean {

    /**
     * Data : {"Balance":10000,"AlipayCard":null,"AlipayName":null}
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
         * Balance : 10000
         * AlipayCard : null
         * AlipayName : null
         */

        private double Balance=0;
        private String AlipayCard="";
        private String AlipayName="";

        public double getBalance() {
            return Balance;
        }

        public void setBalance(double Balance) {
            this.Balance = Balance;
        }

        public String getAlipayCard() {
            return AlipayCard;
        }

        public void setAlipayCard(String AlipayCard) {
            this.AlipayCard = AlipayCard;
        }

        public String getAlipayName() {
            return AlipayName;
        }

        public void setAlipayName(String AlipayName) {
            this.AlipayName = AlipayName;
        }
    }
}
