package com.study.mycookbook.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.squareup.picasso.Picasso;
import com.study.mycookbook.DetailActivity;
import com.study.mycookbook.R;
import com.study.mycookbook.category.CategoryPresenter;
import com.study.mycookbook.category.CategoryPresenterImpl;
import com.study.mycookbook.category.CategoryView;
import com.study.mycookbook.category.beans.Category;
import com.study.mycookbook.category.beans.Category1;
import com.study.mycookbook.category.beans.Category2;
import com.study.mycookbook.cookbook.CookBookPresenter;
import com.study.mycookbook.cookbook.CookBookPresenterImpl;
import com.study.mycookbook.cookbook.CookBookView;
import com.study.mycookbook.cookbook.beans.CookBook;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryView, CookBookView {

    Category category;
    CategoryPresenter categoryPresenter;
    LinearLayout treeContainer;
    ListView listView;
    ProgressBar pb;
    List<CookBook> cookBookList;
    CookBookPresenter cookBookPresenter;
    ListViewAdapter listViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryPresenter = new CategoryPresenterImpl(getActivity(),this);
        categoryPresenter.getCategory();
        cookBookPresenter = new CookBookPresenterImpl(this);
        cookBookList = new ArrayList<>();
        listViewAdapter = new ListViewAdapter();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category,container,false);
        initView(view);
        pb.setVisibility(View.VISIBLE);
        return view;
    }

    public void initView(View view){
        pb = (ProgressBar)view.findViewById(R.id.pb);
        listView = (ListView)view.findViewById(R.id.listview_category);
        treeContainer = (LinearLayout)view.findViewById(R.id.tree_container);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CookBook cookBook = cookBookList.get(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("cookbook", cookBook);
                startActivity(intent);
            }
        });
    }

    /**
     * 创建树形菜单类型方法
     */
    private void initTree(){
        List<Category1> list = this.category.getResult();
        TreeNode root = TreeNode.root();
        for(Category1 c1:list){
            String name = c1.getName();
            //TreeNode parent = new TreeNode(name);

            IconTreeItem nodeItem = new IconTreeItem();
            nodeItem.icon = R.drawable.root_icon;
            nodeItem.text = name;
            nodeItem.isSub = false;
            TreeNode parent = new TreeNode(nodeItem).setViewHolder(new MyHolder(getActivity()));

            List<Category2> list2 = c1.getList();
            for(final Category2 c2:list2){
                String sub_name = c2.getName();
                //TreeNode child = new TreeNode(sub_name);

                IconTreeItem nodeItem2 = new IconTreeItem();
                nodeItem2.icon = R.drawable.sub_icon;
                nodeItem2.text = sub_name;
                nodeItem2.isSub = true;
                TreeNode child = new TreeNode(nodeItem2).setViewHolder(new MyHolder(getActivity()));

                child.setClickListener(new TreeNode.TreeNodeClickListener() {
                    @Override
                    public void onClick(TreeNode node, Object value) {
                        String id = c2.getId();
                        cookBookPresenter.getCookBookListById(getActivity(),id);
                    }
                });

                parent.addChildren(child);
            }
            root.addChild(parent);
        }//c1
        AndroidTreeView tView = new AndroidTreeView(getActivity(), root);
        treeContainer.addView(tView.getView());
    }

    class IconTreeItem {
        public int icon;
        public String text;
        public boolean isSub;
    }

    class MyHolder extends TreeNode.BaseNodeViewHolder<IconTreeItem> {

        public MyHolder(Context context) {
            super(context);
        }

        @Override
        public View createNodeView(TreeNode node, IconTreeItem value) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.list_category_item, null, false);
            if(value.isSub){
                view.setPadding(52,20,20,20);
            }
            TextView tvValue = (TextView) view.findViewById(R.id.tv);
            ImageView ivValue = (ImageView) view.findViewById(R.id.iv);
            tvValue.setText(value.text);
            ivValue.setImageResource(value.icon);
            return view;
        }


    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
        pb.setVisibility(View.GONE);
        initTree();
    }

    //此方法为Cookbookview
    @Override
    public void setCookBookList(List<CookBook> list) {
        this.cookBookList = list;
        pb.setVisibility(View.GONE);
        listViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void setFail() {
        Toast.makeText(getActivity(),"fail",Toast.LENGTH_LONG).show();
    }

    /**
     * 构建适配器，用于Listview显示菜单列表信息
     */
    class ListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return cookBookList.size();
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
                myview = LayoutInflater.from(getActivity()).inflate(R.layout.list_category_cookbook_item,null);
            }else{
                myview = convertView;
            }
            ImageView iv = (ImageView)myview.findViewById(R.id.c_imageView);
            TextView titleTv = (TextView)myview.findViewById(R.id.c_title);
            TextView introTv = (TextView)myview.findViewById(R.id.c_intro);
            CookBook cookBook = cookBookList.get(position);
            titleTv.setText((cookBook.getTitle()));
            introTv.setText(cookBook.getImtro());
            String url = cookBook.getAlbums().get(0);
            Picasso.with(getActivity()).load(url).into(iv);

            return myview;
        }
    }

}
