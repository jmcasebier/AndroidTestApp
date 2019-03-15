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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WebActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_info:
                    WebActivity.super.onBackPressed();
                    break;
                case R.id.navigation_location:
                    finish();
                    intent = new Intent(getApplicationContext(), LocationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_video:
                    finish();
                    intent = new Intent(getApplicationContext(), VideoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_web:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String[] listviewTitles = new String[] {
                "Read the Expo documentation", "Read the Android documentation",
                "Austin Peay State University"
        };
        int[] listviewImages = new int[] {
                R.drawable.expo_logo,
                R.drawable.android_logo,
                R.drawable.austin_peay
        };
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 3; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("listview_image", Integer.toString(listviewImages[i]));
            hashMap.put("listview_title", listviewTitles[i]);
            aList.add(hashMap);
        }
        String[] from = {"listview_image", "listview_title"};
        int[] to = {R.id.listview_image, R.id.listview_text};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_activity,
                from, to);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url;
                Intent intent = new Intent(getApplicationContext(), BrowserActivity.class);
                if (i == 0) {
                    url = "http://docs.expo.io/versions/latest/";
                } else if (i == 1) {
                    url = "http://developer.android.com/docs/";
                } else {
                    url = "http://apsu.edu/";
                }
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000'>Test 3: Web Browser</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(3).setChecked(true);
    }
}
