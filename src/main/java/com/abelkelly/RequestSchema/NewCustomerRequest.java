package com.abelkelly.RequestSchema;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class NewCustomerRequest {
    private final String name;
    private final String email;
    private final Integer age;
}
