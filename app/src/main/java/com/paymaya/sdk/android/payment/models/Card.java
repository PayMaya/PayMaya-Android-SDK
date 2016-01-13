package com.paymaya.sdk.android.payment.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jadeantolingaa on 12/4/15.
 */
public class Card implements Parcelable{

    private String number;
    private String expMonth;
    private String expYear;
    private String cvc;

    public Card(String number, String expMonth, String expYear, String cvc) {
        this.number = number;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvc = cvc;
    }

    protected Card(Parcel in) {
        number = in.readString();
        expMonth = in.readString();
        expYear = in.readString();
        cvc = in.readString();
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

}
