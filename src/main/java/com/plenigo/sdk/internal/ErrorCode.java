package com.plenigo.sdk.internal;

/**
 * <p>
 * Error codes that can be returned by the API.
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
public enum ErrorCode {
    SERVER("server", "A server error has occured, we are informed about the problem and will try to solve it as fast as possible"),
    INVALID_PARAMETERS("invalid_parameter", "There are invalid parameters in your request, "
            + "please check the ErrorDetail list in the PlenigoException object for more details"),
    CANNOT_ACCESS_PRODUCT("does_not_have_access", "The user cannot access the queried product"), CRYPTOGRAPHY_ERROR("crypto_error"),
    INVALID_SECRET_OR_COMPANY_ID("invalid_secret_or_company", "The company id or secret provided were incorrect, please check your configuration"),
    CONNECTION_ERROR("connection_error"),
    UNKNOWN_HOST("unknown_host"),
    PRODUCT_NOT_FOUND("product_not_found", "The provided product id is not valid"),
    CATEGORY_NOT_FOUND("category_not_found", "The provided category id is not valid"),
    USER_NOT_LOGGED_IN("user_not_logged_in", "User is not logged in"),
    SHIPPING_NOT_ALLOWED("shipping_not_allowed", "This product type doesn't allow shipping cost!"),
    PRODUCT_ACCESS_ALLOWED("product_access_allowed", "This user has access to this product"),
    APP_ID_DELETED("app_id_deleted", "The app id was deleted"),
    PROD_ID_TOO_LONG("prod_id_too_long", "The Product ID can be up to 20 chars long!"),
    PROD_ID_REPL_TOO_LONG("prod_rep_id_too_long", "The Product ID replacement can be up to 20 chars long!"),
    INCORRECT_MOBILE_SECRET("incorrect_mobile_secret", "The mobile secret or the customer id is incorrect"),
    COMPANY_NOT_QUALIFIED("company_not_qualified", "The company is not qualified for a closed user group"),
    OVERRIDE_MODE_REQUIRES_PRICE("override_mode_req_price", "You must provide a price in the product object to make use of the override mode!"),
    SOURCE_URL_TOO_LONG("source_url_too_long", "The provided source url must be at most 255 characters long."),
    TARGET_URL_TOO_LONG("target_url_too_long", "The provided target url must be at most 255 characters long."),
    AFFILIATE_ID_TOO_LONG("affiliate_id_too_long","The provided affiliate id must be most 50 characters long"),
    ELEMENT_ID_TOO_LONG("element_id_too_long","The provided element id must be most 100 character long");


    private String code;
    private String errorMsg;

    /**
     * This constructs an error code enum with it's code and error message.
     *
     * @param errorCode The error code
     * @param errorMsg  The error message
     */
    ErrorCode(String errorCode, String errorMsg) {
        this.code = errorCode;
        this.errorMsg = errorMsg;
    }


    /**
     * This constructs an error code enum with it's code.
     *
     * @param errorCode The error code
     */
    ErrorCode(String errorCode) {
        this.code = errorCode;
    }

    /**
     * Retrieves the error code based on the code or the enum name.
     *
     * @param code The error code to find
     *
     * @return An error code if it exists, otherwise it returns the SERVER error code
     */
    public static ErrorCode get(String code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.code.equals(code) || errorCode.name().equalsIgnoreCase(code)) {
                return errorCode;
            }
        }
        return ErrorCode.SERVER;
    }

    /**
     * Returns the error code that corresponds to the enum value.
     *
     * @return The error code
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the error message that corresponds to the code.
     *
     * @return The error message
     */
    public String getMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "ErrorCode{" + "code='" + code + '\'' + ", errorMsg='" + errorMsg + '\'' + '}';
    }
}
