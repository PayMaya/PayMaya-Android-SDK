package com.paymaya.sdk.android.checkout.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by giaquino on 11/3/15.
 */
public final class CheckoutErrorDetails implements Parcelable {

    private String responseCode;
    private String responseDescription;
    private String requestReferenceNumber;

    public static final Creator<CheckoutErrorDetails> CREATOR = new Creator<CheckoutErrorDetails>() {
        @Override
        public CheckoutErrorDetails createFromParcel(Parcel in) {
            return new CheckoutErrorDetails(in);
        }

        @Override
        public CheckoutErrorDetails[] newArray(int size) {
            return new CheckoutErrorDetails[size];
        }
    };

    protected CheckoutErrorDetails(Parcel in) {
        responseCode = in.readString();
        responseDescription = in.readString();
        requestReferenceNumber = in.readString();
    }

    public CheckoutErrorDetails(String responseCode, String responseDescription,
                                String requestReferenceNumber) {
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.requestReferenceNumber = requestReferenceNumber;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getRequestReferenceNumber() {
        return requestReferenceNumber;
    }

    public void setRequestReferenceNumber(String requestReferenceNumber) {
        this.requestReferenceNumber = requestReferenceNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(responseCode);
        dest.writeString(responseDescription);
        dest.writeString(requestReferenceNumber);
    }
}
