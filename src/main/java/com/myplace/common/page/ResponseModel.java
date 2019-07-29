package com.myplace.common.page;

public class ResponseModel {

    public ResponseModel() {
        this.setCode(0);
        this.setMsg("success");
    }

    public static ResponseModel error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static ResponseModel error(String msg) {
        return error(500, msg);
    }

    public static ResponseModel error(int code, String msg) {
        ResponseModel r = new ResponseModel();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static ResponseModel success(String msg) {
        ResponseModel r = new ResponseModel();
        r.setMsg(msg);
        return r;
    }

    public static ResponseModel success() {
        return new ResponseModel();
    }

    public static ResponseModel success(Long count,Object data) {
        ResponseModel r = new ResponseModel();
        r.setCode(0);
        r.setMsg("success");
        r.setCount(count);
        r.setData(data);
        return r;
    }

    public static ResponseModel success(Object data) {
        ResponseModel r = new ResponseModel();
        r.setCode(0);
        r.setMsg("success");
        r.setData(data);
        return r;
    }

    public static ResponseModel success(String msg,Object data) {
        ResponseModel r = new ResponseModel();
        r.setCode(0);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    private Integer code;
    private String msg;
    private Long count;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
