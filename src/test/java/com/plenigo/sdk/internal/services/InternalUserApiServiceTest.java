package com.plenigo.sdk.internal.services;


import com.plenigo.sdk.PlenigoException;
import com.plenigo.sdk.internal.ApiResults;
import com.plenigo.sdk.internal.ErrorCode;
import com.plenigo.sdk.internal.util.HashUtils;
import com.plenigo.sdk.internal.util.HttpConfig;
import com.plenigo.sdk.internal.util.JWT;
import com.plenigo.sdk.internal.util.RestClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.support.SuppressCode.suppressConstructor;

/**
 * <p>
 * Tests for {@link InternalUserApiService}.
 * </p>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpConfig.class, JWT.class, HashUtils.class})
public class InternalUserApiServiceTest {
    private InternalUserApiService internalUserApiService = new InternalUserApiService();
    private RestClient restClient;
    private String SECRET_ID = "AMXzF7qJ9y0uuz2IawRIk6ZMLVeYKq9yXh7lURXQ";
    private String COMPANY_ID = "h7evZBaXvhaLVHYRTIHD";

    @Before
    public void setup() throws PlenigoException {
        suppressConstructor(HttpConfig.class);
        suppressConstructor(HashUtils.class);
        mockStatic(HttpConfig.class);
        mockStatic(HashUtils.class);
        HttpConfig httpConfig = PowerMockito.mock(HttpConfig.class);
        PowerMockito.when(HttpConfig.get()).thenReturn(httpConfig);
        restClient = Mockito.mock(RestClient.class);
        PowerMockito.when(httpConfig.getClient()).thenReturn(restClient);
    }

    @Test
    public void testSuccessfulHasUserBought() throws Exception {

        Mockito.when(restClient.get(anyString(), anyString(), anyString(), anyString(), anyMap()))
                .thenReturn(Collections.singletonMap("enabled", (Object) "true"));
        assertTrue(internalUserApiService.hasUserBought("BASE", "CUSTOMER_ID", SECRET_ID, COMPANY_ID, false, Collections.singletonList("")));
    }

    @Test
    public void testUnsuccessfulHasUserBoughtBecauseOfNoAccess() throws Exception {
        Mockito.when(restClient.get(anyString(), anyString(), anyString(), anyString(), anyMap()))
                .thenThrow(new PlenigoException(ErrorCode.CANNOT_ACCESS_PRODUCT, "", null));
        assertFalse(internalUserApiService.hasUserBought("BASE", "CUSTOMER_ID", SECRET_ID, COMPANY_ID, false, Collections.singletonList("")));
    }

    @Test(expected = PlenigoException.class)
    public void testUnsuccessfulHasUserBoughtBecauseOfInternalServerError() throws Exception {
        Mockito.when(restClient.get(anyString(), anyString(), anyString(), anyString(), anyMap()))
                .thenThrow(new PlenigoException(ErrorCode.SERVER, "", null));
        assertFalse(internalUserApiService.hasUserBought("BASE", "CUSTOMER_ID", SECRET_ID, COMPANY_ID, false, Collections.singletonList("")));
    }

    @Test
    public void testIsPaywallEnabled() throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(ApiResults.PAYWALL_STATE, Boolean.TRUE.toString());
        Mockito.when(restClient.get(anyString(), anyString(), anyString(), anyString(), anyMap()))
                .thenReturn(result);
        assertTrue(internalUserApiService.isPaywallEnabled("baseUri","secret","companyId"));
    }

    @Test
    public void testGetUserData() throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(ApiResults.PAYWALL_STATE, Boolean.TRUE.toString());
        Mockito.when(restClient.get(anyString(), anyString(), anyString(), anyString(), anyMap()))
                .thenReturn(result);
        assertNotNull(internalUserApiService.getUserData("uri", SECRET_ID, COMPANY_ID, "accessToken"));
    }

    @Test(expected = PlenigoException.class)
    public void testUnsuccessfulGetUserData() throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(ApiResults.ERROR, "CODE");
        result.put(ApiResults.DESCRIPTION, "DESC");
        Mockito.when(restClient.get(anyString(), anyString(), anyString(), anyString(), anyMap()))
                .thenReturn(result);
        assertNotNull(internalUserApiService.getUserData("uri", SECRET_ID, COMPANY_ID, "accessToken"));
    }
}
