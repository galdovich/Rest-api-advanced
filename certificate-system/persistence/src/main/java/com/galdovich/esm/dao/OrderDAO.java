package com.galdovich.esm.dao;

import com.galdovich.esm.entity.Order;
import com.galdovich.esm.util.Page;

import java.util.List;

/**
 * Interface {@code OrderDAO} describes abstract behavior
 * for working with 'orders' table in database.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public interface OrderDAO extends GiftDAO<Order> {

    /**
     * Get Orders that belong to user.
     *
     * @param page   the page
     * @param userId user id
     * @return the list of orders
     */
    List<Order> getAllByUser(Page page, long userId);
}
