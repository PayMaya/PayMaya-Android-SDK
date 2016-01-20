package com.paymaya.sdk.android;

import android.os.Parcel;

import com.paymaya.sdk.android.checkout.models.Address;
import com.paymaya.sdk.android.checkout.models.AmountDetails;
import com.paymaya.sdk.android.checkout.models.Buyer;
import com.paymaya.sdk.android.checkout.models.Checkout;
import com.paymaya.sdk.android.checkout.models.Contact;
import com.paymaya.sdk.android.checkout.models.Item;
import com.paymaya.sdk.android.checkout.models.ItemAmount;
import com.paymaya.sdk.android.checkout.models.RedirectUrl;
import com.paymaya.sdk.android.checkout.models.TotalAmount;
import com.paymaya.sdk.android.payment.models.Card;
import com.paymaya.sdk.android.payment.models.PaymentToken;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by giaquino on 11/9/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ParcelableTest {

    private Address provideAddress() {
        return new Address("ADB Avenue", "Pasig City", "Metro Manila", "1012", "PH");
    }

    private AmountDetails provideAmountDetails() {
        BigDecimal discount = new BigDecimal(0);
        BigDecimal serviceCharge = new BigDecimal(0);
        BigDecimal shippingFee = new BigDecimal(0);
        BigDecimal tax = new BigDecimal(0);
        BigDecimal subtotal = new BigDecimal(0);
        return new AmountDetails(discount, serviceCharge, shippingFee, tax, subtotal);
    }

    private Buyer provideBuyer() {
        return new Buyer("Foo", "Bar");
    }

    private Contact provideContact() {
        return new Contact("09470000000", "foo@bar.com");
    }

    private Item provideItem() {
        return new Item("Item 1", 1, provideTotalAmount());
    }

    private TotalAmount provideTotalAmount() {
        BigDecimal amount = new BigDecimal(100);
        return new TotalAmount(amount, "PHP");
    }

    private RedirectUrl provideRedirectUrl() {
        return new RedirectUrl("http://success", "http://failure", "http://cancel");
    }

    private ItemAmount provideItemAmount() {
        return new ItemAmount(new BigDecimal(100));
    }

    private Card provideCard() {
        return new Card("5123456789012346", "05", "17", "111");
    }

    private PaymentToken providePaymentToken() {
        return new PaymentToken("CnRF3ioPU6RVI1GtLrW2CIkJe5Mns4mgaofXe2tl", "created", "sandbox",
                "card", getDate("2016-01-03T08:10:57.000Z", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
                getDate("2016-01-03T08:10:57.000Z", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    }

    private Date getDate(String date, String dateTimeFormat) {
        try {
            DateFormat format = new SimpleDateFormat(dateTimeFormat);
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testPaymentTokenParcelable() {
        PaymentToken original = providePaymentToken();
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        PaymentToken parsed = new PaymentToken(parcel);
        assertEquals(original.toString(), parsed.toString());
    }

    @Test
    public void testCardParcelable() {
        Card original = provideCard();
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Card parsed = new Card(parcel);
        assertEquals(original.toString(), parsed.toString());
    }

    @Test
    public void testAddressParcelable() {
        Address original = provideAddress();
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Address parsed = new Address(parcel);
        assertEquals(original.toString(), parsed.toString());
    }

    @Test
    public void testAmountDetailsParcelable() {
        AmountDetails original = provideAmountDetails();
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AmountDetails parsed = new AmountDetails(parcel);
        assertEquals(original.toString(), parsed.toString());
    }

    @Test
    public void testBuyer() {
        Buyer original = provideBuyer();
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Buyer parsed = new Buyer(parcel);
        assertEquals(original.toString(), parsed.toString());
    }

    @Test
    public void testContact() {
        Contact original = provideContact();
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Contact parsed = new Contact(parcel);
        assertEquals(original.toString(), parsed.toString());
    }

    @Test
    public void testItem() {
        Item original = provideItem();
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Item parsed = new Item(parcel);
        assertEquals(original.toString(), parsed.toString());
    }

    @Test
    public void testItemAmount() {
        ItemAmount original = provideItemAmount();
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ItemAmount parsed = new ItemAmount(parcel);
        assertEquals(original.toString(), parsed.toString());
    }

    @Test
    public void testRedirectUrl() {
        RedirectUrl original = provideRedirectUrl();
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RedirectUrl parsed = new RedirectUrl(parcel);
        assertEquals(original.toString(), parsed.toString());
    }

    @Test
    public void testTotalAmount() {
        TotalAmount original = provideTotalAmount();
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        TotalAmount parsed = new TotalAmount(parcel);
        assertEquals(original.toString(), parsed.toString());
    }

    @Test
    public void testCheckout() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(provideItem());
        Checkout original = new Checkout(provideTotalAmount(), provideBuyer(), itemList,
                "reference_number", provideRedirectUrl());
        Parcel parcel = Parcel.obtain();
        original.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Checkout parsed = new Checkout(parcel);
        assertEquals(original.toString(), parsed.toString());
    }
}
