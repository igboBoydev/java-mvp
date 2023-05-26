package com.abelkelly.RequestSchema;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestData {
    private String refreshToken;
}
