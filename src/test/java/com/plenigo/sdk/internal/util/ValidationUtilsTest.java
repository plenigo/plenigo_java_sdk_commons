package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.models.PageRequest;
import com.plenigo.sdk.models.TransactionSearchRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * <p>
 * Tests for {@link ValidationUtils}.
 * </p>
 */
public class ValidationUtilsTest {

    @Test
    public void testValidatePageRequest() throws PlenigoException {
        PageRequest request = new PageRequest(0, 10);
        ValidationUtils.validate(request);
        assertEquals(0, request.getPageNumber());
        assertEquals(10, request.getPageSize());
    }

    @Test
    public void testValidateInvalidPageRequest() throws PlenigoException {
        PageRequest request = new PageRequest(-1, -5);
        ValidationUtils.validate(request);
        assertEquals(0, request.getPageNumber());
        assertEquals(10, request.getPageSize());
    }

    @Test
    public void testValidatePageRequestWithMaxAmounts() throws PlenigoException {
        PageRequest request = new PageRequest(Integer.MAX_VALUE, 100);
        ValidationUtils.validate(request);
        assertEquals(Integer.MAX_VALUE, request.getPageNumber());
        assertEquals(100, request.getPageSize());
    }

    @Test
    public void testValidateTransactionSearchRequest() throws PlenigoException {
        TransactionSearchRequest request = new TransactionSearchRequest(0, 10).setStartDate(new Date()).setEndDate(new Date());
        ValidationUtils.validateDateRange(request);
        assertNotNull(request.getStartDate());
        assertNotNull(request.getEndDate());
    }

    @Test
    public void testValidateTransactionSearchRequestWithNullDates() throws PlenigoException {
        TransactionSearchRequest request = new TransactionSearchRequest(0, 10);
        ValidationUtils.validateDateRange(request);
        assertNotNull(request.getStartDate());
        assertNotNull(request.getEndDate());
    }

    @Test
    public void testValidateTransactionSearchRequestWithExceededDates() throws PlenigoException {
        TransactionSearchRequest request = new TransactionSearchRequest(0, 10);
        Calendar instance = Calendar.getInstance();
        Date startDate = instance.getTime();
        request.setStartDate(startDate);
        instance.add(Calendar.MONTH, 100);
        Date endDate = instance.getTime();
        request.setEndDate(endDate);
        ValidationUtils.validateDateRange(request);
        assertNotEquals(startDate, request.getStartDate());
        assertNotEquals(endDate, request.getEndDate());
    }
}
