package com.qhw.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2019/8/20  14:15
 */
public class ResultT<T> {

    private Boolean status;
    private String msg;
    private T t;
    private List<T> list = new ArrayList<>();

    //    开始页码
    private Integer pageStart;
    //    每页多少
    private Integer pageSize;

    private int currentPage; // 当前页
    private int currentIndex; // 当前记录起始索引
    private int totalPage; //    总页数

    private int allElementNum; //    总记录数


    public ResultT() {
    }

    public ResultT(Boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResultT(Integer pageStart, Integer pageSize) {
        this.pageStart = pageStart;
        this.pageSize = pageSize;
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

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentIndex() {
        this.currentIndex = (getCurrentPage() - 1) * getPageSize();
        if (currentIndex < 0) currentIndex = 0;
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getAllElementNum() {
        return allElementNum;
    }

    public void setAllElementNum(int allElementNum) {
        if (allElementNum % pageSize == 0) {
            totalPage = allElementNum / pageSize;
        }else {
            totalPage = allElementNum / pageSize + 1;
        }
        this.allElementNum = allElementNum;
    }
}
