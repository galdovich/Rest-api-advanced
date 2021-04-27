package com.galdovich.esm.exception;

/**
 * {@code WrongParameterFormatException} is generated in case wrong parameter format.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class WrongParameterFormatException extends ControllerException {

    /**
     * Instantiates a new wrong format exception.
     *
     * @param messageKey       the message key to get message from properties files
     * @param messageParameter the message parameter to set into message from properties files
     */
    public WrongParameterFormatException(String messageKey, String messageParameter) {
        super(messageKey, messageParameter);
    }

    /**
     * Instantiates a new wrong format exception.
     *
     * @param messageKey the message key to get message from properties files
     */
    public WrongParameterFormatException(String messageKey) {
        super(messageKey);
    }
}
