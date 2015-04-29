package com.plenigo.sdk.models;

/**
 * <p>
 * Contains all the Token grant types available.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is thread safe and can be injected.
 * </p>
 */
public enum TokenGrantType {
    AUTHORIZATION_CODE("authorization_code"), REFRESH_TOKEN("refresh_token");
    private String name;

    /**
     * Constructor with the required token grant type name.
     *
     * @param grantTypeName The name of the grant type
     */
    TokenGrantType(String grantTypeName) {
        name = grantTypeName;
    }

    /**
     * Returns the name of the token grant type.
     *
     * @return the name of the token grant type
     */
    public String getName() {
        return name;
    }
}
