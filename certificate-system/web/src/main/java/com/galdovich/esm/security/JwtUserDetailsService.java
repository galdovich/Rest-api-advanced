package com.galdovich.esm.security;

import com.galdovich.esm.dao.impl.UserDAOImpl;
import com.galdovich.esm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class {@code JwtUserDetailsService}
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    private final UserDAOImpl userDAO;

    @Autowired
    public JwtUserDetailsService(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.getUserByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return JwtUser.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .authorities(user.getRole().getAuthorities())
                .password(user.getPassword())
                .enabled(true)
                .build();
    }
}
