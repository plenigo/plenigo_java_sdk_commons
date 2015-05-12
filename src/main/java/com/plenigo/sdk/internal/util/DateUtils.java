package com.plenigo.sdk.internal.util;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * Utils for working with dates.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is thread safe and can be injected.
 * </p>
 */
public final class DateUtils {

    /**
     * Private constructor because it is a utility class.
     */
    private DateUtils() {
    }

    /**
     * Create a deep copy of a given date.
     *
     * @param date date to get deep copy for
     *
     * @return deep copy
     */
    public static Date copy(Date date) {
        if (date == null) {
            return null;
        }
        return (Date) date.clone();
    }
}