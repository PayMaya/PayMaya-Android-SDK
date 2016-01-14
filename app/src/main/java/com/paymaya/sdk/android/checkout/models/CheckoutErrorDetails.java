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

package com.paymaya.sdk.android.checkout.models;

import android.os.Parcel;
import android.os.Parcelable;

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
