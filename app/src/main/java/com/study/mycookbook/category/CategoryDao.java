package com.study.mycookbook.category;

import android.content.Context;
import com.study.mycookbook.category.beans.Category;

public interface CategoryDao {
    public void getCategory(Context ctx, CategoryDaoListener listener);

    interface CategoryDaoListener{
        public void onSuccess(Category category);
        public void onFail();
    }
}
