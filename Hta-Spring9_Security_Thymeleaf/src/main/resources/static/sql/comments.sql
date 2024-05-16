CREATE TABLE comments
(
    num       NUMBER PRIMARY KEY,
    id        VARCHAR2(30) REFERENCES member (id),
    content   VARCHAR2(200),
    reg_date  DATE,
    board_num NUMBER REFERENCES board (board_num) ON DELETE CASCADE -- comm 테이블이 참조
);

CREATE SEQUENCE com_seq;