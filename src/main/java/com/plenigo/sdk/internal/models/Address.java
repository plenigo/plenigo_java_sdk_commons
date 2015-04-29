package com.plenigo.sdk.internal.models;


import java.io.Serializable;

/**
 * <p>
 * This object represents the user address information.
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
public class Address implements Serializable {
    private String street;
    private String additionalAddressInfo;
    private String postCode;
    private String city;
    private String country;

    /**
     * The constructor for this read-only object.
     *
     * @param street                The street address
     * @param additionalAddressInfo The additional address info
     * @param postCode              The address postcode
     * @param city                  The city the user lives in
     * @param country               The country the user lives in
     */
    public Address(String street, String additionalAddressInfo, String postCode, String city, String country) {
        this.street = street;
        this.additionalAddressInfo = additionalAddressInfo;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }

    /**
     * Retrieves The user's street address.
     *
     * @return The user's street address.
     */
    public String getStreet() {
        return street;
    }

    /**
     * The user address additional information.
     *
     * @return Additional address information
     */
    public String getAdditionalAddressInfo() {
        return additionalAddressInfo;
    }


    /**
     * The user address post code.
     *
     * @return The post code
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * The user address city.
     *
     * @return The user address city
     */
    public String getCity() {
        return city;
    }

    /**
     * The user's country.
     *
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Address{" + "street='" + street + '\'' + ", additionalAddressInfo='" + additionalAddressInfo + '\'' + ", postCode='" + postCode + '\''
                + ", city='" + city + '\'' + ", country='" + country + '\'' + '}';
    }
}
