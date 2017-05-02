package com.plenigo.sdk.internal.services;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ApiParams;
import com.plenigo.sdk.internal.ApiResults;
import com.plenigo.sdk.internal.ApiURLs;
import com.plenigo.sdk.internal.ErrorCode;
import com.plenigo.sdk.internal.models.Address;
import com.plenigo.sdk.internal.util.HttpConfig;
import com.plenigo.sdk.internal.util.JWT;
import com.plenigo.sdk.internal.util.SdkUtils;
import com.plenigo.sdk.models.UserData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This class represents the internal user services functionality.
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
public class InternalUserApiService {
    private static final Logger LOGGER = Logger.getLogger(InternalUserApiService.class.getName());

    /**
     * @param baseUrl               The base url for the request
     * @param customerId            The customer id
     * @param secret                The company secret
     * @param companyId             The company id
     * @param testMode              The test mode
     * @param productIds            The product ids
     * @param useExternalCustomerId Flag indicating if the customer id parameter is an internal plenigo id or an external customer id
     *
     * @return a boolean indicating if the user has bought the product or not
     *
     * @throws PlenigoException if any error happens.
     */
    public boolean hasUserBought(String baseUrl, String customerId, String secret, String companyId, boolean testMode, List<String> productIds, boolean useExternalCustomerId)
            throws PlenigoException {
        Map<String, Object> params = new HashMap<String, Object>();
        //The checksum expects the parameters in this order:
        //"COMPANY_ID&USER_ID&RESOURCE_ID&TEST_MODE"
        params.put(ApiParams.CUSTOMER_ID, customerId);
        params.put(ApiParams.PRODUCT_ID, productIds);
        params.put(ApiParams.TEST_MODE, testMode);
        params.put(ApiParams.USE_EXTERNAL_CUSTOMER_ID, useExternalCustomerId);
        try {
            HttpConfig.get().getClient().get(baseUrl, ApiURLs.USER_PRODUCT_ACCESS, ApiURLs.USER_PRODUCT_ACCESS, SdkUtils.buildUrlQueryString(params)
                    , JWT.generateJWTTokenHeader(companyId, secret));
        } catch (PlenigoException pe) {
            //Forbidden means that the user has not bought the product.
            if (ErrorCode.get(pe.getResponseCode()) == ErrorCode.CANNOT_ACCESS_PRODUCT) {
                return false;
            } else {
                throw pe;
            }
        }
        return true;
    }

    /**
     * Queries the paywall service to check if its enabled, if disabled all product paywall should be disabled.
     *
     * @param companyId The company id
     * @param secret    The company secret
     * @param baseUrl   The base url for the request
     *
     * @return a boolean, true if its enabled an false otherwise
     *
     * @throws PlenigoException if any error happens
     */
    public boolean isPaywallEnabled(String baseUrl, String secret, String companyId) throws PlenigoException {
        Map<String, Object> objectMap = HttpConfig.get().getClient().get(baseUrl, ApiURLs.PAYWALL_STATE, ApiURLs.PAYWALL_STATE, null
                , JWT.generateJWTTokenHeader(companyId, secret));
        Object paywallState = objectMap.get(ApiResults.PAYWALL_STATE);
        boolean isEnabled = false;
        if (paywallState != null) {
            isEnabled = Boolean.valueOf(paywallState.toString());
        }
        return isEnabled;
    }

    /**
     * This method retrieves user data with the provided access token.
     *
     * @param companyId   The company id
     * @param secret      The company secret
     * @param url         The base url for the request
     * @param accessToken The provided access token.
     *
     * @return the user data related to the access token
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public UserData getUserData(String url, String companyId, String secret, String accessToken) throws PlenigoException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(ApiParams.ACCESS_TOKEN, accessToken);
        params.put(ApiParams.COMPANY_ID, companyId);
        LOGGER.log(Level.FINEST, "Seeking user information for company: {0}", params.get(ApiParams.COMPANY_ID));
        Map<String, Object> response = HttpConfig.get().getClient().get(url, ApiURLs.USER_PROFILE, ApiURLs.USER_PROFILE, SdkUtils.buildUrlQueryString(params)
                , JWT.generateJWTTokenHeader(companyId, secret));
        UserData userData;
        if (response.containsKey(ApiResults.ERROR)) {
            throw new PlenigoException(response.get(ApiResults.ERROR).toString(), response.get(ApiResults.DESCRIPTION).toString());
        } else {
            userData = buildUserData(response);
        }
        return userData;
    }

    /**
     * Retrieves the provided user data.
     *
     * @param response The map of the response objects
     *
     * @return The UserData object built from the
     */
    private UserData buildUserData(Map<String, Object> response) {
        String id = SdkUtils.getValueIfNotNull(response, ApiResults.USER_ID);
        String email = SdkUtils.getValueIfNotNull(response, ApiResults.EMAIL);
        String gender = SdkUtils.getValueIfNotNull(response, ApiResults.GENDER);
        String lastName = SdkUtils.getValueIfNotNull(response, ApiResults.LAST_NAME);
        String firstName = SdkUtils.getValueIfNotNull(response, ApiResults.FIRST_NAME);
        String street = SdkUtils.getValueIfNotNull(response, ApiResults.STREET);
        String additionalAddressInfo = SdkUtils.getValueIfNotNull(response, ApiResults.ADDITIONAL_ADDRESS_INFO);
        String postCode = SdkUtils.getValueIfNotNull(response, ApiResults.POST_CODE);
        String city = SdkUtils.getValueIfNotNull(response, ApiResults.CITY);
        String country = SdkUtils.getValueIfNotNull(response, ApiResults.COUNTRY);
        String username = SdkUtils.getValueIfNotNull(response, ApiResults.USERNAME);
        Address address = new Address(street, additionalAddressInfo, postCode, city, country);
        UserData userData = new UserData(id, email, gender, lastName, firstName, address, username);
        LOGGER.log(Level.FINEST, "User data to return: {0}", userData);
        return userData;
    }
}