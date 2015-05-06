package com.plenigo.sdk.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link RefreshTokenRequest}.
 * </p>
 */
public class RefreshTokenRequestTest {
    public static final String REFRESH_TKN = "refreshTkn";
    public static final String TKN = "tkn";
    private RefreshTokenRequest refreshTokenRequest;

    @Before
    public void setup() {
        refreshTokenRequest = new RefreshTokenRequest(REFRESH_TKN, TKN);
    }

    @Test
    public void testGetRefreshToken() {
        assertEquals("Refresh token was not correct", REFRESH_TKN, refreshTokenRequest.getRefreshToken());
    }

    @Test
    public void testGetCsrfToken() {
        assertEquals("CSRF token was not correct", TKN, refreshTokenRequest.getCsrfToken());
    }

    @Test
    public void testToString() {
        assertNotNull(new RefreshTokenRequest("refreshTkn").toString());
    }
}
