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

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SSLHelper {

    public static final String PROTOCOL_TLS_V_1_2 = "TLSv1.2";

    public static void injectSSLSocketFactory(final HttpsURLConnection connection, final String protocol)
            throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance(protocol);
        sslContext.init(null, null, null);
        connection.setSSLSocketFactory(new PayMayaSSLSocketFactory(sslContext.getSocketFactory()));
    }

    private static class PayMayaSSLSocketFactory extends SSLSocketFactory {

        private SSLSocketFactory sslSocketFactory;

        public PayMayaSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
            if (null == sslSocketFactory) {
                throw new NullPointerException("SSLSocketFactory must be defined.");
            }
            this.sslSocketFactory = sslSocketFactory;
        }

        @Override
        public String[] getDefaultCipherSuites() {
            return this.sslSocketFactory.getDefaultCipherSuites();
        }

        @Override
        public String[] getSupportedCipherSuites() {
            return this.sslSocketFactory.getSupportedCipherSuites();
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
            SSLSocket sslSocket = (SSLSocket) this.sslSocketFactory.createSocket(socket, host, port, autoClose);
            sslSocket.setEnabledProtocols(sslSocket.getSupportedProtocols());
            return sslSocket;
        }

        @Override
        public Socket createSocket(String host, int port) throws IOException {
            SSLSocket sslSocket = (SSLSocket) this.sslSocketFactory.createSocket(host, port);
            sslSocket.setEnabledProtocols(sslSocket.getSupportedProtocols());
            return sslSocket;
        }

        @Override
        public Socket createSocket(String host, int port, InetAddress inetAddress, int localPort) throws IOException {
            SSLSocket sslSocket = (SSLSocket) this.sslSocketFactory.createSocket(host, port, inetAddress, localPort);
            sslSocket.setEnabledProtocols(sslSocket.getSupportedProtocols());
            return sslSocket;
        }

        @Override
        public Socket createSocket(InetAddress inetAddress, int port) throws IOException {
            SSLSocket sslSocket = (SSLSocket) this.sslSocketFactory.createSocket(inetAddress, port);
            sslSocket.setEnabledProtocols(sslSocket.getSupportedProtocols());
            return sslSocket;
        }

        @Override
        public Socket createSocket(InetAddress inetAddress, int port, InetAddress localAddress, int localPort) throws IOException {
            SSLSocket sslSocket = (SSLSocket) this.sslSocketFactory.createSocket(inetAddress, port, localAddress, localPort);
            sslSocket.setEnabledProtocols(sslSocket.getSupportedProtocols());
            return sslSocket;
        }
    }

}