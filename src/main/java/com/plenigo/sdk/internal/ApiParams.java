package com.plenigo.sdk.internal;

/**
 * <p>
 * This class contains api parameters
 * that are used through the different methods
 * of the plenigo API.
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
public final class ApiParams {


    /**
     * Default constructor.
     */
    private ApiParams() {
    }

    /**
     * The product price parameter.
     */
    public static final String PROD_PRICE = "pr";
    /**
     * The product price currency parameter.
     */
    public static final String CURRENCY = "cu";
    /**
     * The product tax percentage parameter.
     */
    public static final String PROD_TAX_PCT = "tx";

    /**
     * The product title parameter.
     */
    public static final String PROD_TITLE = "ti";
    /**
     * The product id parameter.
     */
    public static final String PROD_ID = "pi";
    /**
     * The info screen shown on retry parameter.
     */
    public static final String INFO_SCRN_SHOWN_ON_RETRY = "sab";
    /**
     * The info scren shown at the end of a payment parameter.
     */
    public static final String INFO_SCRN_SHOWN_AT_END_OF_PAYMENT = "spf";
    /**
     * Custom amount parameter.
     */
    public static final String CUSTOM_AMOUNT = "sp";

    /**
     * The test transaction parameter.
     */
    public static final String TEST_TRANSACTION = "ts";

    /**
     * The product tax type parameter.
     */
    public static final String PROD_TAX_TYPE = "pt";

    /**
     * cross-site request forgery token parameter.
     */
    public static final String CSRF_TOKEN = "csrf";

    /**
     * single sign on parameter.
     */
    public static final String SINGLE_SIGN_ON = "sso";

    /**
     * Company Id parameter.
     */
    public static final String COMPANY_ID = "companyId";


    /**
     * Secret parameter.
     */
    public static final String SECRET = "secret";

    /**
     * Unique customer ID parameter.
     */
    public static final String CUSTOMER_ID = "customerId";

    /**
     * Unique product ID parameter.
     */
    public static final String PRODUCT_ID = "productId";

    /**
     * Test mode parameter.
     */
    public static final String TEST_MODE = "testMode";

    /**
     * Access token parameter.
     */
    public static final String ACCESS_TOKEN = "token";

    /**
     * The gramt type of the token.
     */
    public static final String TOKEN_GRANT_TYPE = "grant_type";

    /**
     * The access code given by a redirect url during oauth authentication.
     */
    public static final String OAUTH_ACCESS_CODE = "code";

    /**
     * Redirect URL parameter.
     */
    public static final String REDIRECT_URI = "redirect_uri";

    /**
     * The customer id parameter in OAuth standard.
     */
    public static final String CLIENT_ID = "client_id";

    /**
     * The secret key parameter.
     */
    public static final String CLIENT_SECRET = "client_secret";

    /**
     * The CSRF Token parameter in OAuth standard.
     */
    public static final String STATE = "state";

    /**
     * The Refresh token parameter in OAuth standard.
     */
    public static final String REFRESH_TOKEN = "refresh_token";

    /**
     * The amount of results required per page.
     */
    public static final String PAGE_SIZE = "size";


    /**
     * The amount of results required per page.
     */
    public static final String PAGE_NUMBER = "page";

    /**
     * The last id of the last executed result for paging.
     */
    public static final String LAST_ID = "lastId";


    /**
     * The category id of a product.
     */
    public static final String CATEGORY_ID = "ci";

    /**
     * unique mobile's device id.
     */
    public static final String DEVICE_ID = "deviceId";

    /**
     * Article id for meter view count.
     */
    public static final String ARTICLE_ID = "articleId";

    /**
     * Sends a flag indicating if it wants the metered view status returned.
     */
    public static final String STATUS = "status";

    /**
     * Subscription renewal flag on checkout.
     */
    public static final String SUBSCRIPTION_RENEWAL = "rs";

    /**
     * Failed payment flag on checkout.
     */
    public static final String FAILED_PAYMENT = "fp";

    /**
     * Shipping costs parameter.
     */
    public static final String SHIPPING_COST = "sc";

    /**
     * Description parameter.
     */
    public static final String DESCRIPTION = "description";

    /**
     * App Access token parameter.
     */
    public static final String APP_ACCESS_TOKEN = "accessToken";


    /**
     * App Access token parameter.
     */
    public static final String CUSTOMER_APP_ID = "customerAppId";

    /**
     * Email parameter.
     */
    public static final String EMAIL = "email";

    /**
     * Mobile Secret parameter.
     */
    public static final String MOBILE_SECRET = "mobileSecret";

    /**
     * Mobile Secret size parameter.
     */
    public static final String MOBILE_SECRET_SIZE = "size";

    /**
     * Language parameter.
     */
    public static final String LANGUAGE = "language";

    /**
     * Override mode parameter.
     */
    public static final String OVERRIDE_MODE = "om";

    /**
     * User ids parameter.
     */
    public static final String USER_IDS = "userIds";

    /**
     * Start date parameter.
     */
    public static final String START_DATE = "startDate";

    /**
     * End date parameter.
     */
    public static final String END_DATE = "endDate";

    /**
     * Payment method parameter.
     */
    public static final String PAYMENT_METHOD = "paymentMethod";

    /**
     * Transaction status parameter.
     */
    public static final String TRANSACTION_STATUS = "transactionStatus";

    /**
     * Product id replacement parameter.
     */
    public static final String PRODUCT_ID_REPLACEMENT = "pir";
    /**
     * Segment id parameter.
     */
    public static final String SEGMENT_ID = "si";
}