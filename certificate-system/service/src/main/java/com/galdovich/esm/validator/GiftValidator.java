package com.galdovich.esm.validator;

import com.galdovich.esm.dao.util.DirectionType;
import com.galdovich.esm.dao.util.SortType;
import com.galdovich.esm.dto.CertificateDTO;
import com.galdovich.esm.dto.TagDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

/**
 * Class {@code GiftValidator} represents gift validator
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class GiftValidator {
    private static final String REGEX_ID = "^[1-9]\\d{0,18}$";
    private static final String REGEX_NAME = "^[\\S].{0,50}";
    private static final String REGEX_DESCRIPTION = "^[\\S].{0,250}";
    private static final String REGEX_DURATION = "^[1-9]\\d{0,9}$";
    private static final String REGEX_CREATE_DATE = "(((\\d{4})-(\\d{2})-(\\d{2}))|(\\d{4}))(T| )?" +
            "(((\\d{2})\\:(\\d{2})\\:(\\d{2}))(\\.(\\d{9}))?)?";
    private static final String REGEX_PAGE = "^[1-9]\\d{0,18}$";

    /**
     * Method validates parameters, that must be match regular expressions if they not null
     *
     * @param name        the name
     * @param tagNames    the tag names array
     * @param description the description
     * @param createDate  the create date
     * @param sortType    the sort type
     * @param direction   the direction
     * @return the boolean
     */
    public boolean isParamsValid(String name, String[] tagNames, String description,
                                 String createDate, String sortType, String direction) {
        return isNameValidOptional(name) &&
                isTagsValidOptional(tagNames) &&
                isDescriptionValidOptional(description) &&
                isCreateDateValidOptional(createDate) &&
                isSortTypeValidOptional(sortType) &&
                isDirectionTypeValidOptional(direction);
    }

    /**
     * Method validates Certificate DTO. Parameters can be null
     *
     * @param certificate the certificate dto
     * @return the boolean
     */
    public boolean isUpdateCertificateValid(CertificateDTO certificate) {
        return isNameValidOptional(certificate.getName()) &&
                isDescriptionValidOptional(certificate.getDescription()) &&
                isPriceValidOptional(certificate.getPrice()) &&
                isDurationValidOptional(certificate.getDuration()) &&
                isCreateDateValidOptional(certificate.getCreateDate()) &&
                isTagsValidOptional(certificate.getTags());
    }

    /**
     * Method validates adding certificate dto. Parameters can be null
     *
     * @param certificate the certificate dto
     * @return the boolean
     */
    public boolean isAddedCertificateValid(CertificateDTO certificate) {
        return isNameValid(certificate.getName()) &&
                isPriceValid(certificate.getPrice()) &&
                isDescriptionValid(certificate.getDescription()) &&
                isDurationValid(certificate.getDuration()) &&
                isTagsValidOptional(certificate.getTags());
    }

    /**
     * Method validate name parameter of the certificate. Value can be null.
     *
     * @param name the certificate name
     * @return the boolean
     */
    public boolean isNameValidOptional(String name) {
        return name == null || name.matches(REGEX_NAME);
    }

    /**
     * Method validate tag names parameter. Value can be null.
     *
     * @param tags the certificate tags
     * @return the boolean
     */
    public boolean isTagsValidOptional(String[] tags) {
        return tags == null || Arrays.stream(tags).allMatch(tag -> tag.matches(REGEX_NAME));
    }

    /**
     * Method validate description parameter. Value can be null.
     *
     * @param description the certificate description
     * @return the boolean
     */
    public boolean isDescriptionValidOptional(String description) {
        return description == null || description.matches(REGEX_DESCRIPTION);
    }

    /**
     * Method validate create date parameter. Value can be null.
     *
     * @param createDate the certificate create date
     * @return the boolean
     */
    public boolean isCreateDateValidOptional(String createDate) {
        return createDate == null || createDate.matches(REGEX_CREATE_DATE);
    }

    /**
     * Method validate create date parameter. Value can be null.
     *
     * @param createDate the certificate create date
     * @return the boolean
     */
    public boolean isCreateDateValidOptional(LocalDateTime createDate) {
        if(createDate != null){
            return createDate.toString().matches(REGEX_CREATE_DATE);
        }
        return true;
    }

    /**
     * Method validate sort parameter. Value can be null.
     *
     * @param sortType the sort type
     * @return the boolean
     */
    public boolean isSortTypeValidOptional(String sortType) {
        return sortType == null || Arrays.stream(SortType.values())
                .anyMatch(s -> s.name().equals(sortType.toUpperCase()));
    }

    /**
     * Method validate direction parameter. Value can be null.
     *
     * @param direction the direction type
     * @return the boolean
     */
    public boolean isDirectionTypeValidOptional(String direction) {
        return direction == null || Arrays.stream(DirectionType.values())
                .anyMatch(s -> s.name().equals(direction.toUpperCase()));
    }

    /**
     * Method validate price of the certificate. Value can be null
     *
     * @param price the certificate price
     * @return the boolean
     */
    public boolean isPriceValidOptional(BigDecimal price) {
        return price == null || price.doubleValue() > 0;
    }

    /**
     * Method validate duration of the certificate
     *
     * @param duration the certificate duration
     * @return the boolean
     */
    public boolean isDurationValidOptional(Integer duration) {
        return duration == null || String.valueOf(duration).matches(REGEX_DURATION);
    }

    /**
     * Method validate the set of tags. Value can be null
     *
     * @param tags the set of tags
     * @return the boolean
     */
    public boolean isTagsValidOptional(Set<TagDTO> tags) {
        return tags == null || tags.stream().allMatch(this::isTagValid);
    }

    /**
     * Method validate the tag name
     *
     * @param tag the tag
     * @return the boolean
     */
    public boolean isTagValid(TagDTO tag) {
        return tag != null && tag.getName() != null && tag.getName().matches(REGEX_NAME);
    }

    /**
     * Page validator.
     *
     * @param page the page
     * @param size the size
     * @return the boolean
     */
    public boolean isPageValid(int page, int size) {
        return String.valueOf(page).matches(REGEX_PAGE)
                && String.valueOf(size).matches(REGEX_PAGE);
    }

    /**
     * Method validate name of the certificate
     *
     * @param name the certificate name
     * @return the boolean
     */
    public boolean isNameValid(String name) {
        return name != null && name.matches(REGEX_NAME);
    }

    /**
     * Method validate price of the certificate
     *
     * @param price the certificate price
     * @return the boolean
     */
    public boolean isPriceValid(BigDecimal price) {
        return price != null && price.doubleValue() > 0;
    }

    /**
     * Method validate duration of the certificate
     *
     * @param duration the certificate duration
     * @return the boolean
     */
    public boolean isDurationValid(Integer duration) {
        return duration != null && String.valueOf(duration).matches(REGEX_DURATION);
    }

    /**
     * Method validate description parameter
     *
     * @param description the certificate description
     * @return the boolean
     */
    public boolean isDescriptionValid(String description) {
        return description != null && description.matches(REGEX_DESCRIPTION);
    }

}
