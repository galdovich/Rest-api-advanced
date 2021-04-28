package com.galdovich.esm.service;

import com.galdovich.esm.dto.TagDTO;
import com.galdovich.esm.exception.ResourcesNotFoundException;

/**
 * Interface {@code TagService} describes abstract behavior for working
 * with {@link com.galdovich.esm.entity.Tag}
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public interface TagService extends GiftService<TagDTO> {

    /**
     * Is exists boolean.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isExists(String name);

    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     * @throws ResourcesNotFoundException the resources not found exception
     */
    TagDTO getByName(String name);

    /**
     * Gets most popular user tag with highest order sum.
     *
     * @return the most popular user tag with highest order sum
     * @throws ResourcesNotFoundException the resources not found exception
     */
    TagDTO getMostPopularUserTagWithHighestOrderSum();
}
