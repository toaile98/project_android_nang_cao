package com.example.hctpfpoly.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hctpfpoly.R;

public class ph13373_WebViewActivity extends AppCompatActivity {

    private WebView wvNews;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph13373_web_view);
        wvNews = (WebView) findViewById(R.id.wv_news);

        intent=getIntent();
        String link=intent.getStringExtra("linkWeb");
        wvNews.loadUrl(link);
        wvNews.setWebViewClient(new WebViewClient());

    }
}