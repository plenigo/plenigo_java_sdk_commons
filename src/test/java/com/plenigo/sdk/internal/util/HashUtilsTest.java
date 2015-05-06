package com.plenigo.sdk.internal.util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;


/**
 * <p>
 * Tests for {@link HashUtils}.
 * </p>
 */
public class HashUtilsTest {

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = HashUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
