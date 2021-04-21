package com.backend.utils.msgUtils;

import net.sf.json.JSONObject;

public class Msg {
    private Integer status;
    private String msg;
    private JSONObject data;

    Msg(Integer status, String msg, JSONObject data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    Msg(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = null;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
