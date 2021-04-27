package com.galdovich.esm.exception;


/**
 * {@code AlreadyExistsException} is generated in case entity already exists in database.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class AlreadyExistsException extends ServiceException {

    /**
     * Instantiates a new already exists exception.
     *
     * @param messageKey       the message key to get message from properties files
     * @param messageParameter the message parameter to set into message from properties files
     */
    public AlreadyExistsException(String messageKey, String messageParameter) {
        super(messageKey, messageParameter);
    }
}
