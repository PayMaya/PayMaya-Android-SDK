package com.paymaya.sdk.android.checkout.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

import static com.paymaya.sdk.android.common.utils.Preconditions.checkNotNull;

/**
 * Created by samfrancisco on 10/26/2015.
 */
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
     * @param discount
     * @param serviceCharge
     * @param shippingFee
     * @param tax
     * @param subtotal
     */
    public AmountDetails(BigDecimal discount, BigDecimal serviceCharge, BigDecimal shippingFee,
                         BigDecimal tax, BigDecimal subtotal) {
        this.discount = checkNotNull(discount, "discount");
        this.serviceCharge = checkNotNull(serviceCharge, "serviceCharge");
        this.shippingFee = checkNotNull(shippingFee, "shippingFee");
        this.tax = checkNotNull(tax, "tax");
        this.subtotal = checkNotNull(subtotal, "subtotal");
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = checkNotNull(discount, "discount");
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = checkNotNull(serviceCharge, "serviceCharge");
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = checkNotNull(shippingFee, "shippingFee");
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = checkNotNull(tax, "tax");
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

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
