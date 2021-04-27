package com.galdovich.esm.exception;

/**
 * {@code UnsupportedMethodException} is generated in case method not available.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class UnsupportedMethodException extends ServiceException{

    /**
     * Instantiates a new Unsupported method exception.
     *
     * @param messageKey the message key
     */
    public UnsupportedMethodException(String messageKey) {
        super(messageKey);
    }

    /**
     * Instantiates a new Unsupported method exception.
     *
     * @param messageKey       the message key
     * @param messageParameter the message parameter
     */
    public UnsupportedMethodException(String messageKey, String messageParameter) {
        super(messageKey, messageParameter);
    }
}
