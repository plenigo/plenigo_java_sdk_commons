package com.plenigo.sdk.internal;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;


/**
 * <p>
 * Tests for {@link ApiParams}.
 * </p>
 */
public class ApiParamsTest {

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = ApiParams.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
