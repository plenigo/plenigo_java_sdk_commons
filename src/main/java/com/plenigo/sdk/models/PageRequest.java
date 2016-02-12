package com.plenigo.sdk.models;


/**
 * <p>
 * This class represents a page request.
 * </p>
 * <p>
 * <strong>Thread safety:</strong> This class is <b>not</b> thread safe.
 * </p>
 */
public class PageRequest {
    private int pageNumber;
    private int pageSize;

    /**
     * Builds a page request with the required parameters.
     *
     * @param pageNumber page number
     * @param pageSize   page size
     */
    public PageRequest(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    /**
     * Returns the page number.
     *
     * @return page number
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets the page number.
     *
     * @param pageNumber page number
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Returns the page size.
     *
     * @return page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size.
     *
     * @param pageSize page size
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
