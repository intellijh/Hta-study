package com.naver.myhome.mybatis.mapper;

import com.naver.myhome.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    Member isId(String id);

    int insert(Member member);

    int update(Member member);

    int delete(String id);

    int getSearchListCount(Map<String, String> map);

    List<Member> getSearchList(Map<String, Object> map);
}
