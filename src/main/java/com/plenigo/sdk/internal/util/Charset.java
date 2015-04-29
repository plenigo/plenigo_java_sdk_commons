package com.plenigo.sdk.internal.util;

/**
 * <p>
 * This class contains the charset required by the API
 * since JDK 6 does not have them in constants.
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
public final class Charset {
    /**
     * Default constructor.
     */
    private Charset() {
    }

    /**
     * The UTF-8 Charset.
     */
    public static final String UTF8 = "UTF-8";

    /**
     * The UTF-8 Charset.
     */
    public static final String DEFAULT = UTF8;
}
