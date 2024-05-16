CREATE USER boot2 IDENTIFIED BY tiger;
GRANT RESOURCE, CONNECT TO boot2;
DROP TABLE member CASCADE CONSTRAINTS;

CREATE TABLE member
(
    id       VARCHAR2(15),
    password VARCHAR2(60),
    name     VARCHAR2(15), -- 한글 5글자까지 가능
    age      NUMBER(2),
    gender   VARCHAR2(3),
    email    VARCHAR2(30),
    PRIMARY KEY (id)
);

SELECT *
FROM (SELECT b.*, ROWNUM rnum
      FROM (SELECT *
            FROM member
            WHERE id != 'admin'
                    ORDER BY id) b
              WHERE ROWNUM <= 10
              )
        WHERE rnum BETWEEN 1 AND 10