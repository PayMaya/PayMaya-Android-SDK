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
 * Created by samfrancisco on 10/26/2015.
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
     * @param totalAmount
     * @param buyer
     * @param itemList
     * @param requestReferenceNumber
     */
    public Checkout(TotalAmount totalAmount, Buyer buyer, List<Item> itemList, String requestReferenceNumber, RedirectUrl redirectUrl) {
        this.totalAmount = checkNotNull(totalAmount, "totalAmount");
        this.buyer = checkNotNull(buyer, "buyer");
        this.itemList = checkNotNull(itemList, "items");
        this.requestReferenceNumber = checkNotNull(requestReferenceNumber, "requestReferenceNumber");
        this.redirectUrl = checkNotNull(redirectUrl, "redirectUrl");
        isAutoRedirect = false;
    }

    public TotalAmount getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(TotalAmount totalAmount) {
        this.totalAmount = checkNotNull(totalAmount, "totalAmount");
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = checkNotNull(buyer, "buyer");
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = checkNotNull(itemList, "items");
    }

    public RedirectUrl getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(RedirectUrl redirectUrl) {
        this.redirectUrl = checkNotNull(redirectUrl, "redirectUrl");
    }

    public String getRequestReferenceNumber() {
        return requestReferenceNumber;
    }

    public void setRequestReferenceNumber(String requestReferenceNumber) {
        this.requestReferenceNumber = checkNotNull(requestReferenceNumber, "requestReferenceNumber");
    }

    public boolean isAutoRedirect() {
        return isAutoRedirect;
    }

    public void setAutoRedirect(boolean isAutoRedirect) {
        this.isAutoRedirect = isAutoRedirect;
    }

    public JSONObject getMetadata() {
        return metadata;
    }

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
