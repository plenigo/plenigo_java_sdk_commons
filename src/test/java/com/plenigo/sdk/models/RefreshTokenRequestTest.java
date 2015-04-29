package com.plenigo.sdk.models;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link RefreshTokenRequest}.
 * </p>
 */
public class RefreshTokenRequestTest {
    @Test
    public void testToString() {
        assertNotNull(new RefreshTokenRequest("refreshTkn", "tkn").toString());
    }
}
