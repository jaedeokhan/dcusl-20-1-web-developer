CREATE TABLE member1 (
	id VARCHAR2(12) PRIMARY KEY,
	passwd VARCHAR2(12) NOT NULL,
	name VARCHAR2(15) NOT NULL,
	email VARCHAR2(30) NOT NULL,
	addr VARCHAR2(100) NOT NULL,
	age NUMBER NOT NULL,
	gender CHAR(1) NOT NULL,
	tell VARCHAR2(20) NOT NULL
);
