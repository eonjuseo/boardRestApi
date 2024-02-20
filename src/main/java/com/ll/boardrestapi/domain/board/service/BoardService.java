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

    public BoardResponse findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시물을 불러올 수 없습니다."));
        return BoardResponse.of(board);
    }

    @Transactional
    public BoardResponse createBoard(BoardRequest boardRequest) {
        Board board = BoardRequest.toEntity(boardRequest);

        boardRepository.save(board);
        return BoardResponse.of(board);
    }

    @Transactional
    public void updateBoard(long id, BoardRequest boardRequest) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시물을 불러올 수 없습니다."));

        board.update(boardRequest);
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(long id) {
//        Board board = boardRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("게시물을 불러올 수 없습니다."));

        boardRepository.deleteById(id);

    }
}
