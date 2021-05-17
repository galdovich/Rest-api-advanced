package com.galdovich.esm.service.impl;

import com.galdovich.esm.config.ServiceConfiguration;
import com.galdovich.esm.dao.UserDAO;
import com.galdovich.esm.dto.UserDTO;
import com.galdovich.esm.service.UserService;
import com.galdovich.esm.util.DataProvider;
import com.galdovich.esm.util.Page;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceConfiguration.class)
class UserServiceImplTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserDAO userDAO;

    @Test
    void getAll() {
        when(userDAO.getAll(any(Page.class))).thenReturn(DataProvider.USER_LIST);
        List<UserDTO> found = service.getAll(DataProvider.PAGE_DTO);
        List<UserDTO> expected = DataProvider.USER_DTO_LIST;
        assertEquals(expected, found);
    }

    @Test
    void getById() {
        when(userDAO.getById(1L)).thenReturn(Optional.of(DataProvider.USER));
        UserDTO found = service.getById(1L);
        UserDTO expected = DataProvider.USER_DTO;
        assertEquals(expected, found);
    }
}
