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

public final class AmountDetails implements Parcelable {

    private BigDecimal discount;
    private BigDecimal serviceCharge;
    private BigDecimal shippingFee;
    private BigDecimal tax;
    private BigDecimal subtotal;

    public static final Creator<AmountDetails> CREATOR = new Creator<AmountDetails>() {
        @Override
        public AmountDetails createFromParcel(Parcel in) {
            return new AmountDetails(in);
        }

        @Override
        public AmountDetails[] newArray(int size) {
            return new AmountDetails[size];
        }
    };

    public AmountDetails(Parcel in) {
        discount = new BigDecimal(in.readString());
        serviceCharge = new BigDecimal(in.readString());
        shippingFee = new BigDecimal(in.readString());
        tax = new BigDecimal(in.readString());
        subtotal = new BigDecimal(in.readString());
    }

    /**
     * @param discount      discount
     * @param serviceCharge service charge
     * @param shippingFee   shipping fee
     * @param tax           tax
     * @param subtotal      subtotal
     * @throws NullPointerException
     */
    public AmountDetails(BigDecimal discount, BigDecimal serviceCharge, BigDecimal shippingFee,
                         BigDecimal tax, BigDecimal subtotal) {
        this.discount = checkNotNull(discount, "discount");
        this.serviceCharge = checkNotNull(serviceCharge, "serviceCharge");
        this.shippingFee = checkNotNull(shippingFee, "shippingFee");
        this.tax = checkNotNull(tax, "tax");
        this.subtotal = checkNotNull(subtotal, "subtotal");
    }

    /**
     *
     * @return discount
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * set a new value for discount
     *
     * @param discount
     * @throws NullPointerException
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = checkNotNull(discount, "discount");
    }

    /**
     *
     * @return service charge
     */
    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    /**
     * set a new value for service charge
     *
     * @param serviceCharge
     * @throws NullPointerException
     */
    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = checkNotNull(serviceCharge, "serviceCharge");
    }

    /**
     *
     * @return shipping fee
     */
    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    /**
     * set a new value for shipping fee
     *
     * @param shippingFee
     * @throws NullPointerException
     */
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = checkNotNull(shippingFee, "shippingFee");
    }

    /**
     *
     * @return tax
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * set a new value for tax
     *
     * @param tax
     */
    public void setTax(BigDecimal tax) {
        this.tax = checkNotNull(tax, "tax");
    }

    /**
     *
     * @return subtotal
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * set a new value for subtotal
     *
     * @param subtotal
     * @throws NullPointerException
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = checkNotNull(subtotal, "subtotal");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(discount.toPlainString());
        dest.writeString(serviceCharge.toPlainString());
        dest.writeString(shippingFee.toPlainString());
        dest.writeString(tax.toPlainString());
        dest.writeString(subtotal.toPlainString());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AmountDetails{");
        sb.append("discount=").append(discount);
        sb.append(", serviceCharge=").append(serviceCharge);
        sb.append(", shippingFee=").append(shippingFee);
        sb.append(", tax=").append(tax);
        sb.append(", subtotal=").append(subtotal);
        sb.append('}');
        return sb.toString();
    }
}
