package com.movieziv.moviefirst.view.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.movieziv.moviefirst.R;

public class WatchTrailerActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "url";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_trailer);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        WebView videoView = findViewById(R.id.movie_trailer_webview);

        // Get intent extra
        Bundle arguments = getIntent().getExtras();
        String videoPath = arguments.getString(EXTRA_URL);

        videoView.setWebViewClient(new WebViewClient());
        if(videoPath != null) {
            videoView.loadUrl(videoPath);
        }

        WebSettings webSettings = videoView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
