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
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.paymaya.sdk.android.common.utils.Preconditions.checkNotNull;

/**
 * A Checkout contains information about the buyer, the items inside the cart, transaction amount,
 * status of payment and other details.
 */
public final class Checkout implements Parcelable {

    private TotalAmount totalAmount;
    private Buyer buyer;
    private List<Item> itemList;
    private RedirectUrl redirectUrl;
    private String requestReferenceNumber;
    private boolean isAutoRedirect;
    private JSONObject metadata;

    public static final Creator<Checkout> CREATOR = new Creator<Checkout>() {
        @Override
        public Checkout createFromParcel(Parcel in) {
            return new Checkout(in);
        }
        @Override
        public Checkout[] newArray(int size) {
            return new Checkout[size];
        }
    };

    public Checkout(Parcel in) {
        totalAmount = in.readParcelable(TotalAmount.class.getClassLoader());
        buyer = in.readParcelable(Buyer.class.getClassLoader());

        itemList = new ArrayList<>();
        in.readTypedList(itemList, Item.CREATOR);

        redirectUrl = in.readParcelable(RedirectUrl.class.getClassLoader());
        requestReferenceNumber = in.readString();
        isAutoRedirect = in.readByte() != 0;

        try {
            String json = in.readString();
            if (!TextUtils.isEmpty(json)) {
                metadata = new JSONObject(json);
            }
        } catch (JSONException ignored) {
        }
    }

    /**
     * @param totalAmount            transaction total amount details
     * @param buyer                  costumer details
     * @param itemList               item list
     * @param requestReferenceNumber reference number assigned by the merchant to identify a
     *                               transaction
     * @throws NullPointerException
     */
    public Checkout(TotalAmount totalAmount, Buyer buyer, List<Item> itemList,
                    String requestReferenceNumber, RedirectUrl redirectUrl) {
        this.totalAmount = checkNotNull(totalAmount, "totalAmount");
        this.buyer = checkNotNull(buyer, "buyer");
        this.itemList = checkNotNull(itemList, "items");
        this.requestReferenceNumber = checkNotNull(requestReferenceNumber,
                "requestReferenceNumber");
        this.redirectUrl = checkNotNull(redirectUrl, "redirectUrl");
        isAutoRedirect = false;
    }

    /**
     *
     * @return transaction total amount details
     */
    public TotalAmount getTotalAmount() {
        return totalAmount;
    }

    /**
     * set a new value for transaction total amount details
     *
     * @param totalAmount transaction total amount details
     * @throws NullPointerException
     */
    public void setTotalAmount(TotalAmount totalAmount) {
        this.totalAmount = checkNotNull(totalAmount, "totalAmount");
    }

    /**
     *
     * @return costumer details
     */
    public Buyer getBuyer() {
        return buyer;
    }

    /**
     * set a new value for costumer details
     *
     * @param buyer costumer details
     * @throws NullPointerException
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = checkNotNull(buyer, "buyer");
    }

    /**
     *
     * @return item list
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * set a new value for item list
     *
     * @param itemList
     * @throws NullPointerException
     */
    public void setItemList(List<Item> itemList) {
        this.itemList = checkNotNull(itemList, "items");
    }

    /**
     *
     * @return set of redirect URLs upon checkout completion
     */
    public RedirectUrl getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * set a new value for redirect url
     *
     * @param redirectUrl redirect URLS upon checkout completion
     * @throws NullPointerException
     */
    public void setRedirectUrl(RedirectUrl redirectUrl) {
        this.redirectUrl = checkNotNull(redirectUrl, "redirectUrl");
    }

    /**
     *
     * @return reference number assigned by the merchant to identify a transaction
     */
    public String getRequestReferenceNumber() {
        return requestReferenceNumber;
    }

    /**
     * set a new value for request reference number
     *
     * @param requestReferenceNumber reference number assigned by the merchant to identify a
     *                               transaction
     * @throws NullPointerException
     */
    public void setRequestReferenceNumber(String requestReferenceNumber) {
        this.requestReferenceNumber = checkNotNull(requestReferenceNumber, "requestReferenceNumber");
    }

    /**
     * Defines the behaviour of the checkout
     *
     * @return boolean
     */
    public boolean isAutoRedirect() {
        return isAutoRedirect;
    }

    /**
     * defines new behaviour for checkout
     *
     * @param isAutoRedirect
     */
    public void setAutoRedirect(boolean isAutoRedirect) {
        this.isAutoRedirect = isAutoRedirect;
    }

    /**
     *
     * @return additional cart information
     */
    public JSONObject getMetadata() {
        return metadata;
    }

    /**
     * set a new value for metadata
     *
     * @param metadata additional cart information (Optional)
     */
    public void setMetadata(JSONObject metadata) {
        this.metadata = metadata;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(totalAmount, flags);
        dest.writeParcelable(buyer, flags);
        dest.writeTypedList(itemList);
        dest.writeParcelable(redirectUrl, flags);
        dest.writeString(requestReferenceNumber);
        dest.writeByte((byte) (isAutoRedirect ? 1 : 0));
        if (metadata != null) {
            dest.writeString(metadata.toString());
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Checkout{");
        sb.append("totalAmount=").append(totalAmount);
        sb.append(", buyer=").append(buyer);
        sb.append(", itemList=").append(itemList);
        sb.append(", redirectUrl=").append(redirectUrl);
        sb.append(", requestReferenceNumber='").append(requestReferenceNumber).append('\'');
        sb.append(", isAutoRedirect=").append(isAutoRedirect);
        sb.append(", metadata=").append(metadata);
        sb.append('}');
        return sb.toString();
    }
}
