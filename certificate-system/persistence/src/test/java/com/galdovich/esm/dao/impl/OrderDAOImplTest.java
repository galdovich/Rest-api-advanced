package com.galdovich.esm.dao.impl;

import com.galdovich.esm.config.PersistenceConfig;
import com.galdovich.esm.dao.OrderDAO;
import com.galdovich.esm.entity.Order;
import com.galdovich.esm.util.Page;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersistenceConfig.class)
@Transactional
class OrderDAOImplTest {

    @Autowired
    private OrderDAO orderDAO;

    static Stream<Arguments> argsGetAll() {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(3, 0, 0),
                Arguments.of(3, 4, 4),
                Arguments.of(10, 20, 20)
        );
    }

    @ParameterizedTest
    @MethodSource("argsGetAll")
    void getAll(int page, int size, int expected) {
        List<Order> foundList = orderDAO.getAll(new Page(page, size));
        assertEquals(expected, foundList.size());
    }

    static Stream<Arguments> argsGetByUserId() {
        return Stream.of(
                Arguments.of(new Page(1, 5), 2L, 1),
                Arguments.of(new Page(1, 5), 1L, 2),
                Arguments.of(new Page(1, 5), 4L, 1),
                Arguments.of(new Page(1, 5), 20L, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("argsGetByUserId")
    void getAllByUser(Page page, long id, int expected) {
        List<Order> foundList = orderDAO.getAllByUser(page, id);
        assertEquals(expected, foundList.size());
    }

    static Stream<Arguments> argsGetById() {
        return Stream.of(
                Arguments.of(2L, true),
                Arguments.of(1L, true),
                Arguments.of(4L, true),
                Arguments.of(2042L, false)
        );
    }

    @ParameterizedTest
    @MethodSource("argsGetById")
    void getById(long id, boolean expected) {
        Optional<Order> found = orderDAO.getById(id);
        assertEquals(expected, found.isPresent());
    }

    @Test
    void delete() {
        assertThrows(UnsupportedOperationException.class, () -> orderDAO.delete(1l));
    }

    @Test
    void edit() {
        assertThrows(UnsupportedOperationException.class, () -> orderDAO.edit(new Order()));
    }
}