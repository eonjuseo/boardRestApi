package com.ll.boardrestapi.domain.board.dto;

import com.ll.boardrestapi.domain.board.entity.Board;
import com.ll.boardrestapi.domain.board.entity.BoardStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequest {

    private String title;
    private String content;
    private BoardStatus boardStatus;

    public static Board toEntity(BoardUpdateRequest boardUpdateRequest) {
        return Board.builder()
                .title(boardUpdateRequest.getTitle())
                .content(boardUpdateRequest.getContent())
                .boardStatus(boardUpdateRequest.getBoardStatus())
                .build();
    }
}
