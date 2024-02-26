package com.ll.boardrestapi.global.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Integer statusCode;
    private String message;

    public static ErrorResponse of(Integer statusCode, String message) {
        return ErrorResponse.builder()
                .statusCode(statusCode)
                .message(message)
                .build();
    }
}
