package com.galdovich.esm.controller;

import com.galdovich.esm.dto.CertificateDTO;
import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.dto.QueryParamsDTO;
import com.galdovich.esm.exception.MessageKey;
import com.galdovich.esm.exception.WrongParameterFormatException;
import com.galdovich.esm.service.CertificateService;
import com.galdovich.esm.util.HateoasData;
import com.galdovich.esm.validator.GiftValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class {@code CertificateController} is an endpoint of the API
 * which allows to perform CRUD operations on certificates.
 * Annotated by {@link RestController} with no parameters to provide an answer in application/json.
 * Annotated by {@link RequestMapping} with parameter value = "/gift".
 * So that {@code CertificateController} is accessed by sending request to /gift.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
@RestController
@RequestMapping("/gifts")
public class CertificateController {

    private final CertificateService certificateService;

    /**
     * Instantiates a new Certificate controller.
     *
     * @param certificateService the certificate service
     */
    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * Add new certificate.
     * Annotated by {@link PostMapping} with no parameters.
     * Therefore, processes POST requests at /gift.
     *
     * @param certificate the new certificate that will be added
     * @return the added certificate
     */
    @PostMapping
    public CertificateDTO add(@RequestBody CertificateDTO certificate) {
        if (!new GiftValidator().isCertificateValid(certificate)) {
            throw new WrongParameterFormatException(MessageKey.ORDER_NOT_FOUND);
        }
        CertificateDTO found = certificateService.add(certificate);
        add(found);
        return found;
    }

    /**
     * Get certificate by id.
     * Annotated by {@link GetMapping} with parameter value = "/{id}".
     * Therefore, processes GET requests at /gift/{id}.
     *
     * @param id the certificate id that will be found. Inferred from the request URI
     * @return the found certificate
     */
    @GetMapping("/{id}")
    public CertificateDTO getById(@PathVariable("id") long id) {
        CertificateDTO found = certificateService.getById(id);
        addLinks(found);
        return found;
    }

    /**
     * Update certificate.
     * Annotated by {@link PutMapping} with parameter value = "/{id}".
     * Therefore, processes PUT requests at /gift/{id}.
     *
     * @param id          the certificate id which will be updated. Inferred from the request URI
     * @param certificate the certificate with updated fields
     * @return the updated certificate
     */
    @PutMapping("/{id}")
    public CertificateDTO update(@PathVariable("id") long id, @RequestBody CertificateDTO certificate) {
        if (!new GiftValidator().isCertificateValid(certificate)) {
            throw new WrongParameterFormatException(MessageKey.WRONG_PARAM_FORMAT);
        }
        CertificateDTO found = certificateService.update(id, certificate);
        addLinks(found);
        return found;
    }

    /**
     * Delete certificate with the specified id.
     * Annotated by {@link DeleteMapping} with parameter value = "/{id}".
     * Therefore, processes DELETE requests at /gift/{id}.
     *
     * @param id the certificate id which will be deleted. Inferred from the request URI
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        certificateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Find all certificates, satisfying optional find parameters and sort parameters.
     * Annotated by {@link GetMapping}.
     * Therefore, processes GET requests at /gift.
     *
     * @param name        the name
     * @param tagNames    the tag names
     * @param description the description
     * @param createDate  the date of creation
     * @param sortType    the sort type (price, duration, create-date, last-update-date)
     * @param direction   the direction (desc, asc)
     * @param page        the current page
     * @param size        amount of certificates on the page
     * @return the list of found certificates
     */
    @GetMapping
    public List<CertificateDTO> getCertificateByParams(@RequestParam(value = "name", required = false) String name,
                                                       @RequestParam(value = "tag_name", required = false) String[] tagNames,
                                                       @RequestParam(value = "description", required = false) String description,
                                                       @RequestParam(value = "create_date", required = false) String createDate,
                                                       @RequestParam(value = "sort", required = false) String sortType,
                                                       @RequestParam(value = "direction", required = false) String direction,
                                                       @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                       @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        if (!new GiftValidator().isParamsValid(name, tagNames, description, createDate, sortType, direction)) {
            throw new WrongParameterFormatException(MessageKey.WRONG_PARAM_FORMAT);
        }
        QueryParamsDTO queryParamsDTO = new QueryParamsDTO(name, tagNames, description, createDate,
                sortType, direction);
        PageDTO pageDTO = new PageDTO(page, size);
        List<CertificateDTO> foundList = certificateService.getAll(queryParamsDTO, pageDTO);
        foundList.forEach(this::addLinks);
        return foundList;
    }

    private void addLinks(CertificateDTO certificateDTO) {
        certificateDTO.add(linkTo(methodOn(CertificateController.class).getById(certificateDTO.getId()))
                .withSelfRel());
        certificateDTO.add(linkTo(methodOn(CertificateController.class).update(certificateDTO.getId(),
                certificateDTO)).withRel(HateoasData.PUT));
        certificateDTO.add(linkTo(methodOn(CertificateController.class).delete(certificateDTO.getId()))
                .withRel(HateoasData.DELETE));
    }
}
