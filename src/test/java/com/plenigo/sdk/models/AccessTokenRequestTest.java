package com.plenigo.sdk.models;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link AccessTokenRequest}.
 * </p>
 */
public class AccessTokenRequestTest {
    @Test
    public void testToString() {
        assertNotNull(new AccessTokenRequest("code", "redUri").toString());
    }
}
