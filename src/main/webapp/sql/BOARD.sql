DROP TABLE board CASCADE CONSTRAINTS purge;

CREATE TABLE board
(
    board_num       NUMBER, -- 글 번호
    board_name      VARCHAR2(30), -- 작성자
    board_pass      VARCHAR2(30), -- 비밀번호
    board_subject   VARCHAR2(30), -- 제목
    board_contetn   VARCHAR2(30), -- 내용
    board_file      VARCHAR2(30), -- 첨부할 파일
    board_re_ref    NUMBER, -- 답변 글 작성시 참조
    board_re_lev    NUMBER, -- 답변 글의 길이
    board_re_seq    NUMBER, -- 답변 글의 순서
    board_readcount NUMBER, -- 글의 조회수
    boar_date       DATE DEFAULT SYSDATE,
    PRIMARY KEY (board_num)
);
