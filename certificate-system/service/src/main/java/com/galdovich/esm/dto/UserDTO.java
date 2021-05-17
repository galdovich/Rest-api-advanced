package com.galdovich.esm.dto;

import com.galdovich.esm.entity.Role;
import com.galdovich.esm.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO extends RepresentationModel<UserDTO> {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;

    public UserDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
