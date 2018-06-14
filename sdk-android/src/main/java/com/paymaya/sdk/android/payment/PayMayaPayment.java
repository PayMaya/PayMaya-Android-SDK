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

package com.paymaya.sdk.android.payment;

import android.util.Base64;
import android.util.Log;

import com.paymaya.sdk.android.BuildConfig;
import com.paymaya.sdk.android.PayMayaConfig;
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

public class PayMayaPayment {

    private Card mCard;
    private String mClientKey;
    private String mUuidIdempotentKey;

    /**
     * @param clientKey Public API key
     * @param card      User's Credit Card details
     */
    public PayMayaPayment(String clientKey, Card card) {
        mClientKey = clientKey;
        mCard = card;
        mUuidIdempotentKey = UUID.randomUUID().toString();
    }

    /**
     * Request and returns a payment token
     *
     * @return PaymentToken
     * @throws PayMayaPaymentException
     */
    public PaymentToken getPaymentToken() throws PayMayaPaymentException {
        try {
            String productionEndpoint = BuildConfig.API_PAYMENTS_ENDPOINT_PRODUCTION + "/payment-tokens";
            String sandboxEndpoint = BuildConfig.API_PAYMENTS_ENDPOINT_SANDBOX + "/payment-tokens";
            String envEndpoint = PayMayaConfig.getEnvironment() == PayMayaConfig.ENVIRONMENT_PRODUCTION ? productionEndpoint : sandboxEndpoint;

            Log.d("@fromPayMayaPayment", "envEndpoint = " + envEndpoint);

            Request request = new Request(Request.Method.POST, envEndpoint);

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
        } catch (PayMayaPaymentException ppe) {
            throw new PayMayaPaymentException(ppe.getMessage());
        }

    }
}
