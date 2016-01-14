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
 * All of the redirect urls are optional when creating a checkout.
 * If none of them are provided, the buyer will be redirected to
 * the merchant's homepage url (defined during the on-boarding process)
 * once the payment process is done.
 */
public final class RedirectUrl implements Parcelable {

    private String successUrl;
    private String failureUrl;
    private String cancelUrl;

    /**
     * @param successUrl success url
     * @param failureUrl failure url
     * @param cancelUrl  cancel url
     */
    public RedirectUrl(String successUrl, String failureUrl, String cancelUrl) {
        this.successUrl = checkNotNull(successUrl, "successUrl");
        this.failureUrl = checkNotNull(failureUrl, "failureUrl");
        this.cancelUrl = checkNotNull(cancelUrl, "cancelUrl");
    }

    public RedirectUrl(Parcel in) {
        successUrl = in.readString();
        failureUrl = in.readString();
        cancelUrl = in.readString();
    }

    public static final Creator<RedirectUrl> CREATOR = new Creator<RedirectUrl>() {
        @Override
        public RedirectUrl createFromParcel(Parcel in) {
            return new RedirectUrl(in);
        }

        @Override
        public RedirectUrl[] newArray(int size) {
            return new RedirectUrl[size];
        }
    };

    /**
     *
     * @return success url
     */
    public String getSuccessUrl() {
        return successUrl;
    }

    /**
     * set a new value for success url
     *
     * @param successUrl
     * @throws NullPointerException
     */
    public void setSuccessUrl(String successUrl) {
        this.successUrl = checkNotNull(successUrl, "successUrl");
    }

    /**
     *
     * @return failure url
     */
    public String getFailureUrl() {
        return failureUrl;
    }

    /**
     * set a new value for failure url
     *
     * @param failureUrl
     * @throws NullPointerException
     */
    public void setFailureUrl(String failureUrl) {
        this.failureUrl = checkNotNull(failureUrl, "failureUrl");
    }

    /**
     *
     * @return cancel url
     */
    public String getCancelUrl() {
        return cancelUrl;
    }

    /**
     * set a new value for cancel url
     *
     * @param cancelUrl
     * @throws NullPointerException
     */
    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = checkNotNull(cancelUrl, "cancelUrl");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(successUrl);
        dest.writeString(failureUrl);
        dest.writeString(cancelUrl);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RedirectUrl{");
        sb.append("cancelUrl='").append(cancelUrl).append('\'');
        sb.append(", failureUrl='").append(failureUrl).append('\'');
        sb.append(", successUrl='").append(successUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
