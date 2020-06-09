package com.study.mycookbook.category.beans;

import java.util.List;

public class Category1 {
    String parentId;
    String name;
    List<Category2> list;

    public Category1() {

    }


    public Category1(String parentId, String name, List<Category2> list) {
        this.parentId = parentId;
        this.name = name;
        this.list = list;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category2> getList() {
        return list;
    }

    public void setList(List<Category2> list) {
        this.list = list;
    }
}
