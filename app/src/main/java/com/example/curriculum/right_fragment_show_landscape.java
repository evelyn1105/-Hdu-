package com.example.curriculum;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class right_fragment_show_landscape extends Fragment {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;
    private String sql;
    private String day;

    private ArrayList<String> c_name = new ArrayList<>();
    private ArrayList<String> begin = new ArrayList<>();
    private ArrayList<String> finish = new ArrayList<>();
    private String[] time = {"1~2", "3~4", "5", "6~7", "8~9", "10~11", "12"};

    private ListView listView_show_landscape;
    private TextView course_1_landscape;
    private TextView course_2_landscape;
    private TextView course_3_landscape;
    private TextView course_4_landscape;
    private TextView course_5_landscape;
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.right_fragment_show_landscape, viewGroup, false);

        listView_show_landscape = (ListView) view.findViewById(R.id.list_view_course);

        Bundle bundle = getArguments();
        day = bundle.getString("index");
        Log.d("TAG 2 => ", day);

        listView_show_landscape = (ListView) view.findViewById(R.id.list_view_course);
        MyAdapter_show_landscape myAdapter_show_landscape= new MyAdapter_show_landscape(getActivity(), time);
        listView_show_landscape.setAdapter(myAdapter_show_landscape);

        databaseHelper = new DatabaseHelper(getActivity(), "myCourse.db", null, 1);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        String string_show = "";
        sql = "select * from course";
        String[] args;
        if (day != null && !day.equals("")) {
            sql = sql + " where day=?";
            args = new String[]{day};
            cursor = sqLiteDatabase.rawQuery(sql, args);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                c_name.add(cursor.getString(cursor.getColumnIndex("cname")));
                begin.add(cursor.getString(cursor.getColumnIndex("begin_time")));
                finish.add(cursor.getString(cursor.getColumnIndex("finish_time")));
                cursor.moveToNext();
            }
        }

        course_1_landscape = (TextView)view.findViewById(R.id.course_1_landscape);
        course_2_landscape = (TextView)view.findViewById(R.id.course_2_landscape);
        course_3_landscape = (TextView)view.findViewById(R.id.course_3_landscape);
        course_4_landscape = (TextView)view.findViewById(R.id.course_4_landscape);
        course_5_landscape = (TextView)view.findViewById(R.id.course_5_landscape);
        for(int i=0; i<c_name.size(); i++){
            switch (begin.get(i)){
                case "1":
                    course_1_landscape.setText(c_name.get(i));
                    LinearLayout.LayoutParams c1 = new LinearLayout.LayoutParams(course_1_landscape.getLayoutParams());
                    c1.height = 102;
                    c1.setMargins(20, 0, 20, 0);
                    course_1_landscape.setLayoutParams(c1);
                    course_1_landscape.setBackground(getResources().getDrawable(R.drawable.textview_bg));
                    break;
                case "3":
                    course_2_landscape.setText(c_name.get(i));
                    LinearLayout.LayoutParams c2 = new LinearLayout.LayoutParams(course_2_landscape.getLayoutParams());
                    if(finish.get(i).equals("4")){
                        c2.height = 102;
                    }
                    else if(finish.get(i).equals("5")){
                        c2.height = 230;
                    }
                    c2.setMargins(20, 0, 20, 0);
                    course_2_landscape.setLayoutParams(c2);
                    course_2_landscape.setBackground(getResources().getDrawable(R.drawable.textview_bg));
                    break;
                case "6":
                    course_3_landscape.setText(c_name.get(i));
                    LinearLayout.LayoutParams c3 = new LinearLayout.LayoutParams(course_3_landscape.getLayoutParams());
                    c3.height = 102;
                    c3.setMargins(20, 0, 20, 0);
                    course_3_landscape.setLayoutParams(c3);
                    course_3_landscape.setBackground(getResources().getDrawable(R.drawable.textview_bg));
                    break;
                case "8":
                    course_4_landscape.setText(c_name.get(i));
                    LinearLayout.LayoutParams c4 = new LinearLayout.LayoutParams(course_4_landscape.getLayoutParams());
                    c4.height = 102;
                    c4.setMargins(20, 0, 20, 0);
                    course_4_landscape.setLayoutParams(c4);
                    course_4_landscape.setBackground(getResources().getDrawable(R.drawable.textview_bg));
                    break;
                case "10":
                    course_5_landscape.setText(c_name.get(i));
                    LinearLayout.LayoutParams c5 = new LinearLayout.LayoutParams(course_5_landscape.getLayoutParams());
                    if(finish.get(i).equals("11")){
                        c5.height = 102;
                    }
                    else if(finish.get(i).equals("12")){
                        c5.height = 230;
                    }
                    c5.setMargins(20, 0, 20, 0);
                    course_5_landscape.setLayoutParams(c5);
                    course_5_landscape.setBackground(getResources().getDrawable(R.drawable.textview_bg));
                    break;
            }
        }

        return view;
    }
}
