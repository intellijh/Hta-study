package com.naver.myhome.service;

import com.naver.myhome.domain.Board;

import java.util.List;

public interface BoardService {

    //글의 갯수 구하기
    int getListCount();

    //글 목록 보기
    List<Board> getBoardList(int page, int limit);

    //조회수 업데이트
    int setReadCountUpdate(int num);

    //글 내용 보기
    Board getDetail(int num);

    //글 등록하기
    void insertBoard(Board board);

    //글 삭제
    int boardDelete(int num);

    //글 수정
    int boardModify(Board board);

    //글쓴이인지 확인
    boolean isBoardWriter(int num, String pass);

    //글 답변
    int boardReply(Board board);

    //board_re_seq 값 수정
    int boardReplyUpdate(Board board);

    List<String> getDeleteFileList();

    void deleteFileList(String filename);
}
