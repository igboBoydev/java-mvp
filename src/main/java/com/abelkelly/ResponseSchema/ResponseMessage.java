package com.abelkelly.ResponseSchema;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ResponseMessage {
    private int statusCode;
    private String message;
}
