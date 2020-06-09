package com.study.mycookbook.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.squareup.picasso.Picasso;
import com.study.mycookbook.DetailActivity;
import com.study.mycookbook.R;
import com.study.mycookbook.cookbook.CookBookPresenter;
import com.study.mycookbook.cookbook.CookBookPresenterImpl;
import com.study.mycookbook.cookbook.CookBookView;
import com.study.mycookbook.cookbook.beans.CookBook;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements CookBookView {

    String skey;
    CookBookPresenter cookBookPresenter;
    List<CookBook> cookbooklist = new ArrayList<>();
    ListView listView;
    SearchAdapter searchAdapter;

    public  static  SearchFragment newInstance (String key){
        SearchFragment searchFragment = new SearchFragment();
        Bundle b = new Bundle();
        b.putString("searchkey",key);
        Log.i("key",key);
        searchFragment.setArguments(b);
        return searchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = (ListView)view.findViewById(R.id.search_listview);
        searchAdapter = new SearchAdapter();
        listView.setAdapter(searchAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CookBook cookBook = cookbooklist.get(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("cookbook", cookBook);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cookBookPresenter = new CookBookPresenterImpl(this);
        Bundle b =this.getArguments();
        skey = b.getString("searchkey");
        cookBookPresenter.getCookBookListByKey(getActivity(),skey);
    }

    class SearchAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return cookbooklist.size();
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView;
            if(convertView==null)
            {
                myView=getActivity().getLayoutInflater().inflate(R.layout.list_cookbook_item,null);
            }else
            {
                myView=convertView;
            }
            ImageView imageView=(ImageView)myView.findViewById(R.id.imageView);
            TextView tv1=(TextView)myView.findViewById(R.id.title);
            TextView tv2=(TextView)myView.findViewById(R.id.intro);
            CookBook cookBook=cookbooklist.get(position);
            String url=cookBook.getAlbums().get(0);
            String title=cookBook.getTitle();
            String intro=cookBook.getImtro();
            tv1.setText(title);
            tv2.setText(intro);
            //使用Picasso控件显示图片
            Picasso.with(getActivity()).load(url).resize(120,120).into(imageView);
            return myView;
        }

    }

    //CookBookView
    @Override
    public void setCookBookList(List<CookBook> list) {
        this.cookbooklist=list;
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void setFail() {
        Toast.makeText(getActivity(),"fail",Toast.LENGTH_LONG).show();
    }
}
