package com.jasoncasebier.androidtestapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        getSupportActionBar().setTitle(Html.fromHtml(""));
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.i("URL", url);
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                String title = "<font color='white'>" + view.getTitle() + "</font>";
                getSupportActionBar().setTitle(Html.fromHtml(title));
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String title = "<font color='white'>" + view.getTitle() + "</font>";
                getSupportActionBar().setTitle(Html.fromHtml(title));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
