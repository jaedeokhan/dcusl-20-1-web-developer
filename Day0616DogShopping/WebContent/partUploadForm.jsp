<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="upload.do" method="POST" encType="multipart/form-data">
       설명 : <input type="text" name="description" /><br>
       첨부파일 : <input type="file" name="attachment" /><br>
    <input type="submit" value="파일업로드" />
</form>

</body>
</html>