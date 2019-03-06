package com.jasoncasebier.androidtestapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;

public class VideoActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_info:
                    VideoActivity.super.onBackPressed();
                    break;
                case R.id.navigation_location:
                    finish();
                    intent = new Intent(getApplicationContext(), LocationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_video:
                    break;
                case R.id.navigation_web:
                    finish();
                    intent = new Intent(getApplicationContext(), WebActivity.class);
                    startActivity(intent);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000'>Test 2</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(2).setChecked(true);
    }
}
