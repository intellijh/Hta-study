-- jumin은 앞자리와 '-'와 뒷자리로 구성합니다.
-- email은 아이디@도메인으로 구성합니다.
-- hobby는 여러 개의 취미를 콤마로 구분합니다.
-- gender의 value값을 m 또는 f 로 변경합니다.
-- post는 0으로 시작하는 우편번호가 있어 문자형으로 처리합니다. (01234를 숫자형으로 저장하면 1234만 저장됩니다.)

CREATE TABLE template_join
(
    id       VARCHAR2(20) PRIMARY KEY,
    password VARCHAR2(20),
    jumin    VARCHAR2(14),
    email    VARCHAR2(30),
    gender   CHAR(1) CHECK ( gender IN ('m', 'f') ),
    hobby    VARCHAR2(40),
    post     VARCHAR2(5),
    address  VARCHAR2(150),
    intro    VARCHAR2(100),
    register_date DATE DEFAULT SYSDATE
);

INSERT INTO template_join (id, password, jumin, email, gender, hobby, post, address, intro)
VALUES ('admin', '1234', '900909-1234567', 'admin@naver.com', 'm',
        '공부,운동', '12345', '서울시 종로구', '좋아요');

UPDATE template_join
SET password = '11', jumin = '901011-1111111', email = 'aa@naver.com', gender = 'f',
    hobby = '공부', post = '11111', address = '서울', intro = '좋아'
WHERE id = 'Bb1234';

SELECT *
                            FROM template_join
                            WHERE id != 'admin'
                            ORDER BY register_date DESC;