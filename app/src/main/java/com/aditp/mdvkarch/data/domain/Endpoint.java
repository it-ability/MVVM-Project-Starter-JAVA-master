package com.aditp.mdvkarch.data.domain;

/**
 * for better experience , move BASE_URL to BuildConfig class
 *
 * @see com.aditp.mdvkarch.BuildConfig
 */
public final class Endpoint {

    public static final String BASE_URL = "https://api.github.com/";
    public static final int CONNECTION_RETRY_TIMES = 1; // start from 0


    /*** {@link Integer}*/
    static final int NO_CONNECTION = 0;
    static final int BAD_REQUEST = 400;
    static final int WRONG_METHOD = 405;
    static final int INTERNAL_SERVER_ERROR = 500;


}
