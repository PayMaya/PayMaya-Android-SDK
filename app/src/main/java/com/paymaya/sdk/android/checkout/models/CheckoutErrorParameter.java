package com.paymaya.sdk.android.checkout.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by giaquino on 11/3/15.
 */
public final class CheckoutErrorParameter implements Parcelable {

    private String field;
    private String description;

    public static final Creator<CheckoutErrorParameter> CREATOR = new Creator<CheckoutErrorParameter>() {
        @Override
        public CheckoutErrorParameter createFromParcel(Parcel in) {
            return new CheckoutErrorParameter(in);
        }

        @Override
        public CheckoutErrorParameter[] newArray(int size) {
            return new CheckoutErrorParameter[size];
        }
    };

    protected CheckoutErrorParameter(Parcel in) {
        field = in.readString();
        description = in.readString();
    }

    public CheckoutErrorParameter(String field, String description) {
        this.field = field;
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(field);
        dest.writeString(description);
    }
}
