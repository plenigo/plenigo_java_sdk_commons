package com.plenigo.sdk.internal.services;


import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ApiResults;
import com.plenigo.sdk.internal.util.HttpConfig;
import com.plenigo.sdk.internal.util.RestClient;
import com.plenigo.sdk.models.AccessTokenRequest;
import com.plenigo.sdk.models.RefreshTokenRequest;
import com.plenigo.sdk.models.TokenData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.support.SuppressCode.suppressConstructor;

/**
 * <p>
 * Tests for {@link InternalTokenApiService}.
 * </p>
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpConfig.class})
public class InternalTokenApiServiceTest {
    public static final String COMPANY_ID = "companyId";
    public static final String SECRET = "secret";
    public static final String URL = "url";
    private InternalTokenApiService internalTokenApiService = new InternalTokenApiService();
    private RestClient restClient;

    @Before
    public void setup() {
        suppressConstructor(HttpConfig.class);
        mockStatic(HttpConfig.class);
        HttpConfig httpConfig = PowerMockito.mock(HttpConfig.class);
        PowerMockito.when(HttpConfig.get()).thenReturn(httpConfig);
        restClient = Mockito.mock(RestClient.class);
        PowerMockito.when(httpConfig.getClient()).thenReturn(restClient);
    }


    @Test
    public void testSuccessfulAccessToken() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiResults.REFRESH_TOKEN, "123");
        map.put(ApiResults.STATE, "12345");
        map.put(ApiResults.ACCESS_TOKEN, "1");
        map.put(ApiResults.EXPIRES_IN, 3600L);
        Mockito.when(restClient.post(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(map);
        AccessTokenRequest request = new AccessTokenRequest("1234",URL);
        TokenData tokenData = internalTokenApiService.getAccessToken(COMPANY_ID, SECRET, URL, request);
        assertEquals(map.get(ApiResults.REFRESH_TOKEN), tokenData.getRefreshToken());
        assertEquals(map.get(ApiResults.ACCESS_TOKEN), tokenData.getAccessToken());
        assertEquals(map.get(ApiResults.EXPIRES_IN), tokenData.getExpiresIn());
        assertEquals(map.get(ApiResults.STATE), tokenData.getState());
    }

    @Test
    public void testSuccessfulRefreshAccessToken() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiResults.REFRESH_TOKEN, "123");
        map.put(ApiResults.STATE, "12345");
        map.put(ApiResults.ACCESS_TOKEN, "1");
        map.put(ApiResults.EXPIRES_IN, 3600L);
        Mockito.when(restClient.post(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(map);
        RefreshTokenRequest request = new RefreshTokenRequest("1234");
        TokenData tokenData = internalTokenApiService.getNewAccessToken(COMPANY_ID, SECRET, URL, request);
        assertEquals(map.get(ApiResults.REFRESH_TOKEN), tokenData.getRefreshToken());
        assertEquals(map.get(ApiResults.ACCESS_TOKEN), tokenData.getAccessToken());
        assertEquals(map.get(ApiResults.EXPIRES_IN), tokenData.getExpiresIn());
        assertEquals(map.get(ApiResults.STATE), tokenData.getState());
    }


    @Test(expected = PlenigoException.class)
    public void testUnsuccessfulRefreshAccessToken() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiResults.ERROR, "1233");
        map.put(ApiResults.ERROR_DESCRIPTION, "ERROR MSG");
        Mockito.when(restClient.post(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(map);
        RefreshTokenRequest request = new RefreshTokenRequest("1234", "12391823");
        internalTokenApiService.getNewAccessToken(COMPANY_ID, SECRET, URL, request);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testUnsuccessfulRefreshAccessTokenBecauseOfWrongState() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiResults.REFRESH_TOKEN, "123");
        map.put(ApiResults.STATE, "123456");
        map.put(ApiResults.ACCESS_TOKEN, "1");
        map.put(ApiResults.EXPIRES_IN, 3600L);
        Mockito.when(restClient.post(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(map);
        RefreshTokenRequest request = new RefreshTokenRequest("1234", "123");
        TokenData tokenData = internalTokenApiService.getNewAccessToken(COMPANY_ID, SECRET, URL, request);
        assertEquals(map.get(ApiResults.REFRESH_TOKEN), tokenData.getRefreshToken());
        assertEquals(map.get(ApiResults.ACCESS_TOKEN), tokenData.getAccessToken());
        assertEquals(map.get(ApiResults.EXPIRES_IN), tokenData.getExpiresIn());
        assertEquals(map.get(ApiResults.STATE), tokenData.getState());
    }


    @Test
    public void testCreateCsrfToken() throws Exception {
      assertNotNull(internalTokenApiService.createCsrfToken());
    }
}
