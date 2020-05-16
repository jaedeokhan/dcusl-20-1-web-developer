-- 간단한 SQL 구문 실행 
-- 테이블 생성

CREATE TABLE member (
	id VARCHAR2(12) PRIMARY KEY,
	name VARCHAR2(15) NOT NULL,
	addr VARCHAR2(50) NOT NULL,
	passwd VARCHAR2(12) NOT NULL
);

-- 데이블의 데이터를 조회 SELECT
-- SELECT 속성 FROM 테이블명;
SELECT id, name FROM member;

-- 모든 컬럼의 값을 가져올 때는 컬럼명 대신 *를 사용하면 된다.
SELECT * FROM member;
