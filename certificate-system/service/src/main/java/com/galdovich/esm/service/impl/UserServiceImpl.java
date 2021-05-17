package com.galdovich.esm.service.impl;

import com.galdovich.esm.dao.UserDAO;
import com.galdovich.esm.dto.PageDTO;
import com.galdovich.esm.dto.RegisterDTO;
import com.galdovich.esm.dto.UserDTO;
import com.galdovich.esm.entity.Role;
import com.galdovich.esm.entity.User;
import com.galdovich.esm.exception.AlreadyExistsException;
import com.galdovich.esm.exception.MessageKey;
import com.galdovich.esm.exception.ResourcesNotFoundException;
import com.galdovich.esm.service.UserService;
import com.galdovich.esm.util.GiftConverter;
import com.galdovich.esm.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
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
                new ResourcesNotFoundException(MessageKey.USER_NOT_FOUND_BY_ID, String.valueOf(id)));
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

    @Override
    public User getByUserEmail(String email) {
        return userDAO.getUserByEmail(email)
                .orElseThrow(() -> new ResourcesNotFoundException(MessageKey.USER_NOT_FOUND_BY_EMAIL, email));
    }

    @Override
    @Transactional
    public UserDTO register(RegisterDTO registerDTO) {
        String email = registerDTO.getEmail();
        userDAO.getUserByEmail(email).ifPresent(r -> {
            throw new AlreadyExistsException(MessageKey.USER_ALREADY_EXISTS, registerDTO.getEmail());
        });
        User user = GiftConverter.toUser(registerDTO);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User s = userDAO.add(user);
        return GiftConverter.toUserDTO(userDAO.add(user));
    }
}
