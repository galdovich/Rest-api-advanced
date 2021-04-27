package com.galdovich.esm.exception;

/**
 * {@code ControllerException} is generated in case occurrence of an exceptional situation on controller layer.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class ControllerException extends RuntimeException{
    private String messageKey;
    private String messageParameter;

    /**
     * Instantiates a new controller exception.
     *
     * @param messageKey       the message key to get message from properties files
     * @param messageParameter the message parameter to set into message from properties files
     */
    public ControllerException(String messageKey, String messageParameter) {
        this.messageKey = messageKey;
        this.messageParameter = messageParameter;
    }

    /**
     * Instantiates a new service exception.
     *
     * @param messageKey the message key to get message from properties files
     */
    public ControllerException(String messageKey) {
        this.messageKey = messageKey;
        this.messageParameter = null;
    }

    /**
     * Gets message key.
     *
     * @return the message key
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * Gets message parameter.
     *
     * @return the message parameter
     */
    public String getMessageParameter() {
        return messageParameter;
    }
}
