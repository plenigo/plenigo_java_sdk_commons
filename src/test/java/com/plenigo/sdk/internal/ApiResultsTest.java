package com.plenigo.sdk.internal;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;


/**
 * <p>
 * Tests for {@link ApiResults}.
 * </p>
 */
public class ApiResultsTest {

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = ApiResults.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
