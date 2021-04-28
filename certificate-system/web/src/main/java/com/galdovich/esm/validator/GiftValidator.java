package com.galdovich.esm.validator;

import com.galdovich.esm.dao.util.DirectionType;
import com.galdovich.esm.dao.util.SortType;
import com.galdovich.esm.dto.CertificateDTO;
import com.galdovich.esm.dto.TagDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * Class {@code GiftValidator} represents gift validator
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class GiftValidator {
    private static final String REGEX_ID = "^[1-9]\\d{0,18}$";
    private static final String REGEX_NAME = "^.{1,50}";
    private static final String REGEX_DESCRIPTION = "^.{1,250}";
    private static final String REGEX_DURATION = "^[1-9]\\d{0,9}$";
    
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
    public boolean isParamsValid(String name, String[] tagNames, String description, String createDate,
                                 String sortType, String direction) {
        boolean result = true;
        if (name != null) {
            if (!name.matches(REGEX_NAME)) {
                result = false;
            }
        }
        if (tagNames != null) {
            if (!Arrays.stream(tagNames).allMatch(t -> t.matches(REGEX_NAME))) {
                return false;
            }
        }
        if (description != null) {
            if (!description.matches(REGEX_DESCRIPTION)) {
                result = false;
            }
        }
        if (sortType != null) {
            if (!Arrays.stream(SortType.values()).anyMatch(s -> s.name().equals(sortType.toUpperCase()))) {
                result = false;
            }
        }
        if (direction != null) {
            if (!Arrays.stream(DirectionType.values()).anyMatch(d -> d.name().equals(direction.toUpperCase()))) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Method validates Certificate parameters, that can't be null
     *
     * @param certificate the name
     * @return the boolean
     */
    public boolean isCertificateValid(CertificateDTO certificate) {
        return isNameValid(certificate.getName()) &&
                isDescriptionValid(certificate.getDescription()) &&
                isPriceValid(certificate.getPrice()) &&
                isDurationValid(certificate.getDuration()) &&
                isTagsValid(certificate.getTags());
    }

    /**
     * Method validate id of the certificate
     *
     * @param id the certificate id
     * @return the boolean
     */
    public boolean isIdValid(Long id) {
        return id != null && String.valueOf(id).matches(REGEX_ID);
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
     * Method validate description of the certificate
     *
     * @param description the certificate description
     * @return the boolean
     */
    public boolean isDescriptionValid(String description) {
        return description != null && description.matches(REGEX_DESCRIPTION);
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
     * Method validate the tag name
     *
     * @param tag the tag
     * @return the boolean
     */
    public boolean isTagValid(TagDTO tag) {
        return tag != null && tag.getName() != null && tag.getName().matches(REGEX_NAME);
    }


    /**
     * Method validate the set of tags
     *
     * @param tags the set of tags
     * @return the boolean
     */
    private boolean isTagsValid(Set<TagDTO> tags) {
        return tags == null || tags.stream().filter(Objects::nonNull).allMatch(this::isTagValid);
    }
}
