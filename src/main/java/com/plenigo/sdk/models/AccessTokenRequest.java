package com.plenigo.sdk.models;

import java.io.Serializable;

/**
 * <p>
 * This represents an access token request, this is usually used after you get the access code
 * in order to get a token from the server that gave you the code.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is thread safe and can be injected.
 * </p>
 */
public class AccessTokenRequest implements Serializable {
    private String code;
    private String redirectUri;
    private String csrfToken;

    /**
     * This constructor is used when there is no CSRF token involved.
     *
     * @param accessCode The access code given by plenigo
     * @param redUri     A valid return URL defined in the plenigo site
     */
    public AccessTokenRequest(String accessCode, String redUri) {
        this.code = accessCode;
        this.redirectUri = redUri;
    }

    /**
     * This constructor is used when there is a CSRF token involved.
     *
     * @param accessCode The acces token given by plenigo
     * @param redUri     A valid return URL defined in the plenigo site
     * @param token      The CSRF Token used
     */
    public AccessTokenRequest(String accessCode, String redUri, String token) {
        this.code = accessCode;
        this.redirectUri = redUri;
        this.csrfToken = token;
    }

    /**
     * Returns the access code.
     *
     * @return The access code
     */
    public String getCode() {
        return code;
    }

    /**
     * A valid return URL defined in the plenigo site.
     *
     * @return The redirect URI A valid return URL defined in the plenigo site
     */
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * The CSRF Token used to communicate with the plenigo server.
     *
     * @return The CSRF Token
     */
    public String getCsrfToken() {
        return csrfToken;
    }

    @Override
    public String toString() {
        return "AccessTokenRequest{" + "code='" + code + '\'' + ", redirectUri='" + redirectUri + '\'' + ", csrfToken='" + csrfToken + '\'' + '}';
    }
}
