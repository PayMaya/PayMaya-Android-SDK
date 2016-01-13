package com.paymaya.sdk.android.common.network;

/**
 * Created by giaquino on 10/28/15.
 */
public class Response {

    private int code;

    private String response;

    public Response(int code, String response) {
        this.code = code;
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public String getResponse() {
        return response;
    }
}
