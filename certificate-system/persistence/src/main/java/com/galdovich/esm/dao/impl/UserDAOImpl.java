package com.galdovich.esm.dao.impl;

import com.galdovich.esm.dao.UserDAO;
import com.galdovich.esm.dao.util.SQLQuery;
import com.galdovich.esm.entity.User;
import com.galdovich.esm.util.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<User> getAll(Page page) {
        return entityManager.createQuery(SQLQuery.USER_GET_ALL, User.class)
                .setFirstResult((page.getPage() - 1) * page.getSize())
                .setMaxResults(page.getSize())
                .getResultList();
    }

    @Override
    public Optional<User> getById(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public boolean delete(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Long getByHighestCostOfAllOrders() {
        BigInteger bigInteger = (BigInteger) entityManager
                .createNativeQuery(SQLQuery.USER_GET_ID_HIGHEST_ORDERS)
                .getSingleResult();
        return bigInteger.longValue();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return entityManager.createQuery("select u from User u where u.email=?1", User.class)
                .setParameter(1, email)
                .getResultList()
                .stream()
                .findAny();
    }

    @Override
    public User add(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User edit(User user) {
        throw new UnsupportedOperationException();
    }
}
