package com.naver.myhome.mybatis.mapper;

import com.naver.myhome.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/*
* Mapper 인터페이스란 매퍼 파일에 기재된 SQL을 호출하기 위한 인터페이스입니다.
* MyBatis-Spring은 Mapper 인터페이스를 이용해서 실제 SQL 처리가 되는 클래스를 자동으로 생성합니다.
* */
@Mapper
public interface BoardMapper {

    int getListCount();

    List<Board> getBoardList(HashMap<String, Integer> map);

    int setReadCountUpdate(int num);

    Board getDetail(int num);

    void insertBoard(Board board);

    int boardDelete(Board board);

    int boardModify(Board board);

    Board isBoardWriter(HashMap<String, Object> map);

    int boardReply(Board board);

    //board_re_seq 값 수정
    int boardReplyUpdate(Board board);

    List<String> getDeleteFileList();

    void deleteFileList(String filename);
}
