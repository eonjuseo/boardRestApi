package com.ll.boardrestapi.domain.board.dto;

import com.ll.boardrestapi.domain.board.entity.Board;
import com.ll.boardrestapi.domain.board.entity.BoardStatus;
import com.ll.boardrestapi.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.ll.boardrestapi.domain.board.entity.BoardStatus.ENABLE;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private BoardStatus boardStatus;
//    private Member member;
    private Long memberId;
    private String memberName;

    public static BoardResponse of (Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardStatus(board.getBoardStatus())
                .memberId(board.getMember().getId())
                .memberName(board.getMember().getName())
                .build();
    }
}
