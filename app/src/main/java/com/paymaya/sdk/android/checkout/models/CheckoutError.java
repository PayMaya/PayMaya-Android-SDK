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

import java.util.List;

public final class CheckoutError implements Parcelable {

    private int code;
    private String message;
    private List<CheckoutErrorParameter> parameters;
    private CheckoutErrorDetails details;

    public static final Creator<CheckoutError> CREATOR = new Creator<CheckoutError>() {
        @Override
        public CheckoutError createFromParcel(Parcel in) {
            return new CheckoutError(in);
        }

        @Override
        public CheckoutError[] newArray(int size) {
            return new CheckoutError[size];
        }
    };

    protected CheckoutError(Parcel in) {
        code = in.readInt();
        message = in.readString();
        parameters = in.createTypedArrayList(CheckoutErrorParameter.CREATOR);
        details = in.readParcelable(CheckoutErrorDetails.class.getClassLoader());
    }

    public CheckoutError() {
    }

    public CheckoutError(int code, String message, List<CheckoutErrorParameter> parameters,
                         CheckoutErrorDetails details) {
        this.code = code;
        this.message = message;
        this.parameters = parameters;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CheckoutErrorParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<CheckoutErrorParameter> parameters) {
        this.parameters = parameters;
    }

    public CheckoutErrorDetails getDetails() {
        return details;
    }

    public void setDetails(CheckoutErrorDetails details) {
        this.details = details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(message);
        dest.writeTypedList(parameters);
        dest.writeParcelable(details, flags);
    }
}
