package com.plenigo.sdk.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link AccessTokenRequest}.
 * </p>
 */
public class AccessTokenRequestTest {

    public static final String TOKEN = "token";
    public static final String RED_URI = "redUri";
    public static final String CODE = "code";
    private AccessTokenRequest accessTokenRequest;

    @Before
    public void setup(){
        accessTokenRequest = new AccessTokenRequest(CODE, RED_URI, TOKEN);
    }

    @Test
    public void testToString() {
        assertNotNull(new AccessTokenRequest("code", "redUri").toString());
    }

    @Test
    public void testGetCsrfToken() {
        assertEquals("CSRF token was not correct", TOKEN, accessTokenRequest.getCsrfToken());
    }

    @Test
    public void testGetRedirectUri() {
        assertEquals("Redirect uri was not correct", RED_URI, accessTokenRequest.getRedirectUri());
    }

    @Test
    public void testGetCode() {
        assertEquals("Code was not correct", CODE, accessTokenRequest.getCode());
    }
}
