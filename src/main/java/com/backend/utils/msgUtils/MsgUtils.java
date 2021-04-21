package com.backend.utils.msgUtils;

import net.sf.json.JSONObject;

public class MsgUtils {
    public static final Integer SUCCESS  = 0;
    public static final Integer ERROR = -1;
    public static final Integer LOGIN_USER_ERROR = -100;
    public static final Integer NOT_LOGGED_IN_ERROR = -101;
    public static final Integer REGISTER_ERROR = -200;

    public static final String SUCCESS_MSG = "Success!";
    public static final String LOGIN_SUCCESS_MSG = "Login Success!";
    public static final String LOGOUT_SUCCESS_MSG = "Logout Success!";
    public static final String LOGOUT_ERR_MSG = "Logout Error!";
    public static final String ERROR_MSG = "Error!";
    public static final String LOGIN_USER_ERROR_MSG = "Wrong username or password, please try again!";
    public static final String NOT_LOGGED_IN_ERROR_MSG = "Login failed, please login again!";
    public static final String REGISTER_ERROR_MSG = "Username has been taken!";
    public static final String REGISTER_SUCCESS_MSG = "Register Success!";

    public static Msg makeMsg(Integer status, String msg, JSONObject data){
        return new Msg(status, msg, data);
    }

    public static Msg makeMsg(Integer status, String msg){
        return new Msg(status, msg);
    }
}
