package com.plenigo.sdk.internal;

/**
 * <p>
 * This class contains api result variables
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
public final class ApiResults {

    public static final String ERROR = "error";
    public static final String ERROR_DESCRIPTION = "error_description";
    public static final String DESCRIPTION = "description";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String EXPIRES_IN = "expires_in";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String STATE = "state";
    public static final String COLLECTIBLE = "collectible";

    /**
     * Default constructor.
     */
    private ApiResults() {
    }

    /**
     * Timestamp result variable.
     */
    public static final String TIMESTAMP = "ts";

    /**
     * The customer id variable.
     */
    public static final String CUSTOMER_ID = "ci";

    /**
     * Error message parameter when the plenigo API
     * returns a BAD_REQUEST http response code.
     */
    public static final String ERROR_MSG = "Error";

    /**
     * The user id variable.
     */
    public static final String USER_ID = "userId";

    /**
     * E-mail.
     */
    public static final String EMAIL = "email";

    /**
     * Gender prefix (e.g. Mr. or Mrs.).
     */
    public static final String GENDER = "gender";
    /**
     * User's name.
     */
    public static final String LAST_NAME = "name";
    /**
     * User's first name.
     */
    public static final String FIRST_NAME = "firstName";
    public static final String STREET = "street";
    public static final String ADDITIONAL_ADDRESS_INFO = "additionalAddressInfo";
    public static final String POST_CODE = "postCode";
    public static final String CITY = "city";
    public static final String COUNTRY = "country";
    public static final String USERNAME = "username";
    /*
    * Product attributes.
    * */
    public static final String ID = "id";
    public static final String SUBSCRIPTION = "subscription";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String CAN_CHOOSE_PRICE = "choosePrice";
    public static final String PRICE = "price";
    public static final String TAXES = "taxes";
    public static final String CURRENCY = "currency";
    public static final String TERM = "term";
    public static final String CANCELLATION_PERIOD = "cancellationPeriod";
    public static final String AUTO_RENEWAL = "autoRenewal";
    public static final String ACTION_PERIOD_NAME = "actionPeriodName";
    public static final String ACTION_PERIOD_TERM = "actionPeriodTerm";
    public static final String ACTION_PERIOD_PRICE = "actionPeriodPrice";
    public static final String IMAGES = "images";
    public static final String ALT_TEXT = "altText";

    /**
     * Paywall state attributes.
     */
    public static final String PAYWALL_STATE = "enabled";


    /*
    * Product List attributes.
    * */
    public static final String TOTAL_ELEMENTS = "totalElements";
    public static final String PAGE_SIZE = "size";
    public static final String LAST_ID = "lastId";
    public static final String ELEMENTS = "elements";


    /*
    * Product List element attributes.
    * */
    public static final String PROD_ID = "productId";

    /*
    * Category List element attributes.
    * */
    public static final String CATEGORY_ID = "categoryId";


    /**
     * Category data element attributes.
     */
    public static final String VALIDITY_TIME = "validityTime";


    /**
     * Is metered activated result param.
     */
    public static final String METERED_ACTIVATED = "meteredActivated";


    /**
     * Free views allowed result param.
     */
    public static final String FREE_VIEWS_ALLOWED = "freeViews";

    /**
     * Free views taken result param.
     */
    public static final String FREE_VIEWS_TAKEN = "viewsTaken";

    /**
     * Has the limit been reached result param.
     */
    public static final String LIMIT_REACHED = "limitReached";


    /**
     * Login Free views allowed result param.
     */
    public static final String FREE_LOGIN_VIEWS_ALLOWED = "freeAfterLogin";


    /**
     * Login Free views allowed result param.
     */
    public static final String FREE_LOGIN_VIEWS_TAKEN = "freeAfterLoginTaken";


    /**
     * Has the view limit after login reached result param.
     */
    public static final String LOGIN_LIMIT_REACHED = "limitReachedAfterLogin";

    /**
     * Has the subscription product list.
     */
    public static final String SUBSCRIPTIONS_LIST = "subscriptions";

    /**
     * Has the single product list.
     */
    public static final String SINGLE_PAYMENT_PRODUCT_LIST = "singleProducts";

    /**
     * Has the buy date property.
     */
    public static final String BUY_DATE = "buyDate";


    /**
     * Has the end date property.
     */
    public static final String END_DATE = "endDate";

    /**
     * Result attribute that contains all the uniquely visited articles.
     */
    public static final String UNIQUELY_VISITED_SITES = "uniquelyVisitedSites";

    /**
     * Result attribute that contains the application token for app management.
     */
    public static final String APP_TOKEN = "token";


    /**
     * The customer id variable for app management response.
     */
    public static final String CUST_ID = "customerId";

    /**
     * The app list variable for app management response.
     */
    public static final String APP_LIST = "apps";

    /**
     * The customer app id variable for app management response.
     */
    public static final String CUSTOMER_APP_ID = "customerAppId";

    /**
     * the mobile app secret variable.
     */
    public static final String MOBILE_APP_SECRET = "mobileAppSecret";

    /**
     * The login token variable.
     */
    public static final String LOGIN_TOKEN = "loginToken";

    /**
     * Max parallell app access parameter.
     */
    public static final String MAX_PARALLEL_APP_ACCESS = "maxParallelAppAccess";

    /**
     * Custom information parameter.
     */
    public static final String CUSTOM_INFO = "customInfo";


    /**
     * Company user language parameter.
     */
    public static final String LANGUAGE = "language";

    /**
     * Company user mobile number parameter.
     */
    public static final String MOBILE_NUMBER = "mobileNumber";

    /**
     * Company user stater parameter.
     */
    public static final String USER_STATE = "userState";

    /**
     * Company user birthday parameter.
     */
    public static final String BIRTHDAY = "birthday";

    /**
     * Company user newsletter agreement state parameter.
     */
    public static final String AGREEMENT_STATE = "agreementState";

    /**
     * Company user company parameter.
     */
    public static final String COMPANY = "company";

    /**
     * Company user vat number parameter.
     */
    public static final String VAT_NUMBER = "vatNumber";

    /**
     * Transaction id parameter.
     */
    public static final String TRANSACTION_ID = "transactionId";

    /**
     * Taxes percentage parameter.
     */
    public static final String TAXES_PERCENTAGE = "taxesPercentage";

    /**
     * Taxes amount parameter.
     */
    public static final String TAXES_AMOUNT = "taxesAmount";

    /**
     * Taxes country parameter.
     */
    public static final String TAXES_COUNTRY = "taxesCountry";

    /**
     * Payment method parameter.
     */
    public static final String PAYMENT_METHOD = "paymentMethod";

    /**
     * Transaction date parameter.
     */
    public static final String TRANSACTION_DATE = "transactionDate";

    /**
     * Transaction status parameter.
     */
    public static final String TRANSACTION_STATUS = "status";

    /**
     * Shipping costs parameter.
     */
    public static final String SHIPPING_COSTS = "shippingCosts";

    /**
     * Shipping costs taxes percentage parameter.
     */
    public static final String SHIPPING_COSTS_TAXES_PERCENTAGE = "shippingCostsTaxesPercentage";

    /**
     * Shipping costs taxes amount parameter.
     */
    public static final String SHIPPING_COSTS_TAXES_AMOUNT = "shippingCostsTaxesAmount";

    /**
     * Billing id parameter.
     */
    public static final String BILLING_ID = "billingId";

    /**
     * Cancellation transaction id parameter.
     */
    public static final String CANCELLATION_TRANSACTION_ID = "cancellationTransactionId";

    /**
     * Cancelled transaction id parameter.
     */
    public static final String CANCELLED_TRANSACTION_ID = "cancelledTransactionId";
}
