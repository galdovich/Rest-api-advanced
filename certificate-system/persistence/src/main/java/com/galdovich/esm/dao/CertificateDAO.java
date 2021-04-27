package com.galdovich.esm.dao;

import com.galdovich.esm.entity.Certificate;
import com.galdovich.esm.util.Page;
import com.galdovich.esm.util.QueryParams;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@code CertificateDAO} describes abstract behavior
 * for working with 'certificates' table in database.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public interface CertificateDAO extends GiftDAO<Certificate> {

    /**
     * Get certificates by query parameters in database.
     *
     * @param params criteria parameters
     * @return the list of found certificates
     */
    List<Certificate> getAll(QueryParams params, Page page);

    /**
     * Get certificate by name.
     *
     * @param name name of certificate
     * @return the optional of certificate
     */
    Optional<Certificate> getByName(String name);

    /**
     * Get certificate by name.
     *
     * @param id id of certificate
     */
    void deleteCertificateHasTag(long id);
}
