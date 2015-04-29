package com.plenigo.sdk.models;

import com.plenigo.sdk.internal.models.Address;

import java.io.Serializable;

/**
 * <p>
 * This object represents the user information.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is thread safe and can be injected.
 * </p>
 */
public class UserData implements Serializable {
    private String id;
    private String email;
    private String gender;
    private String lastName;
    private String firstName;
    private String username;
    private Address address;

    /**
     * The constructor for this read-only object.
     *  @param id          The user id
     * @param email       The e-mail
     * @param gender      The prefix gender
     * @param lastName    The user's last name
     * @param firstName   The user's first name
     * @param addressInfo The address information
     * @param username The user's name for login
     */
    public UserData(String id, String email, String gender, String lastName, String firstName, Address addressInfo, String username) {
        this.id = id;
        this.email = email;
        this.gender = gender;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = addressInfo;
        this.username = username;
    }

    /**
     * Retrieves the user id.
     *
     * @return The user id
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the user's email address.
     *
     * @return The user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the user's prefix gender.
     *
     * @return The user's prefix gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Retrieves the user's last name.
     *
     * @return The user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves the user's first name.
     *
     * @return The user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves The user's street address.
     *
     * @return The user's street address.
     */
    public String getStreet() {
        return address.getStreet();
    }

    /**
     * The user address additional information.
     *
     * @return Additional address information
     */
    public String getAdditionalAddressInfo() {
        return address.getAdditionalAddressInfo();
    }

    /**
     * The user address post code.
     *
     * @return The post code
     */
    public String getPostCode() {
        return address.getPostCode();
    }

    /**
     * The user address city.
     *
     * @return The user address city
     */
    public String getCity() {
        return address.getCity();
    }

    /**
     * The user's country.
     *
     * @return The country
     */
    public String getCountry() {
        return address.getCountry();
    }

    /**
     * The user's username for login.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "UserData{" + "id='" + id + '\'' + ", email='" + email + '\'' + ", gender='" + gender + '\'' + ", lastName='" + lastName + '\''
                + ", firstName='" + firstName + '\'' + ", address='" + address +  "', username='" + username + "'}";
    }
}
