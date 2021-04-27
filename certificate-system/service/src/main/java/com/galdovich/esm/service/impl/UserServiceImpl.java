package com.galdovich.esm.service.impl;

import com.galdovich.esm.dao.UserDAO;
import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.dto.UserDTO;
import com.galdovich.esm.entity.User;
import com.galdovich.esm.exception.AlreadyExistsException;
import com.galdovich.esm.exception.MessageKey;
import com.galdovich.esm.exception.ResourcesNotFoundException;
import com.galdovich.esm.service.UserService;
import com.galdovich.esm.util.GiftConverter;
import com.galdovich.esm.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<UserDTO> getAll(PageDTO pageDTO) {
        Page page = GiftConverter.toPage(pageDTO);
        List<User> foundList = userDAO.getAll(page);
        return GiftConverter.toUserDTOList(foundList);
    }

    @Override
    public UserDTO getById(long id) throws ResourcesNotFoundException {
        Optional<User> foundUser = userDAO.getById(id);
        return foundUser.map(GiftConverter::toUserDTO).orElseThrow(() ->
                new ResourcesNotFoundException(MessageKey.USER_NOT_FOUND, String.valueOf(id)));
    }

    @Override
    public UserDTO add(UserDTO userDTO) throws AlreadyExistsException {
        return null;
    }

    @Override
    public UserDTO update(long id, UserDTO userDTO) {
        throw new UnsupportedOperationException(MessageKey.UNSUPPORTED_METHOD);
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException(MessageKey.UNSUPPORTED_METHOD);
    }
}
