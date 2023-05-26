package com.abelkelly.RequestSchema;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UpdateCustomerRequest {
    private Integer Id;
    private String email;
}
