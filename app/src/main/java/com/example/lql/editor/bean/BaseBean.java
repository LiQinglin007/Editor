package com.example.lql.editor.bean;

/**
 * Created by LQL on 2016/12/1.
 */

public class BaseBean {
    /**
     * ResultCode : 0
     * Msg : 注册成功！
     * Data : 1030
     */
    private int ResultCode;
    private String Msg;


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

}
