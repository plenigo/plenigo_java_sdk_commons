package com.plenigo.sdk.internal.exceptions;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ErrorCode;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Tests for {@link DefaultHandler}.
 */
public class DefaultHandlerTest {

    @Test
    public void testCreate() {
        assertNotNull("The creation wasnt successful", new DefaultHandler());
    }

    @Test
    public void testHandle() {
        DefaultHandler handler = new DefaultHandler();
        PlenigoException apiUrl = handler.handle(ErrorCode.CONNECTION_ERROR, "apiUrl", null);
        assertNotNull(apiUrl);
    }
}
