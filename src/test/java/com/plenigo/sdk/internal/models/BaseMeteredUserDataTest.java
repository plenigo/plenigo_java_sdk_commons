package com.plenigo.sdk.internal.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * Tests for {@link BaseUserMeteredData}.
 */
public class BaseMeteredUserDataTest {
    public static final Long FREE_VIEWS_ALLOWED = 0L;
    public static final Long VIEWS_TAKEN = 0L;
    public static final boolean IS_LIMIT_REACHED = true;
    public static final Long LOGIN_FREE_VIEWS_ALLOWED = 0L;
    public static final Long LOGIN_FREE_VIEWS_TAKEN = 0L;
    public static final boolean LOGIN_LIMIT_REACHED = true;
    public static final boolean IS_METERED_VIEW_ACTIVATED = true;
    private BaseUserMeteredData data;

    @Before
    public void setup() {
        data = new BaseUserMeteredData(IS_METERED_VIEW_ACTIVATED, FREE_VIEWS_ALLOWED, VIEWS_TAKEN, IS_LIMIT_REACHED, LOGIN_FREE_VIEWS_ALLOWED,
                LOGIN_FREE_VIEWS_TAKEN, LOGIN_LIMIT_REACHED);
    }

    @Test
    public void testGetFreeViewsAllowed() {
        assertNotNull(data.getFreeViewsAllowed());
    }

    @Test
    public void testGetViewsTaken() {
        assertEquals("Get Views taken is not the expected value", VIEWS_TAKEN, data.getViewsTaken());
    }

    @Test
    public void testIsMeteredViewActivated(){
        assertEquals("Is metered view activated is not correct", IS_METERED_VIEW_ACTIVATED, data.isMeteredViewActivated());
    }

    @Test
    public void testIsLimitReached(){
        assertEquals("Is limit reached is not correct", IS_LIMIT_REACHED, data.isLimitReached());
    }

    @Test
    public void testLoginFreeViewsAllowed(){
        assertEquals("Login free views allowed is not correct", LOGIN_FREE_VIEWS_ALLOWED, data.getLoginFreeViewsAllowed());
    }

    @Test
    public void testLoginFreeViewsTaken(){
        assertEquals("Login free views taken is not correct", LOGIN_FREE_VIEWS_TAKEN, data.getLoginFreeViewsTaken());
    }

    @Test
    public void testIsLoginLimitReached(){
        assertEquals("Login limit reached is not correct", LOGIN_LIMIT_REACHED, data.isLoginLimitReached());
    }

    @Test
    public void testAddViews() {
        data.addViews(Collections.singletonList("sample"));
        assertFalse(data.getUniqueVisitedSites().isEmpty());
    }

    @Test
    public void testToString() {
        assertNotNull(data.toString());
    }
}
