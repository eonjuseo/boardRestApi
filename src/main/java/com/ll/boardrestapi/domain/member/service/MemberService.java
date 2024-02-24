package com.ll.boardrestapi.domain.member.service;

import com.ll.boardrestapi.domain.member.dto.JoinRequest;
import com.ll.boardrestapi.domain.member.dto.JoinResponse;
import com.ll.boardrestapi.domain.member.entity.Member;
import com.ll.boardrestapi.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public JoinResponse createMember(JoinRequest joinRequest) {
        Member member = JoinRequest.toEntity(joinRequest);

        memberRepository.save(member);
        return JoinResponse.of(member);
    }
}
