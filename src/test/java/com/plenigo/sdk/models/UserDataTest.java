package com.plenigo.sdk.models;

import com.plenigo.sdk.internal.models.Address;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * Tests for {@link UserData}.
 * </p>
 */
public class UserDataTest {
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    public static final String USERNAME = "username";
    private UserData userData;
    public static final Address ADDRESS = new Address("street", "additionalAddrInfo", "postCode", "city", "country");

    @Before
    public void setup() {
        userData = new UserData(ID, EMAIL, GENDER, LAST_NAME, FIRST_NAME, ADDRESS, USERNAME);
    }

    @Test
    public void testGetId() {
        assertEquals("Id is not correct", ID, userData.getId());
    }


    @Test
    public void testGetEmail() {
        assertEquals("Email is not correct", EMAIL, userData.getEmail());
    }

    @Test
    public void testGetGender() {
        assertEquals("Gender is not correct", GENDER, userData.getGender());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Last name is not correct", LAST_NAME, userData.getLastName());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("First Name is not correct", FIRST_NAME, userData.getFirstName());
    }

    @Test
    public void testGetUsername() {
        assertEquals("Username is not correct", USERNAME, userData.getUsername());
    }

    @Test
    public void testGetAdditionalAddressInfo() {
        assertEquals("Additional address information is not correct", ADDRESS.getAdditionalAddressInfo(), userData.getAdditionalAddressInfo());
    }

    @Test
    public void testGetCity() {
        assertEquals("City is not correct", ADDRESS.getCity(), userData.getCity());
    }

    @Test
    public void testGetCountry() {
        assertEquals("Country is not correct", ADDRESS.getCountry(), userData.getCountry());
    }

    @Test
    public void testGetPostcode() {
        assertEquals("Postcode is not correct", ADDRESS.getPostCode(), userData.getPostCode());
    }

    @Test
    public void testGetStreet() {
        assertEquals("Street is not correct", ADDRESS.getStreet(), userData.getStreet());
    }

    @Test
    public void testToString() {
        Address address = new Address("street", "additionalAddrInfo", "postCode", "city", "country");
        assertNotNull(new UserData("id", "email", "gender", "lastName", "firstName", address, "username").toString());
    }
}
