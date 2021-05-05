package com.galdovich.esm.controller;

import com.galdovich.esm.dto.OrderDTO;
import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.exception.MessageKey;
import com.galdovich.esm.exception.WrongParameterFormatException;
import com.galdovich.esm.service.OrderService;
import com.galdovich.esm.util.HateoasData;
import com.galdovich.esm.validator.GiftValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class {@code OrderController} is an endpoint of the API
 * which allows to perform CRUD operations on orders.
 * Annotated by {@link RestController} with no parameters to provide an answer in application/json.
 * Annotated by {@link RequestMapping} with parameter value = "/orders".
 * So that {@code OrderController} is accessed by sending request to /orders.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * Instantiates a new Order controller.
     *
     * @param orderService the order service
     */
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Find all orders.
     * Annotated by {@link GetMapping}.
     * Therefore, processes GET requests at /orders.
     *
     * @param page the current page
     * @param size amount of tags on the page
     * @return the list of found orders
     */
    @GetMapping
    public List<OrderDTO> getAll(@RequestParam(required = false, defaultValue = "1") int page,
                                 @RequestParam(required = false, defaultValue = "5") int size) {
        PageDTO pageDTO = new PageDTO(page, size);
        List<OrderDTO> foundList = orderService.getAll(pageDTO);
        foundList.forEach(this::addLinks);
        return foundList;
    }

    /**
     * Get order by id.
     * Annotated by {@link GetMapping} with parameter value = "/{id}".
     * Therefore, processes GET requests at /orders/{id}.
     *
     * @param id the order id which will be found. Inferred from the request URI
     * @return the found order dto
     */
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable long id) {
        OrderDTO foundOrderDto = orderService.getById(id);
        addLinks(foundOrderDto);
        return foundOrderDto;
    }

    /**
     * Get order by user id.
     * Annotated by {@link GetMapping} with parameter value = "/users/{userId}".
     * Therefore, processes GET requests at /orders/users/{userId}.
     *
     * @param userId the user id which orders will be found. Inferred from the request URI
     * @param page   the number of page for pagination
     * @param size   the size of page for pagination
     * @return the list of all user's orders dto
     */
    @GetMapping("/users/{userId}")
    public List<OrderDTO> getAllByUserId(@PathVariable(name = "userId") long userId,
                                         @RequestParam(required = false, defaultValue = "1") int page,
                                         @RequestParam(required = false, defaultValue = "5") int size) {
        if (!(new GiftValidator().isPageValid(page, size))) {
            throw new WrongParameterFormatException(MessageKey.WRONG_PARAM_FORMAT);
        }
        PageDTO pageDTO = new PageDTO(page, size);
        List<OrderDTO> foundList = orderService.getUserOrders(pageDTO, userId);
        foundList.forEach(this::addLinks);
        return foundList;
    }

    /**
     * Add new order.
     * Annotated by {@link PostMapping} with no parameters.
     * Therefore, processes POST requests at /orders.
     *
     * @param orderDTO the new order which will be added
     * @return the added order dto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO addedOrder = orderService.add(orderDTO);
        addLinks(addedOrder);
        return addedOrder;
    }


    private void addLinks(OrderDTO orderDTO) {
        orderDTO.add(linkTo(methodOn(OrderController.class)
                .getOrderById(orderDTO.getId())).withSelfRel());
        orderDTO.add(linkTo(methodOn(CertificateController.class)
                .getById(orderDTO.getCertificateId())).withRel(HateoasData.CERTIFICATE));
        orderDTO.add(linkTo(methodOn(UserController.class)
                .getById(orderDTO.getUserId())).withRel(HateoasData.USER));
    }
}
