package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ErrorCode;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Provides different helper methods for working with encryption.
 * </p>
 * <p>
 * <b>IMPORTANT:</b> This class is part of the internal API, please do not use it, because it can
 * be removed in future versions of the SDK or access to such elements could
 * be changed from 'public' to 'default' or less.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is thread safe and can be injected.
 * </p>
 */

public final class EncryptionUtils {
    private static final Logger LOGGER = Logger.getLogger(EncryptionUtils.class.getName());

    /**
     * The cypher transformation used by plenigo.
     */
    private static final String CIPHER_TRANSFORMATION = "AES/CTR/NoPadding";
    /**
     * The encryption algorithm used by plenigo.
     */
    private static final String ENCRYPTION_ALGORITHM = "AES";

    /**
     * The length of the byte array that is used
     * with the hashed data without being hexed.
     */
    private static final int IV_LENGTH = 16;
    /**
     * The length of the hexed array.
     */
    private static final int IV_HEX_LENGTH = 32;
    public static final int NUM_BITS_CSRF_TOKEN = 130;
    public static final int RADIX_CSRF_TOKEN = 32;
    /**
     * The secure random object, used to generate
     * random byte arrays.
     */
    private SecureRandom random;
    /**
     * Singleton instance.
     */
    private static final EncryptionUtils INSTANCE = new EncryptionUtils();

    /**
     * Default constructor.
     */
    private EncryptionUtils() {
        this.random = new SecureRandom();
    }

    /**
     * Singleton instance retrieval method.
     *
     * @return Singleton instance of @{link {@link com.plenigo.sdk.internal.util.EncryptionUtils}}
     */
    public static EncryptionUtils get() {
        return INSTANCE;
    }


    /**
     * Encrypt given data string with AES.
     *
     * @param secret secret phrase for encryption
     * @param data   data to encrypt
     *
     * @return encrypted string
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public String encryptWithAES(final String secret, final String data) throws PlenigoException {
       return encryptWithAES(secret, data, random.generateSeed(IV_LENGTH));
    }


    /**
     * Encrypt given data string with AES.
     *
     * @param secret               secret phrase for encryption
     * @param data                 data to encrypt
     * @param initializationVector The initialization vector
     *
     * @return encrypted string
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public String encryptWithAES(final String secret, final String data, byte[] initializationVector) throws PlenigoException {
        LOGGER.log(Level.FINEST, "Encrypting the following data: {0}", data);
        // Creating the hash of the password is important, because we need a secret
        // that is exact the amount of bits the
        // cryptographic strength in bits should be
        try {
            String keyHash = HashUtils.calculateHash(secret);
            SecretKeySpec keySpec = new SecretKeySpec(
                    HexUtils.decodeHex(keyHash), ENCRYPTION_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(initializationVector));
            byte[] cypheredData = cipher.doFinal(data.getBytes(Charset.DEFAULT));
            String encryptedData = HexUtils.encodeHexString(cypheredData)
                    + HexUtils.encodeHexString(initializationVector);
            LOGGER.log(Level.FINEST, "Encrypted data: {0}", encryptedData);
            return encryptedData;
        } catch (NoSuchAlgorithmException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (BadPaddingException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        }
    }

    /**
     * Decrypt given AES string.
     *
     * @param secret secret phrase for encryption
     * @param data   data to encrypt
     *
     * @return encrypted string
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public String decryptWithAES(final String secret, final String data) throws PlenigoException {
        byte[] iv = HexUtils.decodeHex(data.substring(data.length() - IV_HEX_LENGTH));
        String encryptedText = data.substring(0, data.length() - IV_HEX_LENGTH);
        return decryptWithAES(secret, encryptedText, iv);
    }


    /**
     * Decrypt given AES string.
     *
     * @param key                  key phrase for encryption
     * @param data                 data to encrypt
     * @param initializationVector The initialization vector
     *
     * @return encrypted string
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public String decryptWithAES(String key, String data, byte[] initializationVector) throws PlenigoException {
        LOGGER.log(Level.FINEST, "Decrypting the following data: {0}", data);
        // Creating the hash of the password is important, because we need a key
        // that is exact the amount of bits the
        // cryptographic strength in bits should be
        try {
            String keyHash = HashUtils.calculateHash(key);
            SecretKeySpec keySpec = new SecretKeySpec(HexUtils.decodeHex(keyHash), ENCRYPTION_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(initializationVector));
            String decryptedData = new String(cipher.doFinal(HexUtils.decodeHex(data)), Charset.DEFAULT);
            LOGGER.log(Level.FINEST, "Decrypted data: {0}", decryptedData);
            return decryptedData;
        } catch (NoSuchAlgorithmException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (BadPaddingException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        }
    }


    /**
     * Builds a CSRF token with a 32 bit radix.
     *
     * @return The requested CSRF token
     */
    public String createCsrfToken() {
        return new BigInteger(NUM_BITS_CSRF_TOKEN, random).toString(RADIX_CSRF_TOKEN);
    }
}
