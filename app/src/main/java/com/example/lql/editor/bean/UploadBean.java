package com.example.lql.editor.bean;

/**
 * Created by LQL on 2017/1/4.
 */

public class UploadBean extends  BaseBean {

    /**
     * Data : {"url":"/Upload/img/20170104/ID20170104053132_测试上传.docx","fileName":"ID20170104053133_测试上传.docx"}
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
         * url : /Upload/img/20170104/ID20170104053132_测试上传.docx
         * fileName : ID20170104053133_测试上传.docx
         */

        private String url="";
        private String fileName="";

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }
}
