CREATE USER boot3 IDENTIFIED BY tiger;
GRANT RESOURCE, CONNECT TO boot3;
DROP TABLE member CASCADE CONSTRAINTS;

CREATE TABLE member
(
    id       VARCHAR2(15),
    password VARCHAR2(60), -- 암호화를 하면 password가 60자 필요
    name     VARCHAR2(15), -- 한글 5글자까지 가능
    age      NUMBER(2),
    gender   VARCHAR2(3),
    email    VARCHAR2(30),
    auth VARCHAR2(50) NOT NULL, -- 회원의 권한을 저장하는 곳으로 기본값은 'ROLE_MEMBER'
    PRIMARY KEY (id)
);

UPDATE member
SET auth = 'ROLE_ADMIN'
WHERE id = 'admin';