package com.study.mycookbook.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.squareup.picasso.Picasso;
import com.study.mycookbook.DetailActivity;
import com.study.mycookbook.R;
import com.study.mycookbook.cookbook.CookBookPresenter;
import com.study.mycookbook.cookbook.CookBookPresenterImpl;
import com.study.mycookbook.cookbook.CookBookView;
import com.study.mycookbook.cookbook.beans.CookBook;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements CookBookView {

    ListView listView;
    List<CookBook> list;
    MyAdapter myAdapter;
    CookBookPresenter cookBookPresenter;
    ConvenientBanner convenientBanner;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private Integer[] images = {R.drawable.test01, R.drawable.test02, R.drawable.test03,R.drawable.test04};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        cookBookPresenter = new CookBookPresenterImpl(this);
        cookBookPresenter.getCookBookListByKey(getActivity(),"家常菜");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        userBanner(view);
        initView(view);
        return view;

    }

    /*
    * 显示家常菜列表
    */
    private void initView(View view){
        listView = (ListView)view.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CookBook cookBook = list.get(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("cookbook", cookBook);
                startActivity(intent);
            }
        });
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }

    /*
    * 创建适配家常菜
    */
    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
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
            if(convertView == null){
                myView = getActivity().getLayoutInflater().inflate(R.layout.list_cookbook_item,null);
            }else{
                myView = convertView;
            }
            ImageView imageView = (ImageView)myView.findViewById(R.id.imageView);
            TextView tv1 = (TextView)myView.findViewById(R.id.title);
            TextView tv2 = (TextView)myView.findViewById(R.id.intro);

            CookBook cookBook = list.get(position);
            String url = cookBook.getAlbums().get(0);
            String title = cookBook.getTitle();
            String intro = cookBook.getImtro();
            tv1.setText(title);
            tv2.setText(intro);
            //使用Picasso控件显示图片
            Picasso.with(getActivity()).load(url).resize(120,120).into(imageView);

            return myView;
        }
    }


    //实现CookBookView
    @Override
    public void setCookBookList(List<CookBook> list) {
        this.list = list;
    }

    @Override
    public void setFail() {

    }

    void userBanner(View view){
        //获取轮播图控件
        convenientBanner = (ConvenientBanner)view.findViewById(R.id.convenientBanner);
        //加载图片
        loadImageDatas();
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                /*new CBViewHolderCreator() {

                    @Override
                    public Object createHolder() {
                        return null;
                    }

                    public LocalImageHolderView createHolder(View itemView) {
                        return new LocalImageHolderView(itemView);
                    }


                    public int getLayoutId() {
                        return R.layout.item_localimage;
                    }*/
                }, localImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused});
    }

    private void loadImageDatas() {
        //本地图片集合
        for (int position = 0; position < 4; position++)
            localImages.add(images[position]);
    }


    class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }
    }

}
