package com.ll.boardrestapi.domain.member.controller;

import com.ll.boardrestapi.domain.member.dto.JoinRequest;
import com.ll.boardrestapi.domain.member.dto.JoinResponse;
import com.ll.boardrestapi.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<JoinResponse> createMember(@RequestBody JoinRequest joinRequest) {
        return ResponseEntity.ok(memberService.createMember(joinRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JoinResponse> readMember(@PathVariable("id") long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable("id") long id,
                                            @RequestBody JoinRequest joinRequest) {
        memberService.updateMember(id, joinRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("사용자가 성공적으로 삭제되었습니다.");
    }

    //memberId가 null 일 경우 불러오지 못함
    @GetMapping
    public ResponseEntity<List<JoinResponse>> findAll() {
        List<JoinResponse> memberList = memberService.findAll();
        return ResponseEntity.ok(memberList);
    }
}
