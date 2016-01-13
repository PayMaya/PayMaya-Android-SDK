package com.paymaya.sdk.android.payment;

/**
 * Created by jadeantolingaa on 12/4/15.
 */
public class PayMayaPaymentException extends Exception {

    public PayMayaPaymentException(String message) {
        super(message, null);
    }

    public PayMayaPaymentException(String message, Throwable e) {
        super(message, e);
    }
}
