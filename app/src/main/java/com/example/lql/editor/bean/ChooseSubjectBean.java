package com.example.lql.editor.bean;

import java.util.List;

/**
 * Created by LQL on 2016/12/5.
 */

public class ChooseSubjectBean extends BaseBean {

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Id : 1
         * Schooling_ : 涓撶
         */

        private int Id;
        private String ProjectName="";

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String projectName) {
            ProjectName = projectName;
        }
    }
}
