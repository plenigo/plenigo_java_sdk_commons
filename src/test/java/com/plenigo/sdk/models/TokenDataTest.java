package com.plenigo.sdk.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link TokenData}.
 * </p>
 */
public class TokenDataTest {
    public static final String ACCESS_TKN = "accessTkn";
    public static final Long EXPIRES_IN_TIME = 1L;
    public static final String REFRESH_TKN = "refreshTkn";
    public static final String CSRF_TKN = "csrfTkn";
    public static final TokenType BEARER = TokenType.BEARER;
    private TokenData tokenData;

    @Before
    public void setup() {
        tokenData = new TokenData(ACCESS_TKN, EXPIRES_IN_TIME, REFRESH_TKN, CSRF_TKN, BEARER);
    }

    @Test
    public void testGetAccessToken() {
        assertEquals("Access token is not correct", ACCESS_TKN, tokenData.getAccessToken());
    }

    @Test
    public void testGetExpiresIn() {
        assertEquals("Expires in time is not correct", EXPIRES_IN_TIME, tokenData.getExpiresIn());
    }

    @Test
    public void testGetRefreshToken() {
        assertEquals("Refresh token is not correct", REFRESH_TKN, tokenData.getRefreshToken());
    }

    @Test
    public void testGetState() {
        assertEquals("State is not correct", CSRF_TKN, tokenData.getState());
    }

    @Test
    public void testGetTokenType() {
        assertEquals("Bearer is not correct", BEARER, tokenData.getTokenType());
    }

    @Test
    public void testToString() {
        assertNotNull(new TokenData("accessTkn", 1L, "refreshTkn", "csrfTkn", TokenType.BEARER).toString());
    }
}
