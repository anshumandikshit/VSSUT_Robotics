package com.robotix_vssut.welcomeanimationapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.R.attr.description;

public class activity_gallery extends AppCompatActivity {


    private WebView mWebview;
    //private  String url="http://www.vssutrobotics.in/gallery";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);



       mWebview=(WebView)findViewById(R.id.webview_gallery);
        WebSettings Websettings=mWebview.getSettings();
        Websettings.setJavaScriptEnabled(true);
        mWebview.getSettings().setCacheMode(Websettings.LOAD_CACHE_ELSE_NETWORK);
        mWebview.getSettings().setAppCacheEnabled(true);
        mWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        Websettings.setDomStorageEnabled(true);
        Websettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        Websettings.setUseWideViewPort(true);
        Websettings.setSavePassword(true);
       // Websettings.setSaveFormData(true);
        Websettings.setEnableSmoothTransition(true);


        mWebview.loadUrl("http://www.vssutrobotics.in/gallery");
        mWebview.setWebViewClient(new mWebviewClient());

    }

    private class mWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

       //Progress_dialoug
        ProgressDialog progressDialog=null;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressDialog=new ProgressDialog(activity_gallery.this);
            progressDialog.setTitle("Please wait ....");
            progressDialog.setMessage("Loading Gallery");
            progressDialog.show();
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);
            progressDialog.dismiss();
        }
    }
}

