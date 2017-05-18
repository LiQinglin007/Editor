package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/6.
 */

public class StudioDetailsBean extends  BaseBean {
    /**
     * Data : {"Id":5072,"HeadImg":"/Upload/img/20161230/CP20161230101431820.png","WorkName":"木子柠檬","Deposit":1000,"count":146,"credit":5,"collect":0,"Dis":"木子柠檬工作室，提供论文速审、查重、降重专业服务","comment":8,"commentDetail":{"Id":8350,"name":"小九","img":"/Upload/img/20161229/20161229034945.jpg","ComContent":"不容易"},"service":[{"productId":5296,"Picture":"/Upload/img/20161230/2016123011403605.png","ServiceName":"木子速审3","ServicePrice":"10.99~100.99","Type":3,"OriginalCost":0,"count":4},{"productId":5295,"Picture":"/Upload/img/20161230/2016123011394704.png","ServiceName":"木子降重3","ServicePrice":"20.11","Type":2,"OriginalCost":30.329999923706055,"count":36},{"productId":5294,"Picture":"/Upload/img/20161230/2016123011282703.png","ServiceName":"木子查重3","ServicePrice":"10.99","Type":1,"OriginalCost":20,"count":5},{"productId":5293,"Picture":"/Upload/img/20161230/2016123010054501.png","ServiceName":"测试外网","ServicePrice":"1","Type":1,"OriginalCost":1,"count":3},{"productId":5290,"Picture":"/Upload/img/20161230/2016123010065205.png","ServiceName":"江重数量","ServicePrice":"5","Type":2,"OriginalCost":10,"count":34},{"productId":5289,"Picture":"/Upload/img/20161230/2016123010081209.png","ServiceName":"速审服务验收1228","ServicePrice":"100~200","Type":3,"OriginalCost":0,"count":5},{"productId":5288,"Picture":"/Upload/img/20161230/2016123010070506.png","ServiceName":"降重服务验收1228","ServicePrice":"10.20","Type":2,"OriginalCost":25.75,"count":20},{"productId":5287,"Picture":"/Upload/img/20161230/2016123010060302.png","ServiceName":"查重服务验收1228","ServicePrice":"10.52","Type":1,"OriginalCost":15.5,"count":2},{"productId":5286,"Picture":"/Upload/img/20161230/2016123010083001.png","ServiceName":"木子速审2","ServicePrice":"20~200","Type":3,"OriginalCost":0,"count":1},{"productId":5285,"Picture":"/Upload/img/20161230/2016123010072407.png","ServiceName":"木子降重2","ServicePrice":"200","Type":2,"OriginalCost":300,"count":27},{"productId":5284,"Picture":"/Upload/img/20161230/2016123010061703.png","ServiceName":"木子查重2","ServicePrice":"50","Type":1,"OriginalCost":20,"count":2},{"productId":5283,"Picture":"/Upload/img/20161230/2016123010063404.png","ServiceName":"木子查重1","ServicePrice":"100","Type":1,"OriginalCost":200,"count":5},{"productId":5282,"Picture":"/Upload/img/20161230/2016123010075408.png","ServiceName":"木子降重1","ServicePrice":"100","Type":2,"OriginalCost":200,"count":1},{"productId":5281,"Picture":"/Upload/img/20161230/2016123010085502.png","ServiceName":"木子速审1","ServicePrice":"10~200","Type":3,"OriginalCost":0,"count":1}],"Qq":"1558960175","Telphone":"15076165538"}
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
         * Id : 5072
         * HeadImg : /Upload/img/20161230/CP20161230101431820.png
         * WorkName : 木子柠檬
         * Deposit : 1000
         * count : 146
         * credit : 5
         * collect : 0
         * Dis : 木子柠檬工作室，提供论文速审、查重、降重专业服务
         * comment : 8
         * commentDetail : {"Id":8350,"name":"小九","img":"/Upload/img/20161229/20161229034945.jpg","ComContent":"不容易"}
         * service : [{"productId":5296,"Picture":"/Upload/img/20161230/2016123011403605.png","ServiceName":"木子速审3","ServicePrice":"10.99~100.99","Type":3,"OriginalCost":0,"count":4},{"productId":5295,"Picture":"/Upload/img/20161230/2016123011394704.png","ServiceName":"木子降重3","ServicePrice":"20.11","Type":2,"OriginalCost":30.329999923706055,"count":36},{"productId":5294,"Picture":"/Upload/img/20161230/2016123011282703.png","ServiceName":"木子查重3","ServicePrice":"10.99","Type":1,"OriginalCost":20,"count":5},{"productId":5293,"Picture":"/Upload/img/20161230/2016123010054501.png","ServiceName":"测试外网","ServicePrice":"1","Type":1,"OriginalCost":1,"count":3},{"productId":5290,"Picture":"/Upload/img/20161230/2016123010065205.png","ServiceName":"江重数量","ServicePrice":"5","Type":2,"OriginalCost":10,"count":34},{"productId":5289,"Picture":"/Upload/img/20161230/2016123010081209.png","ServiceName":"速审服务验收1228","ServicePrice":"100~200","Type":3,"OriginalCost":0,"count":5},{"productId":5288,"Picture":"/Upload/img/20161230/2016123010070506.png","ServiceName":"降重服务验收1228","ServicePrice":"10.20","Type":2,"OriginalCost":25.75,"count":20},{"productId":5287,"Picture":"/Upload/img/20161230/2016123010060302.png","ServiceName":"查重服务验收1228","ServicePrice":"10.52","Type":1,"OriginalCost":15.5,"count":2},{"productId":5286,"Picture":"/Upload/img/20161230/2016123010083001.png","ServiceName":"木子速审2","ServicePrice":"20~200","Type":3,"OriginalCost":0,"count":1},{"productId":5285,"Picture":"/Upload/img/20161230/2016123010072407.png","ServiceName":"木子降重2","ServicePrice":"200","Type":2,"OriginalCost":300,"count":27},{"productId":5284,"Picture":"/Upload/img/20161230/2016123010061703.png","ServiceName":"木子查重2","ServicePrice":"50","Type":1,"OriginalCost":20,"count":2},{"productId":5283,"Picture":"/Upload/img/20161230/2016123010063404.png","ServiceName":"木子查重1","ServicePrice":"100","Type":1,"OriginalCost":200,"count":5},{"productId":5282,"Picture":"/Upload/img/20161230/2016123010075408.png","ServiceName":"木子降重1","ServicePrice":"100","Type":2,"OriginalCost":200,"count":1},{"productId":5281,"Picture":"/Upload/img/20161230/2016123010085502.png","ServiceName":"木子速审1","ServicePrice":"10~200","Type":3,"OriginalCost":0,"count":1}]
         * Qq : 1558960175
         * Telphone : 15076165538
         */

        private int Id;
        private String HeadImg;
        private String WorkName;
        private int Deposit;
        private int count;
        private int credit;
        private int collect;
        private String Dis;
        private int comment;
        private CommentDetailBean commentDetail;
        private String Qq;
        private String Telphone;
        private List<ServiceBean> service;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getHeadImg() {
            return HeadImg;
        }

        public void setHeadImg(String HeadImg) {
            this.HeadImg = HeadImg;
        }

        public String getWorkName() {
            return WorkName;
        }

        public void setWorkName(String WorkName) {
            this.WorkName = WorkName;
        }

        public int getDeposit() {
            return Deposit;
        }

        public void setDeposit(int Deposit) {
            this.Deposit = Deposit;
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

        public int getCollect() {
            return collect;
        }

        public void setCollect(int collect) {
            this.collect = collect;
        }

        public String getDis() {
            return Dis;
        }

        public void setDis(String Dis) {
            this.Dis = Dis;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public CommentDetailBean getCommentDetail() {
            return commentDetail;
        }

        public void setCommentDetail(CommentDetailBean commentDetail) {
            this.commentDetail = commentDetail;
        }

        public String getQq() {
            return Qq;
        }

        public void setQq(String Qq) {
            this.Qq = Qq;
        }

        public String getTelphone() {
            return Telphone;
        }

        public void setTelphone(String Telphone) {
            this.Telphone = Telphone;
        }

        public List<ServiceBean> getService() {
            return service;
        }

        public void setService(List<ServiceBean> service) {
            this.service = service;
        }

        public static class CommentDetailBean {
            /**
             * Id : 8350
             * name : 小九
             * img : /Upload/img/20161229/20161229034945.jpg
             * ComContent : 不容易
             */

            private int Id;
            private String name;
            private String img;
            private String ComContent;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getComContent() {
                return ComContent;
            }

            public void setComContent(String ComContent) {
                this.ComContent = ComContent;
            }
        }

        public static class ServiceBean {
            /**
             * productId : 5296
             * Picture : /Upload/img/20161230/2016123011403605.png
             * ServiceName : 木子速审3
             * ServicePrice : 10.99~100.99
             * Type : 3
             * OriginalCost : 0
             * count : 4
             */

            private int productId;
            private String Picture;
            private String ServiceName;
            private String ServicePrice;
            private int Type;
            private double OriginalCost;
            private int count;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
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

            public String getServicePrice() {
                return ServicePrice;
            }

            public void setServicePrice(String ServicePrice) {
                this.ServicePrice = ServicePrice;
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

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }


//
//    /**
//     * Data : {"Id":1,"HeadImg":null,"WorkName":"77","Deposit":"7","count":53,"credit":13,"collect":0,"Dis":"这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段","comment":22,"commentDetail":{"Id":5213,"name":"1","img":"/Upload/img/20161206/20161206091727master.jpg","ComContent":"1111"},"service":[{"productId":1178,"Picture":"/Upload/img/20161123/CP20161123164316402.jpg","ServiceName":"dsf","ServicePrice":"55","Type":3,"OriginalCost":0,"count":2},{"productId":1179,"Picture":"/Upload/img/20161123/CP20161123171341215.jpg","ServiceName":"122","ServicePrice":"1","Type":1,"OriginalCost":122,"count":6},{"productId":1180,"Picture":"/Upload/img/20161123/CP20161123171650246.jpg","ServiceName":"223","ServicePrice":"128","Type":2,"OriginalCost":223,"count":0},{"productId":1181,"Picture":"/Upload/img/20161123/CP20161123171857866.jpg","ServiceName":"第三方","ServicePrice":"3","Type":2,"OriginalCost":3,"count":2},{"productId":1182,"Picture":"/Upload/img/20161123/CP20161123172344113.jpg","ServiceName":"","ServicePrice":"3","Type":2,"OriginalCost":3,"count":4},{"productId":1183,"Picture":"/Upload/img/20161123/CP20161123172455209.jpg","ServiceName":"22","ServicePrice":"2","Type":2,"OriginalCost":2,"count":4},{"productId":1184,"Picture":"/Upload/img/20161123/CP20161123174322499.jpg","ServiceName":"      ","ServicePrice":"4","Type":2,"OriginalCost":6,"count":10},{"productId":1186,"Picture":"/Upload/img/20161123/CP20161123174842347.jpg","ServiceName":"噢噢","ServicePrice":"8","Type":3,"OriginalCost":0,"count":8},{"productId":2188,"Picture":"/Upload/img/20161205/CP20161205144320265.jpg","ServiceName":"查重测试专用1查重测试专用1查重测试专用1","ServicePrice":"180","Type":1,"OriginalCost":200,"count":11}],"Qq":null,"Telphone":null}
//     */
//
//    private DataBean Data;
//
//    public DataBean getData() {
//        return Data;
//    }
//
//    public void setData(DataBean Data) {
//        this.Data = Data;
//    }
//
//    public static class DataBean {
//        /**
//         * Id : 1
//         * HeadImg : null
//         * WorkName : 77
//         * Deposit : 7
//         * count : 53
//         * credit : 13
//         * collect : 0
//         * Dis : 这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段这是描述字段
//         * comment : 22
//         * commentDetail : {"Id":5213,"name":"1","img":"/Upload/img/20161206/20161206091727master.jpg","ComContent":"1111"}
//         * service : [{"productId":1178,"Picture":"/Upload/img/20161123/CP20161123164316402.jpg","ServiceName":"dsf","ServicePrice":"55","Type":3,"OriginalCost":0,"count":2},{"productId":1179,"Picture":"/Upload/img/20161123/CP20161123171341215.jpg","ServiceName":"122","ServicePrice":"1","Type":1,"OriginalCost":122,"count":6},{"productId":1180,"Picture":"/Upload/img/20161123/CP20161123171650246.jpg","ServiceName":"223","ServicePrice":"128","Type":2,"OriginalCost":223,"count":0},{"productId":1181,"Picture":"/Upload/img/20161123/CP20161123171857866.jpg","ServiceName":"第三方","ServicePrice":"3","Type":2,"OriginalCost":3,"count":2},{"productId":1182,"Picture":"/Upload/img/20161123/CP20161123172344113.jpg","ServiceName":"","ServicePrice":"3","Type":2,"OriginalCost":3,"count":4},{"productId":1183,"Picture":"/Upload/img/20161123/CP20161123172455209.jpg","ServiceName":"22","ServicePrice":"2","Type":2,"OriginalCost":2,"count":4},{"productId":1184,"Picture":"/Upload/img/20161123/CP20161123174322499.jpg","ServiceName":"      ","ServicePrice":"4","Type":2,"OriginalCost":6,"count":10},{"productId":1186,"Picture":"/Upload/img/20161123/CP20161123174842347.jpg","ServiceName":"噢噢","ServicePrice":"8","Type":3,"OriginalCost":0,"count":8},{"productId":2188,"Picture":"/Upload/img/20161205/CP20161205144320265.jpg","ServiceName":"查重测试专用1查重测试专用1查重测试专用1","ServicePrice":"180","Type":1,"OriginalCost":200,"count":11}]
//         * Qq : null
//         * Telphone : null
//         */
//
//        private int Id;
//        private String HeadImg="";
//        private String WorkName="";
//        private String Deposit="";
//        private int count=0;
//        private int credit=0;
//        private int collect=0;
//        private String Dis="";
//        private int comment=0;
//        private CommentDetailBean commentDetail;
//        private String Qq="";
//        private String Telphone="";
//        private List<ServiceBean> service;
//
//        public int getId() {
//            return Id;
//        }
//
//        public void setId(int Id) {
//            this.Id = Id;
//        }
//
//        public String getHeadImg() {
//            return HeadImg;
//        }
//
//        public void setHeadImg(String HeadImg) {
//            this.HeadImg = HeadImg;
//        }
//
//        public String getWorkName() {
//            return WorkName;
//        }
//
//        public void setWorkName(String WorkName) {
//            this.WorkName = WorkName;
//        }
//
//        public String getDeposit() {
//            return Deposit;
//        }
//
//        public void setDeposit(String Deposit) {
//            this.Deposit = Deposit;
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
//        public int getCollect() {
//            return collect;
//        }
//
//        public void setCollect(int collect) {
//            this.collect = collect;
//        }
//
//        public String getDis() {
//            return Dis;
//        }
//
//        public void setDis(String Dis) {
//            this.Dis = Dis;
//        }
//
//        public int getComment() {
//            return comment;
//        }
//
//        public void setComment(int comment) {
//            this.comment = comment;
//        }
//
//        public CommentDetailBean getCommentDetail() {
//            return commentDetail;
//        }
//
//        public void setCommentDetail(CommentDetailBean commentDetail) {
//            this.commentDetail = commentDetail;
//        }
//
//        public String getQq() {
//            return Qq;
//        }
//
//        public void setQq(String Qq) {
//            this.Qq = Qq;
//        }
//
//        public String getTelphone() {
//            return Telphone;
//        }
//
//        public void setTelphone(String Telphone) {
//            this.Telphone = Telphone;
//        }
//
//        public List<ServiceBean> getService() {
//            return service;
//        }
//
//        public void setService(List<ServiceBean> service) {
//            this.service = service;
//        }
//
//        public static class CommentDetailBean {
//            /**
//             * Id : 5213
//             * name : 1
//             * img : /Upload/img/20161206/20161206091727master.jpg
//             * ComContent : 1111
//             */
//
//            private int Id;
//            private String name="";
//            private String img="";
//            private String ComContent="";
//
//            public int getId() {
//                return Id;
//            }
//
//            public void setId(int Id) {
//                this.Id = Id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getImg() {
//                return img;
//            }
//
//            public void setImg(String img) {
//                this.img = img;
//            }
//
//            public String getComContent() {
//                return ComContent;
//            }
//
//            public void setComContent(String ComContent) {
//                this.ComContent = ComContent;
//            }
//        }
//
//        public static class ServiceBean {
//            /**
//             * productId : 1178
//             * Picture : /Upload/img/20161123/CP20161123164316402.jpg
//             * ServiceName : dsf
//             * ServicePrice : 55
//             * Type : 3
//             * OriginalCost : 0
//             * count : 2
//             */
//
//            private int productId;
//            private String Picture="";
//            private String ServiceName="";
//            private String ServicePrice="";
//            private int Type=0;
//            private int OriginalCost=0;
//            private int count=0;
//
//            public int getProductId() {
//                return productId;
//            }
//
//            public void setProductId(int productId) {
//                this.productId = productId;
//            }
//
//            public String getPicture() {
//                return Picture;
//            }
//
//            public void setPicture(String Picture) {
//                this.Picture = Picture;
//            }
//
//            public String getServiceName() {
//                return ServiceName;
//            }
//
//            public void setServiceName(String ServiceName) {
//                this.ServiceName = ServiceName;
//            }
//
//            public String getServicePrice() {
//                return ServicePrice;
//            }
//
//            public void setServicePrice(String ServicePrice) {
//                this.ServicePrice = ServicePrice;
//            }
//
//            public int getType() {
//                return Type;
//            }
//
//            public void setType(int Type) {
//                this.Type = Type;
//            }
//
//            public int getOriginalCost() {
//                return OriginalCost;
//            }
//
//            public void setOriginalCost(int OriginalCost) {
//                this.OriginalCost = OriginalCost;
//            }
//
//            public int getCount() {
//                return count;
//            }
//
//            public void setCount(int count) {
//                this.count = count;
//            }
//        }
//    }
}
