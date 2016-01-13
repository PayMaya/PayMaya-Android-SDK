package com.paymaya.sdk.android.checkout.models;

import android.os.Parcel;
import android.os.Parcelable;

import static com.paymaya.sdk.android.common.utils.Preconditions.checkNotNull;

/**
 * Created by samfrancisco on 10/21/15.
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
     * @param firstName
     * @param lastName
     */
    public Buyer(String firstName, String lastName) {
        this.firstName = checkNotNull(firstName, "firstName");
        this.lastName = checkNotNull(lastName, "lastName");
    }

    /**
     * @param firstName
     * @param middleName
     * @param lastName
     */
    public Buyer(String firstName, String middleName, String lastName) {
        this(firstName, lastName);
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = checkNotNull(firstName, "firstName");
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = checkNotNull(lastName, "lastName");
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

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
