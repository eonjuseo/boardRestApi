package com.ll.boardrestapi.domain.board.entity;

import lombok.Getter;

@Getter
public enum BoardStatus {
    ENABLE(0L, "ENABLE"),
    DISABLE(1L, "DISABLE");

    private final Long num;
    private final String value;

    BoardStatus(Long num, String value) {
        this.num = num;
        this.value = value;
    }
}
