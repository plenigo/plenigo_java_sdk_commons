package com.plenigo.sdk.internal.exceptions;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ApiURLs;
import com.plenigo.sdk.internal.ErrorCode;
import com.plenigo.sdk.internal.util.SdkUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * This translates error responses that can come from
 * the plenigo API or SDK into plenigo exceptions.
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
public final class ApiExceptionTranslator {
    private static final Logger LOGGER = Logger.getLogger(ApiExceptionTranslator.class.getName());
    private static final ApiExceptionTranslator INSTANCE = new ApiExceptionTranslator();
    private Map<String, Map<String, ApiExceptionInfo>> errorCodes;
    private ErrorHandler defaultHandler = new DefaultHandler();

    /**
     * Default constructor.
     */
    private ApiExceptionTranslator() {
        LOGGER.log(Level.FINEST, "Registering error codes...");
        errorCodes = new HashMap<String, Map<String, ApiExceptionInfo>>();
        Map<String, ApiExceptionInfo> productApiCodes = new LinkedHashMap<String, ApiExceptionInfo>();
        productApiCodes.put(String.valueOf(HttpURLConnection.HTTP_FORBIDDEN), new ApiExceptionInfo(ErrorCode.CANNOT_ACCESS_PRODUCT));
        productApiCodes.put(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED), new ApiExceptionInfo(ErrorCode.INVALID_SECRET_OR_COMPANY_ID));
        productApiCodes.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        errorCodes.put(ApiURLs.USER_PRODUCT_ACCESS, productApiCodes);
        Map<String, ApiExceptionInfo> queryProductApiCodes = new LinkedHashMap<String, ApiExceptionInfo>();
        queryProductApiCodes.put(String.valueOf(HttpURLConnection.HTTP_NOT_FOUND), new ApiExceptionInfo(ErrorCode.PRODUCT_NOT_FOUND));
        queryProductApiCodes.put(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED), new ApiExceptionInfo(ErrorCode.INVALID_SECRET_OR_COMPANY_ID));
        queryProductApiCodes.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        errorCodes.put(ApiURLs.GET_PRODUCT, queryProductApiCodes);
        Map<String, ApiExceptionInfo> paywallStateApiCodes = new LinkedHashMap<String, ApiExceptionInfo>();
        paywallStateApiCodes.put(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED), new ApiExceptionInfo(ErrorCode.INVALID_SECRET_OR_COMPANY_ID));
        paywallStateApiCodes.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        errorCodes.put(ApiURLs.PAYWALL_STATE, paywallStateApiCodes);
        Map<String, ApiExceptionInfo> productListApiCodes = new LinkedHashMap<String, ApiExceptionInfo>();
        productListApiCodes.put(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED), new ApiExceptionInfo(ErrorCode.INVALID_SECRET_OR_COMPANY_ID));
        productListApiCodes.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        errorCodes.put(ApiURLs.LIST_PRODUCTS, productListApiCodes);
        Map<String, ApiExceptionInfo> categoryListApiCodes = new LinkedHashMap<String, ApiExceptionInfo>();
        categoryListApiCodes.put(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED), new ApiExceptionInfo(ErrorCode.INVALID_SECRET_OR_COMPANY_ID));
        categoryListApiCodes.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        errorCodes.put(ApiURLs.LIST_CATEGORIES, categoryListApiCodes);
        Map<String, ApiExceptionInfo> getCategoryApiCodes = new LinkedHashMap<String, ApiExceptionInfo>();
        getCategoryApiCodes.put(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED), new ApiExceptionInfo(ErrorCode.INVALID_SECRET_OR_COMPANY_ID));
        getCategoryApiCodes.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        getCategoryApiCodes.put(String.valueOf(HttpURLConnection.HTTP_NOT_FOUND), new ApiExceptionInfo(ErrorCode.CATEGORY_NOT_FOUND));
        errorCodes.put(ApiURLs.GET_CATEGORY, getCategoryApiCodes);
        Map<String, ApiExceptionInfo> getMeteredData = new LinkedHashMap<String, ApiExceptionInfo>();
        getMeteredData.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        errorCodes.put(ApiURLs.USER_VIEW_MOBILE, getMeteredData);
        Map<String, ApiExceptionInfo> getProductsBought = new LinkedHashMap<String, ApiExceptionInfo>();
        getProductsBought.put(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED), new ApiExceptionInfo(ErrorCode.INVALID_SECRET_OR_COMPANY_ID));
        getProductsBought.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        errorCodes.put(ApiURLs.USER_PRODUCTS, getProductsBought);
        Map<String, ApiExceptionInfo> accessAppCustomer = new LinkedHashMap<String, ApiExceptionInfo>();
        accessAppCustomer.put(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED), new ApiExceptionInfo(ErrorCode.INVALID_SECRET_OR_COMPANY_ID));
        accessAppCustomer.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        accessAppCustomer.put(String.valueOf(HttpURLConnection.HTTP_FORBIDDEN), new ApiExceptionInfo(ErrorCode.CANNOT_ACCESS_PRODUCT));
        errorCodes.put(ApiURLs.ACCESS_APP_CUSTOMER, accessAppCustomer);
        Map<String, ApiExceptionInfo> deleteCustomerApp = new LinkedHashMap<String, ApiExceptionInfo>();
        deleteCustomerApp.put(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED), new ApiExceptionInfo(ErrorCode.INVALID_SECRET_OR_COMPANY_ID));
        deleteCustomerApp.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        errorCodes.put(ApiURLs.DELETE_CUSTOMER_APP, deleteCustomerApp);
        Map<String, ApiExceptionInfo> verifyMobileSecret = new LinkedHashMap<String, ApiExceptionInfo>();
        verifyMobileSecret.put(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED), new ApiExceptionInfo(ErrorCode.INVALID_SECRET_OR_COMPANY_ID));
        verifyMobileSecret.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        verifyMobileSecret.put(String.valueOf(HttpURLConnection.HTTP_FORBIDDEN), new ApiExceptionInfo(ErrorCode.INCORRECT_MOBILE_SECRET));
        errorCodes.put(ApiURLs.MOBILE_SECRET_VERIFY, verifyMobileSecret);
        Map<String, ApiExceptionInfo> registerExternalUser = new LinkedHashMap<String, ApiExceptionInfo>();
        registerExternalUser.put(String.valueOf(HttpURLConnection.HTTP_UNAUTHORIZED), new ApiExceptionInfo(ErrorCode.INVALID_SECRET_OR_COMPANY_ID));
        registerExternalUser.put(String.valueOf(HttpURLConnection.HTTP_BAD_REQUEST), new ApiExceptionInfo(ErrorCode.INVALID_PARAMETERS,
                new InvalidParametersHandler()));
        registerExternalUser.put(String.valueOf(HttpURLConnection.HTTP_FORBIDDEN), new ApiExceptionInfo(ErrorCode.COMPANY_NOT_QUALIFIED));
        errorCodes.put(ApiURLs.REGISTER_EXTERNAL_USER_URL, registerExternalUser);


        LOGGER.log(Level.FINEST, "Registered error codes: {0}", errorCodes);
    }


    /**
     * Singleton instance retrieval method.
     *
     * @return Singleton instance of @{link {@link com.plenigo.sdk.internal.exceptions.ApiExceptionTranslator}
     */
    public static ApiExceptionTranslator get() {
        return INSTANCE;
    }

    /**
     * Translates the API Response into a {@link PlenigoException} object.
     *
     * @param errorCode   The error code that was received
     * @param resource    The resource that was called
     * @param inputStream The response body
     *
     * @return The PlenigoException
     */
    public PlenigoException translate(String errorCode, String resource, InputStream inputStream) {
        Map<String, ApiExceptionInfo> exceptionInfoMap = errorCodes.get(resource);
        if (exceptionInfoMap != null) {
            ApiExceptionInfo exceptionInfo = exceptionInfoMap.get(errorCode);
            if (exceptionInfo != null) {
                LOGGER.log(Level.FINEST, "Found the error code information: {0}: ", exceptionInfo);
                ErrorHandler handler = exceptionInfo.getHandler();
                if (handler == null) {
                    handler = defaultHandler;
                }
                return handler.handle(exceptionInfo.getErrorCode(), resource, inputStream);
            }
        }
        String defaultMessage = "Error code [" + errorCode + "] occured while querying the '" + resource + "' API URL, "
                + "response body: [" + SdkUtils.toString(inputStream) + "]";
        LOGGER.log(Level.FINEST, "Error code {0} was not found, throwing PlenigoException with default message: {1}"
                , new Object[]{errorCode, defaultMessage});
        return new PlenigoException(errorCode, defaultMessage);
    }
}
