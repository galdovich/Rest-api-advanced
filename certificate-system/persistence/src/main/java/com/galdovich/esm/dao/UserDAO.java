package com.galdovich.esm.dao;

import com.galdovich.esm.entity.User;

/**
 * Interface {@code UserDAO} describes abstract behavior
 * for working with 'users' table in database.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public interface UserDAO extends GiftDAO<User> {

    /**
     * Gets by highest cost of all orders.
     *
     * @return the user with highest cost of all orders
     */
    Long getByHighestCostOfAllOrders();
}
