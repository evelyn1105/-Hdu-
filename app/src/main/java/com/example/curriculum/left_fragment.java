package com.example.curriculum;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class left_fragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{

    public interface MyListener{
        void sendMessage(String message);
    }

    private String day[]= {"周一","周二","周三","周四","周五","周六","周日"};
    private List<Map<String, Object>> data;
    private ListView listView;
    private MyAdapter myAdapter;
    private MyListener myListener;
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.left_fragment, viewGroup, false);
        data = getData();
        //布局
        listView = (ListView) view.findViewById(R.id.list_view);
        myAdapter = new MyAdapter(getActivity(), data);
        listView.setAdapter(myAdapter);
        //点击事件
        listView.setOnItemClickListener(this);
        linearLayout = (LinearLayout) view.findViewById(R.id.layout_btn);
        linearLayout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        myAdapter.selectedItemPosition(position);
        myAdapter.notifyDataSetChanged();
        switch (position) {
            case 0:
                myListener.sendMessage(day[0]);
                break;
            case 1:
                myListener.sendMessage(day[1]);
                break;
            case 2:
                myListener.sendMessage(day[2]);
                break;
            case 3:
                myListener.sendMessage(day[3]);
                break;
            case 4:
                myListener.sendMessage(day[4]);
                break;
            case 5:
                myListener.sendMessage(day[5]);
                break;
            case 6:
                myListener.sendMessage(day[6]);
                break;
            default:
                break;
        }
    }

    public void  onAttach(Context context) {
        super.onAttach(context);
        myListener = (MyListener)getActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_btn:
                myListener.sendMessage("icon");
                myAdapter.selectedItemPosition(-1);
                myAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i<7; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("day", day[i]);
            switch (i){
                case 0:
                    map.put("day_icon", R.drawable.mon);
                    break;
                case 1:
                    map.put("day_icon", R.drawable.tue);
                    break;
                case 2:
                    map.put("day_icon", R.drawable.wed);
                    break;
                case 3:
                    map.put("day_icon", R.drawable.thu);
                    break;
                case 4:
                    map.put("day_icon", R.drawable.fri);
                    break;
                case 5:
                    map.put("day_icon", R.drawable.sat);
                    break;
                case 6:
                    map.put("day_icon", R.drawable.sun);
                    break;
                default:
                    break;
            }
            list.add(map);
        }
        return list;
    }
}