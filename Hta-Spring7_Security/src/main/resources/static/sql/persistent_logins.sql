CREATE TABLE persistent_logins
(
    username  VARCHAR(64),
    series    VARCHAR(64), -- 기기, 브라우저별 쿠키를 구분할 고유값
    token     VARCHAR(64), -- 브라우저가 가지고 있는 쿠키의 값을 검증할 인증값
    last_used TIMESTAMP -- 가장 최신 자동 로그인 시간
);
