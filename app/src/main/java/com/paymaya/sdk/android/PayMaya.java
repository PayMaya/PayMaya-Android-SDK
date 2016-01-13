package com.paymaya.sdk.android;

/**
 * Created by giaquino on 1/6/16.
 */
public class PayMaya {

    public static final int ENVIRONMENT_SANDBOX = 0;
    public static final int ENVIRONMENT_PRODUCTION = 1;

    private static int environment;

    public static void init(int environment) {
        PayMaya.environment = environment;
    }

    public static int getEnvironment() {
        return environment;
    }
}
