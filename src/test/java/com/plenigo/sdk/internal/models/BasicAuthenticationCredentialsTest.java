package com.plenigo.sdk.internal.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * <p>
 * Tests for {@link BasicAuthenticationCredentials}.
 * </p>
 */
public class BasicAuthenticationCredentialsTest {
    public static final String USERNAME = "user";
    public static final String PASSWORD = "pw";
    private BasicAuthenticationCredentials credentials;
    @Before
    public void setup(){
        credentials = new BasicAuthenticationCredentials(USERNAME, PASSWORD);
    }

    @Test
    public void testGetUserName(){
        assertEquals("Username is not correct", USERNAME, credentials.getUsername());
    }

    @Test
    public void testGetPassword(){
        assertEquals("Password is not correct", PASSWORD, credentials.getPassword());
    }
}
