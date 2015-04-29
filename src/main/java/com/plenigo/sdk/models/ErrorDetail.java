package com.plenigo.sdk.models;

import java.io.Serializable;

/**
 * <p>
 * This object represents an invalid parameter that was sent to the plenigo API.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is thread safe and can be injected.
 * </p>
 */
public class ErrorDetail implements Serializable {
    /**
     * This is the parameter name.
     */
    private String name;

    /**
     * This is the error description that details what went wrong.
     */
    private String description;

    /**
     * This constructor creates an error instance with a given error name and description.
     *
     * @param errorName The error name
     * @param errorDesc The error description
     */
    public ErrorDetail(String errorName, String errorDesc) {
        this.name = errorName;
        this.description = errorDesc;
    }

    /**
     * This method returns the name of the parameter.
     *
     * @return The parameter name
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the detailed error description.
     *
     * @return The error description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ErrorDetail{" + "name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }
}
