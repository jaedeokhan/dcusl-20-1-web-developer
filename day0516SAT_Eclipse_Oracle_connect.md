### 2020 0516 SAT 

#### 1. Eclipse와 Oracle Database jdbc 연결하기.

#### 1.1 Eclipse 에서 

#### 1.1.1 DataBase Connect => new 하고, jar 11 선택하기.
![test1](https://user-images.githubusercontent.com/45028904/82106063-ce76d800-9759-11ea-95db-5b13cc656d70.png)

* 선택하고 나면 아래와 같이 나온다.
![image](https://user-images.githubusercontent.com/45028904/82106267-dedb8280-975a-11ea-9eb7-6a15ec25dd21.png)

#### 1.1.2 Properties 에서 Connection URL, Password, User ID 항목을 수정하기
* Coonection URL 에서 localhost는 자신의 컴퓨터 아이피인 127.0.0.1 을 나타낸다. XE는 Oracle Server이다.
* Password : 기존에 CREATE USER해준 java
* User ID : 마찬가지로 설정해준 java로 한다.
![image](https://user-images.githubusercontent.com/45028904/82106289-f581d980-975a-11ea-8943-4f17131fce1c.png)

#### 1.1.3 Next 하고나서 , Test Connection 테스트하기.
![image](https://user-images.githubusercontent.com/45028904/82106365-632e0580-975b-11ea-89d0-3521d9ccafe2.png)

* Test Connection 하기.
![image](https://user-images.githubusercontent.com/45028904/82106370-70e38b00-975b-11ea-863b-501a1bea3130.png)

* 위와 같이 Ping succeeded! 가 나와야 한다.

