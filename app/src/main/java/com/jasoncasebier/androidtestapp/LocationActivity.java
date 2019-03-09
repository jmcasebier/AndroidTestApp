package com.jasoncasebier.androidtestapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;

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
    private Location location;
    private double latitude;
    private double longitude;

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
        enableMyLocation();
        setLocation();
    }

    private void setLocation() {
        UiSettings settings = nMap.getUiSettings();
        settings.setMyLocationButtonEnabled(false);
        nMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
        nMap.moveCamera(CameraUpdateFactory.zoomTo(18.3f));
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this).setTitle("Alert").setMessage("Permission needed to " +
                        "access location").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(LocationActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);
                    }
                }).create().show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);
            }
        }

        nMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        Log.i("LOCATION: ", location.toString());
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }
}
