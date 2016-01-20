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

package com.paymaya.sdk.android.common.network;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

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
