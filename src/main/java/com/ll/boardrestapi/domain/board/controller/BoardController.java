package com.ll.boardrestapi.domain.board.controller;

import com.ll.boardrestapi.domain.board.dto.BoardRequest;
import com.ll.boardrestapi.domain.board.dto.BoardResponse;
import com.ll.boardrestapi.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> readBoard(@PathVariable long id) {
        return new ResponseEntity<>(boardService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BoardResponse> createBoard(@RequestBody BoardRequest boardRequest) {

        return new ResponseEntity<>(boardService.createBoard(boardRequest), HttpStatus.OK);
    }
}
