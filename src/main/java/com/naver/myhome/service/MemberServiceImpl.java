package com.naver.myhome.service;

import com.naver.myhome.domain.Member;
import com.naver.myhome.mybatis.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberMapper dao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberMapper dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public int insert(Member member) {
        return dao.insert(member);
    }

    @Override
    public int update(Member member) {
        return dao.update(member);
    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }

    @Override
    public int isId(String id) {
        Member member = dao.isId(id);
        return member == null ? -1 : 1; //-1 : 아이디 존재 X
                                        // 1 : 아이디 존재
    }

    @Override
    public int isId(String id, String password) {
        int result = -1; //아이디 존재 X

        Member member = dao.isId(id);
        if (member != null) {
            //passwordEncoder.matches(rawPassword, encodedPassword)
            //사용자에게 입력받은 패스워드를 비교하고자 할 때 사용하는 메서드입니다.
            //rawPassword : 사용자가 입력한 패스워드
            //encodedPassword : DB에 저장된 패스워드
            if (passwordEncoder.matches(password, member.getPassword())) {
                result = 1; //아이디 비밀번호 일치
            } else {
                result = 0; //비밀번호 불일치
            }
        }
        return result;
    }

    @Override
    public Member member_info(String id) {
        return dao.isId(id);
    }

    @Override
    public List<Member> getSearchList(int index, String search_word, int page, int limit) {
        Map<String, Object> map = new HashMap<>();

        if (index != -1) {
            String[] search_field = {"id", "name", "age", "gender"};
            map.put("search_field", search_field[index]);
            map.put("search_word", "%" + search_word + "%");
        }
        int startrow = (page - 1) * limit + 1;
        int endrow = startrow + limit - 1;
        map.put("start", startrow);
        map.put("end", endrow);
        return dao.getSearchList(map);
    }

    @Override
    public int getSearchListCount(int index, String search_word) {
        Map<String, String> map = new HashMap<>();

        if (index != -1) {
            String[] search_field = {"id", "name", "age", "gender"};
            map.put("search_field", search_field[index]);
            map.put("search_word", "%" + search_word + "%");
        }
        return dao.getSearchListCount(map);
    }
}
