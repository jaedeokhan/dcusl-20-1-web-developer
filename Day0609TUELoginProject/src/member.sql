CREATE TABLE member (
   id VARCHAR2(12) PRIMARY KEY,
   passwd VARCHAR2(12) NOT NULL,
   name VARCHAR2(15) NOT NULL,
   zip VARCHAR2(5) NOT NULL,
   addr1 VARCHAR2(50) NOT NULL,
   addr2 VARCHAR2(50) NOT NULL,
   email VARCHAR2(30) NOT NULL,
   age NUMBER NOT NULL,
   country VARCHAR2(15) NOT NULL
)

ALTER TABLE member MODIFY addr1 VARCHAR2(100);

SELECT * FROM member;

DROP TABLE zipCode;

CREATE TABLE zipCode(
   num NUMBER PRIMARY KEY,
   zip VARCHAR2(5) NOT NULL,
   do VARCHAR2(15) NOT NULL,
   si VARCHAR2(18) NOT NULL,
   ro VARCHAR2(18) NOT NULL,
   bunzi VARCHAR2(25) NOT NULL
)

CREATE SEQUENCE zipCode_seq;

INSERT INTO zipCode VALUES(zipCode_seq.nextVal, '11111', '경상북도', '경산시', '하양1로', '1번지');
INSERT INTO zipCode VALUES(zipCode_seq.nextVal, '22222', '경상북도', '경산시', '하양1로', '1번지');
INSERT INTO zipCode VALUES(zipCode_seq.nextVal, '33333', '경상북도', '경산시', '하양1로', '1번지');
INSERT INTO zipCode VALUES(zipCode_seq.nextVal, '44444', '경상북도', '경산시', '하양1로', '1번지');


