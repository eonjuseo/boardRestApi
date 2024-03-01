package com.ll.boardrestapi.global.exception.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionStatus {
    // 클라이언트의 잘못된 요청
    INVALID_INPUT(400,"유효하지 않은 입력입니다."), // 필드에 유효하지 않은 형식을 입력했을 때
//    PASSWORDS_DO_NOT_MATCH(401,"비밀번호가 일치 하지 않습니다."),
//    INVALID_TOKEN(401,"유효하지 않은 토큰입니다."),
//    AUTHORIZATION_EXCEPTION(403,"접근할 수 있는 권한이 없습니다."),
//    REQUEST_IS_EMPTY(404,"요청이 존재하지 않습니다."),
    FORBIDDEN_POST(403, "비공개 게시글 입니다."),
    // L 403 FORBIDDEN, 사용자 인증은 되었지만, 권한이 없음
    //   404 NOT FOUND, 비활성화 상태인 것도 숨길 때
    NOT_FOUND_POST(404,"해당 게시글이 존재하지 않습니다."),
    NOT_FOUND_USER(404,"사용자가 존재하지 않습니다.");

    private final Integer statusCode;
    private final String message;
}