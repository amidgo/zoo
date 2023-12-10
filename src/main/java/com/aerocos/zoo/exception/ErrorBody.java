package com.aerocos.zoo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorBody {
    private String code;
    private String message;
}
