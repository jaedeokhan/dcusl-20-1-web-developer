<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <form action="/Day0601MON/UploadServlet" method="post" enctype="multipart/form-data">
        상 품 : <input type="text" name="name"><br>
        설 명 : <input type="text" name="content"><br>
        파일 지정하기 : <input type="file" name="uploadFile"><br>
   <input type="submit" value="전송">     
  </form>
</body>
</html>