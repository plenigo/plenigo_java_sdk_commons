package com.plenigo.sdk.internal.util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Date;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * <p>
 * Tests for {@link DateUtils}.
 * </p>
 */
public class DateUtilsTest {

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = DateUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testCopy() throws Exception {
        assertNull("Copy should return null if given date is null.", DateUtils.copy(null));
        assertNotNull("Copy should return date if given date is not null.", DateUtils.copy(new Date()));
    }
}
