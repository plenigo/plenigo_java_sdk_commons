package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.internal.models.BasicAuthenticationCredentials;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * <p>
 * Tests for {@link com.plenigo.sdk.internal.util.HashUtils}.
 * </p>
 */
public class HttpConfigTest {

    public static final RestClient REST_CLIENT = new RestClient();
    public static final String USER = "user";
    public static final String PASSWORD = "pw";
    public static final BasicAuthenticationCredentials CREDENTIALS = new BasicAuthenticationCredentials(USER, PASSWORD);
    private HttpConfig httpConfig;

    @Before
    public void setup() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor constructor = HttpConfig.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        httpConfig = (HttpConfig) constructor.newInstance();
        httpConfig.setClient(REST_CLIENT);
        httpConfig.setCredentials(CREDENTIALS);
    }

    @Test
    public void testGetRestClient() throws Exception {
        assertEquals("Rest client is not correct", REST_CLIENT, httpConfig.getClient());
    }

    @Test
    public void testGetCredentials() throws Exception {
        assertEquals("Basic credentials are not correct", CREDENTIALS, httpConfig.getCredentials());
    }

    @Test
    public void testGet() throws Exception {
        assertNotNull(HttpConfig.get());
    }
}
