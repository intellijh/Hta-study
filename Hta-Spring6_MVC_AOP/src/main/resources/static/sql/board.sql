CREATE TABLE board
(
    board_num       NUMBER,         -- 글 번호
    board_name      VARCHAR2(30),   -- 작성자
    board_pass      VARCHAR2(30),   -- 비밀번호
    board_subject   VARCHAR2(300),  -- 제목
    board_content   VARCHAR2(4000), -- 내용
    board_file      VARCHAR2(50),   -- 첨부파일명(가공)
    board_original  VARCHAR2(50),   -- 첨부파일명
    board_re_ref    NUMBER,         -- 답변 글 작성시 참조
    board_re_lev    NUMBER,         -- 답변 글의 길이
    board_re_seq    NUMBER,         -- 답변 글의 순서
    board_readcount NUMBER,         -- 글의 조회수
    board_date      DATE DEFAULT SYSDATE,
    PRIMARY KEY (board_num)
);

SELECT *
FROM (SELECT ROWNUM rnum, b.*
      FROM (SELECT board.*, NVL(cnt, 0) cnt
            FROM board, (SELECT board_num, COUNT(num) AS cnt
                         FROM comments
                         GROUP BY board_num) a
            WHERE board.board_num = a.board_num(+)
            ORDER BY board_re_ref DESC, board_re_seq) b
      WHERE ROWNUM <= 10)
        WHERE rnum >= 1
          AND rnum <= 10;