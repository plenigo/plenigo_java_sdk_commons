package com.plenigo.sdk.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * <p>
 * Tests for {@link PageRequest}.
 * </p>
 */
public class PageRequestTest {
    public static final int PAGE_NUMBER = 0;
    public static final int PAGE_SIZE = 10;
    private PageRequest pageRequest;

    @Before
    public void setup() {
        pageRequest = new PageRequest(PAGE_NUMBER, PAGE_SIZE);
    }

    @Test
    public void testGetPageNumber() {
        assertEquals("Page Number was not correct", PAGE_NUMBER, pageRequest.getPageNumber());
    }

    @Test
    public void testGetPageSize() {
        assertEquals("Page size was not correct", PAGE_SIZE, pageRequest.getPageSize());
    }
}
