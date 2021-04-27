package com.galdovich.esm.dao.impl;

import com.galdovich.esm.config.PersistenceConfig;
import com.galdovich.esm.dao.CertificateDAO;
import com.galdovich.esm.dao.util.DirectionType;
import com.galdovich.esm.dao.util.SortType;
import com.galdovich.esm.entity.Certificate;
import com.galdovich.esm.util.Page;
import com.galdovich.esm.util.QueryParams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersistenceConfig.class)
@Transactional
class CertificateDAOImplTest {

    @Autowired
    private CertificateDAO certificateDAO;

    static Stream<Arguments> argsGetAll() {
        return Stream.of(
                Arguments.of(1, 5, 5),
                Arguments.of(2, 9, 9),
                Arguments.of(6, 20, 20)
        );
    }

    @ParameterizedTest
    @MethodSource("argsGetAll")
    void getAll(int page, int size, int expected) {
        List<Certificate> foundList = certificateDAO.getAll(new Page(page, size));
        assertEquals(expected, foundList.size());
    }

    static Stream<Arguments> argsGetById() {
        return Stream.of(
                Arguments.of(156L, true),
                Arguments.of(150L, true),
                Arguments.of(3L, false)
        );
    }

    @ParameterizedTest
    @MethodSource("argsGetById")
    void getById(long id, boolean expected) {
        Optional<Certificate> found = certificateDAO.getById(id);
        assertEquals(expected, found.isPresent());
    }

    static Stream<Arguments> argsAdd() {
        return Stream.of(
                Arguments.of(new Certificate("Test1", "TestDescr1",
                        new BigDecimal(123), 12, LocalDateTime.now(), LocalDateTime.now(), new HashSet<>())),
                Arguments.of(new Certificate("Test2", "TestDescr2",
                        new BigDecimal(123), 12, LocalDateTime.now(), LocalDateTime.now(), new HashSet<>()))
        );
    }

    @ParameterizedTest
    @MethodSource("argsAdd")
    void add(Certificate certificate) {
        Certificate added = certificateDAO.add(certificate);
        Optional<Certificate> optional = certificateDAO.getById(added.getId());
        assertTrue(optional.isPresent());
    }

    static Stream<Arguments> argsEdit() {
        return Stream.of(
                Arguments.of(new Certificate("Edit1", "TestDescr1",
                        new BigDecimal(123), 12, LocalDateTime.now(), LocalDateTime.now(), new HashSet<>()), "newTestName1"),
                Arguments.of(new Certificate("Edit2", "TestDescr2",
                        new BigDecimal(123), 12, LocalDateTime.now(), LocalDateTime.now(), new HashSet<>()), "newTestName2")
        );
    }

    @ParameterizedTest
    @MethodSource("argsEdit")
    void edit(Certificate certificate, String newName) {
        Certificate added = certificateDAO.add(certificate);
        Certificate expected = certificateDAO.getById(added.getId()).get();
        expected.setName(newName);
        Certificate edit = certificateDAO.edit(expected);
        assertEquals(edit.getName(), expected.getName());
    }

    @Test
    void delete() {
        assertTrue(certificateDAO.delete(188L));
    }

    static Stream<Arguments> argsGetByName() {
        return Stream.of(
                Arguments.of("Horse riding", true),
                Arguments.of("Swimming with Dolphins", true),
                Arguments.of("Swing", false)
        );
    }

    @ParameterizedTest
    @MethodSource("argsGetByName")
    void getByName(String name, boolean expected) {
        Optional<Certificate> certificate = certificateDAO.getByName(name);
        assertEquals(expected, certificate.isPresent());
    }

    static Stream<Arguments> argsGetAllByParam() {
        return Stream.of(
                Arguments.of(new QueryParams(null, null, null, null, null, null),
                        new Page(1, 5), 5),
                Arguments.of(new QueryParams("Swimming with Dolphins", null, null, null, null, null),
                        new Page(1, 5), 1),
                Arguments.of(new QueryParams(null, new String[]{"log"}, null, null, null, null),
                        new Page(1, 5), 1),
                Arguments.of(new QueryParams(null, null, "Minsk", null, null, null),
                        new Page(1, 5), 2),
                Arguments.of(new QueryParams("Race", null, "Minsk", null, null, null),
                        new Page(1, 5), 1)
        );
    }

    @ParameterizedTest
    @MethodSource("argsGetAllByParam")
    void testGetAll(QueryParams params, Page page, int expected) {
        List<Certificate> foundList = certificateDAO.getAll(params, page);
        assertEquals(foundList.size(), expected);
    }

    static Stream<Arguments> argsGetAllSorted() {
        return Stream.of(
                Arguments.of(new QueryParams("mas", null, null, null, SortType.PRICE, DirectionType.ASC),
                        new Page(1, 5), Comparator.comparing(Certificate::getPrice)),
                Arguments.of(new QueryParams("mas", null, null, null, SortType.PRICE, DirectionType.DESC),
                        new Page(1, 5), Comparator.comparing(Certificate::getPrice).reversed()),
                Arguments.of(new QueryParams("mas", null, null, null, SortType.DURATION, DirectionType.DESC),
                        new Page(1, 5), Comparator.comparing(Certificate::getDuration).reversed())
        );
    }

    @ParameterizedTest
    @MethodSource("argsGetAllSorted")
    void testGetAllSorted(QueryParams params, Page page, Comparator comparator) {
        List<Certificate> foundList = certificateDAO.getAll(params, page);
        List<Certificate> sorted = new ArrayList<>(foundList);
        sorted.sort(comparator);
        assertEquals(foundList, sorted);
    }
}