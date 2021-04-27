package com.galdovich.esm.service;

import com.galdovich.esm.dto.OrderDTO;
import com.galdovich.esm.dto.PageDTO;

import java.util.List;

/**
 * Interface {@code OrderService} describes abstract behavior for working
 * with {@link com.galdovich.esm.entity.Order}
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public interface OrderService extends GiftService<OrderDTO> {

    /**
     * Gets user orders.
     *
     * @param pageDTO the page dto
     * @param userId  the user id
     * @return the user orders
     */
    List<OrderDTO> getUserOrders(PageDTO pageDTO, long userId);
}
