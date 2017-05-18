package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/5.
 */

public class ChooseFileBean extends  BaseBean{

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Id : 1006
         * Path : /Upload/img/20161205/20161205025528小米_lql0007.docx
         * Name : ID_20161205025528小米_lql0007.docx
         * Userid : 1030
         */

        private int Id;
        private String Path="";
        private String Name="";
        private int Userid;
        private String Tag="0";


        public String getTag() {
            return Tag;
        }

        public void setTag(String tag) {
            Tag = tag;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getPath() {
            return Path;
        }

        public void setPath(String Path) {
            this.Path = Path;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getUserid() {
            return Userid;
        }

        public void setUserid(int Userid) {
            this.Userid = Userid;
        }
    }
}
