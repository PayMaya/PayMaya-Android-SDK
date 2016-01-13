package com.paymaya.sdk.android.common.network;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by giaquino on 10/27/15.
 */
public class Request {

    public enum Method {
        GET, POST
    }

    private Method method;

    private URL url;

    private byte[] body;

    private Map<String, String> headers;

    public Request(Method method, String url) {
        this.method = method;
        try {
            this.url = new URL(url);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public Request(Method method, String url, byte[] body, Map<String, String> headers) {
        this.method = method;
        try {
            this.url = new URL(url);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        this.body = body;
        this.headers = headers;
    }

    public Method getMethod() {
        return method;
    }

    public URL getUrl() {
        return url;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
