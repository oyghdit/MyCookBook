package com.study.mycookbook.category;

import android.content.Context;
import com.study.mycookbook.category.beans.Category;

public class CategoryPresenterImpl implements CategoryPresenter, CategoryDao.CategoryDaoListener {
    CategoryView categoryView;
    CategoryDao categoryDao;
    Context context;

    public CategoryPresenterImpl (Context context,CategoryView categoryView){
        this.context =context;
        this.categoryView = categoryView;
        categoryDao = new CategoryDaoImpl();
    }

    @Override
    public void getCategory() {
        categoryDao.getCategory(context,this);
    }

    @Override
    public void onSuccess(Category category) {
        categoryView.setCategory(category);
    }

    @Override
    public void onFail() {
        categoryView.setFail();
    }
}
