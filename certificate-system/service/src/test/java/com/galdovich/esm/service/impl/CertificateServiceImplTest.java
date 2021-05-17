package com.galdovich.esm.service.impl;

import com.galdovich.esm.config.ServiceConfiguration;
import com.galdovich.esm.dao.CertificateDAO;
import com.galdovich.esm.dto.CertificateDTO;
import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.entity.Certificate;
import com.galdovich.esm.service.CertificateService;
import com.galdovich.esm.service.TagService;
import com.galdovich.esm.util.DataProvider;
import com.galdovich.esm.util.Page;
import com.galdovich.esm.util.QueryParams;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceConfiguration.class)
class CertificateServiceImplTest {

    @Autowired
    private CertificateService certificateService;
    @MockBean
    private TagService tagService;
    @MockBean
    private CertificateDAO certificateDAO;

    @Test
    void getAll() {
        when(certificateDAO.getAll(any(Page.class))).thenReturn(DataProvider.CERTIFICATE_LIST);
        int actual = certificateService.getAll(new PageDTO(1, 2)).size();
        int expected = DataProvider.CERTIFICATE_DTO_LIST.size();
        assertEquals(actual, expected);
    }

    @Test
    void testGetAll() {
        when(certificateDAO.getAll(any(QueryParams.class), any(Page.class))).thenReturn(DataProvider.CERTIFICATE_LIST);
        int actual = certificateService.getAll(DataProvider.QUERY_PARAMS_DTO, new PageDTO(1, 2)).size();
        int expected = DataProvider.CERTIFICATE_DTO_LIST.size();
        assertEquals(actual, expected);
    }

    @Test
    void getById() {
        when(certificateDAO.getById(any(Long.class))).thenReturn(Optional.of(DataProvider.CERTIFICATE));
        CertificateDTO found = certificateService.getById(1L);
        CertificateDTO expected = DataProvider.CERTIFICATE_DTO;
        assertEquals(found, expected);
    }

   /* @Test
    void update() {
        when(tagService.isExists(any(String.class))).thenReturn(true);
        when(certificateDAO.getById(any(Long.class))).thenReturn(Optional.of(DataProvider.UPDATING_CERTIFICATE));
        when(certificateDAO.edit(any(Certificate.class))).thenReturn(DataProvider.CERTIFICATE);
        CertificateDTO updated = certificateService.update(1L, DataProvider.CERTIFICATE_DTO);
        CertificateDTO expected = DataProvider.CERTIFICATE_DTO;
        assertEquals(updated, expected);
    }


    @Test
    void delete() {
    }*/
}
