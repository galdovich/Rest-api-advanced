package com.galdovich.esm.service.impl;

import com.galdovich.esm.dao.CertificateDAO;
import com.galdovich.esm.dto.CertificateDTO;
import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.dto.QueryParamsDTO;
import com.galdovich.esm.dto.TagDTO;
import com.galdovich.esm.entity.Certificate;
import com.galdovich.esm.exception.AlreadyExistsException;
import com.galdovich.esm.exception.MessageKey;
import com.galdovich.esm.exception.ResourcesNotFoundException;
import com.galdovich.esm.service.CertificateService;
import com.galdovich.esm.service.TagService;
import com.galdovich.esm.util.GiftConverter;
import com.galdovich.esm.util.Page;
import com.galdovich.esm.util.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateDAO certificateDAO;
    private final TagService tagService;

    @Autowired
    public CertificateServiceImpl(CertificateDAO certificateDAO,
                                  TagService tagService) {
        this.certificateDAO = certificateDAO;
        this.tagService = tagService;
    }

    @Override
    public List<CertificateDTO> getAll(PageDTO pageDTO) {
        Page page = GiftConverter.toPage(pageDTO);
        List<Certificate> list = certificateDAO.getAll(page);
        return GiftConverter.toCertificateDTOList(list);
    }

    @Override
    public List<CertificateDTO> getAll(QueryParamsDTO params, PageDTO pageDTO) {
        QueryParams queryParams = GiftConverter.toQueryParams(params);
        Page page = GiftConverter.toPage(pageDTO);
        return GiftConverter.toCertificateDTOList(certificateDAO.getAll(queryParams, page));
    }

    @Override
    public CertificateDTO getById(long id) throws ResourcesNotFoundException {
        Optional<Certificate> found = certificateDAO.getById(id);
        return found
                .map(GiftConverter::toCertificateDTO)
                .orElseThrow(() -> new ResourcesNotFoundException
                        (MessageKey.CERTIFICATE_NOT_FOUND_BY_ID, String.valueOf(id)));
    }

    @Override
    @Transactional()
    public CertificateDTO add(CertificateDTO certificateDTO) throws AlreadyExistsException {
        if (certificateDAO.getByName(certificateDTO.getName()).isPresent()) {
            throw new AlreadyExistsException(MessageKey.CERTIFICATE_ALREADY_EXISTS, certificateDTO.getName());
        }
        addTags(certificateDTO);
        certificateDTO.setCreateDate(LocalDateTime.now());
        certificateDTO.setLastUpdateDate(LocalDateTime.now());
        Certificate entity = GiftConverter.toCertificate(certificateDTO);
        Certificate added = certificateDAO.add(entity);
        return GiftConverter.toCertificateDTO(added);
    }

    private void addTags(CertificateDTO certificate) {
        Set<TagDTO> tagDTOSet = new HashSet<>();
        if (certificate.getTags() != null) {
            Set<TagDTO> tags = certificate.getTags()
                    .stream().filter(tagDTO -> tagDTO.getName() != null)
                    .map(tag -> {
                        TagDTO tagDTO = new TagDTO();
                        tagDTO = tagService.isExists(tag.getName())
                                ? tagDTO
                                : tagService.add(tag);
                        return tagDTO;
                    })
                    .collect(Collectors.toSet());
            tagDTOSet.addAll(tags);
        }
        certificate.setTags(tagDTOSet);
    }

    @Transactional
    @Override
    public CertificateDTO update(long id, CertificateDTO certificateDTO) throws ResourcesNotFoundException {
        addTags(certificateDTO);
        Optional<Certificate> certificate = certificateDAO.getById(id);
        if (certificate.isPresent()) {
            Certificate found = certificate.get();
            updateNotEmptyField(certificateDTO, found);
            found.setLastUpdateDate(LocalDateTime.now());
            Certificate updated = certificateDAO.edit(found);
            certificate = Optional.of(updated);
        }
        return certificate.map(GiftConverter::toCertificateDTO).orElseThrow(
                () -> new ResourcesNotFoundException(MessageKey.CERTIFICATE_UPDATE_EXCEPTION, String.valueOf(id)));
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        getById(id);
        certificateDAO.deleteCertificateHasTag(id);
        return certificateDAO.delete(id);
    }

    private void updateNotEmptyField(CertificateDTO source, Certificate found) {
        if (source.getName() != null) {
            found.setName(source.getName());
        }
        if (source.getDescription() != null) {
            found.setDescription(source.getDescription());
        }
        if (source.getPrice() != null) {
            found.setPrice(source.getPrice());
        }
        if (source.getDuration() != null) {
            found.setDuration(source.getDuration());
        }
        if (source.getTags() != null) {
            source.getTags().stream().map(GiftConverter::toTag).forEach(found::addTag);
        }
    }
}
