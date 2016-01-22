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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.paymaya.sdk.android.common.utils.Preconditions;
import com.paymaya.sdk.android.checkout.models.Checkout;

public final class PayMayaCheckout {

    private static final int CHECKOUT_REQUEST_CODE = 707;
    private String mClientKey;
    private PayMayaCheckoutCallback mCallback;

    /**
     * @param clientKey
     * @param callback
     */
    public PayMayaCheckout(String clientKey, PayMayaCheckoutCallback callback) {
        mClientKey = clientKey;
        mCallback = Preconditions.checkNotNull(callback, "callback");
    }

    /**
     * Initiates the checkout flow
     *
     * @param activity
     * @param checkout
     */
    public void execute(Activity activity, Checkout checkout) {
        Bundle checkoutBundle = new Bundle();
        checkoutBundle.putParcelable(PayMayaCheckoutActivity.EXTRAS_CHECKOUT, checkout);
        Intent intent = new Intent(activity, PayMayaCheckoutActivity.class);
        intent.putExtra(PayMayaCheckoutActivity.EXTRAS_CLIENT_KEY, mClientKey);
        intent.putExtra(PayMayaCheckoutActivity.EXTRAS_CHECKOUT_BUNDLE, checkoutBundle);
        activity.startActivityForResult(intent, CHECKOUT_REQUEST_CODE);
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHECKOUT_REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    mCallback.onCheckoutSuccess();
                    break;

                case Activity.RESULT_CANCELED:
                    mCallback.onCheckoutCanceled();
                    break;

                case PayMayaCheckoutActivity.RESULT_FAILURE:
                    String failureMessage = data.getStringExtra(
                            PayMayaCheckoutActivity.EXTRAS_FAILURE_MESSAGE);
                    mCallback.onCheckoutFailure(failureMessage);
                    break;

                default:
                    break;
            }
        }
    }
}
