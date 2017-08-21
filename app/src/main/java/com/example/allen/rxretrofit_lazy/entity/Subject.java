package com.example.allen.rxretrofit_lazy.entity;

import java.io.Serializable;

/**
 * 测试用例
 * Created by allen on 2017/8/21.
 */

public class Subject implements Serializable {
    private int id;
    private String name;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
