package com.ll.boardrestapi.domain.board.service;


import com.ll.boardrestapi.domain.board.dto.BoardRequest;
import com.ll.boardrestapi.domain.board.dto.BoardResponse;
import com.ll.boardrestapi.domain.board.entity.Board;
import com.ll.boardrestapi.domain.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponse createBoard(BoardRequest boardRequest) {
        Board board = BoardRequest.toEntity(boardRequest);

        boardRepository.save(board);
        return BoardResponse.of(board);
    }
}
