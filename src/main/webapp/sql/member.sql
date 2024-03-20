DROP TABLE member CASCADE CONSTRAINTS purge;
-- 1. index.jsp에서 시작합니다.
-- 2. 관리자 계정 admin, 비번 1234를 만듭니다.
-- 3. 사용자 계정을 3개 만듭니다.

CREATE TABLE member
(
    id         VARCHAR2(12),
    password   VARCHAR2(10),
    name       VARCHAR2(15),
    age        NUMBER(2),
    gender     VARCHAR2(3), -- 남, 여
    email      VARCHAR2(30),
    memberfile VARCHAR2(50),
    PRIMARY KEY (id)
);

-- memberfile은 회원정보 수정 시 적용합니다.

INSERT INTO member
VALUES ('admin', '1234', '관리자', 0,
        '남', 'admin@admin.com', NULL);

SELECT *
FROM member;

UPDATE member
SET name = 'a', age = 1, email = 'a',