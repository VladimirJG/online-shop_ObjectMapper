package com.example.onlineshop_ObjectMapper.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private LocalDateTime localDateTime;
}