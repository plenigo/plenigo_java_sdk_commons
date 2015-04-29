package com.plenigo.sdk.internal.exceptions;


import com.plenigo.sdk.internal.ErrorCode;

/**
 * <p>
 * This class contains Exception information used to create
 * the PlenigoException object.
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
public final class ApiExceptionInfo {
    private ErrorCode errorCode;
    private ErrorHandler handler;

    /**
     * This creates an ApiExceptionInfo object with the provided errorCode
     * and uses the default handler.
     *
     * @param errCode The provided error code
     */
    public ApiExceptionInfo(ErrorCode errCode) {
        this(errCode, null);
    }

    /**
     * This creates an ApiExceptionInfo object with the provided errorCode
     * and handler.
     *
     * @param errCode    The provided error code
     * @param rspHandler The provided handler
     */
    public ApiExceptionInfo(ErrorCode errCode, ErrorHandler rspHandler) {
        this.errorCode = errCode;
        this.handler = rspHandler;
    }

    /**
     * Returns the error code.
     *
     * @return The error code
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * Returns the handler.
     *
     * @return The handler
     */
    public ErrorHandler getHandler() {
        return handler;
    }

    @Override
    public String toString() {
        return "ApiExceptionInfo{" + "errorCode=" + errorCode + ", handler=" + handler + '}';
    }
}
