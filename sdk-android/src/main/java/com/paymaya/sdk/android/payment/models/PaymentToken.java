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

package com.paymaya.sdk.android.payment.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Represents payment token, payment tokens go through the following states: created and used.
 * When you request for a card token, it will have the initial state of created. Payment tokens
 * with state created are used for charging money from your customers.
 */
public class PaymentToken implements Parcelable {

    private String paymentTokenId;
    private String env;
    private String type;
    private String state;
    private Date createdAt;
    private Date updatedAt;

    public PaymentToken() {
    }

    /**
     *
     * @param paymentTokenId the payment token id
     * @param env            env
     * @param type           type
     * @param state          state
     * @param createdAt      date timestamp when payment token is created
     * @param updatedAt      date timestamp when payment token is updated
     */
    public PaymentToken(String paymentTokenId, String env, String type, String state,
                        Date createdAt, Date updatedAt) {
        this.paymentTokenId = paymentTokenId;
        this.env = env;
        this.type = type;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PaymentToken(Parcel in) {
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

    /**
     *
     * @return the payment token id
     */
    public String getPaymentTokenId() {
        return paymentTokenId;
    }

    public void setPaymentTokenId(String paymentTokenId) {
        this.paymentTokenId = paymentTokenId;
    }


    /**
     *
     * @return env
     */
    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    /**
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return date timestamp when payment token is created
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return date timestamp when payment token is updated
     */
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
