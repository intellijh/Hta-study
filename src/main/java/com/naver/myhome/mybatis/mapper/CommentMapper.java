package com.naver.myhome.mybatis.mapper;

import com.naver.myhome.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {

    //글의 갯수 구하기
    int getListCount(int board_num);

    List<Comment> getCommentList(Map<String, Integer> map);

    int commentInsert(Comment comment);

    int commentUpdate(Comment comment);

    int commentDelete(int num);
}
