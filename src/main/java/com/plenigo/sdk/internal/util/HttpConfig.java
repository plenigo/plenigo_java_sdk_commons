package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.internal.models.BasicAuthenticationCredentials;

/**
 * Created by rtorres on 15/12/14.
 */
public class HttpConfig {


    private RestClient client;
    private BasicAuthenticationCredentials credentials;
    private static final HttpConfig INSTANCE = new HttpConfig();

    private HttpConfig() {
        client = new RestClient();
    }

    public static HttpConfig get(){
        return INSTANCE;
    }

    public void setClient(RestClient client) {
        this.client = client;
    }

    public void setCredentials(BasicAuthenticationCredentials credentials) {
        this.credentials = credentials;
    }

    public RestClient getClient() {
        return client;
    }

    public BasicAuthenticationCredentials getCredentials() {
        return credentials;
    }
}
