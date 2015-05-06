package com.plenigo.sdk.internal.services;

import com.plenigo.sdk.models.MeteredUserData;

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
     * @param meteredUserData the metered user data to analyze
     * @param isLoggedIn      indicates if the user is logged in
     *
     * @return true if the user has free views, false otherwise
     */
    public boolean hasFreeViews(MeteredUserData meteredUserData, boolean isLoggedIn) {
        //isCookieAvailable
        if (meteredUserData == null) {
            return true;
        }
        //isMeteredViewActivated
        if (meteredUserData.isMeteredViewActivated() == null || !meteredUserData.isMeteredViewActivated()) {
            return false;
        }

        //is login limit reached
        if (isLoggedIn) {
            if ((meteredUserData.isLoginLimitReached() == null || !meteredUserData.isLoginLimitReached())) {
                return true;
            }
        } else if (meteredUserData.isLimitReached() == null) {
            //isLimitReached
            return true;
        } else if (!meteredUserData.isLimitReached()) {
            return true;
        }
        return false;
    }

}
