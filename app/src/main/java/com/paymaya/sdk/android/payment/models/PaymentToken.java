package com.paymaya.sdk.android.payment.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by jadeantolingaa on 12/4/15.
 */
public class PaymentToken implements Parcelable {

    private String paymentTokenId;
    private String env;
    private String type;
    private String state;
    private Date createdAt;
    private Date updatedAt;

    public PaymentToken(String paymentTokenId, String env, String type, String state, Date
            createdAt, Date updatedAt) {
        this.paymentTokenId = paymentTokenId;
        this.env = env;
        this.type = type;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PaymentToken() {
    }

    protected PaymentToken(Parcel in) {
        paymentTokenId = in.readString();
        env = in.readString();
        type = in.readString();
        state = in.readString();
        createdAt = (Date) in.readSerializable();
        updatedAt = (Date) in.readSerializable();
    }

    public static final Creator<PaymentToken> CREATOR = new Creator<PaymentToken>() {
        @Override
        public PaymentToken createFromParcel(Parcel in) {
            return new PaymentToken(in);
        }

        @Override
        public PaymentToken[] newArray(int size) {
            return new PaymentToken[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(paymentTokenId);
        dest.writeString(env);
        dest.writeString(type);
        dest.writeString(state);
        dest.writeSerializable(createdAt);
        dest.writeSerializable(updatedAt);
    }

    public String getPaymentTokenId() {
        return paymentTokenId;
    }

    public void setPaymentTokenId(String paymentTokenId) {
        this.paymentTokenId = paymentTokenId;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PaymentToken{");
        sb.append("id='").append(paymentTokenId).append('\'');
        sb.append(", env='").append(env).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", state=").append(state).append('\'');
        sb.append(", createdAt=").append(createdAt).append('\'');
        sb.append(", updatedAt='").append(updatedAt).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
