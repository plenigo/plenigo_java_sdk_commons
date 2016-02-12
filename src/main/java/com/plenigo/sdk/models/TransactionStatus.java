package com.plenigo.sdk.models;

import com.plenigo.sdk.internal.util.SdkUtils;


/**
 * Transaction status.
 */
public enum TransactionStatus {
    BOOKED, DONE, CANCELED, FAILED, CHARGEBACK;

    /**
     * Returns the transaction status that corresponds to the value, otherwise null.
     *
     * @param value the value to evaluate
     *
     * @return the transaction status
     */
    public static TransactionStatus get(String value) {
        if (SdkUtils.isNotBlank(value)) {
            for (TransactionStatus transactionStatus : values()) {
                if (transactionStatus.name().equals(value.toUpperCase())) {
                    return transactionStatus;
                }
            }
        }
        return null;
    }
}
