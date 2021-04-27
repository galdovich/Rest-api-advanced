package com.galdovich.esm.dto;

import com.galdovich.esm.dao.util.DirectionType;
import com.galdovich.esm.dao.util.SortType;

import java.util.Arrays;

public class QueryParamsDTO {
    private String name;
    private String[] tags;
    private String description;
    private String create_date;
    private SortType sortType;
    private DirectionType direction;

    public QueryParamsDTO(String name, String[] tags, String description, String create_date,
                          String sortType, String direction) {
        this.name = name;
        this.tags = tags;
        this.description = description;
        this.create_date = create_date;
        if (sortType != null) {
            this.sortType = SortType.valueOf(sortType.toUpperCase());
        }
        if (direction != null) {
            this.direction = DirectionType.valueOf(direction.toUpperCase());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueryParamsDTO that = (QueryParamsDTO) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(tags, that.tags)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (create_date != null ? !create_date.equals(that.create_date) : that.create_date != null) return false;
        if (sortType != that.sortType) return false;
        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(tags);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (create_date != null ? create_date.hashCode() : 0);
        result = 31 * result + (sortType != null ? sortType.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}
