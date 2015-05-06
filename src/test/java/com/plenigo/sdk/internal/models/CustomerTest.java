package com.plenigo.sdk.internal.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link Customer}.
 * </p>
 */
public class CustomerTest {
    public static final String SAMPLE = "sample";
    public static final Long COOKIE_TIMESTAMP = 1L;
    private Customer customer;
    @Before
    public void setup(){
        customer = new Customer(SAMPLE, COOKIE_TIMESTAMP);
    }

    @Test
    public void testGetTimestamp(){
        assertEquals("Timestamp is not correct", COOKIE_TIMESTAMP, customer.getTimestamp());
    }

    @Test
    public void testGetCustomerId(){
        assertEquals("Customer id is not correct", SAMPLE, customer.getCustomerId());
    }

    @Test
    public void testToString() {
        assertNotNull(new Customer("sample", 1L).toString());
    }
}
