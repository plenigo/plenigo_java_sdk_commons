package com.plenigo.sdk.internal.services;


import com.plenigo.sdk.models.MeteredUserData;
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
        assertFalse(internalMeterService.hasFreeViews(new MeteredUserData(false, 0L, 0L, false, 0L, 0L, true), true));
    }

    @Test
    public void testHasFreeViewsWithNullMeteredData(){
        assertTrue(internalMeterService.hasFreeViews(null, true));
    }

    @Test
    public void testHasFreeViewsWithLoggedInUserButNoLimitReached(){
        assertTrue(internalMeterService.hasFreeViews(new MeteredUserData(true, 0L, 0L, false, 0L, 0L, null), true));
    }

    @Test
    public void testHasFreeViewsWithAnonymousUser(){
        assertTrue(internalMeterService.hasFreeViews(new MeteredUserData(true, 0L, 0L, false, 0L, 0L, null), false));
    }

    @Test
    public void testHasFreeViewsWithAnonymousUserAndLimitNotReached(){
        assertTrue(internalMeterService.hasFreeViews(new MeteredUserData(true, 0L, 0L, null, 0L, 0L, null), false));
    }


    @Test
    public void testHasFreeViewsWithAnonymousUserAndLimitReached(){
        assertFalse(internalMeterService.hasFreeViews(new MeteredUserData(true, 0L, 0L, true, 0L, 0L, null), false));
    }

}
