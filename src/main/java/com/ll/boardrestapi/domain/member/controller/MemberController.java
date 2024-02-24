package com.ll.boardrestapi.domain.member.controller;

import com.ll.boardrestapi.domain.member.dto.JoinRequest;
import com.ll.boardrestapi.domain.member.dto.JoinResponse;
import com.ll.boardrestapi.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<JoinResponse> createMember(@RequestBody JoinRequest joinRequest) {
        return ResponseEntity.ok(memberService.createMember(joinRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JoinResponse> readMember(@PathVariable("id") long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable("id") long id,
                                            @RequestBody JoinRequest joinRequest) {
        memberService.updateMember(id, joinRequest);
        return ResponseEntity.ok().build();
    }
}
