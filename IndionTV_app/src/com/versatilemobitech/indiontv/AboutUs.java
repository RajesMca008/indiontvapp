package com.versatilemobitech.indiontv;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class AboutUs extends Activity{
	
	  private WebView webview;
	    private static final String TAG = "Main";
	    private ProgressDialog progressBar;
	    
	    private String url="http://www.indiontv.com/privacy-policies.php";
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	
	
     setContentView( R.layout.my_web_view);
     
     
     if(getIntent().getStringExtra("MSG")!=null)
		{
    	 url= getIntent().getStringExtra("MSG");
			 
		}
     
     AdView mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

     this.webview = (WebView)findViewById( R.id.webview);

     WebSettings settings = webview.getSettings();
     settings.setJavaScriptEnabled(true);
     webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

     final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

     progressBar = ProgressDialog.show(AboutUs.this, "Indion TV", "Loading...");
     
     progressBar.setCancelable(true);

     webview.setWebViewClient(new WebViewClient() {
         public boolean shouldOverrideUrlLoading(WebView view, String url) {
             Log.i(TAG, "Processing webview url click...");
             view.loadUrl(url);
             return true;
         }

         public void onPageFinished(WebView view, String url) {
             Log.i(TAG, "Finished loading URL: " +url);
             if (progressBar.isShowing()) {
                 progressBar.dismiss();
             }
         }

         public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
             Log.e(TAG, "Error: " + description);
             Toast.makeText(AboutUs.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
             alertDialog.setTitle("Error");
             alertDialog.setMessage(description);
             alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int which) {
                     return;
                 }
             });
             alertDialog.show();
         }
     });
       webview.loadUrl(url);
       
       webview.getSettings().setAppCacheEnabled(true); 
}

}
