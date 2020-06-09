package com.study.mycookbook.cookbook;

import com.study.mycookbook.cookbook.beans.CookBook;

import java.util.List;

public interface CookBookView {
    public void setCookBookList(List<CookBook> list);
    public void setFail();
}
