create table board(
num NUMBER primary key , -- 게시판 글 번호
writer varchar2(10) not null, -- 작성자(회원 아이디)
email varchar2(30), -- 이메일
subject varchar2(50) not null,-- 글제목
passwd varchar2(12) not null,--비밀번호
reg_date timestamp(6) not null,--작성일
readcount NUMBER default 0,--조회수
ref NUMBER not null,-- 글 그룹번호, 원글하고 답글을 동일하게 묶어주기 위해서 사용
re_step NUMBER not null, -- 같은 그룹에서 글 출력, 요 놈으로 sort를 한다.
re_level NUMBER not null, -- 답글 레벨, 들여쓰기 레벨을 접근하는 것 
content varchar2(4000) not null, -- 글 내용
ip varchar2(20) not null -- 
);

CREATE SEQUENCE board_seq;

