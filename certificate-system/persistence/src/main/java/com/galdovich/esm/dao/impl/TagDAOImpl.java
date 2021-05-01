package com.galdovich.esm.dao.impl;

import com.galdovich.esm.dao.TagDAO;
import com.galdovich.esm.dao.util.SQLQuery;
import com.galdovich.esm.entity.Tag;
import com.galdovich.esm.util.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class TagDAOImpl implements TagDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tag> getAll(Page page) {
        return entityManager.createQuery(SQLQuery.TAG_GET_ALL, Tag.class)
                .setFirstResult((page.getPage() - 1) * page.getSize())
                .setMaxResults(page.getSize())
                .getResultList();
    }

    public List<Tag> getAll(long certificateID) {
        return entityManager.createQuery(SQLQuery.TAG_GET_ALL, Tag.class).getResultList();
    }

    @Override
    public Optional<Tag> getById(long id) {
        return Optional.ofNullable(entityManager.find(Tag.class, id));
    }

    public Optional<Tag> getByName(String name) {
        return entityManager.createQuery(SQLQuery.TAG_GET_BY_NAME, Tag.class)
                .setParameter(1, name)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Tag edit(Tag tag) {
        return entityManager.merge(tag);
    }

    @Override
    public Tag add(Tag tag) {
        entityManager.persist(tag);
        return tag;

    }

    @Override
    public Long getMostPopularUserTag(long userId) {
        BigInteger foundTagId = (BigInteger) entityManager.createNativeQuery(SQLQuery.TAG_MOST_POPUlAR)
                .setParameter(1, userId)
                .getSingleResult();
        return foundTagId.longValue();
    }

    @Override
    public boolean delete(long id) {
        Tag tag = entityManager.find(Tag.class, id);
        if (tag != null) {
            entityManager.remove(tag);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteCertificateHasTag(long tagId) {
        entityManager.createNativeQuery(SQLQuery.REMOVE_TAG_HAS_CERTIFICATE)
                .setParameter(1, tagId)
                .executeUpdate();
    }

    @Override
    public boolean isExistsCertificateHasTag(long certificateId, long tagId) {
        List<Object> id = entityManager.createNativeQuery("select tag_id from certificate_has_tag where certificate_id = ?")
                .setParameter(1, certificateId)
                .getResultList();
        System.out.println(id);
        return id.contains(tagId);
    }
}
