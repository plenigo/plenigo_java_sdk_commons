package com.plenigo.sdk.internal;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link ErrorCode}.
 * </p>
 */
public class ErrorCodeTest {

    @Test
    public void testSuccessfulGet() {
        assertEquals(ErrorCode.CRYPTOGRAPHY_ERROR, ErrorCode.get(ErrorCode.CRYPTOGRAPHY_ERROR.getCode()));
    }

    @Test
    public void testGetWithUnknownValue() {
        assertEquals(ErrorCode.SERVER, ErrorCode.get("12312UNKNOW"));
    }

    @Test
    public void testGetWithNullValue() {
        assertEquals(ErrorCode.SERVER, ErrorCode.get(null));
    }


    @Test
    public void testToString() {
        for (ErrorCode errorCode : ErrorCode.values()) {
            assertNotNull(errorCode.toString());
        }
    }
}
