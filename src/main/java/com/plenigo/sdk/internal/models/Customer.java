package com.plenigo.sdk.internal.models;

/**
 * <p>
 * This class represents a user in the plenigo platform. An user can be any
 * person that wants to buy digital content.
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
public class Customer {
    /**
     * The unique customer id.
     */
    private String customerId;

    /**
     * Constructor of the customer object.
     *
     * @param cookieCustomerId The unique customer id.
     * @param cookieTimestamp  The cookie timestamp in milliseconds.
     */
    public Customer(String cookieCustomerId, Long cookieTimestamp) {
        this.customerId = cookieCustomerId;
        this.timestamp = cookieTimestamp;
    }

    /**
     * The timestamp of the current cookie with the customer information.
     */
    private Long timestamp;

    /**
     * Returns the customer id.
     *
     * @return The id of the customer
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Returns the current timestamp of the cookie in milliseconds.
     *
     * @return The cookie timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId='" + customerId + '\'' + ", timestamp=" + timestamp + '}';
    }
}
