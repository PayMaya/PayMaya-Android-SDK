package com.paymaya.sdk.android.checkout.models;

import android.os.Parcel;
import android.os.Parcelable;

import static com.paymaya.sdk.android.common.utils.Preconditions.checkNotNull;

/**
 * Created by samfrancisco on 10/26/2015.
 */
public final class RedirectUrl implements Parcelable {

    private String successUrl;
    private String failureUrl;
    private String cancelUrl;

    /**
     * @param successUrl
     * @param failureUrl
     * @param cancelUrl
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

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = checkNotNull(successUrl, "successUrl");
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = checkNotNull(failureUrl, "failureUrl");
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

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
