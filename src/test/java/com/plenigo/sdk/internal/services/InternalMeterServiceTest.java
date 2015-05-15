package com.plenigo.sdk.internal.services;


import com.plenigo.sdk.internal.models.BaseUserMeteredData;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
/**
 * <p>
 * Tests for {@link InternalMeterService}.
 * </p>
 */
public class InternalMeterServiceTest {
    private InternalMeterService internalMeterService = new InternalMeterService();
    @Test
    public void testHasFreeViews(){
        assertFalse(internalMeterService.hasFreeViews(new BaseUserMeteredData(false, 0L, 0L, false, 0L, 0L, true), true, false));
    }

    @Test
    public void testHasFreeViewsWithNullMeteredData(){
        assertTrue(internalMeterService.hasFreeViews(null, true, false));
    }

    @Test
    public void testHasFreeViewsWithLoggedInUserButNoLimitReached(){
        assertTrue(internalMeterService.hasFreeViews(new BaseUserMeteredData(true, 0L, 0L, false, 0L, 0L, null), true, false));
    }

    @Test
    public void testHasFreeViewsWithAnonymousUser(){
        assertTrue(internalMeterService.hasFreeViews(new BaseUserMeteredData(true, 0L, 0L, false, 0L, 0L, null), false, false));
    }

    @Test
    public void testHasFreeViewsWithAnonymousUserAndLimitNotReached(){
        assertTrue(internalMeterService.hasFreeViews(new BaseUserMeteredData(true, 0L, 0L, null, 0L, 0L, null), false, false));
    }


    @Test
    public void testHasFreeViewsWithAnonymousUserAndLimitReached(){
        assertFalse(internalMeterService.hasFreeViews(new BaseUserMeteredData(true, 0L, 0L, true, 0L, 0L, null), false, false));
    }

}
