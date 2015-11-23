package com.plenigo.sdk.internal.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * <p>
 * Tests for {@link Base64Util}.
 * </p>
 */
public class Base64UtilTest {
    private static final String ENCODED_DATA = "Y29tcGFueQ==";
    @Test
    public void testSuccessfulEncoding() throws Exception {
        String data = "company";
        String encodedData = Base64Util.encodeUrlSafe(data.getBytes());
        assertEquals("The data was not encoded correctly", ENCODED_DATA, encodedData);
    }
}
