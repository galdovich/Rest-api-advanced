package com.galdovich.esm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "tags")
@Entity
public class Tag implements GiftEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Certificate> certificateSet;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Tag(long id) {
        this.id = id;
    }

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag(Long id, String name, List<Certificate> certificateSet) {
        this.id = id;
        this.name = name;
        this.certificateSet = certificateSet;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Certificate> getCertificateSet() {
        return certificateSet;
    }

    public void setCertificateSet(List<Certificate> certificateSet) {
        this.certificateSet = certificateSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (id != null ? !id.equals(tag.id) : tag.id != null) return false;
        if (name != null ? !name.equals(tag.name) : tag.name != null) return false;
        return certificateSet != null ? certificateSet.equals(tag.certificateSet) : tag.certificateSet == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (certificateSet != null ? certificateSet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
