package com.example.curriculum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements left_fragment.MyListener {
    private String msg = "icon";
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager.beginTransaction().add(R.id.right, new right_fragment()).addToBackStack(null).commit();

    }

    @Override
    public void sendMessage(String message) {
        Log.d("TAG", message);
        msg = message;
        if(msg.equals("icon")){
            Fragment right = new right_fragment();
            fragmentManager.beginTransaction().replace(R.id.right, right).addToBackStack(null).commit();
        }else {
            if(isScreen(this)){
                Fragment right_show = new right_fragment_show();
                Bundle bundle = new Bundle();
                bundle.putString("index", msg);
                right_show.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.right, right_show).addToBackStack(null).commit();
            }
            else {
                Fragment right_show_landscape = new right_fragment_show_landscape();
                Bundle bundle = new Bundle();
                bundle.putString("index", msg);
                right_show_landscape.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.right, right_show_landscape).addToBackStack(null).commit();
            }
        }
    }

    public static boolean isScreen(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(!msg.equals("icon")){
            if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
                Fragment right_show_landscape = new right_fragment_show_landscape();
                Bundle bundle = new Bundle();
                bundle.putString("index", msg);
                right_show_landscape.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.right, right_show_landscape).addToBackStack(null).commit();
            }
            else {
                Fragment right_show = new right_fragment_show();
                Bundle bundle = new Bundle();
                bundle.putString("index", msg);
                right_show.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.right, right_show).addToBackStack(null).commit();
            }
        }
    }


}