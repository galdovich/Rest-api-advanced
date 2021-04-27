package com.galdovich.esm.dao.impl;

import com.galdovich.esm.dao.CertificateDAO;
import com.galdovich.esm.dao.util.QueryCreator;
import com.galdovich.esm.dao.util.SQLQuery;
import com.galdovich.esm.entity.Certificate;
import com.galdovich.esm.util.Page;
import com.galdovich.esm.util.QueryParams;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CertificateDAOImpl implements CertificateDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Certificate> getAll(Page page) {
        return entityManager.createQuery(SQLQuery.CERTIFICATE_GET_ALL, Certificate.class)
                .setFirstResult((page.getPage() - 1) * page.getSize())
                .setMaxResults(page.getSize())
                .getResultList();
    }

    @Override
    public Optional<Certificate> getById(long id) {
        return Optional.ofNullable(entityManager.find(Certificate.class, id));
    }

    @Override
    public Certificate add(Certificate certificate) {
        entityManager.persist(certificate);
        return certificate;
    }

    @Override
    public Certificate edit(Certificate certificate) {
        return entityManager.merge(certificate);
    }

    @Override
    public boolean delete(long id) {
        Certificate certificate = entityManager.find(Certificate.class, id);
        if (certificate != null) {
            entityManager.remove(certificate);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Certificate> getAll(QueryParams params, Page page) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Certificate> criteria = new QueryCreator().createQuery(cBuilder, params);
        return entityManager.createQuery(criteria)
                .setFirstResult((page.getPage() - 1) * page.getSize())
                .setMaxResults(page.getSize())
                .getResultList();
    }

    @Override
    public Optional<Certificate> getByName(String name) {
        return entityManager.createQuery(SQLQuery.CERTIFICATE_GET_BY_NAME, Certificate.class)
                .setParameter(1, name)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public void deleteCertificateHasTag(long id) {
        entityManager.createNativeQuery(SQLQuery.REMOVE_CERTIFICATE_HAS_TAG)
                .setParameter(1, id)
                .executeUpdate();
    }
}

