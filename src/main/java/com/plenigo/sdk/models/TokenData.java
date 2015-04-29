package com.plenigo.sdk.models;


import java.io.Serializable;

/**
 * <p>
 * The Token Data given to access user data.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is thread safe and can be injected.
 * </p>
 */
public class TokenData implements Serializable {
    private String accessToken;
    private Long expiresIn;
    private String refreshToken;
    private String state;
    private TokenType tokenType;

    /**
     * This builds a {@link com.plenigo.sdk.models.TokenData} object with the required parameters.
     *
     * @param accessTkn     The access token
     * @param expiresInTime The time where the access token will expire
     * @param refreshTkn    The refresh token given
     * @param csrfTkn       The CSRF Token
     * @param tknType       The Token Type
     */
    public TokenData(String accessTkn, Long expiresInTime, String refreshTkn, String csrfTkn, TokenType tknType) {
        this.accessToken = accessTkn;
        this.expiresIn = expiresInTime;
        this.refreshToken = refreshTkn;
        this.state = csrfTkn;
        this.tokenType = tknType;
    }

    /**
     * The access token.
     *
     * @return The access token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * The time when the token expires in seconds.
     *
     * @return The expiration time
     */
    public Long getExpiresIn() {
        return expiresIn;
    }

    /**
     * Retrieves the refresh token.
     *
     * @return The refresh token
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Retrieves The CSRF Token.
     *
     * @return The CSRF Token
     */
    public String getState() {
        return state;
    }

    /**
     * Retrieves the token type.
     *
     * @return The token type
     */
    public TokenType getTokenType() {
        return tokenType;
    }

    @Override
    public String toString() {
        return "TokenData{" + "accessToken='" + accessToken + '\'' + ", expiresIn=" + expiresIn + ", refreshToken='" + refreshToken + '\'' + ", state='"
                + state + '\'' + ", tokenType=" + tokenType + '}';
    }
}
