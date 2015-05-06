package com.plenigo.sdk.internal.util;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;


/**
 * <p>
 * Tests for {@link HexUtils}.
 * </p>
 */
public class HexUtilsTest {

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = HexUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
    /**
     * This tests the {@link HexUtils#toDigit(char, int)} method
     * with an invalid character.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testToDigitWithInvalidCharacter() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        char invalidChar = 'Z';
        Method method = HexUtils.class.getDeclaredMethod("toDigit", char.class, int.class);
        method.setAccessible(true);
        ReflectionUtils.invokeMethod(method, null, invalidChar, 0);
    }

    /**
     * This tests the {@link HexUtils#decodeHex(String)}
     * method with invalid data.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDecodeHexWithOddNumberOfCharacters() {
        String data = "invalid";
        HexUtils.decodeHex(data);
    }
}
