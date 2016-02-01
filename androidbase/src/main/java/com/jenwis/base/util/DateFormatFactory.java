package com.jenwis.base.util;

import java.text.SimpleDateFormat;

public class DateFormatFactory {
    public static SimpleDateFormat getFormatMMDD() {
        return new SimpleDateFormat("MM-dd");
    }

    public static SimpleDateFormat getFormatHHmm() {
        return new SimpleDateFormat("HH:mm");
    }

    public static SimpleDateFormat getFormatHHmmss() {
        return new SimpleDateFormat("HH:mm:ss");
    }

    public static SimpleDateFormat getFormatYYYYMMDD() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static SimpleDateFormat getFormatYMDHMS() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static SimpleDateFormat getFormatMMDDHHmm() {
        return new SimpleDateFormat("MM-dd HH:mm");
    }
}
