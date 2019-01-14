package com.notification.core.view.activity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.notification.core.R;
import com.notification.core.view.activity.landing.LandingActivity;


public class WebActivity extends AppCompatActivity {

    private WebView webview;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String currentUrl;
    private boolean isShowingDialog;

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
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (isActivityAlive()) {
                    handleSslError(handler, error);
                }
            }
        });

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

    private boolean isActivityAlive() {
        return !this.isFinishing() && (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR1 || !this.isDestroyed());
    }

    private void handleSslError(final SslErrorHandler handler, SslError error) {
        if (isShowingDialog) {
            return;
        }

        isShowingDialog = true;
        final AlertDialog.Builder builder = new AlertDialog.Builder(WebActivity.this);
        String message = "SSL Certificate error.";
        switch (error.getPrimaryError()) {
            case SslError.SSL_UNTRUSTED:
                message = "The certificate authority is not trusted.";
                break;
            case SslError.SSL_EXPIRED:
                message = "The certificate has expired.";
                break;
            case SslError.SSL_IDMISMATCH:
                message = "The certificate Hostname mismatch.";
                break;
            case SslError.SSL_NOTYETVALID:
                message = "The certificate is not yet valid.";
                break;
        }
        message += " Do you want to continue anyway?";

        builder.setTitle("SSL Certificate Error");
        builder.setMessage(message);
        builder.setPositiveButton("continue", (dialog, which) -> {
            handler.proceed();
            isShowingDialog = false;
        });
        builder.setNegativeButton("cancel", (dialog, which) -> {
            handler.cancel();
            isShowingDialog = false;
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}
