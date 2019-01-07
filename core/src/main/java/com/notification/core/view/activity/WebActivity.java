package com.notification.core.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.notification.core.R;
import com.notification.core.view.activity.landing.LandingActivity;


public class WebActivity extends AppCompatActivity {

    private WebView webview;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String currentUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webview = findViewById(R.id.webview);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        String title = getIntent().getStringExtra("title");
        if (title != null) {
            toolbar.setTitle(title);
        }

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.clearCache(true);
        webview.setWebViewClient(new WebViewClient());

        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    swipeRefreshLayout.setRefreshing(true);
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        webview.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (webview.getScrollY() == 0) {
                swipeRefreshLayout.setEnabled(true);
            } else {
                swipeRefreshLayout.setEnabled(false);
            }
        });


        currentUrl = getIntent().getStringExtra("url");

        webview.loadUrl(currentUrl);

        swipeRefreshLayout.setOnRefreshListener(() -> webview.loadUrl(currentUrl));
    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            Intent intent = new Intent(this, LandingActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
