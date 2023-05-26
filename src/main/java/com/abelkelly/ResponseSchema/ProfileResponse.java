package com.abelkelly.ResponseSchema;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProfileResponse {
    private String firstName;
    private String lastName;
    private String email;
}
