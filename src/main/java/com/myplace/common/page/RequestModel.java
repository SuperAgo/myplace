package com.myplace.common.page;

import java.util.Map;

public class RequestModel {
    //分页页码
    private Integer page;
    //每页条数
    private Integer limit;
    //排序方式
    private String sortType;
    //排序列
    private String sortColumn;
    //其他参数
    private Map<String,Object> params;


    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
