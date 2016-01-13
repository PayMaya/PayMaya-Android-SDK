package com.paymaya.sdk.android.checkout;

/**
 * Created by samfrancisco on 10/26/2015.
 */
public class PayMayaCheckoutException extends Exception {

  public PayMayaCheckoutException(String message) {
    super(message, null);
  }

  public PayMayaCheckoutException(String message, Throwable e) {
    super(message, e);
  }

}
