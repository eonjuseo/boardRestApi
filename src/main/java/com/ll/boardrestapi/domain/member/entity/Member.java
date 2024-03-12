package com.ll.boardrestapi.domain.member.entity;

import com.ll.boardrestapi.domain.board.entity.Board;
import com.ll.boardrestapi.domain.member.dto.JoinRequest;
import com.ll.boardrestapi.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "member")
    List<Board> boards = new ArrayList<>();

    public void update(JoinRequest joinRequest) {
        this.name = joinRequest.getName();
    }

    @PreRemove
    private void preRemove() {
        for (Board board : boards) {
            board.setMember(null);
        }
    }
}
