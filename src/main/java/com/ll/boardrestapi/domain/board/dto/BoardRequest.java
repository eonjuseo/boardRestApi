package com.ll.boardrestapi.domain.board.dto;

import com.ll.boardrestapi.domain.board.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {

    private String title;
    private String content;

    public static Board toEntity(BoardRequest boardRequest) {
        return Board.builder()
                .title(boardRequest.getTitle())
                .content(boardRequest.getContent())
                .build();
    }
}
