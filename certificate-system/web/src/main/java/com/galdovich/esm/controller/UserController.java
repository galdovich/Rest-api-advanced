package com.galdovich.esm.controller;

import com.galdovich.esm.dto.OrderDTO;
import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.dto.UserDTO;
import com.galdovich.esm.service.OrderService;
import com.galdovich.esm.service.UserService;
import com.galdovich.esm.util.HateoasData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class {@code UserController} is an endpoint of the API
 * which allows to perform CRUD operations on users.
 * Annotated by {@link RestController} with no parameters to provide an answer in application/json.
 * Annotated by {@link RequestMapping} with parameter value = "/users".
 * So that {@code UserController} is accessed by sending request to /users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    /**
     * Find all users.
     * Annotated by {@link GetMapping}.
     * Therefore, processes GET requests at /users.
     *
     * @param page the current page
     * @param size amount of tags on the page
     * @return the list of found users
     */
    @GetMapping
    public List<UserDTO> getAll(@RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "5") int size) {
        PageDTO pageDTO = new PageDTO(page, size);
        List<UserDTO> foundList = userService.getAll(pageDTO);
        foundList.forEach(this::addLinks);
        return foundList;
    }

    /**
     * Get user by id.
     * Annotated by {@link GetMapping} with parameter value = "/{id}".
     * Therefore, processes GET requests at /users/{id}.
     *
     * @param id the users id that will be found. Inferred from the request URI
     * @return the found user
     */
    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable(name = "id") long id) {
        UserDTO found = userService.getById(id);
        addLinks(found);
        return found;
    }

    /**
     * Get users order list.
     * Annotated by {@link GetMapping} with parameter value = "/{id}".
     * Therefore, processes GET requests at /users/{id}/orders.
     *
     * @param id the users id that will be found. Inferred from the request URI
     * @return the found user
     */
    @GetMapping("/{id}/orders")
    public List<OrderDTO> getUserOrders(@PathVariable(name = "id") long id,
                                        @RequestParam(required = false, defaultValue = "1") int page,
                                        @RequestParam(required = false, defaultValue = "5") int size) {
        PageDTO pageDTO = new PageDTO(page, size);
        List<OrderDTO> foundList = orderService.getUserOrders(pageDTO, id);
        foundList.forEach(this::addLinks);
        return foundList;
    }

    private void addLinks(UserDTO userDTO) {
        userDTO.add(linkTo(methodOn(UserController.class)
                .getById(userDTO.getId())).withSelfRel());
    }

    private void addLinks(OrderDTO orderDTO) {
        orderDTO.add(linkTo(methodOn(OrderController.class)
                .getOrderById(orderDTO.getId())).withSelfRel());
        orderDTO.add(linkTo(methodOn(UserController.class)
                .getById(orderDTO.getUserId())).withRel(HateoasData.USER));
        orderDTO.add(linkTo(methodOn(CertificateController.class)
                .getById(orderDTO.getCertificateId())).withRel(HateoasData.CERTIFICATE));
    }
}
