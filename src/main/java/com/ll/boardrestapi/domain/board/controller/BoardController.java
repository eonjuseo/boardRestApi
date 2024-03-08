package com.ll.boardrestapi.domain.board.controller;

import com.ll.boardrestapi.domain.board.dto.BoardListResponse;
import com.ll.boardrestapi.domain.board.dto.BoardRequest;
import com.ll.boardrestapi.domain.board.dto.BoardResponse;
import com.ll.boardrestapi.domain.board.dto.BoardUpdateRequest;
import com.ll.boardrestapi.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(@RequestBody BoardRequest boardRequest) {
        return ResponseEntity.ok(boardService.createBoard(boardRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> readBoard(@PathVariable("id") long id) {
        return ResponseEntity.ok(boardService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponse> updateBoard(@PathVariable("id") long id,
                                              @RequestBody BoardUpdateRequest boardUpdateRequest) {
        boardService.updateBoard(id, boardUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok("게시물이 성공적으로 삭제되었습니다.");
    }

//    @GetMapping
//    public ResponseEntity<List<BoardResponse>> findAll() {
//        List<BoardResponse> boardList = boardService.findAll();
//        return ResponseEntity.ok(boardList);
//    }

    @GetMapping
    public ResponseEntity<List<BoardListResponse>> findByStatus() {
        List<BoardListResponse> boardList = boardService.findByStatus();
        return ResponseEntity.ok(boardList);
    }
}
