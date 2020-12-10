package com.example.curriculum;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class right_fragment_show extends Fragment {
    private ListView listView_show;
    private String idx[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private String time[] = {"8:05-8:50", "8:55-9:40", "10:00-10:45", "10:50-11:35", "11:40-12:25", "13:30-14:15", "14:20-15:05", "15:20-16:05", "16:10-16:55", "18:30-19:15", "19:20-20:05", "20:10-20:55"};

    private List<Map<String, Object>> data;

    private DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;
    private String sql;
    private String day;

    //test begin
    private ArrayList<String> c_name = new ArrayList<>();
    private ArrayList<String> begin = new ArrayList<>();
    private ArrayList<String> finish = new ArrayList<>();
    private List<Map<String, Object>> course;
    private TextView course_1;
    private TextView course_2;
    private TextView course_3;
    private TextView course_4;
    private TextView course_5;
    //test over

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.right_fragment_show, viewGroup, false);
        data = getDate();

        Bundle bundle = getArguments();
        day = bundle.getString("index");
        Log.d("TAG => ", day);

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
//                String course_name = cursor.getString(cursor.getColumnIndex("cname"));
//                String course_day = cursor.getString(cursor.getColumnIndex("day"));
//                String course_begin = cursor.getString(cursor.getColumnIndex("begin_time"));
//                String course_finish = cursor.getString(cursor.getColumnIndex("finish_time"));
//                string_show = string_show + course_name + " " + course_day + " " + course_begin + " " + course_finish + "\n";
                c_name.add(cursor.getString(cursor.getColumnIndex("cname")));
                begin.add(cursor.getString(cursor.getColumnIndex("begin_time")));
                finish.add(cursor.getString(cursor.getColumnIndex("finish_time")));
                cursor.moveToNext();
            }
            Log.d(day, string_show);
        }



        listView_show = (ListView) view.findViewById(R.id.list_view_time);
        MyAdapter_show myAdapter_show = new MyAdapter_show(getActivity(), data);
        listView_show.setAdapter(myAdapter_show);

        //test begin
        course_1 = (TextView)view.findViewById(R.id.course_1);
        course_2 = (TextView)view.findViewById(R.id.course_2);
        course_3 = (TextView)view.findViewById(R.id.course_3);
        course_4 = (TextView)view.findViewById(R.id.course_4);
        course_5 = (TextView)view.findViewById(R.id.course_5);
        for(int i=0; i<c_name.size(); i++){
            switch (begin.get(i)){
                case "1":
                    course_1.setText(c_name.get(i));
                    LinearLayout.LayoutParams c1 = new LinearLayout.LayoutParams(course_1.getLayoutParams());
                    c1.height = 240;
                    c1.setMargins(20, 0, 20, 0);
                    course_1.setLayoutParams(c1);
                    course_1.setBackground(getResources().getDrawable(R.drawable.textview_bg));
                    break;
                case "3":
                    course_2.setText(c_name.get(i));
                    LinearLayout.LayoutParams c2 = new LinearLayout.LayoutParams(course_2.getLayoutParams());
                    if(finish.get(i).equals("4")){
                        c2.height = 240;
                    }
                    else if(finish.get(i).equals("5")){
                        c2.height = 360;
                    }
                    c2.setMargins(20, 0, 20, 0);
                    course_2.setLayoutParams(c2);
                    course_2.setBackground(getResources().getDrawable(R.drawable.textview_bg));
                    break;
                case "6":
                    course_3.setText(c_name.get(i));
                    LinearLayout.LayoutParams c3 = new LinearLayout.LayoutParams(course_3.getLayoutParams());
                    c3.height = 240;
                    c3.setMargins(20, 0, 20, 0);
                    course_3.setLayoutParams(c3);
                    course_3.setBackground(getResources().getDrawable(R.drawable.textview_bg));
                    break;
                case "8":
                    course_4.setText(c_name.get(i));
                    LinearLayout.LayoutParams c4 = new LinearLayout.LayoutParams(course_4.getLayoutParams());
                    c4.height = 240;
                    c4.setMargins(20, 0, 20, 0);
                    course_4.setLayoutParams(c4);
                    course_4.setBackground(getResources().getDrawable(R.drawable.textview_bg));
                    break;
                case "10":
                    course_5.setText(c_name.get(i));
                    LinearLayout.LayoutParams c5 = new LinearLayout.LayoutParams(course_5.getLayoutParams());
                    if(finish.get(i).equals("11")){
                        c5.height = 240;
                    }
                    else if(finish.get(i).equals("12")){
                        c5.height = 360;
                    }
                    c5.setMargins(20, 0, 20, 0);
                    course_5.setLayoutParams(c5);
                    course_5.setBackground(getResources().getDrawable(R.drawable.textview_bg));
                    break;
            }
        }
        //test over

        return view;
    }



    private List<Map<String, Object>> getDate() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i<12; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("idx", idx[i]);
            map.put("time", time[i]);
            list.add(map);
        }
        return list;
    }
}
