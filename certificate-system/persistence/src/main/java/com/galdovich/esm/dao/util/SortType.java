package com.galdovich.esm.dao.util;

/**
 * Enum {@code SortType} contains available types of sorting.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public enum SortType {

    /**
     * Id sort type.
     */
    ID("id"),

    /**
     * Name sort type.
     */
    NAME("name"),

    /**
     * Price sort type.
     */
    PRICE("price"),

    /**
     * Duration sort type.
     */
    DURATION("duration"),

    /**
     * Created date sort type.
     */
    CREATED_DATE("created_date");

    private String name;

    SortType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
