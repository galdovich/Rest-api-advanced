package com.galdovich.esm.util;

import com.galdovich.esm.dao.util.DirectionType;
import com.galdovich.esm.dao.util.SortType;

/**
 * Class {@code QueryParams} represents input parameters.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class QueryParams {
    private String name;
    private String[] tags;
    private String description;
    private String create_date;
    private SortType sortType;
    private DirectionType direction;

    /**
     * Instantiates a new Query params.
     *
     * @param name        the name
     * @param tags        the tags
     * @param description the description
     * @param create_date the create date
     * @param sortType    the sort type
     * @param direction   the direction
     */
    public QueryParams(String name, String[] tags, String description, String create_date,
                       SortType sortType, DirectionType direction) {
        this.name = name;
        this.tags = tags;
        this.description = description;
        this.create_date = create_date;
        this.sortType = sortType;
        this.direction = direction;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get tags string [ ].
     *
     * @return the string [ ]
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public String getCreate_date() {
        return create_date;
    }

    /**
     * Sets create date.
     *
     * @param create_date the create date
     */
    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    /**
     * Gets sort type.
     *
     * @return the sort type
     */
    public SortType getSortType() {
        return sortType;
    }

    /**
     * Sets sort type.
     *
     * @param sortType the sort type
     */
    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public DirectionType getDirection() {
        return direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }
}
