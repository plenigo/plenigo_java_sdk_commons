package com.plenigo.sdk.models;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Tests for {@link MeteredUserDataTest}.
 */
public class MeteredUserDataTest {
    @Test
    public void testToString() {
        MeteredUserData data = new MeteredUserData(true, 0L, 0L, true, 0L, 0L, true);
        assertNotNull(data.getFreeViewsAllowed());
        assertNotNull(data.getViewsTaken());
        assertNotNull(data.toString());
    }
}
