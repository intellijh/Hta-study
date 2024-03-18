DROP TABLE comm CASCADE CONSTRAINTS PURGE;
CREATE TABLE comm
(
    num               NUMBER PRIMARY KEY,
    id                VARCHAR2(30) REFERENCES member (id),
    content           VARCHAR2(200),
    reg_date          DATE,
    comment_board_num NUMBER REFERENCES board (board_num) ON DELETE CASCADE, -- comm 테이블이 참조
    comment_re_lev    NUMBER(1) CHECK ( comment_re_lev IN (0, 1, 2) ),       -- 원문이면 0 답글이면 1
    comment_re_seq    NUMBER,                                                -- 원문이면 0, 1레벨이면 1레벨 시퀀스 + 1
    comment_re_ref    NUMBER                                                 --원문은 자신 글번호, 답글이면 원문 글번호
);
-- 게시글이 삭제되면 참조하는 댓글도 삭제됩니다.

DROP SEQUENCE com_seq;

-- 시퀀스를 생성합니다.
CREATE SEQUENCE com_seq;

DELETE comm;

SELECT * FROM comm;