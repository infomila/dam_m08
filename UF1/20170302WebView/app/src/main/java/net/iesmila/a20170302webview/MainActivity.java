package net.iesmila.a20170302webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import net.dgardiner.markdown.MarkdownProcessor;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private Button mBtnGo;
    private EditText mEdtUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webView);
        mBtnGo = (Button) findViewById(R.id.btnGo);
        mEdtUrl = (EditText) findViewById(R.id.edtUrl);

        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);

        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String url = mEdtUrl.getText().toString();
                if(url.length()>3){
                    mWebView.loadUrl(url);
                }*/
                MarkdownProcessor processor = new MarkdownProcessor();
                String html = null;
                try {
                    html = processor.process("## Markdown\nText **negreta** , Text _cursiva_, \n\n1. Primer\n2. Segon\n3. Tercer");
                    //String html="<html><body style='background-color:#00ff00'> HELLO WORLD! </body></html>";
                    mWebView.loadData(html, "text/html", "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
