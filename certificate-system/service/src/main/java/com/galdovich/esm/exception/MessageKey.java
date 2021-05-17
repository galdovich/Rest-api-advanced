package com.galdovich.esm.exception;

/**
 *  Class {@code MessageKey} presents keys by which messages will be taken from properties files.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class MessageKey {

    /**
     * Keys for exception messages associated with {@link com.galdovich.esm.entity.Certificate}.
     */
    public static final String CERTIFICATE_NOT_FOUND_BY_ID = "certificate.notFoundById";
    public static final String CERTIFICATE_ALREADY_EXISTS = "certificate.alreadyExists";
    public static final String CERTIFICATE_UPDATE_EXCEPTION = "certificate.updateException";

    /**
     * Keys for exception messages associated with {@link com.galdovich.esm.entity.Order}.
     */
    public static final String ORDER_NOT_FOUND = "order.notFoundById";

    /**
     * Keys for exception messages associated with {@link com.galdovich.esm.entity.Tag}.
     */
    public static final String TAG_NOT_FOUND_BY_ID = "tag.notFoundById";
    public static final String TAG_NOT_FOUND_BY_NAME = "tag.notFoundByName";
    public static final String TAG_ALREADY_EXISTS = "tag.alreadyExists";

    /**
     * Keys for exception messages associated with {@link com.galdovich.esm.util.Page}.
     */
    public static final String INCORRECT_PAGE_NUMBER = "{page.incorrectNumber}";
    public static final String INCORRECT_PAGE_SIZE = "{page.incorrectSize}";
    public static final String INCORRECT_ID = "{validation.incorrectId}";

    /**
     * Keys for exception messages associated with {@link com.galdovich.esm.entity.User}.
     */
    public static final String USER_NOT_FOUND_BY_ID = "user.notFoundById";
    public static final String USER_NOT_FOUND_BY_EMAIL = "user.notFoundByEmail";
    public static final String USER_ALREADY_EXISTS = "user.alreadyExists";

    /**
     * Keys for exception messages associated with wrong input parameters.
     */
    public static final String WRONG_PARAM_FORMAT = "parameters.wrongFormat";

    /**
     * Keys for exception messages associated with unsupported method.
     */
    public static final String UNSUPPORTED_METHOD = "Method not supported";

    private MessageKey() {
    }
}
