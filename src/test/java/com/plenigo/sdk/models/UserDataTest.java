package com.plenigo.sdk.models;

import com.plenigo.sdk.internal.models.Address;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link UserData}.
 * </p>
 */
public class UserDataTest {
    @Test
    public void testToString() {
        Address address = new Address("street", "additionalAddrInfo", "postCode", "city", "country");
        assertNotNull(new UserData("id", "email", "gender", "lastName", "firstName", address, "username").toString());
    }
}
