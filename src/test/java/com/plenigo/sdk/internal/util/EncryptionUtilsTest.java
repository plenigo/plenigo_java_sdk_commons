package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link EncryptionUtils}.
 * </p>
 */
public class EncryptionUtilsTest {

    /**
     * This method tests an AES encryption successful case.
     */
    @Test
    public void testEncryptWithAESSuccessfulCase() throws PlenigoException {
        EncryptionUtils utils = EncryptionUtils.get();

        String key = "123456";
        String data = "pr=>12.99&cu=>EUR&ti=>Best product ever"
                + "&pi=>1234&ts=>true&tx=>1.99&sc=>2.99";
        String encryptedString = utils.encryptWithAES(key, data);
        String decryptedData = utils.decryptWithAES(key, encryptedString);
        assertEquals(data, decryptedData);
    }

    @Test
    public void testDecryptWithAESSuccessfulCase() throws PlenigoException {
        String encryptedCookie = "e114381cba6ce7873c966ffb6fd7bf23d62711a19b744c809ed31cb7e6936356d36c17f5ab8bec985fc6bc1784dc67cb9f";
        String expectedDecryptedCookie = "ci=>S9NMA0GZ5BNJts=>1398729327349";
        String decryptedCookie = EncryptionUtils.get().decryptWithAES("HKb6IdTh1WZElKDYqNhtJEY6JNWlxlfjg5Nk9bSz", encryptedCookie);
        assertEquals(expectedDecryptedCookie, decryptedCookie);
    }

    /**
     * This tests the {@link EncryptionUtils#createCsrfToken()}
     * method with invalid data.
     */
    @Test
    public void testGetCSRFToken() {
        EncryptionUtils utils = EncryptionUtils.get();
        String csrfToken = utils.createCsrfToken();
        assertNotNull(csrfToken);
    }
}
