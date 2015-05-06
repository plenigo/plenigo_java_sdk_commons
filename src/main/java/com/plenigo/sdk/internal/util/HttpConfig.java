package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.internal.models.BasicAuthenticationCredentials;


/**
 * <p>
 * This represents an http configuration object, used to centralize http calls.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is thread safe and can be injected.
 * </p>
 */
public final class HttpConfig {


    private RestClient client;
    private BasicAuthenticationCredentials credentials;
    private static final HttpConfig INSTANCE = new HttpConfig();

    /**
     * Singleton constructor.
     */
    private HttpConfig() {
        client = new RestClient();
    }

    /**
     * Singleton instance retriever method.
     *
     * @return the singleton instance
     */
    public static HttpConfig get() {
        return INSTANCE;
    }

    /**
     * Sets the rest client to be used.
     *
     * @param client the rest client
     */
    public void setClient(RestClient client) {
        this.client = client;
    }

    /**
     * Sets the credentials to be used.
     *
     * @param credentials the credentials
     */
    public void setCredentials(BasicAuthenticationCredentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Returns the rest client to be used.
     *
     * @return the rest client
     */
    public RestClient getClient() {
        return client;
    }

    /**
     * Returns the basic authentication configuration for the SDK.
     *
     * @return the basic authentication configuration
     */
    public BasicAuthenticationCredentials getCredentials() {
        return credentials;
    }
}
