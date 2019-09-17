package com.aditp.mdvkarch.helper;

import java.util.Locale;
import java.util.TimeZone;

/**
 * ------------------------------------------------------------------------
 *
 * @see java.security.Key type
 * ------------------------------------------------------------------------
 **/


public class CONSTANS {

    /*** @seen like | {@link Integer} */
    public static final int SAMPLE_REQ = 123;


    /*** @seen like | Key Value*/
    public static final String KEY_TOKEN = "TOKEN";


    /*** @seen like | Key Object*/
    public static final String KEY_OBJECT_EXAMPLE = "OBJECT_EXAMPLE";


    /*** @seen like | {@link String} */
    private static TimeZone tz = TimeZone.getDefault();
    public static final String KEY_USERNAME = "USERNAME";
    public static final String TIME_ZONE = tz.getDisplayName(true, TimeZone.SHORT);


}