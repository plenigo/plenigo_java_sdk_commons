package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import org.junit.Before;
import org.junit.Test;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * <p>
 * Tests for {@link RestClient}.
 * </p>
 */

public class RestClientApiCallTest {
    public static final String API_URL = "http://date.jsontest.com";
    private RestClient client;

    @Before
    public void setup(){
        client = mock(RestClient.class);
    }

    @Test
    public final void testGetCallToApi() throws PlenigoException, UnsupportedEncodingException {
        Map<String, Object> stringObjectMap = client.get(API_URL, API_URL, "", null, null);
        assertNotNull(stringObjectMap);
    }

    @Test
    public final void testGetCallToApiWithHeaders() throws PlenigoException, UnsupportedEncodingException {
        Map<String, Object> stringObjectMap = client.get(API_URL, API_URL, "", null, Collections.singletonMap("SampleHeader", "SampleValue"));
        assertNotNull(stringObjectMap);
    }
    @Test
    public final void testPostCallToApi() throws PlenigoException {
        String apiUrl = "http://date.jsontest.com";
        Map<String, Object> stringObjectMap = client.post(apiUrl, apiUrl, "", null, null, null);
        assertNotNull(stringObjectMap);
    }

    @Test
    public final void testPutCallToApi() throws PlenigoException {
        Map<String, String> data = new HashMap<String, String>();
        data.put("id", "1");
        data.put("title", "foo");
        data.put("body", "bar");
        data.put("userId", "1");
        Map<String, Object> stringObjectMap = client.put(API_URL, API_URL, "", null, data, null);
        assertNotNull(stringObjectMap);
    }

    @Test
    public final void testDeleteCallToApi() throws PlenigoException {
        Map<String, Object> stringObjectMap = client.delete(API_URL, API_URL, "", null, null);
        assertNotNull(stringObjectMap);
    }
}
