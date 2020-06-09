package com.study.mycookbook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.nex3z.flowlayout.FlowLayout;
import com.squareup.picasso.Picasso;
import com.study.mycookbook.cookbook.beans.CookBook;
import com.study.mycookbook.cookbook.beans.Step;
import com.study.mycookbook.util.DensityUtil;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    ImageView headImageView;
    CookBook cookBook;
    TextView titTv;
    TextView introTv;
    FlowLayout flowLayout;
    ListView mainListView;
    ListView fuListView;
    String[] mainList;
    String[] fuList;
    MainListAdapter mainListAdapter;
    FuListAdapter fuListAdapter;
    ListView setplistView;
    List<Step> stepList;
    StepListAdapter stepListAdapter;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        cookBook = (CookBook) intent.getSerializableExtra("cookbook");
        intitView();
    }

    private void intitView() {

        //实现封面图片
        headImageView =(ImageView)findViewById(R.id.d_imageView);
        String url = cookBook.getAlbums().get(0);
        Picasso.with(this).load(url).into(headImageView);

        //实现菜名
        titTv = (TextView)findViewById(R.id.d_title);
        titTv.setText(cookBook.getTitle());

        //实现菜品介绍
        introTv = (TextView)findViewById(R.id.d_intro);
        introTv.setText(cookBook.getImtro());

        //实现菜标签
        flowLayout = (FlowLayout)findViewById(R.id.flowlayout);
        String tags = cookBook.getTags();
        String[] array = tags.split(";");
        for(String s : array){
            TextView tv =new TextView(this);
            tv.setText(s);
            tv.setTextColor(Color.RED);
            tv.setBackgroundColor(Color.YELLOW);
            flowLayout.addView(tv);
        }

        //实现主料
        mainListView =(ListView)findViewById(R.id.mainListView);
        mainList = cookBook.getIngredients().split(";");
        mainListAdapter = new MainListAdapter();
        int count1 = mainListAdapter.getCount();
        int h1 = DensityUtil.dip2px(this,50)*count1;
        mainListView.getLayoutParams().height=h1;
        mainListView.setAdapter(mainListAdapter);

        //实现辅料
        fuListView =(ListView)findViewById(R.id.fuListView);
        fuList = cookBook.getBurden().split(";");
        fuListAdapter = new FuListAdapter();
        int count2 = fuListAdapter.getCount();
        int h2 = DensityUtil.dip2px(this,50)*count2;
        fuListView.getLayoutParams().height=h2;
        fuListView.setAdapter(fuListAdapter);

        //实现步骤
        setplistView = (ListView)findViewById(R.id.steplistView);
        stepList = cookBook.getSteps();
        stepListAdapter = new StepListAdapter();
        int count3 = stepListAdapter.getCount();
        int h3 = DensityUtil.dip2px(this,285)*count3;
        setplistView.getLayoutParams().height=h3;
        setplistView.setAdapter(stepListAdapter);
    }

    //主食材适配器
    class MainListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mainList.length;
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
                myView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.detail_listview_cookbook_item,null);
            }else {
                myView = convertView;
            }

            TextView leftTv= (TextView)myView.findViewById(R.id.left);
            TextView rightTv= (TextView)myView.findViewById(R.id.right);
            String items = mainList[position];
            String left = items.split(",")[0];
            String right = items.split(",")[1];
            leftTv.setText(left);
            rightTv.setText(right);
            return myView;
        }
    }

    //调料适配器
    class FuListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return fuList.length;
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
                myView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.detail_listview_cookbook_item,null);
            }else {
                myView = convertView;
            }

            TextView leftTv= (TextView)myView.findViewById(R.id.left);
            TextView rightTv= (TextView)myView.findViewById(R.id.right);
            String items = fuList[position];
            String left = items.split(",")[0];
            String right = items.split(",")[1];
            leftTv.setText(left);
            rightTv.setText(right);
            return myView;
        }
    }

    //详细制作步骤适配器
    class StepListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return stepList.size();
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
            View myview;
            if(convertView == null){
                myview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.detail_step_item,null);
            }else {
                myview = convertView;
            }
            TextView num = (TextView)myview.findViewById(R.id.stepnum);
            ImageView img = (ImageView)myview.findViewById(R.id.stepimg);
            TextView tv = (TextView)myview.findViewById(R.id.steptv);

            Step step = stepList.get(position);
            num.setText("第"+(position+1)+"步");
            Picasso.with(getApplicationContext()).load(step.getImg()).into(img);
            Log.i("步骤",step.getStep());
            Log.i("长度",stepList.size()+"");
            tv.setText(step.getStep());
            return myview;
        }
    }
}
