package com.study.mycookbook.cookbook;

import android.content.Context;
import com.study.mycookbook.cookbook.CookBookDao;
import com.study.mycookbook.cookbook.CookBookDaoImpl;
import com.study.mycookbook.cookbook.CookBookPresenter;
import com.study.mycookbook.cookbook.CookBookView;
import com.study.mycookbook.cookbook.beans.CookBook;

import java.util.List;

public class CookBookPresenterImpl implements CookBookPresenter, CookBookDao.CookBookDaoListener {

    CookBookView cookBookView;
    CookBookDao cookBookDao;

    public CookBookPresenterImpl(CookBookView cookBookView){
        this.cookBookView=cookBookView;
        cookBookDao=new CookBookDaoImpl();
    }

    @Override
    public void getCookBookListById(Context ctx,String id) {
        cookBookDao.getCookBookListById(ctx,id,this);
    }

    @Override
    public void getCookBookListByKey(Context ctx, String key) {
        cookBookDao.getCookBookListByKey(ctx,key,this);
    }

    @Override
    public void onSuccess(List<CookBook> list) {
        cookBookView.setCookBookList(list);
    }

    @Override
    public void onFail() {
        cookBookView.setFail();
    }
}
