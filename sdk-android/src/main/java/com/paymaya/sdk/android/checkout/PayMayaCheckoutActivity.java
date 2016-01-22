/*
 * Copyright (c) 2016 PayMaya Philippines, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.paymaya.sdk.android.checkout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.paymaya.sdk.android.BuildConfig;
import com.paymaya.sdk.android.PayMayaConfig;
import com.paymaya.sdk.android.R;
import com.paymaya.sdk.android.common.utils.JSONUtils;
import com.paymaya.sdk.android.common.utils.Preconditions;
import com.paymaya.sdk.android.checkout.models.Checkout;
import com.paymaya.sdk.android.checkout.models.RedirectUrl;
import com.paymaya.sdk.android.common.network.AndroidClient;
import com.paymaya.sdk.android.common.network.Request;
import com.paymaya.sdk.android.common.network.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public final class PayMayaCheckoutActivity extends Activity {

    public static final int RESULT_FAILURE = 1063;

    public static final String EXTRAS_CLIENT_KEY = "extras_client_key";
    public static final String EXTRAS_CHECKOUT = "extras_checkout";
    public static final String EXTRAS_CHECKOUT_BUNDLE = "extras_bundle";
    public static final String EXTRAS_FAILURE_MESSAGE = "extras_failure_message";

    private Checkout mCheckout;
    private String mClientKey;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private String mSessionRedirectUrl;
    private String mSessionCheckoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymaya_checkout_activity);

        Intent intent = getIntent();
        Preconditions.checkNotNull(intent, "Missing intent.");

        Bundle bundle = intent.getBundleExtra(EXTRAS_CHECKOUT_BUNDLE);
        Preconditions.checkNotNull(bundle, "Missing bundle.");

        mCheckout = bundle.getParcelable(EXTRAS_CHECKOUT);
        Preconditions.checkNotNull(mCheckout, "Missing checkout object.");

        mClientKey = intent.getStringExtra(EXTRAS_CLIENT_KEY);
        Preconditions.checkNotNull(mClientKey, "Missing client key.");

        initialize();
        requestCreateCheckout();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initialize() {
        mSessionRedirectUrl = "";
        mProgressBar = (ProgressBar) findViewById(R.id.paymaya_checkout_activity_progress_bar);
        mWebView = (WebView) findViewById(R.id.paymaya_checkout_activity_web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                RedirectUrl redirectUrl = mCheckout.getRedirectUrl();
                if (url.startsWith(redirectUrl.getSuccessUrl())) {
                    finishSuccess();
                    return true;
                } else if (url.startsWith(redirectUrl.getCancelUrl())) {
                    finishCanceled();
                    return true;
                } else if (url.startsWith(redirectUrl.getFailureUrl())) {
                    finishFailure(redirectUrl.getFailureUrl());
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains(mSessionCheckoutId)) {
                    hideProgress();
                }

                super.onPageFinished(view, url);
            }
        });
    }

    private void requestCreateCheckout() {
        new AsyncTask<Void, Void, Response>() {
            @Override
            protected Response doInBackground(Void... voids) {
                try {
                    Request request = new Request(Request.Method.POST, PayMayaConfig.getEnvironment()
                            == PayMayaConfig.ENVIRONMENT_PRODUCTION ?
                            BuildConfig.API_CHECKOUT_ENDPOINT_PRODUCTION :
                            BuildConfig.API_CHECKOUT_ENDPOINT_SANDBOX);

                    byte[] body = JSONUtils.toJSON(mCheckout).toString().getBytes();
                    request.setBody(body);

                    String key = mClientKey + ":";
                    String authorization = Base64.encodeToString(key.getBytes(), Base64.DEFAULT);

                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", "Basic " + authorization);
                    headers.put("Content-Length", Integer.toString(body.length));
                    request.setHeaders(headers);

                    AndroidClient androidClient = new AndroidClient();
                    return androidClient.call(request);
                } catch (JSONException e) {
                    return new Response(-1, "");
                }
            }

            @Override
            protected void onPostExecute(Response response) {
                if (response.getCode() == 200) {
                    try {
                        JSONObject responseBody = new JSONObject(response.getResponse());
                        mSessionRedirectUrl = responseBody.getString("redirectUrl");
                        String[] redirectUrlParts = mSessionRedirectUrl.split("\\?");
                        mSessionCheckoutId = redirectUrlParts[redirectUrlParts.length - 1];
                        if (Build.MANUFACTURER.toLowerCase().contains("samsung")) {
                            mSessionRedirectUrl += "&cssfix=true";
                        }

                        loadUrl(mSessionRedirectUrl);
                    } catch (JSONException e) {
                        finishFailure(e.getMessage());
                    }
                } else {
                    finishFailure(response.getResponse());
                }
            }
        }.execute();
    }

    private void finishSuccess() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void finishCanceled() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }

    private void finishFailure(String message) {
        Intent intent = new Intent();
        intent.putExtra(EXTRAS_FAILURE_MESSAGE, message);
        setResult(RESULT_FAILURE, intent);
        finish();
    }

    private void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
