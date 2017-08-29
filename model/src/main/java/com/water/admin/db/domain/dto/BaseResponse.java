package com.water.admin.db.domain.dto;

import java.io.Serializable;

/**
 * Created by huangguoping.
 */
public class BaseResponse implements Serializable {
    public static final int SUCCEED = 0;//成功
    /**
     * 参数错误
     */
    public static final int PARAMETER_ERROR = 1;//参数错误
    /**
     * 应用级别错误
     */
    public static final int APP_ERROR = 2;//应用级别错误
    /**
     * 应用级别异常
     */
    public static final int APP_EXCEPTION = 3;//应用级别异常
    /**
     * 不合法的权限
     */
    public static final int NO_RIGHT = 4;//不合法的权限
    /**
     * 其他错误
     */
    public static final int OTHER_ERROR = 5;//其他错误

    private int code;
    private String info;

    public int getCode() {
        return code;
    }

    public BaseResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public BaseResponse setInfo(String info) {
        this.info = info;
        return this;
    }
}
