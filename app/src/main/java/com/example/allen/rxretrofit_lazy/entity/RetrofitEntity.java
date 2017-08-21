package com.example.allen.rxretrofit_lazy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by allen on 2017/8/21.
 */

public class RetrofitEntity implements Serializable {
    private int ret;
    private String msg;
    private List<Subject> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Subject> getData() {
        return data;
    }

    public void setData(List<Subject> data) {
        this.data = data;
    }
}
