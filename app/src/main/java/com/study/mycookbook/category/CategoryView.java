package com.study.mycookbook.category;

import com.study.mycookbook.category.beans.Category;

public interface CategoryView {
    /*
    * 从后端拿到数据返回给前端视图层
    */
    public void setCategory(Category category);
    //失败
    public void setFail();
}
