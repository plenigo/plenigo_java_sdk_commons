package com.plenigo.sdk.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * <p>
 * Tests for {@link TransactionSearchRequest}.
 * </p>
 */
public class TransactionSearchRequestTest {
    public static final int PAGE_NUMBER = 0;
    public static final int PAGE_SIZE = 10;
    private TransactionSearchRequest transactionSearchRequest;

    @Before
    public void setup() {
        transactionSearchRequest = new TransactionSearchRequest(PAGE_NUMBER, PAGE_SIZE);
    }

    @Test
    public void testStartDate() {
        Date date = new Date();
        transactionSearchRequest.setStartDate(date);
        assertEquals("Start date was not correct", date, transactionSearchRequest.getStartDate());
    }

    @Test
    public void testEndDate() {
        Date date = new Date();
        transactionSearchRequest.setEndDate(date);
        assertEquals("End date was not correct", date, transactionSearchRequest.getEndDate());
    }
}
