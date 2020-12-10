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

public class MyAdapter_show_landscape extends BaseAdapter {
    private String[] data;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyAdapter_show_landscape(Context context, String[] data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public class Info {
        public TextView textView_time_landscape;

    }
    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Info info = new Info();
        convertView = layoutInflater.inflate(R.layout.list_layout_show_landscape, null);     //定义的xml文件
        info. textView_time_landscape= (TextView) convertView.findViewById(R.id.time_landscape);     //定义的xml文件里的控件id
        //设置数据
        info.textView_time_landscape.setText((String) data[position]);
        return convertView;
    }

}
