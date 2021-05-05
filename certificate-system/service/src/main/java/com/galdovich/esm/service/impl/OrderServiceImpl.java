package com.galdovich.esm.service.impl;

import com.galdovich.esm.dao.OrderDAO;
import com.galdovich.esm.dto.CertificateDTO;
import com.galdovich.esm.dto.OrderDTO;
import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.dto.UserDTO;
import com.galdovich.esm.entity.Order;
import com.galdovich.esm.exception.AlreadyExistsException;
import com.galdovich.esm.exception.MessageKey;
import com.galdovich.esm.exception.ResourcesNotFoundException;
import com.galdovich.esm.service.CertificateService;
import com.galdovich.esm.service.OrderService;
import com.galdovich.esm.service.UserService;
import com.galdovich.esm.util.GiftConverter;
import com.galdovich.esm.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final CertificateService certificateService;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, CertificateService certificateService,
                            UserService userService) {
        this.orderDAO = orderDAO;
        this.certificateService = certificateService;
        this.userService = userService;
    }

    @Override
    public List<OrderDTO> getAll(PageDTO pageDTO) {
        Page page = GiftConverter.toPage(pageDTO);
        List<Order> foundList = orderDAO.getAll(page);
        return GiftConverter.toOrderDTOList(foundList);
    }

    @Override
    public List<OrderDTO> getUserOrders(PageDTO pageDTO, long userId) {

        Page page = GiftConverter.toPage(pageDTO);
        List<Order> foundList = orderDAO.getAllByUser(page, userId);
        return GiftConverter.toOrderDTOList(foundList);
    }

    @Override
    public OrderDTO getById(long id) throws ResourcesNotFoundException {
        Optional<Order> found = orderDAO.getById(id);
        return found.map(GiftConverter::toOrderDTO).orElseThrow(() ->
                new ResourcesNotFoundException(MessageKey.ORDER_NOT_FOUND, String.valueOf(id)));
    }

    @Transactional
    @Override
    public OrderDTO add(OrderDTO orderDTO) throws AlreadyExistsException {
        Order order = GiftConverter.toOrder(orderDTO);
        CertificateDTO certificate = certificateService.getById(order.getCertificateId());
        UserDTO user = userService.getById(order.getUserId());
        order.setCreatedDate(LocalDateTime.now());
        order.setPrice(certificate.getPrice());
        Order added = orderDAO.add(order);
        return GiftConverter.toOrderDTO(added);
    }

    @Override
    public OrderDTO update(long id, OrderDTO orderDTO) {
        throw new UnsupportedOperationException(MessageKey.UNSUPPORTED_METHOD);
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException(MessageKey.UNSUPPORTED_METHOD);
    }
}
