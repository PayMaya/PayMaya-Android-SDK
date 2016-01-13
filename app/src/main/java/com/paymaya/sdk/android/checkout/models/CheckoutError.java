package com.paymaya.sdk.android.checkout.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by giaquino on 11/3/15.
 */
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
