package com.galdovich.esm.dao;

import com.galdovich.esm.entity.GiftEntity;
import com.galdovich.esm.util.Page;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@code GiftDAO} describes CRUD operations for working with database tables.
 *
 * @param <T> the type parameter
 * @author Alexander Galdovich
 * @version 1.0
 */
public interface GiftDAO<T extends GiftEntity> {

    /**
     * Get all entities in database.
     *
     * @return the list of found entities
     */
    List<T> getAll(Page page);

    /**
     * Get entity in database by id.
     *
     * @param id the id of entity to find
     * @return the optional of found entity
     */
    Optional<T> getById(long id);

    /**
     * Add entity to database.
     *
     * @param t the entity to add
     * @return the added entity
     */
    T add(T t);

    /**
     * Remove entity from database.
     *
     * @param id the entity id
     */
    boolean delete(long id);

    /**
     * Update entity in database.
     *
     * @param t the entity to update
     * @return the updated entity
     */
    T edit(T t);
}
