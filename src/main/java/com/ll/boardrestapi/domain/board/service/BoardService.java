package com.ll.boardrestapi.domain.board.service;


import com.ll.boardrestapi.domain.board.dto.BoardListResponse;
import com.ll.boardrestapi.domain.board.dto.BoardRequest;
import com.ll.boardrestapi.domain.board.dto.BoardResponse;
import com.ll.boardrestapi.domain.board.dto.BoardUpdateRequest;
import com.ll.boardrestapi.domain.board.entity.Board;
import com.ll.boardrestapi.domain.board.entity.BoardStatus;
import com.ll.boardrestapi.domain.board.repository.BoardRepository;
import com.ll.boardrestapi.domain.member.dto.JoinRequest;
import com.ll.boardrestapi.domain.member.entity.Member;
import com.ll.boardrestapi.domain.member.repository.MemberRepository;
import com.ll.boardrestapi.global.exception.CustomException;
import com.ll.boardrestapi.global.exception.status.ExceptionStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardResponse findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new  CustomException(ExceptionStatus.POST_IS_EMPTY));
        return BoardResponse.of(board);
    }

    @Transactional
    public BoardResponse createBoard(BoardRequest boardRequest) {

        Board board = BoardRequest.toEntity(boardRequest);

        boardRepository.save(board);
        return BoardResponse.of(board);
    }

    @Transactional
    public void updateBoard(long id, BoardUpdateRequest boardUpdateRequest) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionStatus.POST_IS_EMPTY));

        board.update(boardUpdateRequest);
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(long id) {

        boardRepository.deleteById(id);
    }

    public List<BoardResponse> findAll() {
        List<BoardResponse> boardList = new ArrayList<>();

        boardRepository.findAll().forEach(i -> boardList.add(BoardResponse.of(i)));
        return boardList;
    }

    public List<BoardListResponse> findByStatus() {
        List<BoardListResponse> boardList = new ArrayList<>();

        boardRepository.findByBoardStatus(BoardStatus.ENABLE)
                .forEach(i -> boardList.add(BoardListResponse.of(i)));
        return boardList;
    }
}
