package com.plenigo.sdk.internal.util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;


/**
 * <p>
 * Tests for {@link Charset}.
 * </p>
 */
public class CharsetTest {

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = Charset.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
