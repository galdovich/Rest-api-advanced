package com.galdovich.esm.dto;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Class {@code AuthenticationDTO}
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "[\\w\\d]{4,64}")
    private String password;
}
