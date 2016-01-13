package com.paymaya_sdk_android;

import android.os.Parcel;

import com.voyagerinnovation.paymaya_sdk_android_checkout.checkout.models.Address;
import com.voyagerinnovation.paymaya_sdk_android_checkout.checkout.models.AmountDetails;
import com.voyagerinnovation.paymaya_sdk_android_checkout.checkout.models.Buyer;
import com.voyagerinnovation.paymaya_sdk_android_checkout.checkout.models.Checkout;
import com.voyagerinnovation.paymaya_sdk_android_checkout.checkout.models.Contact;
import com.voyagerinnovation.paymaya_sdk_android_checkout.checkout.models.Item;
import com.voyagerinnovation.paymaya_sdk_android_checkout.checkout.models.ItemAmount;
import com.voyagerinnovation.paymaya_sdk_android_checkout.checkout.models.RedirectUrl;
import com.voyagerinnovation.paymaya_sdk_android_checkout.checkout.models.TotalAmount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.RobolectricGradleTestRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
