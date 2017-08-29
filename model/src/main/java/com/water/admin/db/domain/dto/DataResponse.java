package com.water.admin.db.domain.dto;

/**
 * Created by huangguoping.
 */
public class DataResponse<T> extends BaseResponse {
    private T data;

    public T getData() {
        return data;
    }

    public DataResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
