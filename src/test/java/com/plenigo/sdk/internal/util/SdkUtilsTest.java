package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.models.ErrorDetail;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * <p>
 * Tests for {@link SdkUtils}.
 * </p>
 */
public class SdkUtilsTest {

    /**
     * The number 3 constant.
     */
    private static final int THREE = 3;
    /**
     * The number 5 constant.
     */
    private static final int FIVE = 5;

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = SdkUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    /**
     * Tests the {@link SdkUtils#getStringFromMap(java.util.Map)} method with valid
     * values.
     */
    @Test
    public final void testGetStringFromMapWithCorrectValues() {
        String expectedOutput = "one=>5&two=>3";
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("one", FIVE);
        map.put("two", THREE);
        String stringFromMap = SdkUtils.getStringFromMap(map);
        assertEquals(expectedOutput, stringFromMap);
    }

    /**
     * Tests the {@link SdkUtils#getStringFromMap(java.util.Map)} method with empty string
     * values.
     */
    @Test
    public final void testGetStringFromMapWithEmptyValues() {
        String expectedOutput = "=>";
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("", "");
        String stringFromMap = SdkUtils.getStringFromMap(map);
        assertEquals(expectedOutput, stringFromMap);
    }

    /**
     * Tests the {@link SdkUtils#getStringFromMap(java.util.Map)} method with null values.
     */
    @Test
    public final void testGetStringFromMapWithNullValues() {
        String expectedOutput = "=>";
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put(null, null);
        String stringFromMap = SdkUtils.getStringFromMap(map);
        assertEquals(expectedOutput, stringFromMap);
    }

    /**
     * Tests the {@link SdkUtils#addIfNotNull(java.util.Map, String, Object)} with a null
     * value.
     */
    @Test
    public final void testAddIfNotNullWithNullValue() {
        Map<String, Object> map = new HashMap<String, Object>();
        String key = "key";
        SdkUtils.addIfNotNull(map, key, null);
        assertTrue(map.isEmpty());
    }

    /**
     * Tests the {@link SdkUtils#addIfNotNull(java.util.Map, String, Object)} with a valid
     * value.
     */
    @Test
    public final void testAddIfNotNullWithValue() {
        Map<String, Object> map = new HashMap<String, Object>();
        String key = "key";
        Object value = "123";
        SdkUtils.addIfNotNull(map, key, value);
        assertFalse(map.isEmpty());
    }

    /**
     * Tests the {@link SdkUtils#addIfNotNull(java.util.Map, String, Object)} with an
     * empty string value.
     */
    @Test
    public final void testAddIfNotNullWithEmptyStringValue() {
        Map<String, Object> map = new HashMap<String, Object>();
        String key = "key";
        Object value = "";
        SdkUtils.addIfNotNull(map, key, value);
        assertFalse(map.isEmpty());
    }

    /**
     * Tests the {@link SdkUtils#addIfNotNull(java.util.Map, String, Object)} with a null
     * key.
     */
    @Test
    public final void testAddIfNotNullWithNullKey() {
        Map<String, Object> map = new HashMap<String, Object>();
        Object value = "";
        SdkUtils.addIfNotNull(map, null, value);
        assertFalse(map.isEmpty());
    }

    /**
     * Tests the {@link SdkUtils#addIfNotNull(java.util.Map, String, Object)} with a null
     * value and a null key.
     */
    @Test
    public final void testAddIfNotNullWithNullKeyAndNullValue() {
        Map<String, Object> map = new HashMap<String, Object>();
        SdkUtils.addIfNotNull(map, null, null);
        assertTrue(map.isEmpty());
    }


    /**
     * This tests an expected result from the
     * {@link SdkUtils#join(String...)}
     * function
     */
    @Test
    public void testSuccessfulJoin() {
        String expectedResult = "one=one&two=two&three=three";
        assertEquals(expectedResult, SdkUtils.join("one=one", "two=two", "three=three"));
    }

    @Test
    public void testInvalidParametersParse() throws IOException, org.json.simple.parser.ParseException {
        String invalidParamsJson = "{\"userId\":{\"Error\":\"cannot be null\",\"Rejected Value\":\"null\"},\"password\":{\"Error\":\"cannot be null\"," +
                "\"Rejected Value\":\"null\"}}";
        Map<String, Object> json = SdkUtils.parseJSONObject(new StringReader(invalidParamsJson));
        List<ErrorDetail> errors = SdkUtils.parseInvalidParamsErrors(json);
        assertNotNull(errors);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);
        for (ErrorDetail error : errors) {
            assertNotNull(error.getName());
            assertNotNull(error.getDescription());
        }
    }

    @Test
    public void testSuccessfulParseQueryStringToMap() throws UnsupportedEncodingException {
        String queryString = "state=security_token%3D138r5719ru3e1%26url%3Dhttps://example.com/given_path&code=AdE123412EdVD";
        Map<String, String> params = SdkUtils.parseQueryStringToMap(queryString);
        assertNotNull(params);
        assertFalse(params.isEmpty());
        assertTrue(params.containsKey("state"));
        assertTrue(params.containsKey("url"));
        assertTrue(params.containsKey("code"));
    }

    @Test
    public void testSuccessfulGetValueIfNotNull() {
        String key = "KEY";
        String value = "VALUE";
        Map<String, Object> result = Collections.singletonMap(key, (Object) value);
        String val = SdkUtils.getValueIfNotNull(result, key);
        assertNotNull(val.isEmpty());
        assertFalse(val.isEmpty());
        assertEquals(val, val);
    }

    @Test
    public void testGetValueIfNotNullWIthNullValue() {
        String key = "KEY";
        Map<String, Object> result = Collections.singletonMap(key, null);
        String val = SdkUtils.getValueIfNotNull(result, key);
        assertNotNull(val);
        assertTrue(val.isEmpty());
    }


    @Test
    public void testGetValueIfNotNullWIthEmptyValue() {
        String key = "KEY";
        Map<String, Object> result = Collections.singletonMap(key, (Object) "");
        String val = SdkUtils.getValueIfNotNull(result, key);
        assertNotNull(val);
        assertTrue(val.isEmpty());
    }


    @Test
    public void testGetMapFromStringWithLessThanTwoParameters() {
        String data = "";
        Map<String, String> val = SdkUtils.getMapFromString(data);
        assertNotNull(val);
        assertTrue(val.isEmpty());
    }


    @Test
    public void testConversionWitNegativeIndex() throws PlenigoException {
        assertNull(SdkUtils.getArrayValueIfExistsOrNull(-1, new String[]{null}, Boolean.class));
    }


    @Test
    public void testConversionWitNonExistingIndex() throws PlenigoException {
        assertNull(SdkUtils.getArrayValueIfExistsOrNull(1, new String[]{null}, Boolean.class));
    }

    @Test
    public void testConversionWithNullValue() throws PlenigoException {
        assertNull(SdkUtils.getArrayValueIfExistsOrNull(0, new String[]{null}, Boolean.class));
    }

    @Test
    public void testConversionWithEmptyValue() throws PlenigoException {
        assertNull(SdkUtils.getArrayValueIfExistsOrNull(0, new String[]{""}, Boolean.class));
    }

    @Test
    public void testConversionWithTrueBoolean() throws PlenigoException {
        assertTrue(SdkUtils.getArrayValueIfExistsOrNull(0, new String[]{"true"}, Boolean.class));
    }

    @Test
    public void testConversionWithFalseBoolean() throws PlenigoException {
        assertFalse(SdkUtils.getArrayValueIfExistsOrNull(0, new String[]{"false"}, Boolean.class));
    }


    @Test
    public void testConversionWithPositiveLongNumber() throws PlenigoException {
        String number = Long.MAX_VALUE + "";
        assertTrue(SdkUtils.getArrayValueIfExistsOrNull(0, new String[]{number}, Long.class).equals(Long.MAX_VALUE));
    }


    @Test
    public void testConversionWithNegativeLongNumber() throws PlenigoException {
        String number = Long.MIN_VALUE + "";
        assertTrue(SdkUtils.getArrayValueIfExistsOrNull(0, new String[]{number}, Long.class).equals(Long.MIN_VALUE));
    }
}
