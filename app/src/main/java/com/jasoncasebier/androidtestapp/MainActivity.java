package com.jasoncasebier.androidtestapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_info:
                    getSupportActionBar().setTitle(Html.fromHtml("<font color='#000'>Information</font>"));
                    return true;
                case R.id.navigation_location:
                    getSupportActionBar().setTitle(Html.fromHtml("<font color='#000'>Test 1</font>"));
                    return true;
                case R.id.navigation_video:
                    getSupportActionBar().setTitle(Html.fromHtml("<font color='#000'>Test 2</font>"));
                    return true;
                case R.id.navigation_web:
                    getSupportActionBar().setTitle(Html.fromHtml("<font color='#000'>Test 3</font>"));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000'>Information</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
