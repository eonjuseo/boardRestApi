package com.ll.boardrestapi.domain.board.controller;

import com.ll.boardrestapi.domain.board.dto.BoardRequest;
import com.ll.boardrestapi.domain.board.dto.BoardResponse;
import com.ll.boardrestapi.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {

    private final BoardService boardService;
    @PostMapping("/create")
    public ResponseEntity<BoardResponse> createBoard(@RequestBody BoardRequest boardRequest) {

        return new ResponseEntity<>(boardService.createBoard(boardRequest), HttpStatus.OK);
    }
}
