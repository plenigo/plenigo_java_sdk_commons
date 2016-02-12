package com.plenigo.sdk.internal.services;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ApiParams;
import com.plenigo.sdk.internal.ApiResults;
import com.plenigo.sdk.internal.ApiURLs;
import com.plenigo.sdk.internal.util.EncryptionUtils;
import com.plenigo.sdk.internal.util.HttpConfig;
import com.plenigo.sdk.internal.util.JWT;
import com.plenigo.sdk.internal.util.SdkUtils;
import com.plenigo.sdk.models.AccessTokenRequest;
import com.plenigo.sdk.models.RefreshTokenRequest;
import com.plenigo.sdk.models.TokenData;
import com.plenigo.sdk.models.TokenGrantType;
import com.plenigo.sdk.models.TokenType;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * This class represents the internal token services functionality.
 * </p>
 * <p>
 * <b>IMPORTANT:</b> This class is part of the internal API, please do not use it, because it can
 * be removed in future versions of the SDK or access to such elements could
 * be changed from 'public' to 'default' or less.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is <b>not</b> thread safe.
 * </p>
 */
public class InternalTokenApiService {
    private static final Logger LOGGER = Logger.getLogger(InternalTokenApiService.class.getName());

    /**
     * Requests an access token with the given info, this method is usually called
     * after the access code has been given. With this token you can request for user
     * information.
     *
     * @param companyId The company id
     * @param secret    The company secret
     * @param url       The base url for the request
     * @param request   The request object with all the parameters necessary for an access token
     *
     * @return The token data that is retrieved from the response
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public TokenData getAccessToken(String companyId, String secret, String url, AccessTokenRequest request) throws PlenigoException {
        LOGGER.log(Level.FINEST, "Requesting access token with the following data: {0}", request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(ApiParams.TOKEN_GRANT_TYPE, TokenGrantType.AUTHORIZATION_CODE.getName());
        params.put(ApiParams.OAUTH_ACCESS_CODE, request.getCode());
        params.put(ApiParams.REDIRECT_URI, request.getRedirectUri());
        SdkUtils.addIfNotNull(params, ApiParams.STATE, request.getCsrfToken());
        Map<String, Object> result = HttpConfig.get().getClient().post(url, ApiURLs.GET_ACCESS_TOKEN, ApiURLs.GET_ACCESS_TOKEN,
                SdkUtils.buildUrlQueryString(params), null, JWT.generateJWTTokenHeader(companyId, secret));
        return validateAndBuildResponse(request.getCsrfToken(), result);
    }

    /**
     * Requests another access token with the provided refresh token, this is usually used
     * when the access token is expired, and with it you can request for user information.
     *
     * @param companyId The company id
     * @param secret    The company secret
     * @param url       The base url for the request
     * @param request The request information necessary for a refreshed access token
     *
     * @return The Token data that is retrieved from the response
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public TokenData getNewAccessToken(String companyId, String secret, String url, RefreshTokenRequest request) throws PlenigoException {
        LOGGER.log(Level.FINEST, "Refreshing access token with the following data: {0}", request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(ApiParams.TOKEN_GRANT_TYPE, TokenGrantType.REFRESH_TOKEN.getName());
        params.put(ApiParams.REFRESH_TOKEN, request.getRefreshToken());
        SdkUtils.addIfNotNull(params, ApiParams.STATE, request.getCsrfToken());
        Map<String, Object> result = HttpConfig.get().getClient().post(url, ApiURLs.GET_ACCESS_TOKEN, ApiURLs.REFRESH_ACCESS_TOKEN
                , SdkUtils.buildUrlQueryString(params), null, JWT.generateJWTTokenHeader(companyId, secret));
        result.put(ApiResults.REFRESH_TOKEN, request.getRefreshToken());
        return validateAndBuildResponse(request.getCsrfToken(), result);
    }

    /**
     * This method validates a given token response and calls the method
     * that builds the {@link TokenData} object.
     *
     * @param state  The CSRF Token
     * @param result The resulting map of parameters
     *
     * @return The TokenData extracted from the map of parameters
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    private TokenData validateAndBuildResponse(String state, Map<String, Object> result) throws PlenigoException {
        Object responseState = result.get(ApiResults.STATE);
        if (result.containsKey(ApiResults.ERROR)) {
            throw new PlenigoException(result.get(ApiResults.ERROR).toString(), result.get(ApiResults.ERROR_DESCRIPTION).toString());
        } else if (state != null && (responseState == null || !state.equals(responseState))) {
            LOGGER.log(Level.FINEST, "State used for the request and the response were different!: expected state: {0}, result state: {1}"
                    , new Object[]{state, responseState});
            throw new IllegalArgumentException("The request and response CSRF Token are different! request=" + state + " ; response=" + result
                    .get(ApiResults.STATE));
        } else {
            return buildTokenData(result);
        }
    }

    /**
     * Builds the {@link TokenData} from the mapped JSON request.
     *
     * @param result The {@link TokenData} object with the info from the result map
     *
     * @return A {@link TokenData} object
     */
    private TokenData buildTokenData(Map<String, Object> result) {
        String accessToken = result.get(ApiResults.ACCESS_TOKEN).toString();
        Long expiresIn = (Long) result.get(ApiResults.EXPIRES_IN);
        String refreshToken = null;
        if (result.containsKey(ApiResults.REFRESH_TOKEN)) {
            refreshToken = result.get(ApiResults.REFRESH_TOKEN).toString();
        }
        String state = null;
        if (result.get(ApiResults.STATE) != null) {
            state = result.get(ApiResults.STATE).toString();
        }
        TokenData tokenData = new TokenData(accessToken, expiresIn, refreshToken, state, TokenType.BEARER);
        LOGGER.log(Level.FINEST, "Token data to return {0}", tokenData);
        return tokenData;
    }

    /**
     * This method generates the cross-site request forgery (CSRF) token.
     *
     * @return a strong cross-site request forgery token
     */
    public String createCsrfToken() {
        return EncryptionUtils.get().createCsrfToken();
    }
}
