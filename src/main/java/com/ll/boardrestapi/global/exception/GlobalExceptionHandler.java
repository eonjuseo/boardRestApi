package com.ll.boardrestapi.global.exception;

import com.ll.boardrestapi.global.exception.dto.ErrorResponse;
import com.ll.boardrestapi.global.exception.status.ExceptionStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice // 컨트롤러에서 발생하는 예외 처리 다 여기서
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<Object> handleCustomException(CustomException ex) {
        ExceptionStatus exceptionStatus = ex.getExceptionStatus();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("statusCode", exceptionStatus.getStatusCode());
        body.put("message", exceptionStatus.getMessage());

        return new ResponseEntity<>(body, HttpStatus.valueOf(exceptionStatus.getStatusCode()));
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<ExceptionStatus> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionStatus.INVALID_INPUT);
//        //ErrorResponse.of(400, "유효하지 않은 입력입니다.")
//    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handlerEtcException(RuntimeException e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//    }
}
