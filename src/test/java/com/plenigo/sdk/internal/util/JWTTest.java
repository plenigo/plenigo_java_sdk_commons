package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * <p>
 * Tests for {@link JWT}.
 * </p>
 */
public class JWTTest {
    public static final int EXP_VAR = 1447152549;
    private String SECRET_ID = "AMXzF7qJ9y0uuz2IawRIk6ZMLVeYKq9yXh7lURXQ";
    private String COMPANY_ID = "h7evZBaXvhaLVHYRTIHD";
    private String RESULT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJjb21wYW55SWQiOiJoN2V2WkJhWHZoYUxWSFlSVElIRCIsImF1ZCI6InBsZW5pZ28iLCJqdGkiOiIwYzMwN2VkMy0zNDA0LTk0OWMtOTE1Ny02NjQ0YjEzNDc4N2EiLCJleHAiOjE0NDcxNTI1NDl9.RQX7v8TGHd5P7Ucs5eLNTzXqRAIc9ZldPnBCzJPAmv8=";
    private String RANDOM_JTI = "0c307ed3-3404-949c-9157-6644b134787a";

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = JWT.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testSuccessfulEncoding() throws PlenigoException {
        JWT.setExpirationTime(1000);
        String jwtToken = JWT.generateJWTToken(COMPANY_ID, SECRET_ID, RANDOM_JTI, EXP_VAR);
        assertEquals(RESULT_TOKEN, jwtToken);
    }
}
