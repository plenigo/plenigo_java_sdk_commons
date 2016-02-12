package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.models.PageRequest;
import com.plenigo.sdk.models.TransactionSearchRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * <p>
 * This class contains general validation utilities that are required.
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
public final class ValidationUtils {
    private static final int MONTHS_IN_A_YEAR = 12;
    private static final int MIN_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 100;
    private static final int MIN_PAGE_NUMBER = 0;
    private static final int MONTH_RANGE = 18;


    /**
     * Default constructor.
     */
    private ValidationUtils() {
    }

    /**
     * Validates a page request logic and changes values to proper ones if invalid.
     *
     * @param request the page request
     */
    public static void validate(PageRequest request) {
        if (request.getPageSize() < MIN_PAGE_SIZE) {
            request.setPageSize(MIN_PAGE_SIZE);
        } else if (request.getPageSize() > MAX_PAGE_SIZE) {
            request.setPageSize(MAX_PAGE_SIZE);
        }

        if (request.getPageNumber() < MIN_PAGE_NUMBER) {
            request.setPageNumber(MIN_PAGE_NUMBER);
        }
    }

    /**
     * Validates a date range and changes values to proper ones if invalid.
     *
     * @param request request to validate
     */
    public static void validateDateRange(TransactionSearchRequest request) {
        boolean isInvalid = false;
        if (request.getStartDate() == null || request.getEndDate() == null) {
            isInvalid = true;
        } else {
            Date startDate = request.getStartDate();
            Date endDate = request.getEndDate();
            Calendar startCalendar = new GregorianCalendar();
            startCalendar.setTime(startDate);
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(endDate);
            int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            int diffMonth = (diffYear * MONTHS_IN_A_YEAR) + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
            if (diffMonth > MONTH_RANGE) {
                isInvalid = true;
            }
        }

        if (isInvalid) {
            Calendar currentDate = Calendar.getInstance();
            request.setEndDate(currentDate.getTime());
            currentDate.add(Calendar.MONTH, -MONTH_RANGE);
            request.setStartDate(currentDate.getTime());
        }
    }
}
