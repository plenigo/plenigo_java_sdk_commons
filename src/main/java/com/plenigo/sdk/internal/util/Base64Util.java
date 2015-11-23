package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;

import javax.xml.bind.DatatypeConverter;


/**
 * <p>
 * Provides help for working with base 64 encoding.
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
public final class Base64Util {

    /**
     * Private utility constructor.
     */
    private Base64Util() {

    }

    /**
     * Encodes with url safety using base 64.
     *
     * @param data the data to encode
     *
     * @return encoded data
     *
     * @throws PlenigoException if any encoding exception happens
     */
    public static String encodeUrlSafe(byte[] data) throws PlenigoException {
        try {
            byte[] encode = DatatypeConverter.printBase64Binary(data).getBytes(Charset.DEFAULT);
            for (int i = 0; i < encode.length; i++) {
                if (encode[i] == '+') {
                    encode[i] = '-';
                } else if (encode[i] == '/') {
                    encode[i] = '_';
                }
            }
            return new String(encode);
        } catch (Exception e) {
            throw new PlenigoException("An error occured while encoding the data", e);
        }
    }
}
