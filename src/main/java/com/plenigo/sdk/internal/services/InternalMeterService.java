package com.plenigo.sdk.internal.services;

import com.plenigo.sdk.internal.models.BaseUserMeteredData;

/**
 * <p>
 * This class represents the internal metered services common functionality.
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
public class InternalMeterService {

    /**
     * Finds out if the given metered user data still has free views.
     *
     * @param baseUserMeteredData the metered user data to analyze
     * @param isLoggedIn          indicates if the user is logged in
     * @param isExpiredData       indicates if the user data has expired
     *
     * @return true if the user has free views, false otherwise
     */
    public boolean hasFreeViews(BaseUserMeteredData baseUserMeteredData, boolean isLoggedIn, boolean isExpiredData) {
        //isCookieAvailable
        if (baseUserMeteredData == null) {
            return true;
        }
        //isMeteredViewActivated
        if (baseUserMeteredData.isMeteredViewActivated() == null || !baseUserMeteredData.isMeteredViewActivated()) {
            return false;
        }

        if (isExpiredData) {
            return true;
        }

        Boolean isLimitReached;
        if (isLoggedIn) {
            isLimitReached = baseUserMeteredData.isLoginLimitReached();
        } else {
            isLimitReached = baseUserMeteredData.isLimitReached();
        }

        if (isLimitReached == null || !isLimitReached) {
            return true;
        }
        return false;
    }

}
