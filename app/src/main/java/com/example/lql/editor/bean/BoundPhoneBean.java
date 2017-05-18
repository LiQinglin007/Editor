package com.example.lql.editor.bean;

/**
 * Created by LQL on 2016/12/12.
 */

public class BoundPhoneBean extends  BaseBean {

    /**
     * Data : {"data":{"telphone":"15284224244","userName":"小米","userId":1030,"Balance":9860,"img":"/Upload/img/20161210/20161210021225.jpg","Sex":"男","birthday":"2016-12-10","school":"北京大学","job":"运动员","graduate":"博士","Profession":"国家二级篮球运动员","real":0},"state":1}
     */

    private DataBeanX Data;

    public DataBeanX getData() {
        return Data;
    }

    public void setData(DataBeanX Data) {
        this.Data = Data;
    }

    public static class DataBeanX {
        /**
         * data : {"telphone":"15284224244","userName":"小米","userId":1030,"Balance":9860,"img":"/Upload/img/20161210/20161210021225.jpg","Sex":"男","birthday":"2016-12-10","school":"北京大学","job":"运动员","graduate":"博士","Profession":"国家二级篮球运动员","real":0}
         * state : 1
         */

        private DataBean data;
        private int state=0;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public static class DataBean {
            /**
             * telphone : 15284224244
             * userName : 小米
             * userId : 1030
             * Balance : 9860
             * img : /Upload/img/20161210/20161210021225.jpg
             * Sex : 男
             * birthday : 2016-12-10
             * school : 北京大学
             * job : 运动员
             * graduate : 博士
             * Profession : 国家二级篮球运动员
             * real : 0
             */

            private String telphone="";
            private String userName="";
            private int userId;
            private int Balance=0;
            private String img="";
            private String Sex="";
            private String birthday="";
            private String school="";
            private String job="";
            private String graduate="";
            private String Profession="";
            private int real=2;

            public String getTelphone() {
                return telphone;
            }

            public void setTelphone(String telphone) {
                this.telphone = telphone;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getBalance() {
                return Balance;
            }

            public void setBalance(int Balance) {
                this.Balance = Balance;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getSex() {
                return Sex;
            }

            public void setSex(String Sex) {
                this.Sex = Sex;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public String getGraduate() {
                return graduate;
            }

            public void setGraduate(String graduate) {
                this.graduate = graduate;
            }

            public String getProfession() {
                return Profession;
            }

            public void setProfession(String Profession) {
                this.Profession = Profession;
            }

            public int getReal() {
                return real;
            }

            public void setReal(int real) {
                this.real = real;
            }
        }
    }
}
