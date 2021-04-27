package com.galdovich.esm.exception;

/**
 * {@code ResourcesNotFoundException} is generated in case entity not found in database.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class ResourcesNotFoundException extends ServiceException {

    /**
     * Instantiates a new not found exception.
     *
     * @param messageKey       the message key to get message from properties files
     * @param messageParameter the message parameter to set into message from properties files
     */
    public ResourcesNotFoundException(String messageKey, String messageParameter) {
        super(messageKey, messageParameter);
    }
}
