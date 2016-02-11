package com.plenigo.sdk.models;

import com.plenigo.sdk.internal.util.DateUtils;

import java.util.Date;


/**
 * <p>
 * This class represents a transaction search request.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is <b>not</b> thread safe.
 * </p>
 */
public class TransactionSearchRequest extends PageRequest {
    private Date startDate;
    private Date endDate;
    private PaymentMethod paymentMethod;
    private TransactionStatus transactionStatus;

    /**
     * Constructor with the required parameters.
     *
     * @param pageNumber page number
     * @param pageSize   page size
     */
    public TransactionSearchRequest(int pageNumber, int pageSize) {
        super(pageNumber, pageSize);
    }

    /**
     * Sets the start date.
     *
     * @param startDate start date
     *
     * @return the same {@link TransactionSearchRequest} instance
     */
    public TransactionSearchRequest setStartDate(Date startDate) {
        this.startDate = DateUtils.copy(startDate);
        return this;
    }

    /**
     * Sets the end date.
     *
     * @param endDate end date
     *
     * @return the same {@link TransactionSearchRequest} instance
     */
    public TransactionSearchRequest setEndDate(Date endDate) {
        this.endDate = DateUtils.copy(endDate);
        return this;
    }

    /**
     * Sets the payment method.
     *
     * @param paymentMethod payment method
     *
     * @return the same {@link TransactionSearchRequest} instance
     */
    public TransactionSearchRequest setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }


    /**
     * Sets the transaction status.
     *
     * @param transactionStatus transaction status
     *
     * @return the same {@link TransactionSearchRequest} instance
     */
    public TransactionSearchRequest setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
        return this;
    }

    /**
     * Returns the start date.
     *
     * @return start date
     */
    public Date getStartDate() {
        return DateUtils.copy(startDate);
    }

    /**
     * Returns the end date.
     *
     * @return end date
     */
    public Date getEndDate() {
        return DateUtils.copy(endDate);
    }

    /**
     * Sets the payment method.
     *
     * @return payment method
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the transaction status.
     *
     * @return transaction status
     */
    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }
}
