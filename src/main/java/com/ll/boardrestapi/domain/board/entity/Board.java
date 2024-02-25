package com.ll.boardrestapi.domain.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ll.boardrestapi.domain.board.dto.BoardUpdateRequest;
import com.ll.boardrestapi.domain.member.entity.Member;
import com.ll.boardrestapi.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseEntity {

    private String title;
    private String content;

    //@Convert(converter = BoardStatusConverter.class)
    //@Enumerated(EnumType.ORDINAL) // Enum 선언 순서를 int로 변환해 db에 numeric으로 저장
    private BoardStatus boardStatus;

    public void update(BoardUpdateRequest boardUpdateRequest) {
        this.content = boardUpdateRequest.getContent();
        this.title = boardUpdateRequest.getTitle();
        this.boardStatus = boardUpdateRequest.getBoardStatus();
    }

}
