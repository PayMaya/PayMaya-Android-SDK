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

import com.paymaya.sdk.android.common.utils.Preconditions;

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
     * @param name         item's name
     * @param quantity     quantity
     * @param totalAmount  total amount
     * @throws NullPointerException
     */
    public Item(String name, int quantity, TotalAmount totalAmount) {
        this.name = Preconditions.checkNotNull(name, "name");
        this.quantity = Preconditions.checkNotNull(quantity, "quantity");
        this.totalAmount = Preconditions.checkNotNull(totalAmount, "totalAmount");
    }

    /**
     *
     * @return item's name
     */
    public String getName() {
        return name;
    }

    /**
     * set a new value for item's name
     *
     * @param name item's name
     * @throws NullPointerException
     */
    public void setName(String name) {
        this.name = Preconditions.checkNotNull(name, "name");
    }

    /**
     *
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * set a new value for quantity
     *
     * @param quantity
     * @throws NullPointerException
     */
    public void setQuantity(int quantity) {
        this.quantity = Preconditions.checkNotNull(quantity, "quantity");
    }

    /**
     *
     * @return sku code
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * set a new value for sku code
     *
     * @param skuCode
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    /**
     *
     * @return item's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set a new value for description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return additional item amount details
     */
    public ItemAmount getItemAmount() {
        return itemAmount;
    }

    /**
     * set a new value for item amount
     *
     * @param itemAmount
     */
    public void setItemAmount(ItemAmount itemAmount) {
        this.itemAmount = itemAmount;
    }

    /**
     *
     * @return total amount
     */
    public TotalAmount getTotalAmount() {
        return totalAmount;
    }

    /**
     * set a new value for total amount
     *
     * @param totalAmount
     * @throws NullPointerException
     */
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
