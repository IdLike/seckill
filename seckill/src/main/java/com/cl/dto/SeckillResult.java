package com.cl.dto;

public class SeckillResult<T> {

    private  Boolean isSuccess;

    private String msg;

    private  T data;


    public  SeckillResult(){}
    public SeckillResult(Boolean isSuccess, String msg) {
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    public SeckillResult(Boolean isSuccess, T data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
