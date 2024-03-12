package com.ll.boardrestapi.global.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionStatus {
    // 클라이언트의 잘못된 요청
    INVALID_INPUT(400,"유효하지 않은 입력입니다."), // 필드에 다른 형식을 입력했을때
    //    PASSWORDS_DO_NOT_MATCH(401,"비밀번호가 일치 하지 않습니다."),
//    INVALID_TOKEN(401,"유효하지 않은 토큰입니다."),
//    AUTHORIZATION_EXCEPTION(403,"접근할 수 있는 권한이 없습니다."),
//    REQUEST_IS_EMPTY(404,"요청이 존재하지 않습니다."),
    POST_IS_EMPTY(404,"해당 게시글이 존재하지 않습니다."),
    USER_IS_NOT_EXIST(404,"사용자가 존재하지 않습니다.");

    private final Integer statusCode;
    private final String message;
}