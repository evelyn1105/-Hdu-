package com.example.curriculum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyAdapter_show extends BaseAdapter {
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyAdapter_show(Context context, List<Map<String, Object>> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public class Info{
        public TextView textView_idx;
        public TextView textView_time;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Info info = new Info();
        convertView = layoutInflater.inflate(R.layout.list_layout_show, null);
        info.textView_idx = (TextView) convertView.findViewById(R.id.idx);
        info.textView_time = (TextView) convertView.findViewById(R.id.time);
        info.textView_idx.setText((String) data.get(position).get("idx"));
        info.textView_time.setText((String) data.get(position).get("time"));
        return convertView;
    }
}
