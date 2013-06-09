package com.shazamtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OpenURL extends Activity {

	String URL;
	WebView webview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//Display as full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//Fetch the URL associated with the tag that has been selected.
		fetchURL();
		//View the link
		setupWebView();
	}
	
	public void fetchURL() {
		//Assign URL to the string (link) that was passed in to this Activity with the intent
		Bundle b = getIntent().getExtras();
		URL = b.getString("URL");
	}
	
	public void setupWebView() {
		//Prepare the WebView to display the web page.
		webview = new WebView(this);
		webview.setWebViewClient(new WebViewClient());
		webview.getSettings().setBuiltInZoomControls(true);
		webview.getSettings().setSupportZoom(true); 
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
		setContentView(webview);
		webview.loadUrl(URL);
	}
}