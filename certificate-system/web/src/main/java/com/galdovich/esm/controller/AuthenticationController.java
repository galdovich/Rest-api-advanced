package com.galdovich.esm.controller;

import com.galdovich.esm.dto.RegisterDTO;
import com.galdovich.esm.dto.UserDTO;
import com.galdovich.esm.exception.ResourcesNotFoundException;
import com.galdovich.esm.dto.AuthenticationDTO;
import com.galdovich.esm.exception.JwtAuthenticationException;
import com.galdovich.esm.security.JwtTokenProvider;
import com.galdovich.esm.security.JwtUser;
import com.galdovich.esm.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class {@code AuthenticationController}
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO request) {
        try {
            String email = request.getEmail();
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));
            JwtUser user = (JwtUser) authentication.getPrincipal();
            String role = user.getAuthorities().stream().findAny().orElseThrow(ResourcesNotFoundException::new).toString();
            String token = jwtTokenProvider.createToken(email, role);
            return ResponseEntity.ok(Map.of("email", email, "token", token));
        } catch (JwtAuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(
            @RequestBody @Valid RegisterDTO registerDTO) {
        UserDTO createdUser = userService.register(registerDTO);
        String token = jwtTokenProvider.createToken(createdUser.getEmail(), createdUser.getRole().name());
        addLinks(createdUser);
        Map<String, Object> response = Map.of("user", createdUser, "token", token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private void addLinks(UserDTO userDTO) {
        userDTO.add(linkTo(methodOn(UserController.class)
                .getById(userDTO.getId())).withSelfRel());
    }


}
