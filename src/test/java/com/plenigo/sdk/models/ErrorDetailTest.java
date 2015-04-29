package com.plenigo.sdk.models;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link com.plenigo.sdk.models.ErrorDetail}.
 * </p>
 */
public class ErrorDetailTest {
    @Test
    public void testToString() {
        assertNotNull(new ErrorDetail("errorName", "errorDesc").toString());
    }
}
