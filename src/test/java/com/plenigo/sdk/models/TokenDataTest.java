package com.plenigo.sdk.models;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link TokenData}.
 * </p>
 */
public class TokenDataTest {
    @Test
    public void testToString() {
        assertNotNull(new TokenData("accessTkn", 1L, "refreshTkn", "csrfTkn", TokenType.BEARER).toString());
    }
}
