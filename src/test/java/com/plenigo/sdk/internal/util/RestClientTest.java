package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ApiURLs;
import com.plenigo.sdk.internal.ErrorCode;
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
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
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
    private String url = "http://api.search.live.net";

    /**
     * Tests the {@link RestClient#buildURL(String, String)} method with a query
     * string.
     */
    @Test
    public final void testBuildURLWithQueryParams() {
        String expectedUrl = "/json.aspx?query=sushi&sources=web";
        RestClient client = new RestClient();
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
        RestClient client = new RestClient();
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
        RestClient client = new RestClient();
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
        RestClient client = new RestClient();
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
        RestClient client = new RestClient();
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
        RestClient client = new RestClient();
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
        Map<String, Object> result = client.get(url,
                JSON_ASPX, QUERY_SUSHI_SOURCES_WEB);
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
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class))
                .withArguments(anyString(), anyString(), anyString()).thenReturn(connection);
        Map<String, Object> map = client.get(url, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB);
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
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class))
                .withArguments(anyString(), anyString(), anyString()).thenThrow(exception);
        client.get(url, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB);
    }


    /**
     * This tests {@link RestClient#get(String, String, String)} method.
     */
    @Test(expected = PlenigoException.class)
    public final void testUnsuccessfulStreamClose() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        HttpURLConnection connection = Mockito.mock(HttpURLConnection.class);
        Mockito.when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_CLIENT_TIMEOUT);
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class))
                .withArguments(anyString(), anyString(), anyString()).thenReturn(connection);
        InputStream mock = Mockito.mock(InputStream.class);
        Mockito.doThrow(new IOException("Test error")).when(mock).close();
        Mockito.when(connection.getErrorStream()).thenReturn(mock);
        Map<String, Object> result = client.get(url, JSON_ASPX,
                QUERY_SUSHI_SOURCES_WEB);
        assertNotNull(result);
    }

    private HttpURLConnection mockOkConnection(RestClient client) throws Exception {
        HttpURLConnection connection = Mockito.mock(HttpURLConnection.class);
        Mockito.when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        Mockito.when(connection.getInputStream()).thenReturn(new ByteArrayInputStream(LOGGED_IN_OK_JSON.getBytes()));
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class))
                .withArguments(anyString(), anyString(), anyString()).thenReturn(connection);
        return connection;
    }

    /**
     * This tests {@link RestClient#get(String, String, String)} method.
     */
    @Test
    public final void testResponseCodeInPlenigoException() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        HttpURLConnection connection = Mockito
                .mock(HttpURLConnection.class);
        Mockito.when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_FORBIDDEN);
        Mockito.when(connection.getErrorStream()).thenReturn(new ByteArrayInputStream(LOGGED_IN_OK_JSON.getBytes()));
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class))
                .withArguments(anyString(), anyString(), anyString()).thenReturn(connection);
        try {
            client.get(url, JSON_ASPX, QUERY_SUSHI_SOURCES_WEB);
        } catch (PlenigoException pe) {
            assertEquals(String.valueOf(HttpURLConnection.HTTP_FORBIDDEN), pe.getResponseCode());
        }
    }


    /**
     * This tests {@link RestClient#get(String, String, String)} method.
     */
    @Test
    public final void testBadRequestResponseCode() throws Exception {
        RestClient client = PowerMockito.spy(new RestClient());
        HttpURLConnection connection = Mockito
                .mock(HttpURLConnection.class);
        Mockito.when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST);
        PowerMockito.when(client, method(RestClient.class, "getHttpConnection", String.class, String.class, String.class))
                .withArguments(anyString(), anyString(), anyString()).thenReturn(connection);
        String invalidParamsJson = "{\"userId\":{\"Error\":\"cannot be null\",\"Rejected Value\":\"null\"}}";
        Mockito.when(connection.getErrorStream()).thenReturn(new ByteArrayInputStream(invalidParamsJson.getBytes()));
        try {
            PowerMockito.doCallRealMethod().
                    when(client,
                            method(RestClient.class, "handleResponse", HttpURLConnection.class, String.class))
                    .withArguments(any(HttpURLConnection.class), anyString());
            client.get(url, ApiURLs.USER_PRODUCT_ACCESS, QUERY_SUSHI_SOURCES_WEB);
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
                JSON_ASPX, QUERY_SUSHI_SOURCES_WEB);
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
                JSON_ASPX, QUERY_SUSHI_SOURCES_WEB, Collections.EMPTY_MAP);
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
                JSON_ASPX, QUERY_SUSHI_SOURCES_WEB);
        assertNotNull(result);
    }

    @Test
    public final void testCreateWithEncodedPassword() throws Exception {
        assertNotNull(new RestClient("test"));
    }

    @Test
    public final void testCreateWithUserAndPassword() throws Exception {
        assertNotNull(new RestClient("username","password"));
    }
}
