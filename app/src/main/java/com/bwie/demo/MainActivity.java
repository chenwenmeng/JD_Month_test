package com.bwie.demo;

import android.graphics.Bitmap;


import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.graphics.Rect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;

import android.view.View;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;

import com.bwie.bean.Bean;

import com.bwie.util.MyFromJson;
import com.bwie.view.RecyclerViewDivider;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import java.util.List;

public
class
MainActivity extends AppCompatActivity {


    private static final String TAG = "Main";
    private RecyclerView listRecyclerView;
    private RecyclerView gridRecyclerView1;
    private TextView textView1;
    private boolean flag;
    private boolean isFlag = true;
    private int num = 0;
    private String path = "http://mock.eoapi.cn/success/4q69ckcRaBdxhdHySqp2Mnxdju5Z8Yr4";
    private List<Bean.RsBean> rsBeanList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private int spacingInPixels = -9;
    private int spacingInPixelsleft = 0;
    private int spacingInPixelstop = 15;
    private List<Bean.RsBean.ChildrenBeanX> childrenBeanXList=new ArrayList<>();


    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据
        initcontrol();
        //请求数据
        fromGson();

    }

    public void fromGson() {

        MyfromJson_string myfromJson_string = new MyfromJson_string();
        myfromJson_string.getdate(path);

    }

    class MyfromJson_string extends MyFromJson {


        @Override
        public void myfromjson_String(String s) {

            Bean bean = new Gson().fromJson(s, Bean.class);
            rsBeanList.addAll(bean.getRs());
            for (int i = 0; i < rsBeanList.size(); i++) {

                stringList.add(rsBeanList.get(i).getDirName());

            }
            listRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels, spacingInPixelsleft, spacingInPixelstop));
            listRecyclerView.addItemDecoration(new RecyclerViewDivider(MainActivity.this, LinearLayoutManager.VERTICAL));

            final CommonAdapter commonAdapter = new CommonAdapter(MainActivity.this, R.layout.listr_item, stringList) {

                @Override
                protected void convert(ViewHolder holder, Object o, int position) {

                    if (flag && num == position) {
                        holder.setBackgroundColor(R.id.r, Color.parseColor("#dddddd"));
                        holder.setBackgroundColor(R.id.item_1, Color.parseColor("#ffaa33"));
                        holder.setTextColor(R.id.listr_item_title, Color.parseColor("#ffaa33"));
                    } else {

                        holder.setBackgroundColor(R.id.r, Color.parseColor("#ffffff"));
                        holder.setBackgroundColor(R.id.item_1, Color.parseColor("#ffffff"));
                        holder.setTextColor(R.id.listr_item_title, Color.parseColor("#000000"));
                    }
                    if (isFlag && num == 0) {
                        isFlag = false;
                        holder.setBackgroundColor(R.id.r, Color.parseColor("#dddddd"));
                        holder.setBackgroundColor(R.id.item_1, Color.parseColor("#ffaa33"));
                        holder.setTextColor(R.id.listr_item_title, Color.parseColor("#ffaa33"));
                    }
                    holder.setText(R.id.listr_item_title, stringList.get(position));
                }
            };

            listRecyclerView.setAdapter(commonAdapter);
            childrenBeanXList.addAll(rsBeanList.get(0).getChildren());
            myAdapter = new MyAdapter(childrenBeanXList,MainActivity.this);
            gridRecyclerView1.setAdapter(myAdapter);
            commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    flag = true;
                    num = position;
                    commonAdapter.notifyDataSetChanged();
                    childrenBeanXList.clear();
                    childrenBeanXList.addAll(rsBeanList.get(position).getChildren());
                    myAdapter.notifyDataSetChanged();
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
        }
    }

    private int spacingInPixelsleft2=0;
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;
        private int spaceleft;
        private int spacetop;


        public SpacesItemDecoration(int space, int spaceleft, int spacetop) {
            this.space = space;
            this.spaceleft = spaceleft;
            this.spacetop = spacetop;
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            super.getItemOffsets(outRect, itemPosition, parent);
            outRect.left = spaceleft;
            outRect.right = spaceleft;
            outRect.bottom = space;
            outRect.top = spacetop;

        }
    }

    public void initcontrol() {

        listRecyclerView = (RecyclerView) findViewById(R.id.listview_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listRecyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridRecyclerView1 = (RecyclerView) findViewById(R.id.gridview1_RecyclerView);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        gridRecyclerView1.setLayoutManager(linearLayoutManager1);

    }

}
