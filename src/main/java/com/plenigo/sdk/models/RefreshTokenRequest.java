package com.plenigo.sdk.models;

import java.io.Serializable;

/**
 * <p>
 * This object contains the data required to make a refresh access token request.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is thread safe and can be injected.
 * </p>
 */
public class RefreshTokenRequest implements Serializable {
    private String refreshToken;
    private String csrfToken;

    /**
     * This constructs a {@link com.plenigo.sdk.models.RefreshTokenRequest} object.
     *
     * @param refreshTkn The refresh token given previously by the access token request
     * @param csrfTkn    The CSRF Token
     */
    public RefreshTokenRequest(String refreshTkn, String csrfTkn) {
        this.refreshToken = refreshTkn;
        this.csrfToken = csrfTkn;
    }

    /**
     * This constructs a {@link com.plenigo.sdk.models.RefreshTokenRequest} object.
     *
     * @param refreshTkn The refresh token given by the access token request
     */
    public RefreshTokenRequest(String refreshTkn) {
        this(refreshTkn, null);
    }

    /**
     * The refresh token given by the access token request.
     *
     * @return The refresh token
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * CSRF Token.
     *
     * @return The CSRF token
     */
    public String getCsrfToken() {
        return csrfToken;
    }

    @Override
    public String toString() {
        return "RefreshTokenRequest{" + "refreshToken='" + refreshToken + '\'' + ", csrfToken='" + csrfToken + '\'' + '}';
    }
}
