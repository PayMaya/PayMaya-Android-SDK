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

import java.math.BigDecimal;

import static com.paymaya.sdk.android.common.utils.Preconditions.checkNotNull;

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
     * @param value    total amount value
     * @param currency ISO 4217 currency code
     * @throws NullPointerException
     */
    public TotalAmount(BigDecimal value, String currency) {
        this.value = checkNotNull(value, "value");
        this.currency = checkNotNull(currency, "currency");
    }

    /**
     *
     * @return total amount value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * set a new value for total amount
     *
     * @param value
     */
    public void setValue(BigDecimal value) {
        this.value = checkNotNull(value, "value");
    }

    /**
     *
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * set a new value for currency
     *
     * @param currency
     * @throws NullPointerException
     */
    public void setCurrency(String currency) {
        this.currency = checkNotNull(currency, "currency");
    }

    /**
     *
     * @return amount details
     */
    public AmountDetails getAmountDetails() {
        return amountDetails;
    }

    /**
     * set a new value for amount details
     *
     * @param amountDetails amount details (Optional)
     */
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
