package com.study.mycookbook.cookbook;

import android.content.Context;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.mycookbook.cookbook.beans.CookBook;
import com.study.mycookbook.cookbook.beans.TagCategoryList;
import com.study.mycookbook.networking.MyVolley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CookBookDaoImpl implements CookBookDao,MyVolley.CallBack {

    CookBookDaoListener listener;

    @Override
    public void getCookBookListById(Context context,String id, CookBookDaoListener listener) {
        this.listener=listener;
        String url="http://apis.juhe.cn/cook/index?dtype=&pn=&rn=&format=&key=338c432f0e92e2357d57ea78e907cb29&cid="+id;
        MyVolley myVolley = MyVolley.newMyVolley();
        myVolley.stringRequestGet(context,url,this);
    }

    @Override
    public void getCookBookListByKey(Context context, String key, CookBookDaoListener listener) {
        this.listener=listener;
        String url="http://apis.juhe.cn/cook/query.php?dtype=&pn=&rn=&key=338c432f0e92e2357d57ea78e907cb29&menu="+key;
        MyVolley myVolley = MyVolley.newMyVolley();
        myVolley.stringRequestGet(context,url,this);
    }

    @Override
    public void onStringSuccess(String response) {
        Gson gson = new Gson();
        try{
            JSONObject jsonObject=new JSONObject(response);
            JSONObject jsonObject1=jsonObject.getJSONObject("result");
            TagCategoryList t1=gson.fromJson(jsonObject1.toString(),new TypeToken<TagCategoryList>(){}.getType());
            List<CookBook> list = t1.getData();
            listener.onSuccess(list);
        }catch (JSONException e){e.printStackTrace();}

    }

    @Override
    public void onFailure(VolleyError error) {
        listener.onFail();
    }
}
