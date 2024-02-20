package com.ll.boardrestapi.domain.board.entity;

import com.ll.boardrestapi.domain.board.dto.BoardRequest;
import com.ll.boardrestapi.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
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

    public void update(BoardRequest boardRequest) {
        if (boardRequest.getTitle() != null) {
            this.title = boardRequest.getTitle();
        }
        if (boardRequest.getContent() != null) {
            this.content = boardRequest.getContent();
        }
    }

}
