package com.galdovich.esm.service;

import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.exception.AlreadyExistsException;
import com.galdovich.esm.exception.ResourcesNotFoundException;

import java.util.List;

/**
 * Interface {@code GistService} describes CRUD operations for working with <T>
 *
 * @param <T> the type parameter
 * @author Alexander Galdovich
 * @version 1.0
 */
public interface GiftService<T> {

    /**
     * Find all T.
     *
     * @return the list of T
     */
    List<T> getAll(PageDTO pageDTO);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    T getById(long id) throws ResourcesNotFoundException;

    /**
     * Add T.
     *
     * @param t the entity
     * @return the T
     */
    T add(T t) throws AlreadyExistsException;

    /**
     * Update optional.
     *
     * @param t the entity
     * @return the optional
     */
    T update(long id, T t);

    /**
     * Delete boolean.
     *
     * @param id the entity id
     */
    boolean delete(long id);
}
