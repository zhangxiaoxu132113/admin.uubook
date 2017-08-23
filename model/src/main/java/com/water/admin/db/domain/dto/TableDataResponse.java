package com.water.admin.db.domain.dto;

import java.util.List;

/**
 * Created by mrwater on 2017/8/23.
 */
public class TableDataResponse<T> {
    private Integer iTotalRecords; //实际的行数
    private Integer iTotalDisplayRecords; //过滤之后，实际的行数
    private Integer draw; //datatables传过来的参数 原样返回
    private List<T> aaData; //返回实体

    public Integer getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Integer iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public Integer getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }
}
