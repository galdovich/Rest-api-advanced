package com.galdovich.esm.dao.util;

/**
 * The type Column name.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class ColumnName {
    /**
     * The column name constants for {@link com.galdovich.esm.entity.Tag}.
     */
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";

    /**
     * The column name constants for {@link com.galdovich.esm.entity.Certificate}.
     */
    public static final String CERTIFICATE_ID = "id";
    public static final String CERTIFICATE_NAME = "name";
    public static final String CERTIFICATE_DESCRIPTION = "description";
    public static final String CERTIFICATE_PRICE = "price";
    public static final String CERTIFICATE_DURATION = "duration";
    public static final String CERTIFICATE_CREATE_DATE = "create_date";
    public static final String CERTIFICATE_LAST_UPDATE_DATE = "last_update_date";

    private ColumnName() {
    }
}
