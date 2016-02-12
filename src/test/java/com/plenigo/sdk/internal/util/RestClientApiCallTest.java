package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import org.junit.Test;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link RestClient}.
 * </p>
 */

public class RestClientApiCallTest {
    public static final String API_URL = "http://jsonplaceholder.typicode.com/posts/1";

    @Test
    public final void testGetCallToApi() throws PlenigoException, UnsupportedEncodingException {
        RestClient client = new RestClient("encodedPassword");
        Map<String, Object> stringObjectMap = client.get(API_URL, API_URL, "", null, null);
        assertNotNull(stringObjectMap);
    }

    @Test
    public final void testGetCallToApiWithHeaders() throws PlenigoException, UnsupportedEncodingException {
        RestClient client = new RestClient("userTest", "passwordTest");
        Map<String, Object> stringObjectMap = client.get(API_URL, API_URL, "", null, Collections.singletonMap("SampleHeader", "SampleValue"));
        assertNotNull(stringObjectMap);
    }
    @Test
    public final void testPostCallToApi() throws PlenigoException {
        RestClient client = new RestClient();
        String apiUrl = "http://jsonplaceholder.typicode.com/posts";
        Map<String, Object> stringObjectMap = client.post(apiUrl, apiUrl, "", null, null, null);
        assertNotNull(stringObjectMap);
    }

    @Test
    public final void testPutCallToApi() throws PlenigoException {
        RestClient client = new RestClient();
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
        RestClient client = new RestClient();
        Map<String, Object> stringObjectMap = client.delete(API_URL, API_URL, "", null, null);
        assertNotNull(stringObjectMap);
    }
}
