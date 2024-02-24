package com.ll.boardrestapi.domain.member.dto;

import com.ll.boardrestapi.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinResponse {
    private long id;
    private String name;

    public static JoinResponse of(Member member) {
        return JoinResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }
}
