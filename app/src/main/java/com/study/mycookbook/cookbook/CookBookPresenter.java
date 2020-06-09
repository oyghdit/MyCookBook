package com.study.mycookbook.cookbook;

import android.content.Context;

public interface CookBookPresenter {
    public void getCookBookListById(Context ctx, String id);
    public void getCookBookListByKey(Context ctx,String key);
}
