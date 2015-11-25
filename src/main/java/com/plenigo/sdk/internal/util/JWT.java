package com.plenigo.sdk.internal.util;

import com.plenigo.sdk.PlenigoException;
import org.json.simple.JSONValue;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;


/**
 * <p>
 * This provides utility towards building Json web tokens (JWT).
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
public final class JWT {
    private static final int DEFAULT_EXPIRATION_TIME = (60 * 5);
    public static final int MILLIS_IN_A_SECOND = 1000;
    private static int expirationTime = DEFAULT_EXPIRATION_TIME;

    /**
     * Private utility constructor.
     */
    private JWT() {

    }

    /**
     * Sets the expiration time of the token in seconds.
     *
     * @param expirationTime expiration time in seconds
     */
    public static void setExpirationTime(int expirationTime) {
        JWT.expirationTime = expirationTime;
    }

    /**
     * Generates the Json Web token string.
     *
     * @param companyId the company id to include in the token
     * @param secret    company secret
     * @param jti       unique json token id
     * @param exp       expiration time
     *
     * @return jwt encoded string
     *
     * @throws PlenigoException if any exception happens during token creation
     */
    public static String generateJWTToken(String companyId, String secret, String jti, long exp) throws PlenigoException {
        Map<String, Object> algorithmHeader = new LinkedHashMap<String, Object>();
        algorithmHeader.put("alg", "HS256");
        Map<String, Object> payloadData = new LinkedHashMap<String, Object>();
        payloadData.put("companyId", companyId);
        payloadData.put("aud", "plenigo");
        payloadData.put("jti", jti);
        payloadData.put("exp", exp);
        String token;
        try {
            String algorithmHeaderJsonStr = JSONValue.toJSONString(algorithmHeader);
            String algorithmHeaderJson = Base64Util.encodeUrlSafe(algorithmHeaderJsonStr.getBytes(Charset.DEFAULT));
            String payloadHeaderJsonStr = JSONValue.toJSONString(payloadData);
            String payloadJson = Base64Util.encodeUrlSafe(payloadHeaderJsonStr.getBytes(Charset.DEFAULT));
            String dataToken = algorithmHeaderJson + "." + payloadJson;
            String signToken = HashUtils.calculateHMAC(dataToken, secret);
            token = dataToken + "." + signToken;
        } catch (Exception e) {
            throw new PlenigoException("An error occured while encoding the data", e);
        }
        return token;
    }

    /**
     * Creates a map with the header name and the json token.
     *
     * @param companyId company id
     * @param secret    company secret
     *
     * @return jwt encoded string
     *
     * @throws PlenigoException if any exception happens during token creation
     */
    public static Map<String, String> generateJWTTokenHeader(String companyId, String secret) throws PlenigoException {
        String jti = UUID.randomUUID().toString();
        long exp = (System.currentTimeMillis() / MILLIS_IN_A_SECOND) + (expirationTime); //add 5minutes to current time
        String token = "";
        if (companyId != null && !companyId.isEmpty() && secret != null && !secret.isEmpty()) {
            token = generateJWTToken(companyId, secret, jti, exp);
        }
        return Collections.singletonMap("plenigoToken", token);
    }
}
