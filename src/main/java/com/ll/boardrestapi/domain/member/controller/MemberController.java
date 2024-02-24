package com.ll.boardrestapi.domain.member.controller;

import com.ll.boardrestapi.domain.board.dto.BoardRequest;
import com.ll.boardrestapi.domain.board.dto.BoardResponse;
import com.ll.boardrestapi.domain.member.dto.JoinRequest;
import com.ll.boardrestapi.domain.member.dto.JoinResponse;
import com.ll.boardrestapi.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<JoinResponse> createMember(@RequestBody JoinRequest joinRequest) {
        return ResponseEntity.ok(memberService.createMember(joinRequest));
    }
}
