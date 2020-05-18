### 2020 0514 THU  

#### 1. DataBase

#### 1.1 Oracle DB USE

```
# 윈도우의 사용자로 접근하기.
$ sqlplus "/as sysdba"

# root 로 접근하기.
$ sqlplus system/비밀번호

# 계정 생성하는 방법
SQL> CREATE USER 이름 IDENTIFIED BY 비밀번호;

# 모든 권한 부여하기, 보통 connect, resource 두 개를 주면 웬만하면 다 된다.
# connect는 접근을 위한 권한
# resource는 테이블이나 객체를 다루는 권한이다.
SQL> GRANT CONNECT, RESOURCE TO 이름;

# 접속 종료
SQL> DISCONN

# 방금 설정한 계정으로 접근
SQL> CONN java/java
```

#### 1.2 Oracle 연동하기.
Windows => Show View => Other => Data Manangement => Data Source Explorer

#### 1.3 위의 설정을 마치고 eclipse 에서 접근하기.

