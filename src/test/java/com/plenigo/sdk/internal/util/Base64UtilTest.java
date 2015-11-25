package com.plenigo.sdk.internal.util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * <p>
 * Tests for {@link Base64Util}.
 * </p>
 */
public class Base64UtilTest {
    private static final String ENCODED_DATA = "Y29tcGFueQ==";
    private static final String ENCODED_DATA_SPECIAL_CHARS = "c3ViamVjdHM_JSsv";

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = Base64Util.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testSuccessfulEncoding() throws Exception {
        String data = "company";
        String encodedData = Base64Util.encodeUrlSafe(data.getBytes());
        assertEquals("The data was not encoded correctly", ENCODED_DATA, encodedData);
    }

    @Test
    public void testSuccessfulEncodingWithSpecialChars() throws Exception {
        String data = "subjects?%+/";
        String encodedData = Base64Util.encodeUrlSafe(data.getBytes());
        assertEquals("The data was not encoded correctly", ENCODED_DATA_SPECIAL_CHARS, encodedData);
    }
}
