package com.ll.boardrestapi.domain.board.repository;

import com.ll.boardrestapi.domain.board.dto.BoardResponse;
import com.ll.boardrestapi.domain.board.entity.Board;
import com.ll.boardrestapi.domain.board.entity.BoardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    @Query("SELECT b.id, b.title, b.content, b.member.id, b.member.name " +
//            "FROM Board b " +
//            "WHERE b.boardStatus = :boardStatus")
//    List<Board> findByBoardStatus(@Param("boardStatus") BoardStatus boardStatus);

    List<Board> findByBoardStatus(BoardStatus boardStatus);
    Optional<Board>  findByIdAndBoardStatus(Long id, BoardStatus boardStatus);
}
