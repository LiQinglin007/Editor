package com.example.lql.editor.bean;

/**
 * Created by LQL on 2016/12/1.
 */

public class LoginBean extends  BaseBean{


    /**
     * Data : {"data":{"telphone":"15284224244","userName":"","userId":1030,"Balance":10000,"img":"","Sex":"男","birthday":"1970-01-01","school":"","job":"","graduate":"","Profession":"","real":2},"state":0}
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
         * data : {"telphone":"15284224244","userName":"","userId":1030,"Balance":10000,"img":"","Sex":"男","birthday":"1970-01-01","school":"","job":"","graduate":"","Profession":"","real":2}
         * state : 0
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
             * userName :
             * userId : 1030
             * Balance : 10000
             * img :
             * Sex : 男
             * birthday : 1970-01-01
             * school :
             * job :
             * graduate :
             * Profession :
             * real : 2
             */

            private String telphone="";
            private String userName="";
            private int userId;
            private double Balance=0;
            private String img="";
            private String Sex="男";
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

            public double getBalance() {
                return Balance;
            }

            public void setBalance(double Balance) {
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
