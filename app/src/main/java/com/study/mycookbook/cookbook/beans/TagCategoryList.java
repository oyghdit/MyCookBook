package com.study.mycookbook.cookbook.beans;

import java.util.List;

public class TagCategoryList {
    List<CookBook> data;

    public TagCategoryList() {

    }

    public TagCategoryList(List<CookBook> data) {
        this.data = data;
    }

    public List<CookBook> getData() {
        return data;
    }

    public void setData(List<CookBook> data) {
        this.data = data;
    }
}
