package com.ll.boardrestapi.domain.board.dto;

import com.ll.boardrestapi.domain.board.entity.Board;
import com.ll.boardrestapi.domain.board.entity.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListResponse {
    private long id;
    private String title;
    private String content;
    private BoardStatus boardStatus;
    private Long memberId;
    private String memberName;

    public static BoardListResponse of (Board board) {
        return BoardListResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardStatus(board.getBoardStatus())
                .memberId(board.getMember().getId())
                .memberName(board.getMember().getName())
                .build();
    }
}
