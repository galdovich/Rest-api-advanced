package com.galdovich.esm.service.impl;

import com.galdovich.esm.config.ServiceConfiguration;
import com.galdovich.esm.dao.OrderDAO;
import com.galdovich.esm.dto.OrderDTO;
import com.galdovich.esm.entity.Order;
import com.galdovich.esm.service.CertificateService;
import com.galdovich.esm.service.OrderService;
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
class OrderServiceImplTest {

    @Autowired
    private OrderService service;

    @MockBean
    private OrderDAO orderDAO;
    @MockBean
    private CertificateService certificateService;
    @MockBean
    private UserService userService;

    @Test
    void getAll() {
        when(orderDAO.getAll(any(Page.class))).thenReturn(DataProvider.ORDER_LIST);
        List<OrderDTO> foundList = service.getAll(DataProvider.PAGE_DTO);
        List<OrderDTO> expected = DataProvider.ORDER_DTO_LIST;
        assertEquals(foundList, expected);
    }

    @Test
    void getUserOrders() {
        when(orderDAO.getAllByUser(any(Page.class), any(long.class))).thenReturn(DataProvider.ORDER_LIST_LIMIT);
        List<OrderDTO> foundList = service.getUserOrders(DataProvider.PAGE_DTO, 1L);
        List<OrderDTO> expected = DataProvider.ORDER_DTO_LIST_LIMIT;
        assertEquals(foundList, expected);
    }

    @Test
    void getById() {
        when(orderDAO.getById(any(long.class))).thenReturn(Optional.of(DataProvider.ORDER));
        OrderDTO found = service.getById(1L);
        OrderDTO expected = DataProvider.ORDER_DTO;
        assertEquals(found, expected);
    }

    @Test
    void add() {
        when(certificateService.getById(any(long.class))).thenReturn(DataProvider.CERTIFICATE_DTO);
        when(userService.getById(any(long.class))).thenReturn(DataProvider.USER_DTO);
        when(orderDAO.add(any(Order.class))).thenReturn(DataProvider.ORDER);
        OrderDTO added = service.add(DataProvider.ORDER_DTO);
        OrderDTO expected = DataProvider.ORDER_DTO;
        assertEquals(added, expected);
    }
}
