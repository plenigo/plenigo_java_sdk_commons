package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ApiResults;
import com.plenigo.sdk.models.ErrorDetail;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This class contains general SDK utilities that are required.
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
public final class SdkUtils {
    private static final Logger LOGGER = Logger.getLogger(SdkUtils.class.getName());

    /**
     * The default entry separator used between key value pairs.
     */
    private static final String ENTRY_SEPARATOR = "&";
    /**
     * The default separator between key and value.
     */
    private static final String KEY_VALUE_SEPARATOR = "=>";

    /**
     * The default separator used for URL query strings.
     */
    private static final String URL_QUERY_STRING_ENTRY_SEPARATOR = "&";

    /**
     * The default separator used for URL query strings.
     */
    private static final String URL_QUERY_STRING_KEY_VALUE_SEPARATOR = "=";

    /**
     * The container factory used through all the json parsing.
     */
    private static final ContainerFactory CONTAINER_FACTORY = new DefaultContainerFactory();


    /**
     * The JSON container factory.
     */
    private static class DefaultContainerFactory implements ContainerFactory {

        @Override
        public Map<String, String> createObjectContainer() {
            return new LinkedHashMap<String, String>();
        }

        @Override
        public List<Map<String, String>> creatArrayContainer() {
            return new LinkedList<Map<String, String>>();
        }
    }

    /**
     * Default constructor.
     */
    private SdkUtils() {
    }

    /**
     * This method converts a Map into a query string of key-value pairs with
     * the format that plenigo's API accepts.
     *
     * @param keyValuePairs The map to convert
     *
     * @return a String of key value pairs
     */
    public static String getStringFromMap(final Map<String, Object> keyValuePairs) {
        return buildQueryString(keyValuePairs, ENTRY_SEPARATOR, KEY_VALUE_SEPARATOR);
    }

    /**
     * This builds an encoded query string.
     *
     * @param map The map that contains all the key-value pairs.
     *
     * @return The query string
     */
    public static String buildUrlQueryString(final Map<String, Object> map) {
        return buildQueryString(map, URL_QUERY_STRING_ENTRY_SEPARATOR, URL_QUERY_STRING_KEY_VALUE_SEPARATOR);
    }

    /**
     * This builds query string with the given entry separator
     * and key value separator,
     * e.g: key1{keyValueSeparator}value1{entrySeparator}key2{keyValueSeparator}value2 .
     *
     * @param map               The key value map
     * @param entrySeparator    The separator used per key-value pair
     * @param keyValueSeparator The separator used to delimit the key and value
     *
     * @return A query string
     */
    private static String buildQueryString(Map<String, Object> map, String entrySeparator, String keyValueSeparator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> key : map.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(entrySeparator);
            }
            Object mapValue = key.getValue();
            String value = null;
            if (mapValue != null) {
                value = mapValue.toString();
            }

            if (mapValue instanceof Collection) {
                Collection values = (Collection) mapValue;
                for (Object collVal : values) {
                    if(stringBuilder.length() > 0 && stringBuilder.indexOf(entrySeparator) != stringBuilder.length() - 1) {
                        stringBuilder.append(entrySeparator);
                    }
                    if (collVal != null) {
                        value = collVal.toString();
                    }
                    appendQueryStringValue(keyValueSeparator, stringBuilder, key, value);
                }
            } else {
                appendQueryStringValue(keyValueSeparator, stringBuilder, key, value);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Append a query string value to the specified string builder.
     *
     * @param keyValueSeparator The separator of the key and value
     * @param stringBuilder     The string builder to append to
     * @param key               The key to be used
     * @param value             The value to be used
     */
    private static void appendQueryStringValue(String keyValueSeparator, StringBuilder stringBuilder, Map.Entry<String, Object> key,
                                               String value) {
        try {
            if (key.getKey() != null) {
                String keyStr;
                keyStr = URLEncoder.encode(key.getKey(), Charset.DEFAULT);
                stringBuilder.append(keyStr);
            }
            stringBuilder.append(keyValueSeparator);
            if (value != null) {
                String valueStr;
                valueStr = URLEncoder.encode(value, Charset.DEFAULT);
                stringBuilder.append(valueStr);
            }
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("The encoding is not supported", e);
        }
    }

    /**
     * This method parses a string with the format
     * that plenigo's API accepts, into a map.
     *
     * @param data The data to parse
     *
     * @return a {@link java.util.Map}
     */
    public static Map<String, String> getMapFromString(final String data) {
        Map<String, String> map = new HashMap<String, String>();
        for (String pairs : data.split(ENTRY_SEPARATOR)) {
            String[] entry = pairs.split(KEY_VALUE_SEPARATOR);
            if (entry.length < 2) {
                LOGGER.log(Level.FINEST, "Could not split entry data [{0}], exiting mapping...", data);
                break;
            }
            map.put(entry[0], entry[1]);
        }
        LOGGER.log(Level.FINEST, "Map data to return {0}", map);
        return map;
    }


    /**
     * This method is used to evaluate if a value
     * is null, in that case, it will not put
     * the key-value pair into the map.
     *
     * @param map   The map to use
     * @param key   The key
     * @param value The value to evaluate
     */
    public static void addIfNotNull(final Map<String, Object> map,
                                    final String key, final Object value) {
        if (value != null) {
            LOGGER.log(Level.FINEST, "Formatted value of key: {0} has the value of {1} , adding to map", new Object[]{key, value});
            map.put(key, value);
        } else {
            LOGGER.log(Level.FINEST, "Value of key {0} is null, not adding to map", key);
        }
    }

    /**
     * This method parses a JSON as a Map or List depending
     * on the input stream.
     *
     * @param in Input stream representing the JSON
     *
     * @return A {@link java.util.List} or {@link java.util.Map} representing
     * the json.
     *
     * @throws org.json.simple.parser.ParseException When there was an error parsing the JSON
     * @throws java.io.IOException                   When there was a read error.
     */
    private static Object parseJSON(final Reader in)
            throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        return parser.parse(in, CONTAINER_FACTORY);
    }

    /**
     * Returns the array value if it exists, otherwise it returns null.
     *
     * @param indexPosition The index position of the value
     * @param array         The array
     * @param outputClass   The output class
     * @param <O>           The output class placeholder
     *
     * @return The output class value
     *
     * @throws PlenigoException If a conversion error occurs
     */
    public static <O> O getArrayValueIfExistsOrNull(int indexPosition, String[] array, Class<O> outputClass) throws PlenigoException {
        O output = null;
        if (LOGGER.isLoggable(Level.FINEST)) {
            String arrayStr = null;
            if (array != null) {
                arrayStr = Arrays.toString(array);
            }
            LOGGER.log(Level.FINEST, "Attempting to retrieve the following array data: index position: {0}, array {1}, output class: {2}",
                    new Object[]{indexPosition, arrayStr, outputClass});
        }
        if (indexPosition >= 0 && array != null && array.length > indexPosition && array[indexPosition] != null) {
            /*
             * Converts an input type class to the specified output class using a 1 arg constructor, this is useful for wrapper classes mostly.
             */
            String input = array[indexPosition];
            output = convert(outputClass, input);
        }
        return output;
    }

    /**
     * Converts the input into the given output class using a one parameter constructor strategy.
     *
     * @param outputClass the output class to use
     * @param input       the input information
     * @param <O>         the type of the output class
     *
     * @return the converted instance
     *
     * @throws PlenigoException if an error happens during conversion
     */
    private static <O> O convert(Class<O> outputClass, String input) throws PlenigoException {
        O output = null;
        try {
            if (!(input == null || input.equals(""))) {
                output = outputClass.getConstructor(
                        String.class).newInstance(input);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, SdkUtils.getConversionErrMsg(input, outputClass), e);
            throw new PlenigoException(SdkUtils.getConversionErrMsg(input, outputClass), e);
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, SdkUtils.getConversionErrMsg(input, outputClass), e);
            throw new PlenigoException(SdkUtils.getConversionErrMsg(input, outputClass), e);
        } catch (InstantiationException e) {
            LOGGER.log(Level.SEVERE, SdkUtils.getConversionErrMsg(input, outputClass), e);
            throw new PlenigoException(SdkUtils.getConversionErrMsg(input, outputClass), e);
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, SdkUtils.getConversionErrMsg(input, outputClass), e);
            throw new PlenigoException(SdkUtils.getConversionErrMsg(input, outputClass), e);
        } catch (InvocationTargetException e) {
            LOGGER.log(Level.SEVERE, SdkUtils.getConversionErrMsg(input, outputClass), e);
            throw new PlenigoException(getConversionErrMsg(input, outputClass), e);
        } catch (NoSuchMethodException e) {
            LOGGER.log(Level.SEVERE, SdkUtils.getConversionErrMsg(input, outputClass), e);
            throw new PlenigoException(SdkUtils.getConversionErrMsg(input, outputClass), e);
        }
        return output;
    }

    /**
     * Returns a conversion error msg.
     *
     * @param input  The input data
     * @param output The output class
     *
     * @return The error message
     */
    private static String getConversionErrMsg(Object input, Object output) {
        return "Error occured while converting from input " + input
                + " to type " + output;
    }

    /**
     * This method parses a JSON as a Map.
     *
     * @param in Input stream representing the JSON
     *
     * @return A {@link java.util.Map} representing the json.
     *
     * @throws org.json.simple.parser.ParseException When there was an error parsing the JSON
     * @throws java.io.IOException                   When there was a read error.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseJSONObject(final Reader in)
            throws ParseException, IOException {
        Object o = parseJSON(in);
        Map<String, Object> objectMap;
        if (o instanceof Map) {
            objectMap = (Map<String, Object>) o;
        } else {
            objectMap = new HashMap<String, Object>();
            objectMap.put(ApiResults.ELEMENTS, o);
        }
        return objectMap;
    }


    /**
     * This join builds a query string separated by the ampersand sign.
     *
     * @param stringsToJoin The strings to delimit by the ampersand sign
     *
     * @return The joined string
     */
    public static String join(String... stringsToJoin) {
        StringBuilder builder = new StringBuilder(stringsToJoin[0]);
        for (int i = 1; i < stringsToJoin.length; i++) {
            builder.append(URL_QUERY_STRING_ENTRY_SEPARATOR).append(stringsToJoin[i]);
        }
        return builder.toString();
    }

    /**
     * Parses invalid parameters from a Map, this is usually used when
     * the API responds with a BAD_REQUEST parameter.
     *
     * @param parameters The mapped information
     *
     * @return a list of all the invalid parameters.
     */
    @SuppressWarnings("unchecked")
    public static List<ErrorDetail> parseInvalidParamsErrors(Map<String, Object> parameters) {
        List<ErrorDetail> errorDetails = new LinkedList<ErrorDetail>();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            String name = entry.getKey();
            String errorMsg = null;
            if (entry.getValue() instanceof Map) {
                //noinspection unchecked
                Map<String, String> errorDetail = (Map<String, String>) entry.getValue();
                errorMsg = errorDetail.get(ApiResults.ERROR_MSG);
            }
            errorDetails.add(new ErrorDetail(name, errorMsg));
        }
        return errorDetails;
    }

    /**
     * This method parses a URL query string into a Map.
     *
     * @param queryString The URL Query string
     *
     * @return The map of key-value pairs
     *
     * @throws java.io.UnsupportedEncodingException If The Charset is not supported
     */
    public static Map<String, String> parseQueryStringToMap(String queryString) throws UnsupportedEncodingException {
        String decodedQueryString = URLDecoder.decode(queryString, Charset.DEFAULT);
        Map<String, String> params = new LinkedHashMap<String, String>();
        String[] pairs = decodedQueryString.split(URL_QUERY_STRING_ENTRY_SEPARATOR);
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            params.put(pair.substring(0, idx), pair.substring(idx + 1));
        }
        return params;
    }

    /**
     * This converts an input stream into a String.
     *
     * @param is The input stream to convert
     *
     * @return The string retrieved from the input stream
     */
    public static String toString(java.io.InputStream is) {
        String streamContent = "";
        if (is == null) {
            return streamContent;
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(is, Charset.DEFAULT).useDelimiter("\\A");
            if (scanner.hasNext()) {
                streamContent = scanner.next();
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return streamContent;
    }

    /**
     * Returns an empty string if the value is not null, if it is null it will return an empty string.
     *
     * @param map The map to get the key value from
     * @param key The key to be used to get the value
     *
     * @return The corresponding value
     */
    public static String getValueIfNotNull(Map<String, Object> map, String key) {
        Object value = null;
        if (map != null) {
            value = map.get(key);
        }
        if (value == null) {
            return "";
        }
        return value.toString();
    }

    /**
     * Converts values to CSV values.
     *
     * @param values values to separate by comma
     *
     * @return a comma separated value list
     */
    public static String toCsv(List<String> values) {
        return listToCsv(values, ',');
    }

    /**
     * Converts a list to a string of values separated by the provided separator.
     *
     * @param listOfStrings the list of string to convert
     * @param separator     the separator
     *
     * @return a character separated string
     */
    private static String listToCsv(List<String> listOfStrings, char separator) {
        StringBuilder sb = new StringBuilder();

        // all but last
        for (int i = 0; i < listOfStrings.size() - 1; i++) {
            sb.append(listOfStrings.get(i));
            sb.append(separator);
        }

        // last string, no separator
        if (listOfStrings.size() > 0) {
            sb.append(listOfStrings.get(listOfStrings.size() - 1));
        }

        return sb.toString();
    }

    /**
     * Evaluates if a string is blank.
     *
     * @param str the string to evaluate
     *
     * @return true if its blank, false otherwise
     */
    public static boolean isBlank(final String str) {
        return str == null || (str.trim().length()) == 0;
    }

    /**
     * Evaluates if a string is not blank.
     *
     * @param str the string to evaluate
     *
     * @return true if its NOT blank, false otherwise
     */
    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }
}
