package com.study.mycookbook.cookbook;

import android.content.Context;
import com.study.mycookbook.cookbook.beans.CookBook;

import java.util.List;

public interface CookBookDao {

    //根据前端所需要的内容，写对应的方法

    public void getCookBookListById(Context ctx, String id, CookBookDaoListener listener);
    public void getCookBookListByKey(Context ctx, String key, CookBookDaoListener listener);

    interface CookBookDaoListener{
        public void onSuccess(List<CookBook> list);
        public void onFail();
    }
}
