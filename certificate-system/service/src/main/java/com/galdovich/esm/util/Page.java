package com.galdovich.esm.util;

/**
 * Class {@code Page} contains page parameters
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class Page {
    private int page;
    private int size;

    /**
     * Instantiates a new Page.
     *
     * @param page the page
     * @param size the size
     */
    public Page(int page, int size) {
        this.page = page;
        this.size = size;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) {
        this.size = size;
    }
}
