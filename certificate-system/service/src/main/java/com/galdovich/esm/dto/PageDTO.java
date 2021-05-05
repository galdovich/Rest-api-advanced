package com.galdovich.esm.dto;

import com.galdovich.esm.exception.MessageKey;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class PageDTO {

    @Min(value = 1, message = MessageKey.INCORRECT_PAGE_NUMBER)
    private int page;
    @Min(value = 1, message = MessageKey.INCORRECT_PAGE_SIZE)
    @Max(value = 50, message = MessageKey.INCORRECT_PAGE_SIZE)
    private int size;

    public PageDTO(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageDTO pageDTO = (PageDTO) o;

        if (page != pageDTO.page) return false;
        return size == pageDTO.size;
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + size;
        return result;
    }
}
