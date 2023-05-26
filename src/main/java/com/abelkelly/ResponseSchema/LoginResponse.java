package com.abelkelly.ResponseSchema;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class LoginResponse {
    private String authToken;

}
