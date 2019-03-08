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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_info:
                    LocationActivity.super.onBackPressed();
                    break;
                case R.id.navigation_location:
                    break;
                case R.id.navigation_video:
                    finish();
                    intent = new Intent(getApplicationContext(), VideoActivity.class);
                    startActivity(intent);
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

    private GoogleMap nMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000'>Test 1: Current Location</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(1).setChecked(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        nMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        nMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        nMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
