package com.ll.boardrestapi.domain.board.controller;

import com.ll.boardrestapi.domain.board.entity.Board;
import com.ll.boardrestapi.domain.board.entity.BoardStatus;
import com.ll.boardrestapi.domain.board.repository.BoardRepository;
import com.ll.boardrestapi.domain.member.entity.Member;
import com.ll.boardrestapi.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@Transactional
@SpringBootTest
public class BoardControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    @Transactional
    void beforeEach() {
        Member member = Member.builder()
                .name("test name")
                .build();
        memberRepository.save(member);

        Board board = Board.builder()
                .title("test title")
                .content("test content")
                .boardStatus(BoardStatus.ENABLE)
                .member(member)
                .build();
        boardRepository.save(board);
    }

    @AfterEach
    @Transactional
    void afterEach() {
        boardRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @DisplayName("목록 조회 성공")
    @Test
    public void getBoardsTest() throws Exception{
        ResultActions resultActions = mvc
                .perform(get("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BoardController.class))
                .andExpect(handler().methodName("getBoards"))
                .andExpect(jsonPath("$", instanceOf(List.class)))
                .andExpect(jsonPath("$.data[0].title", notNullValue()))
                .andExpect(jsonPath("$.data[0].content", notNullValue()))
                .andExpect(jsonPath("$.data[0].boardStatus", notNullValue()))
                .andExpect(jsonPath("$.data[0].member.name", notNullValue()));
    }


}
