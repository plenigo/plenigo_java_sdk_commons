package com.plenigo.sdk.internal.exceptions;


import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ErrorCode;

import java.io.InputStream;

/**
 * <p>
 * This interface is used to implement a {@link PlenigoException}
 * handler with the given parameters.
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
public interface ErrorHandler {
    /**
     * This method handles error information and converts it into a {@link PlenigoException}.
     *
     * @param errorCode   The error code
     * @param apiUrl      The API Url
     * @param inputStream The inputStream
     *
     * @return A {@link PlenigoException} object
     */
    PlenigoException handle(ErrorCode errorCode, String apiUrl, InputStream inputStream);
}
