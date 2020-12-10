package com.example.curriculum;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class right_fragment extends Fragment implements View.OnClickListener{
    private EditText cname_editText;
    private Button button_create;
    private Button button_delete;

    private Spinner day_spinner;
    private Spinner begin_spinner;
    private Spinner finish_spinner;
    private String day;
    private String begin_time;
    private String finish_time;
    private ArrayAdapter<String> arrayAdapter_day;
    private ArrayAdapter<String> arrayAdapter_begin;
    private ArrayAdapter<String> arrayAdapter_finish;
    private int position_day = 0;
    private int position_begin = 0;
    private int position_finish = 0;

    private String[] day_sel = new String[]{"时间", "周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private String[] begin_time_sel = new String[]{" ", "1", "3", "6", "8", "10"};
    private String[][] finish_time_sel = {{" "}, {"2"}, {"4", "5"}, {"7"}, {"9"}, {"11", "12"}};

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    String sql;

    public void  onAttach(Context context) {
        super.onAttach(context);
        Log.d("TAG", "on Attach");
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.right_fragment, viewGroup, false);

        Log.d("TAG =>", "Create view");

        cname_editText = (EditText) view.findViewById(R.id.c_name);
        button_create = (Button) view.findViewById(R.id.create_course);
        button_delete = (Button) view.findViewById(R.id.delete_course);
        button_create.setOnClickListener(this);
        button_delete.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(getActivity(), "myCourse.db", null, 1);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        setSpinner(view);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("temp_text", Context.MODE_PRIVATE);
        cname_editText.setText(sharedPreferences.getString("c_name", ""));
        day_spinner.setSelection(sharedPreferences.getInt("day" ,0), true);
        begin_spinner.setSelection(sharedPreferences.getInt("begin", 0), true);
        finish_spinner.setSelection(sharedPreferences.getInt("finish", 0), true);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        return view;
    }

    @Override
    public void onClick(View v) {
        String cname = cname_editText.getText().toString();
        switch (v.getId()) {
            case R.id.delete_course:
                sql = "delete from course where cname = '" + cname +
                        "' and day = '" + day +
                        "' and begin_time = '" + begin_time +
                        "' and finish_time = '" + finish_time + "'";
                if (check_if_null(cname)){
                    if(check_if_exit(cname, "del")){
                        sqLiteDatabase.execSQL(sql);
                        cname_editText.setText("");
                        day_spinner.setSelection(0, true);
                        begin_spinner.setSelection(0, true);
                        finish_spinner.setSelection(0, true);
                        Toast.makeText(getActivity(), "删除课程成功", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getActivity(), "该课程不存在", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.create_course:
                sql = "insert into course(cname, day, begin_time, finish_time) " +
                        "values('" + cname + "', '" + day + "', '" + begin_time + "', '" + finish_time + "')";
                if(check_if_null(cname)){
                    if(!check_if_exit(cname, "add")){
                        sqLiteDatabase.execSQL(sql);
                        cname_editText.setText("");
                        day_spinner.setSelection(0, true);
                        begin_spinner.setSelection(0, true);
                        finish_spinner.setSelection(0, true);
                        Toast.makeText(getActivity(), "添加课程成功", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getActivity(), "该时间段有课", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void setSpinner(View view) {
        day_spinner = (Spinner) view.findViewById(R.id.day);
        begin_spinner = (Spinner) view.findViewById(R.id.begin);
        finish_spinner = (Spinner) view.findViewById(R.id.finish);

        arrayAdapter_day = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, day_sel);
        arrayAdapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day_spinner.setAdapter(arrayAdapter_day);
        day_spinner.setSelection(0, true);

        arrayAdapter_begin = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, begin_time_sel);
        arrayAdapter_begin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        begin_spinner.setAdapter(arrayAdapter_begin);
        begin_spinner.setSelection(0, true);

        arrayAdapter_finish = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, finish_time_sel[0]);
        arrayAdapter_finish.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        finish_spinner.setAdapter(arrayAdapter_finish);
        finish_spinner.setSelection(0, true);

        day_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position_day = position;
                day = ((TextView) view).getText().toString();
                Log.d("time", day);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        begin_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                begin_time = ((TextView) view).getText().toString();
                position_begin = position;
                Log.d("time", begin_time);
                arrayAdapter_finish = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, finish_time_sel[position]);
                arrayAdapter_finish.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                finish_spinner.setAdapter(arrayAdapter_finish);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        finish_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position_finish = position;
                finish_time = finish_time_sel[position_begin][position];
                Log.d("time", finish_time);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Boolean check_if_exit(String cname, String flag) {
        String string_flag = "";
        String check_sql = "select * from course";
        String[] args;
        if(flag.equals("del")){
            check_sql = check_sql + " where cname=? and day=? and begin_time=? and finish_time=?";
            args = new String[]{cname, day, begin_time, finish_time};
        }
        else {
            check_sql = check_sql + " where day=? and begin_time=?";
            args = new String[]{day, begin_time};
        }
        cursor = sqLiteDatabase.rawQuery(check_sql, args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String course_name = cursor.getString(cursor.getColumnIndex("cname"));
            String course_begin = cursor.getString(cursor.getColumnIndex("begin_time"));
            String course_finish = cursor.getString(cursor.getColumnIndex("finish_time"));
            string_flag = string_flag + course_name + course_begin + course_finish;
            cursor.moveToNext();
        }
        Log.d("flag => ", string_flag);
        if(!string_flag.equals("")){
            return true;
        }
        else {
            return false;
        }
    }

    private Boolean check_if_null(String cname) {
        if (cname != null && !cname.equals("") && day != null && begin_time != null && finish_time != null) {
            return true;
        } else if (cname == null || cname.equals("")) {
            Toast.makeText(getActivity(), "请输入课程名称", Toast.LENGTH_LONG).show();
            return false;
        } else {
            Toast.makeText(getActivity(), "请选择上课时间", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("temp_text", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("c_name", cname_editText.getText().toString());
        editor.putInt("day", position_day);
        editor.putInt("begin", position_begin);
        editor.putInt("finish", position_finish);
        editor.commit();
        Log.d("TAG", "destroy view");
    }
}
