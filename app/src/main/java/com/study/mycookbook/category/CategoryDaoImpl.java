package com.study.mycookbook.category;

import android.content.Context;
import android.util.Log;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.mycookbook.category.beans.Category;
import com.study.mycookbook.networking.MyVolley;
import org.json.JSONException;

public class CategoryDaoImpl implements CategoryDao, MyVolley.CallBack {

    CategoryDaoListener listener;
    @Override
    public void getCategory(Context ctx, CategoryDaoListener listener) {
        this.listener = listener;
        String url = "http://apis.juhe.cn/cook/category?key=338c432f0e92e2357d57ea78e907cb29";
        MyVolley myVolley = MyVolley.newMyVolley();
        myVolley.stringRequestGet(ctx,url,this);
    }

    @Override
    public void onStringSuccess(String response) throws JSONException {
        Gson gson = new Gson();
        Category c = gson.fromJson(response,new TypeToken<Category>(){}.getType());
        listener.onSuccess(c);
    }

    @Override
    public void onFailure(VolleyError error) {
        listener.onFail();
    }
}
