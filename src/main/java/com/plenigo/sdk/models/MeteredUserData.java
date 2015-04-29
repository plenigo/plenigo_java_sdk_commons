package com.plenigo.sdk.models;


import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * This object represents the metered user data.
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
public class MeteredUserData implements Serializable {
    private Boolean isMeteredViewActivated;
    private Long freeViewsAllowed;
    private AtomicLong viewsTaken;
    private Boolean isLimitReached;
    private Long loginFreeViewsAllowed;
    private Long loginFreeViewsTaken;
    private Boolean loginLimitReached;
    private Set<String> uniqueVisitedSites;

    /**
     * Required constructor.
     *
     * @param isMeteredViewActivated indicates if the metered view is activated
     * @param freeViewsAllowed       indicates how many free views are allowed
     * @param viewsTaken             indicates the amount of views that the user has taken
     * @param isLimitReached         indicates if the limit has been reached
     * @param loginFreeViewsAllowed  indicates how many free views are allowed after login
     * @param loginFreeViewsTaken    indicates the amount of views that the user has taken after login
     * @param loginLimitReached    indicates if the limit has been reached after login
     */
    public MeteredUserData(Boolean isMeteredViewActivated, Long freeViewsAllowed, Long viewsTaken, Boolean isLimitReached, Long loginFreeViewsAllowed
            , Long loginFreeViewsTaken, Boolean loginLimitReached) {
        this.isMeteredViewActivated = isMeteredViewActivated;
        this.freeViewsAllowed = freeViewsAllowed;
        this.viewsTaken = new AtomicLong(viewsTaken);
        this.isLimitReached = isLimitReached;
        this.loginFreeViewsAllowed = loginFreeViewsAllowed;
        this.loginFreeViewsTaken = loginFreeViewsTaken;
        this.loginLimitReached = loginLimitReached;
        this.uniqueVisitedSites = new HashSet<String>();
    }

    /**
     * Flag indicating if the metered view is activated.
     *
     * @return a boolean indicating if the metered view is activated
     */
    public Boolean isMeteredViewActivated() {
        return isMeteredViewActivated;
    }

    /**
     * The amount of free views allowed.
     *
     * @return the amount of free views allowed
     */
    public Long getFreeViewsAllowed() {
        return freeViewsAllowed;
    }

    /**
     * The amount of taken views.
     *
     * @return the amount of taken views
     */
    public Long getViewsTaken() {
        return viewsTaken.get();
    }

    /**
     * Flag indicating if the limit has been reached.
     *
     * @return The reached limit
     */
    public Boolean isLimitReached() {
        return isLimitReached;
    }


    /**
     * The amount of free views allowed after login.
     *
     * @return the amount of free views allowed after login
     */
    public Long getLoginFreeViewsAllowed() {
        return loginFreeViewsAllowed;
    }


    /**
     * The amount of taken views after login.
     *
     * @return the amount of taken views after login
     */
    public Long getLoginFreeViewsTaken() {
        return loginFreeViewsTaken;
    }


    /**
     * Flag indicating if the limit has been reached after login.
     *
     * @return The reached limit after login
     */
    public Boolean isLoginLimitReached() {
        return loginLimitReached;
    }

    @Override
    public String toString() {
        return "MeteredUserData{" + "isMeteredViewActivated=" + isMeteredViewActivated + ", freeViewsAllowed=" + freeViewsAllowed + ", viewsTaken="
                + viewsTaken + ", isLimitReached=" + isLimitReached + ", loginFreeViewsAllowed=" + loginFreeViewsAllowed + ", loginFreeViewsTaken="
                + loginFreeViewsTaken + ", loginLimitReached=" + loginLimitReached + '}';
    }

    /**
     * Adds views to the amount of views taken.
     *
     * @param articles The ids of the articles to add
     */
    public void addViews(List<String> articles) {
        viewsTaken.addAndGet(articles.size());
        uniqueVisitedSites.addAll(articles);
        if(viewsTaken.longValue() >= freeViewsAllowed){
            isLimitReached = true;
        }
    }

    /**
     * Returns the unique
     *
     * @return
     */
    public Set<String> getUniqueVisitedSites() {
        return uniqueVisitedSites;
    }
}
