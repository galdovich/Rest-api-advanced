package com.galdovich.esm.dao.util;

/**
 * The type Sql query.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class SQLQuery {

    /**
     * SQL query for {@link com.galdovich.esm.entity.Certificate}
     */
    public static final String CERTIFICATE_GET_ALL = "select c from Certificate c";
    public static final String CERTIFICATE_GET_BY_NAME = "select c from Certificate c WHERE c.name=?1";
    public static final String REMOVE_CERTIFICATE_HAS_TAG = "DELETE FROM certificate_has_tag "
            + "WHERE certificate_id = ?";

    /**
     * SQL query for {@link com.galdovich.esm.entity.Tag}
     */
    public static final String TAG_GET_BY_NAME = "SELECT t FROM Tag t WHERE t.name = ?1";
    public static final String TAG_GET_ALL = "SELECT t FROM Tag t";
    public static final String REMOVE_TAG_HAS_CERTIFICATE = "DELETE FROM certificate_has_tag "
            + "WHERE tag_id = ?";
    public static final String TAG_MOST_POPUlAR = "select tag_id FROM (select tag_id, count(tag_id) AS popular from certificate_orders " +
            "JOIN certificate_has_tag cht on certificate_orders.certificate_id = cht.certificate_id " +
            "where user_id=? group by tag_id order by popular desc limit 1) AS temp";

    /**
     * SQL query for {@link com.galdovich.esm.entity.User}
     */
    public static final String USER_GET_ALL = "select c from User c";
    public static final String USER_GET_ID_HIGHEST_ORDERS = "SELECT o.user_id from certificate_orders AS o " +
            "group by o.user_id order by sum(o.certificate_order_price) desc limit 1";

    /**
     * SQL query for {@link com.galdovich.esm.entity.Order}
     */
    public static final String ORDER_GET_ALL = "select o from Order o";
    public static final String ORDER_GET_ALL_BY_USER = "select o from Order o where o.userId=?1";

    private SQLQuery() {
    }
}
