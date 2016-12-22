package com.zhang.tulingdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mr.Z on 2016/10/19 0019.
 */

public class TextAdapter extends BaseAdapter {

    private Context context;
    private List<ListData> lists;

    public TextAdapter(Context context, List<ListData> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        int type = getItemViewType(position);
//        ViewHolder1 holder1;
//        ViewHolder2 holder2;
//        if (convertView == null) {
//            switch (type) {
//                case ListData.RECEIVE:
//                    convertView = View.inflate(context, R.layout.leftitem, null);
//                    holder1 = new ViewHolder1(convertView);
//                    holder1.time.setText(lists.get(position).getTime());
//                    holder1.tv.setText(lists.get(position).getContent());
//                    convertView.setTag(holder1);
//                    break;
//                case ListData.SEND:
//                    convertView = View.inflate(context, R.layout.rightitem, null);
//                    holder2 = new ViewHolder2(convertView);
//                    holder2.time.setText(lists.get(position).getTime());
//                    holder2.tv.setText(lists.get(position).getContent());
//                    convertView.setTag(holder2);
//                    break;
//            }
//        } else {
//            switch (type) {
//                case ListData.RECEIVE:
//                    holder1 = (ViewHolder1) convertView.getTag();
//                    holder1.time.setText(lists.get(position).getTime());
//                    holder1.tv.setText(lists.get(position).getContent());
//                    break;
//                case ListData.SEND:
//                    holder2 = (ViewHolder2) convertView.getTag();
//                    holder2.time.setText(lists.get(position).getTime());
//                    holder2.tv.setText(lists.get(position).getContent());
//                    break;
//            }
//        }


        ViewHolder1 holder1;
        if (convertView ==null ){
            switch (getItemViewType(position)){
                case ListData.RECEIVE:
                    convertView = View.inflate(context, R.layout.leftitem, null);
                    break;
                case ListData.SEND:
                    convertView = View.inflate(context, R.layout.rightitem, null);
                    break;
            }
            holder1 = new ViewHolder1(convertView);
            convertView.setTag(holder1);
        }else {
            holder1 = (ViewHolder1) convertView.getTag();
        }
        holder1.time.setText(lists.get(position).getTime());
        holder1.tv.setText(lists.get(position).getContent());

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return lists.get(position).getFlag();
    }

    private static class ViewHolder1 {
        TextView time;
        TextView tv;

        ViewHolder1(View v) {
            time = (TextView) v.findViewById(R.id.tvTime);
            tv = (TextView) v.findViewById(R.id.tv);
        }

    }

    private static class ViewHolder2 {
        TextView time;
        TextView tv;

        ViewHolder2(View v) {
            time = (TextView) v.findViewById(R.id.tvTime);
            tv = (TextView) v.findViewById(R.id.tv);
        }
    }
}
