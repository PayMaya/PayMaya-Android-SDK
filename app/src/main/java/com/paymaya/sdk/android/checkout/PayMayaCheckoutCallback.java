package com.paymaya.sdk.android.checkout;

/**
 * Created by giaquino on 11/13/15.
 */
public interface PayMayaCheckoutCallback {

    void onCheckoutSuccess();

    void onCheckoutCanceled();

    void onCheckoutFailure();

}
