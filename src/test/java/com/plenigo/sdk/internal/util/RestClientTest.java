package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ApiURLs;
import com.plenigo.sdk.internal.ErrorCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

/**
 * <p>
 * Tests for {@link RestClient}.
 * </p>
 */


@RunWith(PowerMockRunner.class)
@PrepareForTest(RestClient.class)
public class RestClientTest {
    /**
     * The query string used on the examples.
     */
    private static final String QUERY_SUSHI_SOURCES_WEB =
            "query=sushi&sources=web";
    /**
     * The resource of the page.
     */
    private static final String JSON_ASPX = "/json.aspx";
    private static final String LOGGED_IN_OK_JSON = "{\"userId\":{\"Error\":\"cannot be null\",\"Rejected Value\":\"null\"}}";
    /**
     * URL of a test API.
     */
    private String API_URL = "http://example.com";
    private String url = "http://example.com";

    private RestClient client;

    @Before
    public void setUp() throws PlenigoException {
        client = Mockito.mock(RestClient.class);
        doReturn(new HashMap()).when(client).get(anyString(), anyString(), anyString(), anyString(), anyMap());
    }

    @Test
    public final void testGetCallToApi() throws PlenigoException {
        String referenceUrl = API_URL;
        Map<String, Object> stringObjectMap = client.get(API_URL, referenceUrl, "", null, null);
        assertNotNull(stringObjectMap);
    }

    @Test
    public final void testGetCallToApiWithHeaders() throws PlenigoException {
        
        String referenceUrl = API_URL;
        Map<String, Object> stringObjectMap = client.get(API_URL, referenceUrl, "", null, Collections.singletonMap("SampleHeader", "SampleValue"));
        assertNotNull(stringObjectMap);
    }

    @Test
    public final void testPostCallToApi() throws PlenigoException {
        
        String referenceUrl = " http://validate.jsontest.com";
        Map<String, Object> stringObjectMap = client.post(referenceUrl, referenceUrl, "", null, null, null);
        assertNotNull(stringObjectMap);
    }

    @Test
    public final void testPutCallToApi() throws PlenigoException {
        
        Map<String, String> data = new HashMap<String, String>();
        data.put("id", "1");
        data.put("title", "foo");
        data.put("body", "bar");
        data.put("userId", "1");
        String referenceUrl = API_URL;
        Map<String, Object> stringObjectMap = client.put(API_URL, referenceUrl, "", null, data, null);
        assertNotNull(stringObjectMap);
    }

    @Test
    public final void testDeleteCallToApi() throws PlenigoException {
        
        String referenceUrl = API_URL;
        Map<String, Object> stringObjectMap = client.delete(API_URL, referenceUrl, "", null, null);
        assertNotNull(stringObjectMap);
    }

    /**
     * Tests the {@link RestClient#buildURL(String, String)} method with a query
     * string.
     */
    @Test
    public final void testGetCall() throws PlenigoException {
        
        String referenceUrl = API_URL;
        Map<String, Object> stringObjectMap = client.get(API_URL, referenceUrl, "", null, null);
        assertNotNull(stringObjectMap);
    }

    /**
     * Tests the {@link RestClient#buildURL(String, String)} method with a query
     * string.
     */
    @Test
    public final void testBuildURLWithQueryParams() {
        String expectedUrl = "/json.aspx?query=sushi&sources=web";
        
        String action = JSON_ASPX;
        String query = QUERY_SUSHI_SOURCES_WEB;
        String builtURL = ReflectionTestUtils.invokeMethod(client, "buildURL", action, query);
        assertEquals(expectedUrl, builtURL);
    }

    @Test
    public final void testEncodedPasswordConstructor() throws UnsupportedEncodingException {
        String expectedUrl = "/json.aspx?query=sushi&sources=web";
        RestClient client = new RestClient("samplePassword");
        String action = JSON_ASPX;
        String query = QUERY_SUSHI_SOURCES_WEB;
        String builtURL = ReflectionTestUtils.invokeMethod(client, "buildURL", action, query);
        assertEquals(expectedUrl, builtURL);
    }

    @Test
    public final void testUsernameAndPasswordConstructor() throws UnsupportedEncodingException {
        String expectedUrl = "/json.aspx?query=sushi&sources=web";
        RestClient client = new RestClient("sampleUser", "samplePassword");
        String action = JSON_ASPX;
        String query = QUERY_SUSHI_SOURCES_WEB;
        String builtURL = ReflectionTestUtils.invokeMethod(client, "buildURL", action, query);
        assertEquals(expectedUrl, builtURL);
    }

    /**
     * Tests the {@link RestClient#buildURL(String, String)} method with a null
     * query string.
     */
    @Test
    public final void testBuildURLWithoutQueryParams() {
        String expectedUrl = JSON_ASPX;
        
        String action = JSON_ASPX;
        String builtURL = ReflectionTestUtils.invokeMethod(client, "buildURL", action, null);
        assertEquals(expectedUrl, builtURL);
    }

    /**
     * Tests the {@link RestClient#buildURL(String, String)} method with an
     * empty query string.
     */
    @Test
    public final void testBuildURLWithActionThatHasQuestionMark() {
        String expectedUrl = JSON_ASPX + RestClient.QUESTION_MARK;
        
        String query = "";
        String builtURL = ReflectionTestUtils.invokeMethod(client, "buildURL", expectedUrl, query);
        assertEquals(expectedUrl, builtURL);
    }


    /**
     * Tests the {@link RestClient#buildURL(String, String)} method with an
     * empty query string.
     */
    @Test
    public final void testBuildURLWithActionThatHasQuestionMarkAndQueryString() {
        String query = "sampleQueryString";
        String expectedUrl = JSON_ASPX + RestClient.QUESTION_MARK + query;
        
        String action = JSON_ASPX;
        String builtURL = ReflectionTestUtils.invokeMethod(client, "buildURL", action, query);
        assertEquals(expectedUrl, builtURL);
    }


    /**
     * Tests the {@link RestClient#buildURL(String, String)} method with an
     * empty query string.
     */
    @Test
    public final void testBuildURLWithActionThatHasQueryStringWithQuestionMark() {
        String query = "?sampleQueryString";
        String expectedUrl = JSON_ASPX + query;
        
        String action = JSON_ASPX;
        String builtURL = ReflectionTestUtils.invokeMethod(client, "buildURL", action, query);
        assertEquals(expectedUrl, builtURL);
    }


    /**
     * Tests the {@link RestClient#buildURL(String, String)} method with an
     * action containing the question mark.
     */
    @Test
    public final void testBuildURLWithEmptyQueryParams() {
        String expectedUrl = JSON_ASPX;
        
        String action = JSON_ASPX;
        String query = "";
        String builtURL = ReflectionTestUtils.invokeMethod(client, "buildURL", action, query);
        assertEquals(expectedUrl, builtURL);
    }

    /**
     * This tests a successful GET HTTP call.
     */
    @Test
    public final void testSuccessfulGet() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        mockOkConnection(client);
        String referenceUrl = "http://jsonplaceholder.typicode.com/posts";
        Map<String, Object> result = client.get(url, referenceUrl, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB, null);
        assertNotNull(result);
    }

    /**
     * This tests a an erroneous HTTPCall.
     */
    @Test(expected = PlenigoException.class)
    public final void testPlenigoExceptionOnResultCode() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        HttpURLConnection connection = Mockito
                .mock(HttpURLConnection.class);
        Mockito.when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST);
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class, Map.class))
                .withArguments(anyString(), anyString(), anyString(), anyMap()).thenReturn(connection);
        String referenceUrl = url;
        Map<String, Object> map = client.get(url, referenceUrl, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB, null);
        assertNotNull(map);
    }


    /**
     * This tests the {@link PlenigoException} catch code block on a ConnectionException happens.
     */
    @Test(expected = PlenigoException.class)
    public final void testPlenigoExceptionOnConnectException() throws Exception {
        testRestClientExceptions(new ConnectException());
    }


    /**
     * This tests the {@link PlenigoException} catch code block on an UnknownException happens.
     */
    @Test(expected = PlenigoException.class)
    public final void testPlenigoExceptionOnUnknownHostException() throws Exception {
        testRestClientExceptions(new UnknownHostException());
    }


    /**
     * This tests the {@link PlenigoException} catch code block on a General Exception happens.
     */
    @Test(expected = PlenigoException.class)
    public final void testPlenigoExceptionOnGeneralException() throws Exception {
        testRestClientExceptions(new IllegalArgumentException());
    }

    private void testRestClientExceptions(Exception exception) throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class, Map.class))
                .withArguments(anyString(), anyString(), anyString(), anyMap()).thenThrow(exception);
        String referenceUrl = url;
        client.get(url, referenceUrl, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB, null);
    }

    @Test(expected = PlenigoException.class)
    public final void testUnsuccessfulStreamClose() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        HttpURLConnection connection = Mockito.mock(HttpURLConnection.class);
        Mockito.when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_CLIENT_TIMEOUT);
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class, Map.class))
                .withArguments(anyString(), anyString(), anyString(), anyMap()).thenReturn(connection);
        InputStream mock = Mockito.mock(InputStream.class);
        Mockito.doThrow(new IOException("Test error")).when(mock).close();
        Mockito.when(connection.getErrorStream()).thenReturn(mock);
        Map<String, Object> result = client.get(url, url, JSON_ASPX,
                QUERY_SUSHI_SOURCES_WEB, null);
        assertNotNull(result);
    }

    private HttpURLConnection mockOkConnection(RestClient client) throws Exception {
        HttpURLConnection connection = Mockito.mock(HttpURLConnection.class);
        Mockito.when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        Mockito.when(connection.getInputStream()).thenReturn(new ByteArrayInputStream(LOGGED_IN_OK_JSON.getBytes()));
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class, Map.class))
                .withArguments(anyString(), anyString(), anyString(), anyMap()).thenReturn(connection);
        return connection;
    }

    @Test
    public final void testResponseCodeInPlenigoException() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        HttpURLConnection connection = Mockito
                .mock(HttpURLConnection.class);
        Mockito.when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_FORBIDDEN);
        Mockito.when(connection.getErrorStream()).thenReturn(new ByteArrayInputStream(LOGGED_IN_OK_JSON.getBytes()));
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class, Map.class))
                .withArguments(anyString(), anyString(), anyString(), anyMap()).thenReturn(connection);
        try {
            client.get(url, url, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB, null);
        } catch (PlenigoException pe) {
            assertEquals(String.valueOf(HttpURLConnection.HTTP_FORBIDDEN), pe.getResponseCode());
        }
    }

    @Test
    public final void testBadRequestResponseCode() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        HttpURLConnection connection = Mockito
                .mock(HttpURLConnection.class);
        Mockito.when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST);
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class, Map.class))
                .withArguments(anyString(), anyString(), anyString(), anyMap()).thenReturn(connection);
        String invalidParamsJson = "{\"userId\":{\"Error\":\"cannot be null\",\"Rejected Value\":\"null\"}}";
        Mockito.when(connection.getErrorStream()).thenReturn(new ByteArrayInputStream(invalidParamsJson.getBytes()));
        try {
            PowerMockito.doCallRealMethod().
                    when(client,
                            method(RestClient.class, "handleResponse", HttpURLConnection.class, String.class, String.class))
                    .withArguments(any(HttpURLConnection.class), anyString(), anyString());
            client.get(url, ApiURLs.USER_PRODUCT_ACCESS, ApiURLs.USER_PRODUCT_ACCESS, QUERY_SUSHI_SOURCES_WEB, null);
        } catch (PlenigoException pe) {
            assertEquals(ErrorCode.INVALID_PARAMETERS.getCode(), pe.getResponseCode());
            assertNotNull(pe.getErrors());
            assertFalse(pe.getErrors().isEmpty());
        }
    }


    /**
     * This tests a successful POST HTTP call.
     */
    @Test
    public final void testSuccessfulPost() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        HttpURLConnection connection = mockOkConnection(client);
        Mockito.when(connection.getOutputStream()).thenReturn(Mockito.mock(OutputStream.class));
        Map<String, Object> result = client.post(url,
                url, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB, null, null);
        assertNotNull(result);
    }


    /**
     * This tests a successful POST HTTP call with a body.
     */
    @Test
    public final void testSuccessfulPostWithBody() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        HttpURLConnection connection = mockOkConnection(client);
        Mockito.when(connection.getOutputStream()).thenReturn(Mockito.mock(OutputStream.class));
        Map<String, Object> result = client.post(url,
                url, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB, Collections.EMPTY_MAP, null);
        assertNotNull(result);
    }

    /**
     * This tests a successful POST HTTP call with a body.
     */
    @Test
    public final void testSuccessfulDelete() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        HttpURLConnection connection = mockOkConnection(client);
        Mockito.when(connection.getOutputStream()).thenReturn(Mockito.mock(OutputStream.class));
        Map<String, Object> result = client.delete(url,
                url, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB, null);
        assertNotNull(result);
    }

    @Test
    public final void testSuccessfulPostWithBodyAndHeader() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient("sampleUserNameAndPassword"));
        HttpURLConnection connection = mockOkConnection(client);
        Mockito.when(connection.getOutputStream()).thenReturn(Mockito.mock(OutputStream.class));
        Map<String, Object> result = client.post(url,
                url, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB, Collections.EMPTY_MAP, Collections.singletonMap("header", "value"));
        assertNotNull(result);
    }

    @Test
    public final void testSuccessfulPut() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        HttpURLConnection connection = mockOkConnection(client);
        Mockito.when(connection.getOutputStream()).thenReturn(Mockito.mock(OutputStream.class));
        Map<String, Object> result = client.put(url,
                url, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB, null, null);
        assertNotNull(result);
    }

    @Test
    public final void testCreateWithEncodedPassword() throws Exception {
        assertNotNull(new RestClient("test"));
    }

    @Test
    public final void testCreateWithUserAndPassword() throws Exception {
        assertNotNull(new RestClient("username", "password"));
    }
}
