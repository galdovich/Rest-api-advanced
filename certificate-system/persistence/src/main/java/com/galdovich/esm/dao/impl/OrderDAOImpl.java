package com.galdovich.esm.dao.impl;

import com.galdovich.esm.dao.OrderDAO;
import com.galdovich.esm.dao.util.SQLQuery;
import com.galdovich.esm.entity.Order;
import com.galdovich.esm.util.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private static final String FIND_BY_HIGHEST_COST_OF_ALL_ORDERS = "SELECT u FROM User u frw";

    @PersistenceContext
    private EntityManager entityManager;

    public OrderDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Order> getAll(Page page) {
        return entityManager.createQuery(SQLQuery.ORDER_GET_ALL, Order.class)
                .setFirstResult((page.getPage() - 1) * page.getSize())
                .setMaxResults(page.getSize())
                .getResultList();
    }

    @Override
    public List<Order> getAllByUser(Page page, long id) {
        return entityManager.createQuery(SQLQuery.ORDER_GET_ALL_BY_USER, Order.class)
                .setParameter(1, id)
                .setFirstResult((page.getPage() - 1) * page.getSize())
                .setMaxResults(page.getSize())
                .getResultList();
    }

    @Override
    public Optional<Order> getById(long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public Order add(Order order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Order edit(Order order) {
        throw new UnsupportedOperationException();
    }
}
