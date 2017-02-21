package com.bwie.demo;


import android.content.Context;

import android.support.v7.widget.RecyclerView;



import android.view.View;
import android.view.ViewGroup;



import android.widget.AdapterView;

import android.widget.BaseAdapter;


import android.widget.ImageView;
import android.widget.TextView;


import android.widget.Toast;


import com.bwie.bean.Bean;


import com.lidroid.xutils.BitmapUtils;


import java.util.List;

/**
 * 类描述:
 * 作者：陈文梦
 * 时间:2017/2/21 14:36
 * 邮箱:18310832074@163.com
 */

public
class
MyAdapter extends RecyclerView.Adapter{


    private List<Bean.RsBean.ChildrenBeanX> childrenBeanXList;
    private Context context;


    public MyAdapter(List<Bean.RsBean.ChildrenBeanX> childrenBeanXList, Context context) {
        this.childrenBeanXList = childrenBeanXList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextViewViewholder textViewViewholder=new TextViewViewholder(View.inflate(context,R.layout.right_item,null));
        return textViewViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TextViewViewholder viewholder= (TextViewViewholder) holder;
        viewholder.textView.setText(childrenBeanXList.get(position).getDirName());
        viewholder.gridView.setAdapter(new Badapter(childrenBeanXList.get(position).getChildren()));

    }

    @Override
    public int getItemCount() {
        return childrenBeanXList.size();
    }


    class TextViewViewholder extends RecyclerView.ViewHolder{

        TextView textView;
        MyGridView gridView;
        public TextViewViewholder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.textview);
            gridView= (MyGridView) itemView.findViewById(R.id.mygridview);
        }
    }

    class Badapter extends BaseAdapter{

        private List<Bean.RsBean.ChildrenBeanX.ChildrenBean> childrenBeanList;


        public Badapter(List<Bean.RsBean.ChildrenBeanX.ChildrenBean> childrenBeanList) {
            this.childrenBeanList = childrenBeanList;
        }

        @Override
        public int getCount() {
            return childrenBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return childrenBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=null;
            if(convertView==null){

                viewHolder=new ViewHolder();
                convertView=View.inflate(context,R.layout.gridview_item,null);
                viewHolder.imageView= (ImageView) convertView.findViewById(R.id.grid_image1);
                viewHolder.textView= (TextView) convertView.findViewById(R.id.grid_item1);
                convertView.setTag(viewHolder);
            }else {

                viewHolder= (ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(childrenBeanList.get(position).getDirName());
            new BitmapUtils(context).display(viewHolder.imageView,childrenBeanList.get(position).getImgApp());
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,childrenBeanList.get(position).getDirName(),Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }

        class ViewHolder{

            ImageView imageView;
            TextView textView;

        }
    }

}
