package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ErrorCode;
import com.plenigo.sdk.internal.exceptions.ApiExceptionTranslator;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This class is a client used to communicate with plenigo's API services.
 * </p>
 * <p>
 * <b>IMPORTANT:</b> This class is part of the internal API, please do not use it, because it can
 * be removed in future versions of the SDK or access to such elements could
 * be changed from 'public' to 'default' or less.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is thread safe and can be injected.
 * </p>
 */
public class RestClient {

    private static final Logger LOGGER = Logger.getLogger(RestClient.class.getName());
    /**
     * Default connection timeout.
     */
    private static final int DEFAULT_CONN_TIMEOUT = 10000;
    /**
     * Default read timeout.
     */
    private static final int DEFAULT_READ_TIMEOUT = 20000;
    /**
     * Get Method.
     */
    private static final String GET_METHOD = "GET";
    /**
     * POST Method.
     */
    private static final String POST_METHOD = "POST";
    /**
     * DELETE Method.
     */
    private static final String DELETE_METHOD = "DELETE";

    /**
     * Http Accept header name.
     */
    private static final String ACCEPT_HEADER_NAME = "Accept";

    /**
     * JSON media type.
     */
    private static final String JSON_MEDIA_TYPE = "application/json";
    /**
     * Authorization header name.
     */
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    /**
     * Basic authentication prefix.
     */
    private static final String BASIC_AUTH_PREFIX = "Basic ";
    /**
     * Question mark that separates the action from the
     * query string.
     */
    public static final String QUESTION_MARK = "?";
    public static final int INITIAL_CORRECT_STATUS_CODE = 200;
    public static final int FINAL_CORRECT_STATUS_CODE = 300;

    /**
     * Default headers used in every request.
     */
    private Map<String, String> defaultHeaders;

    /**
     * Connection timeout used by the client instance.
     */
    private int connectionTimeout;

    /**
     * Connection timeout used by the client instance.
     *
     * @return the connectionTimeout
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Sets the connection timeout to use by the client.
     *
     * @param milliseconds the connectionTimeout to set
     */
    public void setConnectionTimeout(final int milliseconds) {
        this.connectionTimeout = milliseconds;
    }

    /**
     * Returns the read timeout used by the client.
     *
     * @return the readTimeout
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * @param milliseconds the milliseconds to set
     */
    public void setReadTimeout(final int milliseconds) {
        this.readTimeout = milliseconds;
    }

    /**
     * Read timeout used by the client.
     */
    private int readTimeout;

    /**
     * Default constructor.
     */
    public RestClient() {
        setConnectionTimeout(DEFAULT_CONN_TIMEOUT);
        setReadTimeout(DEFAULT_READ_TIMEOUT);
        defaultHeaders = new HashMap<String, String>();
        defaultHeaders.put(ACCEPT_HEADER_NAME, JSON_MEDIA_TYPE);
    }


    /**
     * Constructor that creates an Authorization header.
     *
     * @param encodedusernameAndPassword The encoded basic authentication user name/password
     *
     * @throws java.io.UnsupportedEncodingException When the default encoding is not supported
     */
    public RestClient(String encodedusernameAndPassword) throws UnsupportedEncodingException {
        this();
        String authorizationString = BASIC_AUTH_PREFIX + encodedusernameAndPassword;
        defaultHeaders.put(AUTHORIZATION_HEADER_NAME, authorizationString);
    }


    /**
     * Constructor that creates an Authorization header.
     *
     * @param username The basic authentication user name
     * @param password The basic authentication password
     *
     * @throws java.io.UnsupportedEncodingException When the default encoding is not supported
     */
    public RestClient(String username, String password) throws UnsupportedEncodingException {
        this();
        String usernamePassword = username + ":" + password;

        String authorizationString = BASIC_AUTH_PREFIX + DatatypeConverter.printBase64Binary(
                usernamePassword.getBytes(Charset.DEFAULT));
        defaultHeaders.put(AUTHORIZATION_HEADER_NAME, authorizationString);
    }

    /**
     * This method builds an URL based on the action and the query string.
     *
     * @param action The action to call
     * @param query  The query string to use
     *
     * @return a partial URL with the action and the query string
     */
    private String buildURL(final String action, final String query) {
        StringBuilder url = new StringBuilder(action);
        if (query != null && !query.isEmpty()) {
            if (!action.contains(QUESTION_MARK) && !query.contains(QUESTION_MARK)) {
                url.append(QUESTION_MARK);
            }
            url.append(query);
        }
        return url.toString();
    }

    /**
     * This method does a HTTP request and reads the response as a JSON object
     * and puts it into a {@link java.util.Map}.
     *
     * @param apiUrl  The URL of the method call
     * @param method  The method to call e.g. (GET or POST)
     * @param action  The action to call
     * @param query   The query string to use
     * @param body    the json body to send
     * @param headers the headers to send
     *
     * @return map of JSON based results
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> call(String apiUrl, final String method,
                                     final String action, final String query, final Map<String, String> body, final Map<String, String> headers)
            throws PlenigoException {
        String restCallInfo = String.format("rest resource call: [apiUrl: %s, method: %s, action: %s]", apiUrl, method
                , action);
        LOGGER.log(Level.INFO, "Doing a " + restCallInfo);
        try {
            HttpURLConnection con = getHttpConnection(apiUrl, action, query, headers);
            con.setRequestMethod(method);
            addBodyToRequest(con, body);
            return handleResponse(con, action);
        } catch (ConnectException e) {
            throw new PlenigoException(ErrorCode.CONNECTION_ERROR, restCallInfo + " had a connection error", e);
        } catch (UnknownHostException e) {
            throw new PlenigoException(ErrorCode.UNKNOWN_HOST, restCallInfo + " is using an unknown host: ", e);
        } catch (PlenigoException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, restCallInfo + " had an unexpected error", e);
            throw new PlenigoException(ErrorCode.SERVER, ErrorCode.SERVER.getMsg(), e);
        }
    }

    /**
     * Adds the body map as a json object to the http request.
     *
     * @param con  the http connection
     * @param body the json body to send
     *
     * @throws IOException if a write error occurs
     */
    private void addBodyToRequest(HttpURLConnection con, Map<String, String> body) throws IOException {
        if (body != null) {
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            byte[] outputInBytes = new JSONObject(body).toJSONString().getBytes(Charset.DEFAULT);
            OutputStream os = con.getOutputStream();
            os.write(outputInBytes);
            os.flush();
            os.close();
        }
    }

    /**
     * This handles the response or throws a PlenigoException based on the response code.
     *
     * @param con      The HTTPConnection
     * @param resource The api resource name used
     *
     * @return A Map that represents the json response
     *
     * @throws java.io.IOException              if a communication error happened
     * @throws ParseException                   If there was an error parsing the response given as a JSON
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    private Map<String, Object> handleResponse(HttpURLConnection con, String resource) throws IOException, ParseException, PlenigoException {
        Map<String, Object> json;
        InputStream in = null;
        try {
            LOGGER.log(Level.FINEST, "HTTP Response for resource {0}: {1}", new Object[]{resource, con.getResponseCode()});
            if (con.getResponseCode() >= INITIAL_CORRECT_STATUS_CODE && con.getResponseCode() <= FINAL_CORRECT_STATUS_CODE) {
                in = con.getInputStream();
                try {
                    json = SdkUtils.
                            parseJSONObject(
                                    new InputStreamReader(in, Charset.DEFAULT));
                } catch (ParseException pe) {
                    //we will return the parse exceptions as null since there are calls that do not contain any data to read
                    //This will only be considered for OK responses
                    //Only case where this would also return null is if the response json is also invalid
                    return null;
                }
                LOGGER.log(Level.FINEST, "Parsed JSON Response for resource {0}: {1}", new Object[]{resource, json});
            } else {
                in = con.getErrorStream();
                throw ApiExceptionTranslator.get().translate(String.valueOf(con.getResponseCode()), resource, in);
            }
        } finally {
            if (in != null) {
                try {
                    LOGGER.log(Level.FINEST, "Closing response stream...");
                    in.close();
                    LOGGER.log(Level.FINEST, "Response stream closed");
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error while closing the response stream", e);
                }
            }
            if (con != null) {
                LOGGER.log(Level.FINEST, "Closing connection stream...");
                con.disconnect();
                LOGGER.log(Level.FINEST, "Connection stream closed");
            }
        }
        return json;
    }

    /**
     * Creates a HTTP connection with the base URL, the action
     * that wants to be called and the query string if any.
     *
     * @param apiUrl  The API url to be queried
     * @param action  action to be used
     * @param query   query string
     * @param headers the headers to send
     *
     * @return {@link java.net.HttpURLConnection}
     *
     * @throws java.io.IOException When there is a read/write error
     */
    private HttpURLConnection getHttpConnection(String apiUrl, String action,
                                                String query, Map<String, String> headers) throws IOException {
        if (apiUrl == null || apiUrl.isEmpty()) {
            LOGGER.log(Level.FINEST, "There api url is null or empty, not doing Http Request");
            return null;
        }

        String fullURL;

        fullURL = apiUrl + buildURL(action, query);

        URL url = new URL(fullURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setReadTimeout(getReadTimeout());
        con.setConnectTimeout(getConnectionTimeout());

        if (!defaultHeaders.isEmpty()) {
            for (Map.Entry<String, String> header : defaultHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
        }
        addHeadersToRequest(con, defaultHeaders);
        addHeadersToRequest(con, headers);
        return con;
    }

    /**
     * Adds the headers to teh http request.
     *
     * @param con     the connection to use
     * @param headers headers to add
     */
    private void addHeadersToRequest(HttpURLConnection con, Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
        }
    }

    /**
     * This does a GET HTTP call.
     *
     * @param apiUrl   The base API url
     * @param resource The resource to call
     * @param query    The query string to use
     * @param headers  the headers to send
     *
     * @return The map result of the call
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public Map<String, Object> get(String apiUrl, String resource,
                                   String query, Map<String, String> headers) throws PlenigoException {
        return call(apiUrl, GET_METHOD, resource, query, null, headers);
    }

    /**
     * This does a POST HTTP call and accepts a body to be sent as a json object.
     *
     * @param apiUrl   The base API url
     * @param resource The resource to call
     * @param query    The query string to use
     * @param body     The query string to use
     * @param headers  the headers to send
     *
     * @return The map result of the call
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public Map<String, Object> post(String apiUrl, String resource,
                                    String query, Map<String, String> body, Map<String, String> headers) throws PlenigoException {
        return call(apiUrl, POST_METHOD, resource, query, body, headers);
    }

    /**
     * This does a DELETE HTTP call.
     *
     * @param apiUrl   The base API url
     * @param resource The resource to call
     * @param query    The query string to use
     * @param headers  the headers to send
     *
     * @return The map result of the call
     *
     * @throws com.plenigo.sdk.PlenigoException whenever an error happens
     */
    public Map<String, Object> delete(String apiUrl, String resource,
                                      String query, Map<String, String> headers) throws PlenigoException {
        return call(apiUrl, DELETE_METHOD, resource, query, null, headers);
    }
}
