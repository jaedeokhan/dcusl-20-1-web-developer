### 2020 0525 MON
### Today
1. Servlet
   * statment vs preparedStatement 의 차이점
      * prepareStatement(sql) 문을 가지고 실행을 한다. => pstmt = conn.prepareStatement(sql);
      * statment 는 일단 생성하고 나중에 executeUpdate(sql)을 사용한다. => stmt = conn.preapreStatment(sql)
2. CSS
3. JSP


#### 1. 회원가입 페이지 구현하기.

#### 2. 

일반 태그, id, class 태그 이용해서 꾸미기

> ex.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	p {
	color : blue;
	}

	#use_id {
		color : yellow;
	}

	.use_class {
		color : green;
	}

	ul > p {
		color : red;
	}
</style>
</head>
<body>
	<p>일반 태그를 사용</p>
	<p id="use_id">id 선택자를 사용</p>
	<p class="use_class">Class 선택자를 사용</p>
	<ul>
		<p>ul태그 안</p>
		<p>안에 있어요</p>
	</ul>
</body>
</html>
```

> ex2.html

마찬가지!!

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<style type="text/css">  
    h1 {
        color : red;
    }
</style>
</head>
<body>
    <h1 class="logo">서울특별시</h1>
    <p>주소록을 작성합니다.</p>
    <h1>전라도</h1>
    <p>전화번호부를 작성합니다</p>
</body>
</html>
```

> ex3.html

a:hover 즉 마우스를 올려놓았을때 색상이 변경!

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<style type="text/css">
    a { color : red;}
    a:hover { color : blue};
</style>
</head>
<body>
    <ul>
        <li><a href="#">COMPANY</a></li>
        <li><a href="#">PRODUCT</a></li>
        <li><a href="#">SERVICE</a></li>
    </ul>
</body>
</html>
```

> ex4.html
.box:after => box class 뒤에 content를 작성하고 color 설정
p:frist-letter : p 태그의 첫 번째!
p:last-child :  p 태그의 마지막 child

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .box:after { content : "항목을 선택해주세요."; color : green;}
        p:first-letter { font-size : 300%;}
        p { border-bottom : 1px dashed #000;}
        p:last-child { border : none;}

    </style>
</head>
<body>
    <div class="box">
        <p>COMPANY</p>
        <p>PRODUCT</p>
        <p>SERVICE</p>
    </div>
</body>
</html>
```

> ex5.html

id를 이용해서 HEADER, NAV, SECTION, FOOTER 나누기

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        #header {
            background-color : lightgrey;
            height : 100px;
        }
        #nav {
            background-color : #339999;
            color : white;
            width : 200px;
            height : 300px;
            float  : left;
        }
        #section {
            width : 400px;
            text-align : left;
            float : left;
            padding : 10px;
        }
        #footer {
            background-color : #FFCC00;
            height : 100px;
            clear : both;
        }
        #header, #nav, #section, #footer { text-align : center;}
        #header, #footer { line-height : 100px;}
        #nav { line-height : 240px;}
</style>
</head>
<body>
레이아웃
HTML 레이아수 (Layout)
레이아웃(Layout)이란 특정 공간에 여러 구성 요소를 보기 좋게 효과적으로 배치하는 작업을 의미한다. 
1. div 요소를 이용한 레이아웃
2. HTML5 레이아웃
3. table 요소를 이용한 레이아웃
<h1>div 요소를 이용한 레이아웃</h1>
    <div id="header">
        <h2>HEADER 영역</h2>
    </div>
    <div id="nav">
        <h2>NAV 영억</h2>
    </div>
    <div id="section">
        <h2>SECTION 영역</h2>
        <form action="/Day0525MON/MemberServletInsert" method="post">
            <fieldset>
               <legend>회원가입</legend>
               <p>아이디 : <input type="text" size="12" maxlength="8" name="id"></p>
               <p>비밀번호 : <input type="password" size="12" maxlength="8" name="pw"></p>
               <p>메일 수신 여부 : 
                  <input type="radio" value="y" name="receive">Y
                  <input type="radio" value="n" name="receive">N
               </p>
               <p>관심분야 : 
                 <input type="checkbox" value="HTML" name="chk">HTML
                 <input type="checkbox" value="CSS" name="chk">CSS
                 <input type="checkbox" value="JavaScript" name="chk">JavaScript
               </p>
               <input type="image" src="" alt="검색">
               <input type="submit" value="전송">
               <input type="reset" value="취소">
               <input type="button" value="확인">         
            </fieldset>
         </form>
    </div>
    <div id="footer">
        <h2>FOOTER 영역</h2>
    </div>
</body>
</html>
```

> table.html

table을 이용해서 HEADER, NAV, SECTION, FOOTER 구현하기.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>

    </style>
</head>
<body>
    <h1>table 요소를 이용한 레이아웃</h1>
    <table width="100%" style="text-align : center; border : none">
        <tr>
            <td colpsan="2" style="background-color : lightgrey;">
                <h2>HEADER 영역</h2>
            </td>
        </tr>
        <tr>
            <td style="background-color : #339999; color : white; width : 20%;"> 
                <h2>NAV 영역</h2>
            </td>
            <td style="height : 200px; text-align : left">
                <h2>SECTION 영역</h2>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="background-color : #FFCC00">
                <h2>FOOTER 영역</h2>
            </td>
        </tr>
    </table>
</body>
</html>
```
