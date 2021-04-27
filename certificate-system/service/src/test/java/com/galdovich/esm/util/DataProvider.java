package com.galdovich.esm.util;

import com.galdovich.esm.dto.*;
import com.galdovich.esm.entity.Certificate;
import com.galdovich.esm.entity.Order;
import com.galdovich.esm.entity.Tag;
import com.galdovich.esm.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataProvider {
    public static final PageDTO PAGE_DTO = new PageDTO(1, 10);
    public static final Integer LIMIT = 2;
    public static final TagDTO TAG_DTO;
    public static final TagDTO ADDING_TAG_DTO;
    public static final TagDTO ADDED_TAG_DTO;
    public static final Tag TAG;
    public static final Tag ADDING_TAG;
    public static final Tag ADDED_TAG;
    public static final List<TagDTO> TAG_DTO_LIST;
    public static final List<TagDTO> TAG_DTO_LIST_LIMIT;
    public static final List<Tag> TAG_LIST;
    public static final List<Tag> TAG_LIST_LIMIT;
    public static final UserDTO USER_DTO;
    public static final User USER;
    public static final List<UserDTO> USER_DTO_LIST;
    public static final List<UserDTO> USER_DTO_LIST_LIMIT;
    public static final List<User> USER_LIST;
    public static final List<User> USER_LIST_LIMIT;
    public static final Certificate CERTIFICATE;
    public static final CertificateDTO CERTIFICATE_DTO;
    public static final List<Certificate> CERTIFICATE_LIST;
    public static final List<Certificate> CERTIFICATE_LIST_LIMIT;
    public static final List<CertificateDTO> CERTIFICATE_DTO_LIST;
    public static final List<CertificateDTO> CERTIFICATE_DTO_LIST_LIMIT;
    public static final Order ORDER;
    public static final OrderDTO ORDER_DTO;
    public static final List<Order> ORDER_LIST;
    public static final List<Order> ORDER_LIST_LIMIT;
    public static final List<OrderDTO> ORDER_DTO_LIST;
    public static final List<OrderDTO> ORDER_DTO_LIST_LIMIT;
    public static final Certificate ADDING_CERTIFICATE;
    public static final Certificate UPDATING_CERTIFICATE;
    public static final Order ADDING_ORDER;

    static {
        TAG_DTO = new TagDTO(1L, "Вязание");
        ADDING_TAG_DTO = new TagDTO();
        ADDING_TAG_DTO.setName("Baseball");
        ADDED_TAG_DTO = new TagDTO(25L, "Baseball");
        TAG = new Tag(1L, "Вязание");
        ADDING_TAG = new Tag();
        ADDING_TAG.setName("Baseball");
        ADDED_TAG = new Tag(25L, "Baseball");
        TAG_DTO_LIST = Collections.nCopies(10, TAG_DTO);
        TAG_DTO_LIST_LIMIT = Collections.nCopies(LIMIT, TAG_DTO);
        TAG_LIST = Collections.nCopies(10, TAG);
        TAG_LIST_LIMIT = Collections.nCopies(LIMIT, TAG);
        USER_DTO = new UserDTO(1L, "Alex");
        USER = new User(1L, "Alex");
        USER_DTO_LIST = Collections.nCopies(10, USER_DTO);
        USER_DTO_LIST_LIMIT = Collections.nCopies(LIMIT, USER_DTO);
        USER_LIST = Collections.nCopies(10, USER);
        USER_LIST_LIMIT = Collections.nCopies(LIMIT, USER);
        CERTIFICATE = new Certificate(
                1L,
                "English courses",
                "English courses in school of foreign languages SkyEng",
                BigDecimal.valueOf(250.00),
                180,
                LocalDateTime.now(),
                LocalDateTime.now(),
                Set.of(TAG)
        );
        ADDING_CERTIFICATE = new Certificate(
                null,
                "English courses",
                "English courses in school of foreign languages SkyEng",
                BigDecimal.valueOf(250.00),
                180,
                LocalDateTime.now(),
                LocalDateTime.now(),
                new HashSet<>(Set.of(new Tag(1L, "Активность"), new Tag(19L, "Развлечения")))
        );
        UPDATING_CERTIFICATE = new Certificate(
                1L,
                "Spanish courses",
                "Spanish courses in school of foreign languages SkySpain",
                BigDecimal.valueOf(255.00),
                180,
                LocalDateTime.now(),
                LocalDateTime.now(),
                new HashSet(Set.of(new Tag(8L, "Активность"), new Tag(1L, "Отдых"))));
        CERTIFICATE_DTO = new CertificateDTO(
                1L,
                "English courses",
                "English courses in school of foreign languages SkyEng",
                BigDecimal.valueOf(250.00),
                180,
                LocalDateTime.now(),
                LocalDateTime.now(),
                Set.of(TAG_DTO)
        );
        CERTIFICATE_LIST = Collections.nCopies(10, CERTIFICATE);
        CERTIFICATE_LIST_LIMIT = Collections.nCopies(LIMIT, CERTIFICATE);
        CERTIFICATE_DTO_LIST = Collections.nCopies(10, CERTIFICATE_DTO);
        CERTIFICATE_DTO_LIST_LIMIT = Collections.nCopies(LIMIT, CERTIFICATE_DTO);
        ORDER = new Order(1L, BigDecimal.valueOf(250.00), LocalDateTime.now(), CERTIFICATE.getId(), USER.getId());
        ORDER_DTO = new OrderDTO(1L, BigDecimal.valueOf(250.00), LocalDateTime.now(), CERTIFICATE_DTO.getId(), USER_DTO.getId());
        ORDER_LIST = Collections.nCopies(10, ORDER);
        ORDER_LIST_LIMIT = Collections.nCopies(LIMIT, ORDER);
        ORDER_DTO_LIST = Collections.nCopies(10, ORDER_DTO);
        ORDER_DTO_LIST_LIMIT = Collections.nCopies(LIMIT, ORDER_DTO);
        ADDING_ORDER = new Order(1L, 1L);
    }
}
