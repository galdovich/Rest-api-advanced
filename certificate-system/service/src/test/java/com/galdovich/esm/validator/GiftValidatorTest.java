package com.galdovich.esm.validator;

import com.galdovich.esm.config.ServiceConfiguration;
import com.galdovich.esm.dto.CertificateDTO;
import com.galdovich.esm.dto.TagDTO;
import com.galdovich.esm.util.DataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceConfiguration.class)
class GiftValidatorTest {

    private GiftValidator validator;

    @BeforeEach
    void setUp() {
        validator = new GiftValidator();
    }

    static Stream<Arguments> argsPagePositive() {
        return Stream.of(
                Arguments.of(1, 4),
                Arguments.of(5, 1),
                Arguments.of(5, 52354),
                Arguments.of(54214, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("argsPagePositive")
    void isPageValidPositive(int page, int size) {
        boolean found = validator.isPageValid(page, size);
        assertTrue(found);
    }

    static Stream<Arguments> argsPageNegative() {
        return Stream.of(
                Arguments.of(-1, 4),
                Arguments.of(6, -1),
                Arguments.of(0, 3),
                Arguments.of(1, 0),
                Arguments.of(0, -1),
                Arguments.of(-1, 0),
                Arguments.of(-1, -87)
        );
    }

    @ParameterizedTest
    @MethodSource("argsPageNegative")
    void isPageValidNegative(int page, int size) {
        boolean found = validator.isPageValid(page, size);
        assertFalse(found);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "Minsk", "Borisov", "K", "NameTest"
    })
    void isNameValidTestPositive(String name) {
        assertTrue(validator.isNameValidOptional(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    void isNameValidTestNegative(String name) {
        assertFalse(validator.isNameValidOptional(name));
    }

    static Stream<Arguments> argsTagsPositive() {
        return Stream.of(
                Arguments.of((Object) new String[]{"name", "n"}),
                Arguments.of((Object) new String[]{"e", "f"}),
                Arguments.of((Object) new String[]{"testTag", "ddd"}),
                Arguments.of((Object) new String[]{"123", "testTag"})
        );
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("argsTagsPositive")
    void isTagsValidTestPositive(String[] names) {
        assertTrue(validator.isTagsValidOptional(names));
    }

    static Stream<Arguments> argsTagsNegative() {
        return Stream.of(
                Arguments.of((Object) new String[]{"", "null"}),
                Arguments.of((Object) new String[]{" ", " "}),
                Arguments.of((Object) new String[]{"testTag", ""}),
                Arguments.of((Object) new String[]{"", "testTag"})
        );
    }

    @ParameterizedTest
    @MethodSource("argsTagsNegative")
    void isTagsValidTestNegative(String[] tags) {
        assertFalse(validator.isTagsValidOptional(tags));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "O", "K", "Minsk", "3423", "-3;"
    })
    void isDescriptionValidTestPositive(String desc) {
        assertTrue(validator.isDescriptionValidOptional(desc));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            " ", ""
    })
    void isDescriptionValidTestNegative(String desc) {
        assertFalse(validator.isDescriptionValidOptional(desc));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "2019-10-16T13:17:26", "2019-10-16", "2019-10-16 13:17:26", "2020"
    })
    void isCreateDateValidTestPositive(String createDate) {
        assertTrue(validator.isCreateDateValidOptional(createDate));
    }

    @Test
    void isCreateDateValidTestPositive2() {
        assertTrue(validator.isCreateDateValidOptional(LocalDateTime.now()));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2019-10-16 13:17",
            "2019-10T13:17:26",
            "2019-10-16Y13:17:26",
            "10-09-2020 13:17:26",
    })
    void isCreateDateValidTestNegative(String createDate) {
        assertFalse(validator.isCreateDateValidOptional(createDate));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "Name", "iD", "crEate_date", "price", "Duration",
    })
    void isSortTypeValidTestPositive(String sortType) {
        assertTrue(validator.isSortTypeValidOptional(sortType));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", " ", "create_datee", "132", "duration2",
    })
    void isSortTypeValidTestNegative(String sortType) {
        assertFalse(validator.isSortTypeValidOptional(sortType));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "ASC", "DESC", "asc", "desc", "DeSc", "aSC"
    })
    void isDirectionTypeValidTestPositive(String dir) {
        assertTrue(validator.isDirectionTypeValidOptional(dir));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", "ascc", "dessc", "d", " ",
    })
    void isDirectionTypeValidTestNegative(String dir) {
        assertFalse(validator.isDirectionTypeValidOptional(dir));
    }

    static Stream<Arguments> argsIsParamsValidPositive() {
        return Stream.of(
                Arguments.of(null, null, null, null, null, null),
                Arguments.of("name", null, null, null, null, null),
                Arguments.of(null, new String[]{"tag1", "tag2"}, null, null, null, null),
                Arguments.of(null, null, "description", null, null, null),
                Arguments.of(null, null, null, "2019-10-16T13:17:26", null, null),
                Arguments.of(null, null, null, null, "id", null),
                Arguments.of(null, null, null, null, null, "desc"),
                Arguments.of("name", new String[]{"tag1", "tag2"}, "description", "2019-10-16", "id", "desc")
        );
    }

    @ParameterizedTest
    @MethodSource("argsIsParamsValidPositive")
    void isParamsValidTestPositive(String name, String[] tagNames, String description,
                                   String createDate, String sortType, String direction) {
        assertTrue(validator.isParamsValid(name, tagNames, description, createDate, sortType, direction));
    }

    static Stream<Arguments> argsIsParamsValidNegative() {
        return Stream.of(
                Arguments.of(" ", null, null, null, null, null),
                Arguments.of(null, new String[]{" "}, null, null, null, null),
                Arguments.of(null, null, " ", null, null, null),
                Arguments.of(null, null, null, " ", null, null),
                Arguments.of(null, null, null, null, " ", null),
                Arguments.of(null, null, null, null, null, " ")
        );
    }

    @ParameterizedTest
    @MethodSource("argsIsParamsValidNegative")
    void isParamsValidTestNegative(String name, String[] tagNames, String description,
                                   String createDate, String sortType, String direction) {
        assertFalse(validator.isParamsValid(name, tagNames, description, createDate, sortType, direction));
    }

    static Stream<Arguments> argsPricePositive() {
        return Stream.of(
                Arguments.of(new BigDecimal(12)),
                Arguments.of(new BigDecimal(555)),
                Arguments.of(new BigDecimal(1.24)),
                Arguments.of(new BigDecimal(1244.52))
        );
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("argsPricePositive")
    void isPriceValidTestPositive(BigDecimal price) {
        assertTrue(validator.isPriceValidOptional(price));
    }

    static Stream<Arguments> argsPriceNegative() {
        return Stream.of(
                Arguments.of(new BigDecimal(0)),
                Arguments.of(new BigDecimal(-1))
        );
    }

    @ParameterizedTest
    @MethodSource("argsPriceNegative")
    void isPriceValidTestNegative(BigDecimal price) {
        assertFalse(validator.isPriceValidOptional(price));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {132, 4124, 2, 1})
    void isDurationValidTestPositive(Integer duration) {
        assertTrue(validator.isDurationValidOptional(duration));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1,})
    void isDurationValidTestNegative(Integer duration) {
        assertFalse(validator.isDurationValidOptional(duration));
    }

    static Stream<Arguments> argsTagsArrayPositive() {
        TagDTO tag1 = new TagDTO("kk");
        TagDTO tag2 = new TagDTO("ok");
        TagDTO tag3 = new TagDTO("fef");
        TagDTO tag4 = new TagDTO("fwew");
        TagDTO tag5 = new TagDTO("fff");
        TagDTO tag6 = new TagDTO("fwe");
        Set<TagDTO> set = new HashSet<>(List.of(tag1, tag2, tag3, tag4, tag5, tag6));
        return Stream.of(
                Arguments.of(set)
        );
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("argsTagsArrayPositive")
    void isTagsValidTestPositive(Set<TagDTO> tagDTOSet) {
        assertTrue(validator.isTagsValidOptional(tagDTOSet));
    }

    static Stream<Arguments> argsTagsArrayNegative() {
        TagDTO tag1 = new TagDTO(" ");
        TagDTO tag2 = new TagDTO("");
        Set<TagDTO> set = new HashSet<>(List.of(tag1, tag2));
        return Stream.of(
                Arguments.of(set)
        );
    }

    @ParameterizedTest
    @MethodSource("argsTagsArrayNegative")
    void isTagsValidTestNegative(Set<TagDTO> tagDTOSet) {
        assertFalse(validator.isTagsValidOptional(tagDTOSet));
    }

    static Stream<Arguments> argsUpdateCertificate() {
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setName("Name");
        certificateDTO.setDescription("Minsk");
        certificateDTO.setCreateDate(LocalDateTime.now());
        certificateDTO.setLastUpdateDate(LocalDateTime.now());
        TagDTO tag1 = new TagDTO("firstTag");
        TagDTO tag2 = new TagDTO("secondTag");
        Set<TagDTO> set = new HashSet<>(List.of(tag1, tag2));
        certificateDTO.setTags(set);
        return Stream.of(
                Arguments.of(certificateDTO)
        );
    }

    @ParameterizedTest
    @MethodSource("argsUpdateCertificate")
    void isUpdateCertificateValidTestPositive(CertificateDTO certificateDTO) {
        assertTrue(validator.isUpdateCertificateValid(certificateDTO));
    }

    static Stream<Arguments> argsUpdateCertificateNegative() {
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setName("Name");
        certificateDTO.setPrice(new BigDecimal(123));
        certificateDTO.setDuration(2);
        certificateDTO.setDescription("Minsk");
        certificateDTO.setCreateDate(LocalDateTime.now());
        certificateDTO.setLastUpdateDate(LocalDateTime.now());
        TagDTO tag1 = new TagDTO("firstTag");
        TagDTO tag2 = new TagDTO(" ");
        Set<TagDTO> set = new HashSet<>(List.of(tag1, tag2));
        certificateDTO.setTags(set);
        return Stream.of(
                Arguments.of(certificateDTO)
        );
    }

    @ParameterizedTest
    @MethodSource("argsUpdateCertificateNegative")
    void isUpdateCertificateValidTestNegative(CertificateDTO certificateDTO) {
        assertFalse(validator.isUpdateCertificateValid(certificateDTO));
    }

    static Stream<Arguments> argsAddedCertificate() {
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setName("Name");
        certificateDTO.setPrice(new BigDecimal(123));
        certificateDTO.setDuration(2);
        certificateDTO.setDescription("Minsk");
        certificateDTO.setCreateDate(LocalDateTime.now());
        certificateDTO.setLastUpdateDate(LocalDateTime.now());
        TagDTO tag1 = new TagDTO("firstTag");
        TagDTO tag2 = new TagDTO("secondTag");
        Set<TagDTO> set1 = new HashSet<>(List.of(tag1, tag2));
        certificateDTO.setTags(set1);

        CertificateDTO certificateDTO2 = new CertificateDTO();
        certificateDTO2.setName("Name");
        certificateDTO2.setPrice(new BigDecimal(123));
        certificateDTO2.setDuration(2);
        certificateDTO2.setDescription("Minsk");
        certificateDTO2.setCreateDate(LocalDateTime.now());
        certificateDTO2.setLastUpdateDate(LocalDateTime.now());
        return Stream.of(
                Arguments.of(certificateDTO),
                Arguments.of(certificateDTO2)
        );
    }

    @ParameterizedTest
    @MethodSource("argsAddedCertificate")
    void isAddedCertificateValidTestPositive(CertificateDTO certificateDTO) {
        assertTrue(validator.isAddedCertificateValid(certificateDTO));
    }

    static Stream<Arguments> argsAddedCertificateNegative() {
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setPrice(new BigDecimal(123));
        certificateDTO.setDuration(2);
        certificateDTO.setDescription("Minsk");
        certificateDTO.setCreateDate(LocalDateTime.now());
        certificateDTO.setLastUpdateDate(LocalDateTime.now());
        TagDTO tag1 = new TagDTO("firstTag");
        TagDTO tag2 = new TagDTO("secondTag");
        Set<TagDTO> set1 = new HashSet<>(List.of(tag1, tag2));
        certificateDTO.setTags(set1);

        CertificateDTO certificateDTO2 = new CertificateDTO();
        certificateDTO2.setName("Name");
        certificateDTO2.setPrice(null);
        certificateDTO2.setDuration(2);
        certificateDTO2.setDescription("Minsk");
        certificateDTO2.setCreateDate(LocalDateTime.now());
        certificateDTO2.setLastUpdateDate(LocalDateTime.now());
        return Stream.of(
                Arguments.of(certificateDTO),
                Arguments.of(certificateDTO2)
        );
    }

    @ParameterizedTest
    @MethodSource("argsAddedCertificateNegative")
    void isAddedCertificateValidTestNegative(CertificateDTO certificateDTO) {
        assertFalse(validator.isAddedCertificateValid(certificateDTO));
    }

    static Stream<Arguments> argsCertificatesWithEmptyField() {
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setPrice(DataProvider.CERTIFICATE_DTO.getPrice());

        CertificateDTO certificateDTO2 = new CertificateDTO();
        certificateDTO2.setName(DataProvider.CERTIFICATE_DTO.getName());
        certificateDTO2.setDuration(DataProvider.CERTIFICATE_DTO.getDuration());
        return Stream.of(
                Arguments.of(certificateDTO, 1),
                Arguments.of(certificateDTO2, 2),
                Arguments.of(DataProvider.CERTIFICATE_DTO, 8)
        );
    }

    @ParameterizedTest
    @MethodSource("argsCertificatesWithEmptyField")
    void isCertificateHasNotEmptyFieldPositive(CertificateDTO certificateDTO, int count) {
        assertEquals(validator.countNotEmptyFields(certificateDTO), count);
    }

    static Stream<Arguments> argsCertificatesWithEmptyField2() {
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setPrice(DataProvider.CERTIFICATE_DTO.getPrice());

        CertificateDTO certificateDTO2 = new CertificateDTO();
        certificateDTO2.setName(DataProvider.CERTIFICATE_DTO.getName());
        certificateDTO2.setDuration(DataProvider.CERTIFICATE_DTO.getDuration());

        CertificateDTO certificateDTO3 = new CertificateDTO();
        certificateDTO3.setPrice(new BigDecimal(0));
        return Stream.of(
                Arguments.of(certificateDTO, true),
                Arguments.of(certificateDTO2, false),
                Arguments.of(DataProvider.CERTIFICATE_DTO, false),
                Arguments.of(certificateDTO3, false)
        );
    }

    @ParameterizedTest
    @MethodSource("argsCertificatesWithEmptyField2")
    void isCertificateHasNotEmptyFieldPositive(CertificateDTO certificateDTO, boolean result) {
        assertEquals(validator.isUpdateOneFieldValid(certificateDTO), result);
    }

}
