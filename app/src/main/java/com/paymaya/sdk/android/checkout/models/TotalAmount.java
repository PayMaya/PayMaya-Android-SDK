package com.paymaya.sdk.android.checkout.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

import static com.paymaya.sdk.android.common.utils.Preconditions.checkNotNull;

/**
 * Created by samfrancisco on 10/26/2015.
 */
public final class TotalAmount implements Parcelable {

    private BigDecimal value;
    private String currency;
    private AmountDetails amountDetails;

    public static final Creator<TotalAmount> CREATOR = new Creator<TotalAmount>() {
        @Override
        public TotalAmount createFromParcel(Parcel in) {
            return new TotalAmount(in);
        }

        @Override
        public TotalAmount[] newArray(int size) {
            return new TotalAmount[size];
        }
    };

    public TotalAmount(Parcel in) {
        value = new BigDecimal(in.readString());
        currency = in.readString();
        amountDetails = in.readParcelable(AmountDetails.class.getClassLoader());
    }

    /**
     * @param value
     * @param currency ISO 4217 currency code
     */
    public TotalAmount(BigDecimal value, String currency) {
        this.value = checkNotNull(value, "value");
        this.currency = checkNotNull(currency, "currency");
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = checkNotNull(value, "value");
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = checkNotNull(currency, "currency");
    }

    public AmountDetails getAmountDetails() {
        return amountDetails;
    }

    public void setAmountDetails(AmountDetails amountDetails) {
        this.amountDetails = amountDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value.toPlainString());
        dest.writeString(currency);
        dest.writeParcelable(amountDetails, flags);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TotalAmount{");
        sb.append("amountDetails=").append(amountDetails);
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
