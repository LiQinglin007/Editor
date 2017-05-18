package com.example.lql.editor.bean;

/**
 * Created by LQL on 2016/12/8.
 */

public class OrderDetailsBean {
    private String ServerName="";
    private String serviceTime="";

    public String getServerName() {
        return ServerName;
    }

    public void setServerName(String serverName) {
        ServerName = serverName;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    //    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
//
//    public String getTime() {
//        return Time;
//    }
//
//    public void setTime(String time) {
//        Time = time;
//    }
}
