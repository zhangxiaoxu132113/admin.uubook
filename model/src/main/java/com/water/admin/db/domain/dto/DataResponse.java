package com.water.admin.db.domain.dto;

/**
 * Created by huangguoping.
 */
public class DataResponse<T> extends BaseResponse {
    public DataResponse() {
        setCode(SUCCEED);
        setInfo("操作成功");
    }
    public DataResponse(T data) {
        setCode(SUCCEED);
        setInfo("操作成功");
        this.data = data;
    }
    public DataResponse(Integer code, String info) {
        setCode(code);
        setInfo(info);
    }
    public DataResponse(Integer code, String info, T data) {
        setCode(code);
        setInfo(info);
        this.data = data;
    }

    private T data;

    public T getData() {
        return data;
    }

    public DataResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
