package com.ll.boardrestapi.domain.member.service;

import com.ll.boardrestapi.domain.member.dto.JoinRequest;
import com.ll.boardrestapi.domain.member.dto.JoinResponse;
import com.ll.boardrestapi.domain.member.entity.Member;
import com.ll.boardrestapi.domain.member.repository.MemberRepository;
import com.ll.boardrestapi.global.exception.CustomException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ll.boardrestapi.global.exception.status.ExceptionStatus;


import java.util.ArrayList;
import java.util.List;

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

    public JoinResponse findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionStatus.NOT_FOUND_USER));
        return JoinResponse.of(member);
    }

    @Transactional
    public void updateMember(long id, JoinRequest joinRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionStatus.NOT_FOUND_USER));

        member.update(joinRequest);
        memberRepository.save(member);
    }

    @Transactional
    public void deleteMember(long id) {
        memberRepository.deleteById(id);
    }

    public List<JoinResponse> findAll() {
        List<JoinResponse> memberList = new ArrayList<>();

        memberRepository.findAll().forEach(i -> memberList.add(JoinResponse.of(i)));
        return memberList;
    }
}
