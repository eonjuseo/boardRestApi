package com.ll.boardrestapi.domain.board.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
// 해당 변환 클래스에 지정된 타입에 대해 모두 클래스의 메소드를 이용해 db와 통신에서 값을 변환
public class BoardStatusConverter implements AttributeConverter<BoardStatus, Long> {

    // ENUM -> DB에 어떤 값으로 넣을 것인지 정의
    @Override
    public Long convertToDatabaseColumn(BoardStatus boardStatus) {
        if(boardStatus == null) {
            return null;
        }
        return boardStatus.getNum();
    }


    // DB에 대한 ENUM 어떻게 매칭 시킬지 정의
    @Override
    public BoardStatus convertToEntityAttribute(Long num) {
        if(num == null) {
            return null;
        }
        if (num == 0L) {
            return BoardStatus.ENABLE;
        } else if (num == 1L) {
            return BoardStatus.DISABLE;
        }
        throw new IllegalStateException("Unexpected value: " + num);
    }
}
