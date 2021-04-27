package com.galdovich.esm.dao.impl;

import com.galdovich.esm.config.PersistenceConfig;
import com.galdovich.esm.dao.TagDAO;
import com.galdovich.esm.entity.Tag;
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
@Transactional
class TagDAOImplTest {

    @Autowired
    private TagDAO tagDAO;

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
        List<Tag> tagList = tagDAO.getAll(new Page(page, size));
        assertEquals(expected, tagList.size());
    }

    static Stream<Arguments> argsGetById() {
        return Stream.of(
                Arguments.of(1L, true),
                Arguments.of(300L, true),
                Arguments.of(0L, false)
        );
    }

    @ParameterizedTest
    @MethodSource("argsGetById")
    void getById(long id, boolean expected) {
        Optional<Tag> foundTag = tagDAO.getById(id);
        assertEquals(expected, foundTag.isPresent());
    }

    static Stream<Arguments> argsGetByName() {
        return Stream.of(
                Arguments.of("ACTIVE", true),
                Arguments.of("FORTWO", true),
                Arguments.of("RELAX", true),
                Arguments.of("ADRENALIN12", false)
        );
    }

    @ParameterizedTest
    @MethodSource("argsGetByName")
    void getByName(String name, boolean expected) {
        Optional<Tag> foundTag = tagDAO.getByName(name);
        assertEquals(expected, foundTag.isPresent());
    }

    static Stream<Arguments> argsEdit() {
        return Stream.of(
                Arguments.of(new Tag(15L, "Riding")),
                Arguments.of(new Tag(16L, "MaxSnow"))
        );
    }

    @ParameterizedTest
    @MethodSource("argsEdit")
    void edit(Tag tag) {
        Tag editTag = tagDAO.edit(tag);
        assertEquals(tag.getName(), editTag.getName());
    }

    static Stream<Arguments> argsAdd() {
        return Stream.of(
                Arguments.of(new Tag(null, "NewTagTest1")),
                Arguments.of(new Tag(null, "NewTagTest2"))
        );
    }

    @ParameterizedTest
    @MethodSource("argsAdd")
    void add(Tag tag) {
        Tag added = tagDAO.add(tag);
        assertNotNull(added);
        assertEquals(tag.getName(), added.getName());
    }

    @Test
    void getMostPopularUserTag() {
        long tagId = tagDAO.getMostPopularUserTag(644L);
        assertEquals(661, tagId);
    }

    @Test
    void delete() {
        boolean actual = tagDAO.delete(55L);
        assertTrue(actual);
    }
}