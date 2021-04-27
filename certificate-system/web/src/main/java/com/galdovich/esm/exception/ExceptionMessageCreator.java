package com.galdovich.esm.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Class {@code ExceptionMessageCreator} represents exception message creator.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
@Component
public class ExceptionMessageCreator {

    private final ResourceBundleMessageSource messageSource;

    /**
     * Instantiates a new Exception message creator.
     *
     * @param messageSource the message source
     */
    @Autowired
    public ExceptionMessageCreator(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Create exception message by getting string from properties files and set parameter to it.
     *
     * @param exceptionMessageKey       the exception message key to get message from properties files
     * @param exceptionMessageParameter the exception message parameter
     * @return the created localized exception message
     */
    public String createMessage(String exceptionMessageKey, String exceptionMessageParameter) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(exceptionMessageKey, new Object[]{}, locale);
        return String.format(message, exceptionMessageParameter);
    }

    /**
     * Create exception message by getting string from properties files.
     *
     * @param exceptionMessageKey the exception message key to get message from properties files
     * @return the created localized exception message
     */
    public String createMessage(String exceptionMessageKey) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(exceptionMessageKey, new Object[]{}, locale);
    }
}