package com.water.admin.db.domain.dto;


/**
 * Created by huangguoping.
 */
public class PageDataResponse<T> extends BaseResponse {
    private T data;
    private int totalCount;
    private int pageIndex;
    private int pageSize;

    public T getData() {
        return data;
    }

    public PageDataResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
