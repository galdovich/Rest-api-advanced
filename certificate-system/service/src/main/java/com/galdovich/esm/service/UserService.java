package com.galdovich.esm.service;

import com.galdovich.esm.dto.AuthenticationDTO;
import com.galdovich.esm.dto.RegisterDTO;
import com.galdovich.esm.dto.UserDTO;
import com.galdovich.esm.entity.User;

/**
 * Interface {@code UserService} describes abstract behavior for working
 * with {@link com.galdovich.esm.entity.User}
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public interface UserService extends GiftService<UserDTO> {

    /**
     * Gets user by name.
     *
     * @return user
     */
    User getByUserEmail(String email);

    UserDTO register(RegisterDTO registerDTO);
}
