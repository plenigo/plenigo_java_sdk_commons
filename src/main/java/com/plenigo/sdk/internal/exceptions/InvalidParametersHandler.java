package com.plenigo.sdk.internal.exceptions;


import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ErrorCode;
import com.plenigo.sdk.internal.util.Charset;
import com.plenigo.sdk.internal.util.SdkUtils;
import com.plenigo.sdk.models.ErrorDetail;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Invalid parameter handler.
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
public class InvalidParametersHandler implements ErrorHandler {
    private static final Logger LOGGER = Logger.getLogger(InvalidParametersHandler.class.getName());

    /**
     * This method handles error information and converts it into a {@link PlenigoException}.
     *
     * @param errorCode   The error code
     * @param apiUrl      The API Url
     * @param inputStream The inputStream
     *
     * @return A {@link PlenigoException} object
     */
    public PlenigoException handle(ErrorCode errorCode, String apiUrl, InputStream inputStream) {
        LOGGER.log(Level.FINEST, "Handling invalid parameter exception {0} for apiUrl: {1}", new Object[]{errorCode, apiUrl});
        try {
            Map<String, Object> jsonObject = SdkUtils.
                    parseJSONObject(
                            new InputStreamReader(inputStream, Charset.DEFAULT));
            LOGGER.log(Level.FINEST, "Parsed invalid parameter inputstream data: {0}", jsonObject);
            List<ErrorDetail> errorDetails = SdkUtils.parseInvalidParamsErrors(jsonObject);
            LOGGER.log(Level.FINEST, "Error detail data taken from the parsed data: {0}", errorDetails);
            return new PlenigoException(errorCode.getCode(), errorCode.getMsg(), errorDetails);
        } catch (ParseException e) {
            throw new IllegalArgumentException("There was an error parsing the json response.", e);
        } catch (IOException e) {
            throw new IllegalStateException("There was an error reading the response.", e);
        }
    }
}
