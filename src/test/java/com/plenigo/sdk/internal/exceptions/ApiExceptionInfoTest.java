package com.plenigo.sdk.internal.exceptions;

import static org.junit.Assert.assertNotNull;

import com.plenigo.sdk.internal.ErrorCode;
import org.junit.Test;

/**
 * Tests for {@link ApiExceptionInfo}.
 */
public class ApiExceptionInfoTest {

    @Test
    public void testToString() {
        ApiExceptionInfo info = new ApiExceptionInfo(ErrorCode.CANNOT_ACCESS_PRODUCT);
        assertNotNull(info);
    }
}
