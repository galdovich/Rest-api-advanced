package com.galdovich.esm.dao.impl;

import com.galdovich.esm.config.PersistenceConfig;
import com.galdovich.esm.dao.UserDAO;
import com.galdovich.esm.entity.User;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersistenceConfig.class)
class UserDAOImplTest {

    @Autowired
    private UserDAO userDAO;

    static Stream<Arguments> argsGetById() {
        return Stream.of(
                Arguments.of(1L, true),
                Arguments.of(2L, true),
                Arguments.of(-1L, false),
                Arguments.of(1300L, false)
        );
    }

    static Stream<Arguments> argsGetAll() {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(3, 0, 0),
                Arguments.of(3, 4, 4),
                Arguments.of(10, 20, 20)
        );
    }

    static Stream<Arguments> argsAdd() {
        return Stream.of(
                Arguments.of(new User(1400L, "Viskas")),
                Arguments.of(new User(1401L, "Max")),
                Arguments.of(new User(1402L, "Grisha"))
        );
    }

    @ParameterizedTest
    @MethodSource("argsGetAll")
    void getAll(int page, int size, int expectedSize) {
        List<User> actual = userDAO.getAll(new Page(page, size));
        assertEquals(expectedSize, actual.size());
    }

    @ParameterizedTest
    @MethodSource("argsGetById")
    void getById(long id, boolean expected) {
        Optional<User> user = userDAO.getById(id);
        assertEquals(expected, user.isPresent());

    }

    @ParameterizedTest
    @MethodSource("argsAdd")
    void add(User user) {
        assertThrows(UnsupportedOperationException.class, () -> userDAO.add(user));
    }

    @ParameterizedTest
    @MethodSource("argsAdd")
    void edit(User user) {
        assertThrows(UnsupportedOperationException.class, () -> userDAO.edit(user));
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(userDAO.delete(145L));
    }
}