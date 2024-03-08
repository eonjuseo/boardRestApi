package com.ll.boardrestapi.domain.board.service;

import com.ll.boardrestapi.domain.board.dto.BoardListResponse;
import com.ll.boardrestapi.domain.board.dto.BoardRequest;
import com.ll.boardrestapi.domain.board.dto.BoardResponse;
import com.ll.boardrestapi.domain.board.dto.BoardUpdateRequest;
import com.ll.boardrestapi.domain.board.entity.Board;
import com.ll.boardrestapi.domain.board.entity.BoardStatus;
import com.ll.boardrestapi.domain.board.repository.BoardRepository;
import com.ll.boardrestapi.domain.member.entity.Member;
import com.ll.boardrestapi.global.exception.CustomException;
import com.ll.boardrestapi.global.exception.status.ExceptionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
public class BoardServiceTest {

    // TODO
    // 목록 조회 실패 - 목록 없음 / 다 비공개
    // 등록 실패 - 찾을 수 없는 게시글 ?
    // 삭제 성공
    // 삭제 실패

    @InjectMocks
    private BoardService boardService;
    @Mock
    private BoardRepository boardRepository;

    private final Long boardId = 1L;
    private final String title = "test title 1";
    private final String content = "test content 1";
    private final BoardStatus boardStatusEnable = BoardStatus.ENABLE;
    private final BoardStatus boardStatusDisable = BoardStatus.DISABLE;

    private final Long memberId = 1L;
    private final String memberName = "사용자 1";


    private final String updateTitle = "test title update";
    private final String updateContent = "test content update";
    private final BoardStatus updateBoardStatusEnable = BoardStatus.ENABLE;
    private final BoardStatus updateBoardStatusDisable = BoardStatus.DISABLE;

    @DisplayName("목록 조회 성공")
    @Test
    public void getBoardsTest() {
        // given
        when(this.boardRepository.findByBoardStatus(boardStatusEnable)).thenReturn(this.getBoards());

        // when
        List<BoardListResponse> boards = this.boardService.findByStatus();

        // then
        assertThat(boards).isInstanceOf(List.class);
        assertThat(boards.get(0).getId()).isEqualTo(boardId); // get(0) 리스트의  요소부터 반환
        assertThat(boards.get(0).getTitle()).isEqualTo(title);
        assertThat(boards.get(0).getContent()).isEqualTo(content);
        assertThat(boards.get(0).getBoardStatus()).isEqualTo(boardStatusEnable);
        assertThat(boards.get(0).getMemberId()).isEqualTo(memberId);
        assertThat(boards.get(0).getMemberName()).isEqualTo(memberName);
    }

    @DisplayName("상세 조회 성공")
    @Test
    public void getBoardTest() {
        // given
        when(this.boardRepository.findById(any())).thenReturn(this.getBoardOne());

        // when
        BoardResponse boardResponse = this.boardService.findById(boardId);

        // then
        assertThat(boardResponse.getId()).isEqualTo(boardId); // get(0) 리스트의 첫번째 요소부터 반환
        assertThat(boardResponse.getTitle()).isEqualTo(title);
        assertThat(boardResponse.getContent()).isEqualTo(content);
        assertThat(boardResponse.getBoardStatus()).isEqualTo(boardStatusEnable);
        assertThat(boardResponse.getMemberId()).isEqualTo(memberId);
        assertThat(boardResponse.getMemberName()).isEqualTo(memberName);
    }

    @DisplayName("상세 조회 실패 - 비공개 상태")
    @Test
    public void getBoardDisableTest() {
        // given
        // 조회하려는 게시글이 비공개임을 모의
        Board disabledBoard = this.getBoardOne().get();
        disabledBoard = disabledBoard.toBuilder()
                        .boardStatus(boardStatusDisable)
                        .build();
        when(this.boardRepository.findById(boardId)).thenReturn(Optional.of(disabledBoard));

        // when
        // findById를 호출했을 때 CustomException이 발생하는지 검증
        // 게시글이 존재하지 않을 때 예외 처리가 제대로 동작하는지 확인
        CustomException exception = assertThrows(CustomException.class, () -> {
            boardService.findById(boardId);
        });

        // then
        // (예상값, 발생한 상태 코드값)
        assertEquals(ExceptionStatus.FORBIDDEN_POST.getStatusCode(), exception.getExceptionStatus().getStatusCode());
        assertEquals(ExceptionStatus.FORBIDDEN_POST.getMessage(), exception.getExceptionStatus().getMessage());
    }

    @DisplayName("상세 조회 실패 - 찾을 수 없는 게시글 - 없는 번호")
    @Test
    public void getBoardNotFoundTest() {
        // given
        // 어떤 값이든 empty 출력
        // 조회하려는 게시글이 존재하지 않음을 모의
        when(this.boardRepository.findById(any())).thenReturn(Optional.empty());

        // when
        // findById를 호출했을 때 CustomException이 발생하는지 검증
        // 게시글이 존재하지 않을 때 예외 처리가 제대로 동작하는지 확인
        CustomException exception = assertThrows(CustomException.class, () -> {
             boardService.findById(boardId);
        });

        // then
        // (예상값, 발생한 상태 코드값)
        assertEquals(ExceptionStatus.NOT_FOUND_POST.getStatusCode(), exception.getExceptionStatus().getStatusCode());
        assertEquals(ExceptionStatus.NOT_FOUND_POST.getMessage(), exception.getExceptionStatus().getMessage());
    }

    @DisplayName("등록 성공")
    @Test
    public void createBoardTest() {
        // given 준비
        BoardRequest boardRequest = BoardRequest.builder()
                .title(title)
                .content(content)
                .boardStatus(boardStatusEnable)
                .member(
                        Member.builder()
                                .id(memberId)
                                .name(memberName)
                                .build()
                )
                .build();
        // 메서드 모의
        when(boardRepository.save(any(Board.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When 실행
        BoardResponse boardResponse = boardService.createBoard(boardRequest);

        // then 검증
        assertThat(boardResponse.getTitle()).isEqualTo(title);
        assertThat(boardResponse.getContent()).isEqualTo(content);
        assertThat(boardResponse.getBoardStatus()).isEqualTo(boardStatusEnable);
        assertThat(boardResponse.getMemberId()).isEqualTo(memberId);
        assertThat(boardResponse.getMemberName()).isEqualTo(memberName);
    }

    @DisplayName("수정 성공")
    @Test
    public void updateBoardTest() {
        // given 준비
        BoardUpdateRequest boardUpdateRequest = BoardUpdateRequest.builder()
                .title(updateTitle)
                .content(updateContent)
                .boardStatus(updateBoardStatusEnable)
                .build();

        // 메서드 모의
        when(boardRepository.findById(boardId)).thenReturn(this.getBoardOne());
        when(boardRepository.save(any(Board.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When 실행
        BoardResponse boardResponse = boardService.updateBoard(boardId, boardUpdateRequest);

        // then 검증
        assertThat(boardResponse.getTitle()).isEqualTo(updateTitle);
        assertThat(boardResponse.getContent()).isEqualTo(updateContent);
        assertThat(boardResponse.getBoardStatus()).isEqualTo(updateBoardStatusEnable);
    }

    @DisplayName("수정 실패 - 찾을 수 없는 게시글")
    @Test
    public void updateBoardNotFoundTest() {
        // given 준비
        BoardUpdateRequest boardUpdateRequest = BoardUpdateRequest.builder()
                .title(updateTitle)
                .content(updateContent)
                .boardStatus(updateBoardStatusEnable)
                .build();

        // 메서드 모의
        when(boardRepository.findById(boardId)).thenReturn(Optional.empty());

        // When 실행
        CustomException exception = assertThrows(CustomException.class, () -> {
            boardService.updateBoard(boardId, boardUpdateRequest);
        });

        // then
        // (예상값, 발생한 상태 코드값)
        assertEquals(ExceptionStatus.NOT_FOUND_POST.getStatusCode(), exception.getExceptionStatus().getStatusCode());
        assertEquals(ExceptionStatus.NOT_FOUND_POST.getMessage(), exception.getExceptionStatus().getMessage());
    }


    //---------------------------------------
    @DisplayName("삭제 성공")
    @Test
    public void deleteBoardTest() {
        // when
        boardService.deleteBoard(boardId);

        // then
        verify(boardRepository).deleteById(boardId);
    }
    @DisplayName("삭제 실패 - 찾을 수 없는 게시글")
    @Test
    public void deleteBoardNotFoundTest() {
        // given 준비
        // 메서드 모의
        Mockito.when(boardRepository.findById(any())).thenReturn(Optional.empty());

        // When 실행
        CustomException exception = assertThrows(CustomException.class, () -> {
            boardService.deleteBoard(boardId);
        });

        // then
        // (예상값, 발생한 상태 코드값)
        assertEquals(ExceptionStatus.NOT_FOUND_POST.getStatusCode(), exception.getExceptionStatus().getStatusCode());
        assertEquals(ExceptionStatus.NOT_FOUND_POST.getMessage(), exception.getExceptionStatus().getMessage());
    }


    //===========================================

    private List<Board> getBoards() {
        return List.of(this.getBoardData());
    }
    private Optional<Board> getBoardOne() {
        return Optional.of(this.getBoardData());
    }
    private Board getBoardData() {
        Member member = getMemberData();

        return Board.builder()
                .id(boardId)
                .title(title)
                .content(content)
                .boardStatus(boardStatusEnable)
                .member(member)
                .build();
    }
    private Member getMemberData() {
        return Member.builder()
                .id(memberId)
                .name(memberName)
                .build();
    }

}
