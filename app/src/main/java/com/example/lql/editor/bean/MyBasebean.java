package com.example.lql.editor.bean;

/**
 * Created by LQL on 2016/12/1.
 */

public class MyBasebean {

    /**
     * ResultCode : 1
     * Msg : 该手机号已注册，请登录
     * Data :
     */

    private int ResultCode;
    private String Msg;
    private String Data;

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
}
