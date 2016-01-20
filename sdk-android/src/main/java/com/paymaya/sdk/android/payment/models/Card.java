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

/**
 * Class containing credit card information
 */
public class Card implements Parcelable{

    private String number;
    private String expMonth;
    private String expYear;
    private String cvc;

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    /**
     *
     * @param number   the credit card number
     * @param expMonth the credit card expiration date in month
     * @param expYear  the credit card expiration date in year
     * @param cvc      the credit card cvc
     */
    public Card(String number, String expMonth, String expYear, String cvc) {
        this.number = number;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvc = cvc;
    }

    public Card(Parcel in) {
        number = in.readString();
        expMonth = in.readString();
        expYear = in.readString();
        cvc = in.readString();
    }

    /**
     * @return the credit card number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the credit card number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     *
     * @return the credit card expiration date in month
     */
    public String getExpMonth() {
        return expMonth;
    }

    /**
     *
     * @param expMonth the credit card expiration date in month
     */
    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    /**
     *
     * @return the credit card expiration date in year
     */
    public String getExpYear() {
        return expYear;
    }

    /**
     *
     * @param expYear the credit card expiration date in year
     */
    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    /**
     *
     * @return the credit card cvc
     */
    public String getCvc() {
        return cvc;
    }

    /**
     *
     * @param cvc the credit card cvc
     */
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeString(expMonth);
        dest.writeString(expYear);
        dest.writeString(cvc);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Card{");
        sb.append("number='").append(number).append('\'');
        sb.append(", expMonth='").append(expMonth).append('\'');
        sb.append(", expYear='").append(expYear).append('\'');
        sb.append(", cvc='").append(cvc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
