package com.jasoncasebier.androidtestapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

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

        TextView videoTextView = findViewById(R.id.textView8);
        videoTextView.setText(Html.fromHtml("<font color='#9a9a9a'><b>Source: </b>" +
                "<i>https://www.pixabay.com/</i></font>"));
        VideoView videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(VideoActivity.this);
        videoView.setVideoURI(Uri.parse("https://player.vimeo.com/external/178513668.hd.mp4?s=303b5872a66b955a7471d23c87940bff2c1e5047&profile_id=174&oauth2_token_id=57447761"));
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000'>Test 2: Video Stream</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(2).setChecked(true);
    }
}
