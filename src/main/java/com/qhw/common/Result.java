package com.qhw.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2019/8/20  14:15
 */
public class Result<T> {

    private Boolean status;
    private String msg;
    private T t;
    private List<T> list = new ArrayList<>();


    public Result() {
    }

    public Result(Boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(Boolean status, String msg, List<T> list) {
        this.status = status;
        this.msg = msg;
        this.list = list;
    }

    public Result(Boolean status, String msg, T t) {
        this.status = status;
        this.msg = msg;
        this.t = t;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
