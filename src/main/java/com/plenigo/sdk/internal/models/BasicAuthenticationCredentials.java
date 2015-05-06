package com.plenigo.sdk.internal.models;

/**
 * <p>
 * This class represents the basic authentication information.
 * </p>
 * <p>
 * <b>IMPORTANT:</b> This class is part of the internal API, please do not use it, because it can
 * be removed in future versions of the SDK or access to such elements could
 * be changed from 'public' to 'default' or less.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is <b>not</b> thread safe.
 * </p>
 */
public class BasicAuthenticationCredentials {
    private String username;
    private String password;

    /**
     * Required constructor.
     *
     * @param username the username
     * @param password the password
     */
    public BasicAuthenticationCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the user name.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
