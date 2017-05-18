package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/5.
 */

public class ServiceDetailsBean extends BaseBean {


    /**
     * Data : {"detail":{"Id":2193,"Picture":"/Upload/img/20161214/CP20161214101315864.jpg","ServiceName":"是打发","OriginalCost":0,"ServicePrice":"3","count":0,"shopId":1,"QQ":null,"name":"77","deposit":"7","credit":34,"totalCount":70,"img":null,"CheckType":"经济,档案,","PublicationLevel":"3","InfluenceFactor":"3","TotalInfluenceFactor":"3","PrePeriod":"3","FinalPeriod":"3","ReviewCycle":"3","collect":0},"serviceimg":[{"img":"/Upload/img/20161214/2016121410132620160221006.jpg"},{"img":"/Upload/img/20161214/201612141013262015061310475434.jpg"},{"img":"/Upload/img/20161214/20161214101326CP20161018163629240.jpg"},{"img":"/Upload/img/20161214/20161214101326CP20161018163612128.jpg"}]}
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
         * detail : {"Id":2193,"Picture":"/Upload/img/20161214/CP20161214101315864.jpg","ServiceName":"是打发","OriginalCost":0,"ServicePrice":"3","count":0,"shopId":1,"QQ":null,"name":"77","deposit":"7","credit":34,"totalCount":70,"img":null,"CheckType":"经济,档案,","PublicationLevel":"3","InfluenceFactor":"3","TotalInfluenceFactor":"3","PrePeriod":"3","FinalPeriod":"3","ReviewCycle":"3","collect":0}
         * serviceimg : [{"img":"/Upload/img/20161214/2016121410132620160221006.jpg"},{"img":"/Upload/img/20161214/201612141013262015061310475434.jpg"},{"img":"/Upload/img/20161214/20161214101326CP20161018163629240.jpg"},{"img":"/Upload/img/20161214/20161214101326CP20161018163612128.jpg"}]
         */

        private DetailBean detail;
        private List<ServiceimgBean> serviceimg;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public List<ServiceimgBean> getServiceimg() {
            return serviceimg;
        }

        public void setServiceimg(List<ServiceimgBean> serviceimg) {
            this.serviceimg = serviceimg;
        }

        public static class DetailBean {
            /**
             * Id : 2193
             * Picture : /Upload/img/20161214/CP20161214101315864.jpg
             * ServiceName : 是打发
             * OriginalCost : 0
             * ServicePrice : 3
             * count : 0
             * shopId : 1
             * QQ : null
             * name : 77
             * deposit : 7
             * credit : 34
             * totalCount : 70
             * img : null
             * CheckType : 经济,档案,
             * PublicationLevel : 3
             * InfluenceFactor : 3
             * TotalInfluenceFactor : 3
             * PrePeriod : 3
             * FinalPeriod : 3
             * ReviewCycle : 3
             * collect : 0
             */

            private int Id;
            private String Picture;
            private String ServiceName;
            private double OriginalCost;
            private String ServicePrice;
            private int count;
            private int shopId;
            private String QQ;
            private String name;
            private String deposit;
            private int credit;
            private int totalCount;
            private String img;
            private String CheckType;
            private String PublicationLevel;
            private String InfluenceFactor;
            private String TotalInfluenceFactor;
            private String PrePeriod;
            private String FinalPeriod;
            private String ReviewCycle;
            private int collect;

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

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getQQ() {
                return QQ;
            }

            public void setQQ(String QQ) {
                this.QQ = QQ;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDeposit() {
                return deposit;
            }

            public void setDeposit(String deposit) {
                this.deposit = deposit;
            }

            public int getCredit() {
                return credit;
            }

            public void setCredit(int credit) {
                this.credit = credit;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getCheckType() {
                return CheckType;
            }

            public void setCheckType(String CheckType) {
                this.CheckType = CheckType;
            }

            public String getPublicationLevel() {
                return PublicationLevel;
            }

            public void setPublicationLevel(String PublicationLevel) {
                this.PublicationLevel = PublicationLevel;
            }

            public String getInfluenceFactor() {
                return InfluenceFactor;
            }

            public void setInfluenceFactor(String InfluenceFactor) {
                this.InfluenceFactor = InfluenceFactor;
            }

            public String getTotalInfluenceFactor() {
                return TotalInfluenceFactor;
            }

            public void setTotalInfluenceFactor(String TotalInfluenceFactor) {
                this.TotalInfluenceFactor = TotalInfluenceFactor;
            }

            public String getPrePeriod() {
                return PrePeriod;
            }

            public void setPrePeriod(String PrePeriod) {
                this.PrePeriod = PrePeriod;
            }

            public String getFinalPeriod() {
                return FinalPeriod;
            }

            public void setFinalPeriod(String FinalPeriod) {
                this.FinalPeriod = FinalPeriod;
            }

            public String getReviewCycle() {
                return ReviewCycle;
            }

            public void setReviewCycle(String ReviewCycle) {
                this.ReviewCycle = ReviewCycle;
            }

            public int getCollect() {
                return collect;
            }

            public void setCollect(int collect) {
                this.collect = collect;
            }
        }

        public static class ServiceimgBean {
            /**
             * img : /Upload/img/20161214/2016121410132620160221006.jpg
             */

            private String img;
            private String width;

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
