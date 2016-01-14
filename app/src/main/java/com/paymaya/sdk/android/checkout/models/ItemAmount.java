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

import com.paymaya.sdk.android.common.utils.Preconditions;

import java.math.BigDecimal;

/**
 * Represents detail about each item's amount
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

    /**
     *
     * @param value item value
     */
    public ItemAmount(BigDecimal value) {
        this.value = Preconditions.checkNotNull(value, "value");
    }

    /**
     *
     * @return item value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * set a new value for item
     *
     * @param value item value
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     *
     * @return item amount additional details
     */
    public AmountDetails getDetails() {
        return details;
    }

    /**
     * set a new value for item amount details
     *
     * @param details
     */
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
