package com.plenigo.sdk.internal;

/**
 * <p>
 * The URLs that are used internally by the SDK.
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
public final class ApiURLs {

    /**
     * Default constructor.
     */
    private ApiURLs() {
    }

    /*
    * Version used for all API URLs from this class.
    * */

    private static final String VERSION = "v1";

    /**
     * Base API URL for plenigo service interface.
     */
    private static final String API_BASE_URL = "/api/" + VERSION;

    /**
     * Default plenigo URL.
     */
    public static final String DEFAULT_PLENIGO_URL = "https://plenigo.com";

    /**
     * This URL is used to check if an user has access to a product.
     * This is usually used to see if a user has bought a product or not.
     */
    public static final String USER_PRODUCT_ACCESS = API_BASE_URL + "/user/product";

    /**
     * This URL is used to get user information with the given access token.
     */
    public static final String USER_PROFILE = API_BASE_URL + "/user/profile";

    /**
     * This URL is used to retrieve an access token,
     * this is usually called after an access code has been given.
     */
    public static final String GET_ACCESS_TOKEN = API_BASE_URL + "/oauth2/verify";

    /**
     * This URL is used to refresh an access token,
     * this is usually called after an access token has been expired
     * and the third party application still needs to access the user data.
     */
    public static final String REFRESH_ACCESS_TOKEN = API_BASE_URL + "/oauth2/renew";

    /**
     * This URL is used to retrieve product information.
     */
    public static final String GET_PRODUCT = API_BASE_URL + "/product";


    /**
     * This URL is used to check the state of the paywall (enabled or disabled).
     */
    public static final String PAYWALL_STATE = API_BASE_URL + "/paywall/state";

    /**
     * This URL is used to retrieve a list of products.
     */
    public static final String LIST_PRODUCTS = API_BASE_URL + "/products";


    /**
     * This URL is used to retrieve a list of categories.
     */
    public static final String LIST_CATEGORIES = API_BASE_URL + "/categories";


    /**
     * This URL is used to retrieve category information.
     */
    public static final String GET_CATEGORY = API_BASE_URL + "/category";

    /**
     * This URL is used to retrieve metered user information.
     */
    public static final String USER_VIEW_MOBILE = API_BASE_URL + "/user/view/mobile";


    /**
     * This url is the initial URL for oauth2 authentication.
     */
    public static final String OAUTH_LOGIN = API_BASE_URL + "/oauth2/auth";
}