package com.paymaya.sdk.android.checkout.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.paymaya.sdk.android.common.utils.Preconditions;

import java.math.BigDecimal;

/**
 * Created by samfrancisco on 10/26/2015.
 */
public final class ItemAmount implements Parcelable {

    private BigDecimal value;
    private AmountDetails details;

    public static final Creator<ItemAmount> CREATOR = new Creator<ItemAmount>() {
        @Override
        public ItemAmount createFromParcel(Parcel in) {
            return new ItemAmount(in);
        }

        @Override
        public ItemAmount[] newArray(int size) {
            return new ItemAmount[size];
        }
    };

    public ItemAmount(Parcel in) {
        value = new BigDecimal(in.readString());
        details = in.readParcelable(AmountDetails.class.getClassLoader());
    }

    public ItemAmount(BigDecimal value) {
        this.value = Preconditions.checkNotNull(value, "value");
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public AmountDetails getDetails() {
        return details;
    }

    public void setDetails(AmountDetails details) {
        this.details = details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value.toPlainString());
        dest.writeParcelable(details, flags);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemAmount{");
        sb.append("value=").append(value);
        sb.append(", details=").append(details);
        sb.append('}');
        return sb.toString();
    }
}
