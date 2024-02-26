package com.ll.boardrestapi.global.exception;

import com.ll.boardrestapi.global.exception.status.ExceptionStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{
    // 발생할 예외를 처리해 줄 런타임 예외를 상속받는 예외클래스
    private final ExceptionStatus exceptionStatus;
}
