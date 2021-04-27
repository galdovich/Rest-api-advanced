package com.galdovich.esm.util;

import com.galdovich.esm.dto.*;
import com.galdovich.esm.entity.Certificate;
import com.galdovich.esm.entity.Order;
import com.galdovich.esm.entity.Tag;
import com.galdovich.esm.entity.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class {@code GiftConverter} contains entity/dto converters.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class GiftConverter {

    /**
     * Convert Certificate to CertificateDTO.
     *
     * @param certificate the certificate
     * @return the certificate dto
     */
    public static CertificateDTO toCertificateDTO(Certificate certificate) {
        return new CertificateDTO(certificate.getId(),
                certificate.getName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getDuration(),
                certificate.getCreateDate(),
                certificate.getLastUpdateDate(),
                certificate.getTags()
                        .stream()
                        .map(tag -> toTagDTO(tag))
                        .collect(Collectors.toSet()));
    }

    /**
     * Convert Tag to TagDTO.
     *
     * @param tag the tag
     * @return the tag dto
     */
    public static TagDTO toTagDTO(Tag tag) {
        return new TagDTO(tag.getId(),
                tag.getName());
    }

    /**
     * Convert CertificateDTO to Certificate.
     *
     * @param certificateDTO the certificate dto
     * @return the certificate
     */
    public static Certificate toCertificate(CertificateDTO certificateDTO) {
        Certificate certificate = new Certificate();
        certificate.setId(certificateDTO.getId());
        certificate.setName(certificateDTO.getName());
        certificate.setDuration(certificateDTO.getDuration());
        certificate.setPrice(certificateDTO.getPrice());
        certificate.setDescription(certificateDTO.getDescription());
        certificate.setCreateDate(certificateDTO.getCreateDate());
        certificate.setLastUpdateDate(certificateDTO.getLastUpdateDate());
        certificate.setTags(certificateDTO.getTags().stream().map(GiftConverter::toTag).collect(Collectors.toSet()));
        return certificate;
    }

    /**
     * Convert TagDTO to Tag.
     *
     * @param tagDTO the tag dto
     * @return the tag
     */
    public static Tag toTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());
        return tag;
    }

    /**
     * Convert Certificate list to CertificateDTO list.
     *
     * @param list the list
     * @return the list
     */
    public static List<CertificateDTO> toCertificateDTOList(List<Certificate> list) {
        return list.stream().map(GiftConverter::toCertificateDTO).collect(Collectors.toList());
    }

    /**
     * Convert Tag set to TagDTO list.
     *
     * @param set the set
     * @return the list
     */
    public static List<TagDTO> toTagDTOList(List<Tag> set) {
        return set.stream().map(GiftConverter::toTagDTO).collect(Collectors.toList());
    }

    /**
     * Convert QueryParamsDTO to QueryParams.
     *
     * @param paramsDTO the params dto
     * @return the query params
     */
    public static QueryParams toQueryParams(QueryParamsDTO paramsDTO) {
        return new QueryParams(paramsDTO.getName(), paramsDTO.getTags(), paramsDTO.getCreate_date(),
                paramsDTO.getDescription(), paramsDTO.getSortType(), paramsDTO.getDirection());
    }

    /**
     * Convert PageDTO to Page.
     *
     * @param pageDTO the page dto
     * @return the page
     */
    public static Page toPage(PageDTO pageDTO) {
        return new Page(pageDTO.getPage(), pageDTO.getSize());
    }

    /**
     * Convert User to UserDTO.
     *
     * @param user the user
     * @return the user dto
     */
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName());
    }

    /**
     * Convert User list to UserDTO list.
     *
     * @param list the list
     * @return the list
     */
    public static List<UserDTO> toUserDTOList(List<User> list) {
        return list.stream().map(GiftConverter::toUserDTO).collect(Collectors.toList());
    }

    /**
     * Convert Order to OrderDTO.
     *
     * @param order the order
     * @return the order dto
     */
    public static OrderDTO toOrderDTO(Order order) {
        return new OrderDTO(order.getId(),
                order.getPrice(),
                order.getCreatedDate(),
                order.getCertificateId(),
                order.getUserId());
    }

    /**
     * Convert Order list to OrderDTO list.
     *
     * @param list the list
     * @return the list
     */
    public static List<OrderDTO> toOrderDTOList(List<Order> list) {
        return list.stream().map(GiftConverter::toOrderDTO).collect(Collectors.toList());
    }

    /**
     * Convert orderDTO to order.
     *
     * @param orderDTO the order dto
     * @return the order
     */
    public static Order toOrder(OrderDTO orderDTO) {
        return new Order(orderDTO.getId(),
                orderDTO.getPrice(),
                orderDTO.getCreatedDate(),
                orderDTO.getCertificateId(),
                orderDTO.getUserId());
    }
}
