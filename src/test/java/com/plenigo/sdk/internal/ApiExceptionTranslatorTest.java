package com.plenigo.sdk.internal;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.exceptions.ApiExceptionTranslator;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link ApiExceptionTranslator}.
 * </p>
 */
public class ApiExceptionTranslatorTest {

    @Test
    public void testDefaultHandlerSuccesfulCase() {
        String errorCode = ErrorCode.INVALID_SECRET_OR_COMPANY_ID.getCode();
        String apiUrl = ApiURLs.USER_PRODUCT_ACCESS;
        PlenigoException ex = ApiExceptionTranslator.get().translate(errorCode, apiUrl, null);
        assertNotNull(ex);
        assertNotNull(ex.getResponseCode());
        assertNotNull(ex.getMessage());
    }

    @Test
    public void testErrorCodeWithoutHandlerSuccesfulCase() {
        String apiUrl = ApiURLs.USER_PRODUCT_ACCESS;
        PlenigoException ex = ApiExceptionTranslator.get().translate("403", apiUrl, null);
        assertNotNull(ex);
        assertNotNull(ex.getResponseCode());
        assertNotNull(ex.getMessage());
    }

}
