package com.naver.myhome.service;

import com.naver.myhome.domain.Member;

import java.util.List;

public interface MemberService {

    int insert(Member member);

    int update(Member member);

    void delete(String id);

    int isId(String id);

    int isId(String id, String pass);

    Member member_info(String id);

    List<Member> getSearchList(int index, String search_word, int page, int limit);

    int getSearchListCount(int index, String search_word);
}
