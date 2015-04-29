package com.plenigo.sdk.internal.models;

/**
 * Created by rtorres on 15/12/14.
 */
public class BasicAuthenticationCredentials {
    private String username;
    private String password;

    public BasicAuthenticationCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
