package com.jasonscotthoffman.shout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
     /** Called when the activity is first created. */
     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          //setContentView(R.layout.main);
        
          // Let's display the progress in the activity title bar, like the
          // browser app does.
          getWindow().requestFeature(Window.FEATURE_PROGRESS);

          final WebView webview = new WebView(this);
          setContentView(webview);
        
          
          webview.getSettings().setJavaScriptEnabled(true);
          //this should force the webview to draw the whole width of the screen
          webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
          final Activity activity = this;
          webview.setWebChromeClient(new WebChromeClient() {
          public void onProgressChanged(WebView view, int progress) {
               // Activities and WebViews measure progress with different scales.
               // The progress meter will automatically disappear when we reach 100%
               activity.setProgress(progress * 1000);
          }
        });
        
webview.setWebViewClient(new WebViewClient() {
	@Override  
    public void onPageFinished(WebView view, String url) {
       //
		super.onPageFinished(webview, url);
        //"Toast" line is the webview equivalent of an alert for dev purposes. Uncomment to see when page is loaded.
        Toast.makeText(getApplicationContext(), "ALABTU!", Toast.LENGTH_SHORT).show();
       //get yui.main element and hide it
        view.loadUrl("javascript:var con = document.getElementById('yui-main'); " +
                "con.style.display = 'none'; ");
        //get rid of navbar-can't have people clicking away.
        view.loadUrl("javascript:var con = document.getElementById('navbar-top'); " +
                "con.style.display = 'none'; ");
        //get rid of chillout zone
        view.loadUrl("javascript:var con = document.getElementsByTagName('hd'); " +
                "con.style.display = 'none'; ");
        //get sidebar-right (shoutbox or log-in) element and make it full screen
        view.loadUrl("javascript:var con = document.getElementById('sidebar-right'); " +
                "con.style.width = '100%'; ");
        //get rid of everything but shoutbox
        view.loadUrl("javascript:var con = document.getElementById('block-blog-0'); " +
                "con.style.display = 'none'; ");//Latest blog entries
        view.loadUrl("javascript:var con = document.getElementById('block-block-7'); " +
                "con.style.display = 'none'; ");//search page
        view.loadUrl("javascript:var con = document.getElementById('block-block-4'); " +
                "con.style.display = 'none'; ");//External links to:
        view.loadUrl("javascript:var con = document.getElementById('block-links_weblink-1'); " +
                "con.style.display = 'none'; ");//Recently submitted links
        view.loadUrl("javascript:var con = document.getElementById('block-user-3'); " +
                "con.style.display = 'none'; ");//Who's online
        view.loadUrl("javascript:var con = document.getElementById('block-user-1'); " +
                "con.style.display = 'none'; ");//Quick links
        view.loadUrl("javascript:var anchors = document.getElementsByTagName('a'); " +
                " for (var i = 0; i < anchors.length; i++) {anchors[i].onclick = function() {return(false);};};");//...and finally all links.

    }            
	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
          //Users will be notified in case there's an error (i.e. no internet connection)
          Toast.makeText(activity, "No connection" + description, Toast.LENGTH_SHORT).show();
}
 });

		//This will load the webpage that we want to see
        webview.loadUrl("http://www.letsmakerobots.com");
        
     
    
}

    
};
//String htmlText="<script>"+"s=$('#yui-main').hide();$('#sidebar-right').css('width', '100%');"+"</script>";