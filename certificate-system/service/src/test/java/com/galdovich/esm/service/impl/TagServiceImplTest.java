package com.galdovich.esm.service.impl;

import com.galdovich.esm.config.ServiceConfiguration;
import com.galdovich.esm.dao.TagDAO;
import com.galdovich.esm.dao.UserDAO;
import com.galdovich.esm.dto.TagDTO;
import com.galdovich.esm.entity.Tag;
import com.galdovich.esm.exception.AlreadyExistsException;
import com.galdovich.esm.exception.ResourcesNotFoundException;
import com.galdovich.esm.exception.UnsupportedMethodException;
import com.galdovich.esm.service.TagService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceConfiguration.class)
class TagServiceImplTest {

    @MockBean
    private TagDAO tagDAO;
    @MockBean
    private UserDAO userDAO;
    @Autowired
    private TagService service;

    @Test
    void getAll() {
        when(tagDAO.getAll(any(Page.class))).thenReturn(DataProvider.TAG_LIST);
        List<TagDTO> foundList = service.getAll(DataProvider.PAGE_DTO);
        List<TagDTO> expected = DataProvider.TAG_DTO_LIST;
        assertEquals(foundList, expected);
    }

    @Test
    void getById() {
        when(tagDAO.getById(any(long.class))).thenReturn(Optional.of(DataProvider.TAG));
        TagDTO found = service.getById(1L);
        TagDTO expected = DataProvider.TAG_DTO;
        assertEquals(expected, found);
    }

    @Test
    void add() {
        when(tagDAO.edit(any(Tag.class))).thenReturn(DataProvider.TAG);
        when(tagDAO.getByName(any(String.class))).thenReturn(Optional.empty());
        TagDTO added = service.add(DataProvider.TAG_DTO);
        TagDTO expected = DataProvider.TAG_DTO;
        assertEquals(added, expected);
    }

    @Test
    void addEdit() {
        when(tagDAO.getByName(any(String.class))).thenReturn(Optional.empty());
        when(tagDAO.edit(any(Tag.class))).thenReturn(DataProvider.TAG);
        TagDTO added = service.add(DataProvider.TAG_DTO);
        TagDTO expected = DataProvider.TAG_DTO;
        assertEquals(added, expected);
    }

    @Test
    void addException() {
        when(tagDAO.getByName(any(String.class))).thenReturn(Optional.of(DataProvider.TAG));
        when(tagDAO.add(any(Tag.class))).thenReturn(DataProvider.TAG);
        assertThrows(AlreadyExistsException.class, () -> service.add(DataProvider.TAG_DTO));
    }

    @Test
    void update() {
        assertThrows(UnsupportedMethodException.class, () -> service.update(1L, DataProvider.TAG_DTO));
    }

    @Test
    void isExistsPositive() {
        when(tagDAO.getByName(any(String.class))).thenReturn(Optional.of(DataProvider.TAG));
        assertTrue(service.isExists("Tag"));
    }

    @Test
    void isExistsNegative() {
        when(tagDAO.getByName(any(String.class))).thenReturn(Optional.empty());
        assertFalse(service.isExists("Tag"));
    }

    @Test
    void getMostPopularUserTagWithHighestOrderSum() {
        when(userDAO.getByHighestCostOfAllOrders()).thenReturn(1L);
        when(tagDAO.getMostPopularUserTag(any(long.class))).thenReturn(1L);
        when(tagDAO.getById(any(long.class))).thenReturn(Optional.of(DataProvider.TAG));
        TagDTO found = service.getMostPopularUserTagWithHighestOrderSum();
        TagDTO expected = DataProvider.TAG_DTO;
        assertEquals(found, expected);
    }

    @Test
    void getMostPopularUserTagWithHighestOrderSumException() {
        when(userDAO.getByHighestCostOfAllOrders()).thenReturn(1L);
        when(tagDAO.getMostPopularUserTag(any(long.class))).thenReturn(1L);
        when(tagDAO.getById(any(long.class))).thenReturn(Optional.empty());
        assertThrows(ResourcesNotFoundException.class, () -> service.getMostPopularUserTagWithHighestOrderSum());
    }

    @Test
    void getByName() {
        when(tagDAO.getByName(any(String.class))).thenReturn(Optional.of(DataProvider.TAG));
        assertEquals(service.getByName("Tag"), DataProvider.TAG_DTO);
    }

    @Test
    void getByNameException() {
        when(tagDAO.getByName(any(String.class))).thenReturn(Optional.empty());
        assertThrows(ResourcesNotFoundException.class, () -> service.getByName("Tag"));
    }
}