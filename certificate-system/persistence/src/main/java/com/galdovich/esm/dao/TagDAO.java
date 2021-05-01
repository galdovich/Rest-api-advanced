package com.galdovich.esm.dao;

import com.galdovich.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@code TagDAO} describes abstract behavior
 * for working with 'tags' table in database.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public interface TagDAO extends GiftDAO<Tag> {

    /**
     * Get tags that belong to the certificate.
     *
     * @param certificateId the certificate id
     * @return the list of tags
     */
    List<Tag> getAll(long certificateId);

    /**
     * Gets most popular user tag.
     *
     * @param userId the user id
     * @return the most popular user tag
     */
    Long getMostPopularUserTag(long userId);

    /**
     * Delete certificate from table 'certificate_has_tag'.
     *
     * @param tagId the tag id
     */
    void deleteCertificateHasTag(long tagId);

    /**
     * Get tags that belong to the certificate.
     *
     * @param name the of tag
     * @return the optional
     */
    Optional<Tag> getByName(String name);

    boolean isExistsCertificateHasTag(long certificateId, long tagId);
}
