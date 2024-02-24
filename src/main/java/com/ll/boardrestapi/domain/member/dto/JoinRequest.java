package com.ll.boardrestapi.domain.member.dto;

import com.ll.boardrestapi.domain.board.entity.Board;
import com.ll.boardrestapi.domain.member.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequest {
    private String name;

    public static Member toEntity(JoinRequest joinRequest) {
        return Member.builder()
                .name(joinRequest.getName())
                .build();
    }
}
