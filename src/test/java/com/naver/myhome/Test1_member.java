package com.naver.myhome;

import com.naver.myhome.domain.Member;
import com.naver.myhome.mybatis.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class Test1_member {

    private static final Logger logger = LoggerFactory.getLogger(Test1_member.class);

    @Autowired
    private MemberMapper dao;

    @Test
    public void insertMember() {
        Member member = new Member();
        member.setId("F1234");
        member.setPassword("1234");
        member.setName("고길동");
        member.setAge(30);
        member.setGender("남");
        member.setEmail("B1234@naver.com");

        dao.insert(member);

        logger.info("=== 삽입 완료 ===");
    }

    @Test
    public void getSearchListCount() {
        Map<String, String> map = new HashMap<>();

        int count = dao.getSearchListCount(map);
        logger.info("=== " + count + " 개 있어요 ===");
    }

    @Test
    public void getSearchListCount2() {
        Map<String, String> map = new HashMap<>();
        map.put("search_field", "id");
        map.put("search_word", "%a%");

        int count = dao.getSearchListCount(map);
        logger.info("=== " + count + " 개 있어요 ===");
    }

    @Test
    public void getSearchList() {
        Map<String, Object> map = new HashMap<>();
        map.put("start", 1);
        map.put("end", 10);

        List<Member> members = dao.getSearchList(map);
        for (Member member : members) {
            logger.info("=======================");
            logger.info("id: " + member.getId());
            logger.info("password: " + member.getPassword());
            logger.info("name: " + member.getName());
            logger.info("age: " + member.getAge());
            logger.info("gender: " + member.getGender());
            logger.info("email: " + member.getEmail());
        }
    }

    @Test
    public void getSearchList2() {
        Map<String, Object> map = new HashMap<>();
        map.put("search_field", "id");
        map.put("search_word", "%a%");
        map.put("start", 1);
        map.put("end", 10);

        List<Member> members = dao.getSearchList(map);
        for (Member member : members) {
            logger.info("=======================");
            logger.info("id: " + member.getId());
            logger.info("password: " + member.getPassword());
            logger.info("name: " + member.getName());
            logger.info("age: " + member.getAge());
            logger.info("gender: " + member.getGender());
            logger.info("email: " + member.getEmail());
        }
    }
}
