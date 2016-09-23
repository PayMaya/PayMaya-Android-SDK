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

package com.paymaya.sdk.android.common.utils;

import android.util.Log;

import com.paymaya.sdk.android.checkout.models.Address;
import com.paymaya.sdk.android.checkout.models.AmountDetails;
import com.paymaya.sdk.android.checkout.models.Buyer;
import com.paymaya.sdk.android.checkout.models.Checkout;
import com.paymaya.sdk.android.checkout.models.CheckoutError;
import com.paymaya.sdk.android.checkout.models.CheckoutErrorDetails;
import com.paymaya.sdk.android.checkout.models.CheckoutErrorParameter;
import com.paymaya.sdk.android.checkout.models.Contact;
import com.paymaya.sdk.android.checkout.models.Item;
import com.paymaya.sdk.android.checkout.models.ItemAmount;
import com.paymaya.sdk.android.checkout.models.RedirectUrl;
import com.paymaya.sdk.android.checkout.models.TotalAmount;
import com.paymaya.sdk.android.payment.PayMayaPaymentException;
import com.paymaya.sdk.android.payment.models.Card;
import com.paymaya.sdk.android.payment.models.PaymentToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class JSONUtils {

    private JSONUtils() {
        throw new RuntimeException("Cannot create instance of this class");
    }

    public static CheckoutError fromJSONCheckoutError(String json) throws JSONException {
        CheckoutError error = new CheckoutError();
        JSONObject root = new JSONObject(json);
        error.setCode(root.getInt("code"));
        error.setMessage(root.getString("message"));
        if (root.has("parameters")) {
            JSONArray child = root.getJSONArray("parameters");
            List<CheckoutErrorParameter> parameters = new ArrayList<>(child.length());
            for (int i = 0, size = child.length(); i < size; i++) {
                JSONObject item = child.getJSONObject(i);
                CheckoutErrorParameter parameter = new CheckoutErrorParameter(item.getString("field"),
                        item.getString("description"));
                parameters.add(parameter);
            }
            error.setParameters(parameters);
        }
        if (root.has("details")) {
            JSONObject child = root.getJSONObject("details");
            CheckoutErrorDetails details = new CheckoutErrorDetails(child.getString("responseCode"),
                    child.getString("responseDescription"), child.getString("requestReferenceNumber"));
            error.setDetails(details);
        }
        return error;
    }

    public static JSONObject toJSON(Checkout checkout) throws JSONException {
        JSONObject root = new JSONObject();
        root.put("totalAmount", toJSON(checkout.getTotalAmount()));
        root.put("buyer", toJSON(checkout.getBuyer()));
        root.put("items", toJSON(checkout.getItemList()));
        root.put("redirectUrl", toJSON(checkout.getRedirectUrl()));
        root.put("requestReferenceNumber", checkout.getRequestReferenceNumber());
        root.put("isAutoRedirect", false);
        root.put("metadata", checkout.getMetadata());
        return root;
    }

    public static JSONObject toJSON(Card card) throws JSONException {
        if (card != null) {
            JSONObject root = new JSONObject();

            root.put("number", card.getNumber());
            root.put("expMonth", card.getExpMonth());
            root.put("expYear", card.getExpYear());
            root.put("cvc", card.getCvc());

            JSONObject parentRoot = new JSONObject();
            parentRoot.put("card", root);

            return parentRoot;
        }
        return null;
    }

    public static PaymentToken fromJSONPaymentToken(String json) throws JSONException {
        final String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

        JSONObject root = new JSONObject(json);
        Log.d("@fromJsonPaymentToken", "Json root = " + root.toString());

        if (root.has("error")) {
            throw new PayMayaPaymentException(root.getString("error"));
        }

        PaymentToken paymentToken = new PaymentToken();
        paymentToken.setPaymentTokenId(root.getString("paymentTokenId"));
        paymentToken.setState(root.getString("state"));
        paymentToken.setEnv(root.optString("env"));
        paymentToken.setType(root.optString("type"));

        paymentToken.setCreatedAt(DateUtils.formatDate(root.getString("createdAt"), dateTimeFormat));
        paymentToken.setUpdatedAt(DateUtils.formatDate(root.getString("updatedAt"), dateTimeFormat));
        return paymentToken;
    }

    public static JSONObject toJSON(TotalAmount total) throws JSONException {
        if (total != null) {
            JSONObject root = new JSONObject();
            root.put("value", total.getValue().toPlainString());
            root.put("currency", total.getCurrency());
            root.put("details", toJSON(total.getAmountDetails()));
            return root;
        }
        return null;
    }

    private static JSONObject toJSON(AmountDetails details) throws JSONException {
        if (details != null) {
            JSONObject root = new JSONObject();
            root.put("discount", details.getDiscount().toPlainString());
            root.put("serviceCharge", details.getServiceCharge().toPlainString());
            root.put("shippingFee", details.getShippingFee().toPlainString());
            root.put("tax", details.getTax().toPlainString());
            root.put("subtotal", details.getSubtotal().toPlainString());
            return root;
        }
        return null;
    }

    public static JSONObject toJSON(Buyer buyer) throws JSONException {
        if (buyer != null) {
            JSONObject root = new JSONObject();
            root.put("firstName", buyer.getFirstName());
            root.put("middleName", buyer.getMiddleName());
            root.put("lastName", buyer.getLastName());
            root.put("contact", toJSON(buyer.getContact()));
            root.put("shippingAddress", toJSON(buyer.getShippingAddress()));
            root.put("billingAddress", toJSON(buyer.getBillingAddress()));
            root.put("ipAddress", buyer.getIpAddress());
            return root;
        }
        return null;
    }

    public static JSONObject toJSON(Contact contact) throws JSONException {
        if (contact != null) {
            JSONObject root = new JSONObject();
            root.put("phone", contact.getPhone());
            root.put("email", contact.getEmail());
            return root;
        }
        return null;
    }

    public static JSONObject toJSON(Address address) throws JSONException {
        if (address != null) {
            JSONObject root = new JSONObject();
            root.put("line1", address.getLine1());
            root.put("line2", address.getLine2());
            root.put("city", address.getCity());
            root.put("state", address.getState());
            root.put("zipCode", address.getZipCode());
            root.put("countryCode", address.getCountryCode());
            return root;
        }
        return null;
    }

    public static JSONObject toJSON(Item item) throws JSONException {
        if (item != null) {
            JSONObject root = new JSONObject();
            root.put("name", item.getName());
            root.put("quantity", String.valueOf(item.getQuantity()));
            root.put("code", item.getSkuCode());
            root.put("description", item.getDescription());
            root.put("amount", toJSON(item.getItemAmount()));
            root.put("totalAmount", toJSON(item.getTotalAmount()));
            return root;
        }
        return null;
    }

    public static JSONObject toJSON(ItemAmount amount) throws JSONException {
        if (amount != null) {
            JSONObject root = new JSONObject();
            root.put("value", amount.getValue().toPlainString());
            root.put("details", toJSON(amount.getDetails()));
            return root;
        }
        return null;
    }

    public static JSONArray toJSON(List<Item> itemList) throws JSONException {
        if (itemList != null && itemList.size() > 0) {
            JSONArray array = new JSONArray();
            for (Item item : itemList) {
                array.put(toJSON(item));
            }
            return array;
        }
        return null;
    }

    public static JSONObject toJSON(RedirectUrl urls) throws JSONException {
        if (urls != null) {
            JSONObject root = new JSONObject();
            root.put("success", urls.getSuccessUrl());
            root.put("failure", urls.getFailureUrl());
            root.put("cancel", urls.getCancelUrl());
            return root;
        }
        return null;
    }
}
