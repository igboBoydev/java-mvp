package com.abelkelly.RequestSchema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    @NotBlank(message = "User first name cannot be null")
    private final String firstName;
    @NotBlank(message = "User last name cannot be null")
    private final String lastName;
    @Email(message = "invalid email address")
    private final String email;
    @NotBlank(message = "Password cannot be empty")
    private final String password;
}
