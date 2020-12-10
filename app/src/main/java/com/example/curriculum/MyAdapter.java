package com.example.curriculum;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    private int selected = -1;


    public MyAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public class Info {
        public TextView textView_day;
        public ImageView imageView_icon;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void selectedItemPosition(int position) {
        this.selected = position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Info info = new Info();
        convertView = layoutInflater.inflate(R.layout.list_layout, null);
        info.textView_day = (TextView) convertView.findViewById(R.id.day);
        info.imageView_icon = (ImageView) convertView.findViewById(R.id.day_icon);
        //设置数据
        info.textView_day.setText((String) data.get(position).get("day"));
        info.imageView_icon.setImageResource((Integer) data.get(position).get("day_icon"));

        if(position == selected){
            convertView.setBackgroundColor(context.getResources().getColor(R.color.pink_sel));
        }
        else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.pink_bg));
        }
        return convertView;
    }

}



//public class MyAdapter extends BaseAdapter {
//
//    private List<Map<String, Object>> data;
//    private LayoutInflater layoutInflater;
//    private Context context;
//
//
//    public MyAdapter(Context context, List<Map<String, Object>> data) {
//        this.context = context;
//        this.data = data;
//        this.layoutInflater = LayoutInflater.from(context);
//    }
//
//    public class Info {
//        public TextView textView_day;
//        public ImageView imageView_icon;
//    }
//
//    @Override
//    public int getCount() {
//        return data.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return data.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Info info = new Info();
//        convertView = layoutInflater.inflate(R.layout.list_layout, null);
//        info.textView_day = (TextView) convertView.findViewById(R.id.day);
//        info.imageView_icon = (ImageView) convertView.findViewById(R.id.day_icon);
//        //设置数据
//        info.textView_day.setText((String) data.get(position).get("day"));
//        info.imageView_icon.setImageResource((Integer) data.get(position).get("day_icon"));
//        return convertView;
//    }
//
//}
