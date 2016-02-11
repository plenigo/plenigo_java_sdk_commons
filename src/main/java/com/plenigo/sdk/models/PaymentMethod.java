package com.plenigo.sdk.models;

import com.plenigo.sdk.internal.util.SdkUtils;

/**
 * Transaction payment methods.
 */
public enum PaymentMethod {
    BANK_ACCOUNT, CREDIT_CARD, BILLING, ZERO, SOFORT, POST_FINANCE, PAYPAL;

    /**
     * Returns the payment method that corresponds to the value, otherwise null.
     *
     * @param value the value to evaluate
     *
     * @return the payment method
     */
    public static PaymentMethod get(String value) {
        if (SdkUtils.isNotBlank(value)) {
            for (PaymentMethod paymentMethod : values()) {
                if (paymentMethod.name().equals(value.toUpperCase())) {
                    return paymentMethod;
                }
            }
        }
        return null;
    }
}
