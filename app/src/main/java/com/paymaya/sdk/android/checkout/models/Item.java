package com.paymaya.sdk.android.checkout.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.paymaya.sdk.android.common.utils.Preconditions;

/**
 * Created by samfrancisco on 10/26/2015.
 */
public class Item implements Parcelable {

    private String name;
    private int quantity;
    private String skuCode;
    private String description;
    private ItemAmount itemAmount;
    private TotalAmount totalAmount;

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public Item(Parcel in) {
        name = in.readString();
        quantity = in.readInt();
        skuCode = in.readString();
        description = in.readString();
        itemAmount = in.readParcelable(ItemAmount.class.getClassLoader());
        totalAmount = in.readParcelable(TotalAmount.class.getClassLoader());
    }

    /**
     * @param name
     * @param quantity
     * @param totalAmount
     */
    public Item(String name, int quantity, TotalAmount totalAmount) {
        this.name = Preconditions.checkNotNull(name, "name");
        this.quantity = Preconditions.checkNotNull(quantity, "quantity");
        this.totalAmount = Preconditions.checkNotNull(totalAmount, "totalAmount");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Preconditions.checkNotNull(name, "name");
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Preconditions.checkNotNull(quantity, "quantity");
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemAmount getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(ItemAmount itemAmount) {
        this.itemAmount = itemAmount;
    }

    public TotalAmount getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(TotalAmount totalAmount) {
        this.totalAmount = Preconditions.checkNotNull(totalAmount, "totalAmount");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(quantity);
        dest.writeString(skuCode);
        dest.writeString(description);
        dest.writeParcelable(itemAmount, flags);
        dest.writeParcelable(totalAmount, flags);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("name='").append(name).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", skuCode='").append(skuCode).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", itemAmount=").append(itemAmount);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append('}');
        return sb.toString();
    }
}
