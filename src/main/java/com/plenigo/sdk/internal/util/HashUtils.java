package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ErrorCode;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * Provides different helper methods for working with hashing.
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
public final class HashUtils {
    /**
     * The hash algorithm for medium strength.
     */
    private static final String HASH_ALGORITHM = "MD5";

    /**
     * Hash algorithm for HMAC and SHA512.
     */
    private static final String HMAC_ALGORITHM = "HmacSHA512";

    /**
     * Default constructor.
     */
    private HashUtils() {
    }

    /**
     * This method calculates the MD5 hash
     * of a given String of data.
     *
     * @param data The data to hash
     *
     * @return the hashed string
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public static String calculateHash(final String data) throws PlenigoException {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(data.getBytes(Charset.DEFAULT));
            byte[] checksum = md.digest();
            return HexUtils.encodeHexString(checksum);
        } catch (NoSuchAlgorithmException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        }
    }

    /**
     * Generate hmac for data.
     *
     * @param data   data to create checksum for
     * @param secret secret to use for hmac
     *
     * @return generated checksum
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public static String calculateHMAC(String data, String secret) throws PlenigoException {
        try {
            Mac sha512HMAC = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(Charset.UTF8), HMAC_ALGORITHM);
            sha512HMAC.init(secretKey);
            byte[] checksum = sha512HMAC.doFinal(data.getBytes(Charset.UTF8));
            return HexUtils.encodeHexString(checksum);
        } catch (NoSuchAlgorithmException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            throw new PlenigoException(ErrorCode.CRYPTOGRAPHY_ERROR, e.getMessage(), e);
        }
    }
}
