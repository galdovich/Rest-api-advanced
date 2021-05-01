package com.galdovich.esm.dto;

import org.springframework.hateoas.RepresentationModel;

public class TagDTO extends RepresentationModel<TagDTO> {
    private Long id;
    private String name;

    public TagDTO(String name) {
        this.name = name;
    }

    public TagDTO() {
    }

    public TagDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TagDTO tagDTO = (TagDTO) o;

        if (id != null ? !id.equals(tagDTO.id) : tagDTO.id != null) return false;
        return name != null ? name.equals(tagDTO.name) : tagDTO.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
