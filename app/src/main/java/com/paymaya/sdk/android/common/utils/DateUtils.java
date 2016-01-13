package com.paymaya.sdk.android.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jadeantolingaa on 12/8/15.
 */
public class DateUtils {

    public static Date formatDate(String date, String dateTimeFormat) {
        try {
            DateFormat format = new SimpleDateFormat(dateTimeFormat);
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
