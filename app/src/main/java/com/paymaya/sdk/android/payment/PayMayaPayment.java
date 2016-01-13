package com.paymaya.sdk.android.payment;

import android.util.Base64;

import com.paymaya.sdk.android.BuildConfig;
import com.paymaya.sdk.android.PayMaya;
import com.paymaya.sdk.android.common.network.AndroidClient;
import com.paymaya.sdk.android.common.network.Request;
import com.paymaya.sdk.android.common.network.Response;
import com.paymaya.sdk.android.common.utils.JSONUtils;
import com.paymaya.sdk.android.payment.models.Card;
import com.paymaya.sdk.android.payment.models.PaymentToken;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jadeantolingaa on 12/4/15.
 */
public class PayMayaPayment {

    private String mUuidIdempotentKey;
    private Card mCard;
    private String mClientKey;

    public PayMayaPayment(String clientKey, Card card) {
        mClientKey = clientKey;
        mCard = card;
        mUuidIdempotentKey = UUID.randomUUID().toString();
    }

    public PaymentToken getPaymentToken() throws PayMayaPaymentException {
        try {
            Request request = new Request(Request.Method.POST,
                    PayMaya.getEnvironment() == PayMaya.ENVIRONMENT_PRODUCTION ? BuildConfig
                            .API_PAYMENTS_ENDPOINT_PRODUCTION : BuildConfig
                            .API_PAYMENTS_ENDPOINT_SANDBOX + "/payment-tokens");

            byte[] body = JSONUtils.toJSON(mCard).toString().getBytes();
            request.setBody(body);

            String key = mClientKey + ":";
            String authorization = Base64.encodeToString(key.getBytes(), Base64.DEFAULT);

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Basic " + authorization);
            headers.put("Content-Length", Integer.toString(body.length));
            headers.put("Idempotent-Token", mUuidIdempotentKey);
            request.setHeaders(headers);

            AndroidClient androidClient = new AndroidClient();
            Response response = androidClient.call(request);

            //check errors

            return JSONUtils.fromJSONPaymentToken(response.getResponse());
        } catch (JSONException je) {
            throw new PayMayaPaymentException("Unknown Error", je);
        }

    }
}
