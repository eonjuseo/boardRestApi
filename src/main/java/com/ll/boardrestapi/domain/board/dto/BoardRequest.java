package com.ll.boardrestapi.domain.board.dto;

import com.ll.boardrestapi.domain.board.entity.Board;
import com.ll.boardrestapi.domain.board.entity.BoardStatus;
import com.ll.boardrestapi.domain.member.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {

    private String title;
    private String content;
    private BoardStatus boardStatus;
    private Member member;


    public static Board toEntity(BoardRequest boardRequest) {
        return Board.builder()
                .title(boardRequest.getTitle())
                .content(boardRequest.getContent())
                .boardStatus(boardRequest.getBoardStatus())
                .member(boardRequest.getMember())
                .build();
    }

//    public static String getMemberName(BoardRequest boardRequest) {
//        return boardRequest.getMember().getName();
//    }
}
