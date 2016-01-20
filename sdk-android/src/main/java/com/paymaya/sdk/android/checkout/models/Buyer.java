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
 * Represents the entity that bought items from the merchant's shop.
 */
public final class Buyer implements Parcelable {

    private String firstName;
    private String middleName;
    private String lastName;
    private Contact contact;
    private Address shippingAddress;
    private Address billingAddress;
    private String ipAddress;

    public static final Creator<Buyer> CREATOR = new Creator<Buyer>() {
        @Override
        public Buyer createFromParcel(Parcel in) {
            return new Buyer(in);
        }

        @Override
        public Buyer[] newArray(int size) {
            return new Buyer[size];
        }
    };

    public Buyer(Parcel in) {
        firstName = in.readString();
        middleName = in.readString();
        lastName = in.readString();
        contact = in.readParcelable(Contact.class.getClassLoader());
        shippingAddress = in.readParcelable(Address.class.getClassLoader());
        billingAddress = in.readParcelable(Address.class.getClassLoader());
        ipAddress = in.readString();
    }

    /**
     * @param firstName first name
     * @param lastName  last name
     * @throws NullPointerException
     */
    public Buyer(String firstName, String lastName) {
        this.firstName = checkNotNull(firstName, "firstName");
        this.lastName = checkNotNull(lastName, "lastName");
    }

    /**
     * @param firstName  first name
     * @param middleName middle name (Optional)
     * @param lastName   last name
     * @throws NullPointerException
     */
    public Buyer(String firstName, String middleName, String lastName) {
        this(firstName, lastName);
        this.middleName = middleName;
    }

    /**
     *
     * @return buyer's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * set a new value for buyer's first name
     *
     * @param firstName
     * @throws NullPointerException
     */
    public void setFirstName(String firstName) {
        this.firstName = checkNotNull(firstName, "firstName");
    }

    /**
     *
     * @return buyer's middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * set a new value for buyer's middle name
     *
     * @param middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     *
     * @return buyer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * set a new value for buyer's last name
     *
     * @param lastName
     * @throws NullPointerException
     */
    public void setLastName(String lastName) {
        this.lastName = checkNotNull(lastName, "lastName");
    }

    /**
     *
     * @return buyer's contact (Optional)
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * set a new value for buyer's contact
     *
     * @param contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     *
     * @return buyer's shipping address
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     * set a new value for buyer's shipping address
     *
     * @param shippingAddress
     */
    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     *
     * @return buyer's billing address
     */
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * set a new value for buyer's billing address
     *
     * @param billingAddress
     */
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     *
     * @return buyer's ip address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * set a new value for buyer's ip address
     *
     * @param ipAddress
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(middleName);
        dest.writeString(lastName);
        dest.writeParcelable(contact, flags);
        dest.writeParcelable(shippingAddress, flags);
        dest.writeParcelable(billingAddress, flags);
        dest.writeString(ipAddress);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Buyer{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", contact=").append(contact);
        sb.append(", shippingAddress=").append(shippingAddress);
        sb.append(", billingAddress=").append(billingAddress);
        sb.append(", ipAddress='").append(ipAddress).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
