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
    private static final String VERSION = "v2";

    /**
     * Base API URL for plenigo service interface.
     */
    private static final String API_BASE_URL = "/api/" + VERSION;

    /**
     * Default plenigo URL.
     */
    public static final String DEFAULT_PLENIGO_URL = "https://api.plenigo.com";

    /**
     * OAuth2 plenigo URL.
     */
    public static final String OAUTH_PLENIGO_URL = "https://www.plenigo.com";

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
     * This URL is used to get all the products this user has bought.
     */
    public static final String USER_PRODUCTS = API_BASE_URL + "/user/%s/products";
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

    /**
     * This url is used to request a token from the app management.
     */
    public static final String ACCESS_APP_TOKEN = API_BASE_URL + "/access/app/token/%s";

    /**
     * This url is used to request all the customer apps from the app management.
     */
    public static final String ACCESS_APP_CUSTOMER = API_BASE_URL + "/access/app/%s";

    /**
     * This method verifies if an user has access to a specified product.
     */
    public static final String VERIFY_CUSTOMER_APP_PRODUCT = API_BASE_URL + "/access/app/%s/%s/%s";

    /**
     * This method deletes an app from a customer.
     */
    public static final String DELETE_CUSTOMER_APP = API_BASE_URL + "/access/app/%s/%s";


    /**
     * This URL is used to verify if mobile app has access to certain customer.
     */
    public static final String MOBILE_SECRET_VERIFY = API_BASE_URL + "/access/mobileSecret/verify";

    /**
     * This URL is used to get, create or delete mobile secret data.
     */
    public static final String MOBILE_SECRET_URL = API_BASE_URL + "/access/mobileSecret/%s";

    /**
     * This URL is used to register an external user.
     */
    public static final String REGISTER_EXTERNAL_USER_URL = API_BASE_URL + "/externalUser/register";

    /**
     * This URL is used to change an external users email.
     */
    public static final String EXTERNAL_USER_EMAIL_CHANGE_URL = API_BASE_URL + "/externalUser/%s/changeEmail";

    /**
     * This URL is used to create a login token.
     */
    public static final String EXTERNAL_USER_CREATE_LOGIN_TOKEN_URL = API_BASE_URL + "/externalUser/%s/createLoginToken";

}
