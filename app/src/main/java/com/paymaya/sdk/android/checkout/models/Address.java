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

import static com.paymaya.sdk.android.common.utils.Preconditions.checkNotNull;

/**
 * Created by samfrancisco on 10/22/15.
 */
public final class Address implements Parcelable {

    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zipCode;
    private String countryCode;

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public Address(Parcel in) {
        line1 = in.readString();
        line2 = in.readString();
        city = in.readString();
        state = in.readString();
        zipCode = in.readString();
        countryCode = in.readString();
    }

    /**
     * @param line1
     * @param city
     * @param state
     * @param zipCode
     * @param countryCode ISO 3166-1 alpha-2 country code
     */
    public Address(String line1, String city, String state, String zipCode, String countryCode) {
        this.line1 = checkNotNull(line1, "line1");
        this.city = checkNotNull(city, "city");
        this.state = checkNotNull(state, "state");
        this.zipCode = checkNotNull(zipCode, "zipCode");
        this.countryCode = checkNotNull(countryCode, "countryCode");
    }

    /**
     * @param line1
     * @param line2
     * @param city
     * @param state
     * @param zipCode
     * @param countryCode ISO 3166-1 alpha-2 country code
     */
    public Address(String line1, String line2, String city, String state, String zipCode,
                   String countryCode) {
        this(line1, city, state, zipCode, countryCode);
        this.line2 = line2;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = checkNotNull(line1, "line1");
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = checkNotNull(city, "city");
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = checkNotNull(state, "state");
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = checkNotNull(zipCode, "zipCode");
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = checkNotNull(countryCode, "countryCode");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(line1);
        dest.writeString(line2);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zipCode);
        dest.writeString(countryCode);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("line1='").append(line1).append('\'');
        sb.append(", line2='").append(line2).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
