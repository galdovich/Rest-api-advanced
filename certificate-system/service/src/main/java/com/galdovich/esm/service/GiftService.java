package com.galdovich.esm.service;

import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.exception.MessageKey;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Interface {@code GistService} describes CRUD operations for working with <T>
 *
 * @param <T> the type parameter
 * @author Alexander Galdovich
 * @version 1.0
 */
@Validated
public interface GiftService<T> {

    /**
     * Find all T.
     *
     * @return the list of T
     */
    List<T> getAll(@Valid PageDTO pageDTO);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    T getById(@Min(value = 1, message = MessageKey.INCORRECT_ID) long id);

    /**
     * Add T.
     *
     * @param t the entity
     * @return the T
     */
    T add(T t);

    /**
     * Update optional.
     *
     * @param t the entity
     * @return the optional
     */
    T update(@Min(value = 1, message = MessageKey.INCORRECT_ID) long id, T t);

    /**
     * Delete boolean.
     *
     * @param id the entity id
     */
    boolean delete(@Min(value = 1, message = MessageKey.INCORRECT_ID) long id);
}
