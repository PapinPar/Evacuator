package com.evacuator.uses.evacuator;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by root on 13.01.16.
 */
public class PaymentActivity extends Activity {

    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        WebView mWebView = (WebView) findViewById(R.id.webView);
        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        // указываем страницу загрузки
        String url = "https://money.yandex.ru/eshop.xml?"+
                "orderNumber="+588+
                "&orderKey="+"gq9TyUtw"+
                "&shopId="+47306+
                "&scid="+32298+
                "&shopSuccessURL="+"https://bb-evacuator.ru/order/gq9TyUtw?success=1"+
                "&shopFailURL="+"https://bb-evacuator.ru/order/gq9TyUtw?fail=1"+
                "&paymentType="+"AC"+
                "&sum="+"2711"+
                "&customerNumber="+"+380 63 836 7925"+
                "&cps_phone="+"+380 63 836 7925"+
                "&orderId="+588;

        mWebView.loadUrl(url);

        //mWebView.load
    }

    private Button findButton(int id) {
        return (Button) findViewById(id);
    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }

    }
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
