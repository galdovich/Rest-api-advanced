package com.galdovich.esm.service;

import com.galdovich.esm.dto.CertificateDTO;
import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.dto.QueryParamsDTO;

import java.util.List;

/**
 * Interface {@code CertificateService} describes abstract behavior for working
 * with {@link com.galdovich.esm.entity.Certificate}
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public interface CertificateService extends GiftService<CertificateDTO> {

    /**
     * Find certificates according to parameters.
     *
     * @param params criteria parameters
     * @return the list of DTO certificates
     */
    List<CertificateDTO> getAll(QueryParamsDTO params, PageDTO pageDTO);
}
