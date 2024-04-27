package com.naver.myhome.service;

import com.naver.myhome.domain.Comment;

import java.util.List;

public interface CommentService {

    //글의 갯수 구하기
    int getListCount(int board_num);

    List<Comment> getCommentList(int board_num, int page);

    int commentInsert(Comment comment);

    int commentUpdate(Comment comment);

    int commentDelete(int num);
}
