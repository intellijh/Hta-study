package com.naver.myhome;

import com.naver.myhome.domain.Board;
import com.naver.myhome.mybatis.mapper.BoardMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
    1. Junit이란 Java에서 독립된 단위테스트를 지원해주는 프레임워크입니다.
    2. 단위 테스트
    단위테스트는 하나의 모듈을 기준으로 독립적으로 진행되는 가장 작은 단위의 테스트입니다.
    하나의 모듈이란 각 계층에서의 하나의 기능 또는 메소드로 이해할 수 있습니다.
    하나의 기능이 올바르게 동작하는지를 독립적으로 테스트하는 것입니다.
* */

@SpringBootTest
class Spring4MvcApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(Spring4MvcApplicationTests.class);

    @Autowired
    private BoardMapper dao;

    @Test
    public void boardDelete() {
        Board board = new Board();
        board.setBoard_re_ref(3);
        board.setBoard_re_lev(0);
        board.setBoard_re_seq(0);

        int result = dao.boardDelete(board);
        if (result >= 1) {
            logger.info(result + "개 삭제되었습니다.");
        }
    }

}
