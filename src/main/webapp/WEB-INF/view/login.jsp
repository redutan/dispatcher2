<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>로그인</title>
</head>
<body>
    <h2>사용자 로그인</h2>

    <form action="login" method="post" target="/login">
        아이디  : <input type="text" name="id" /><br />
        비밀번호 : <input type="password" name="password" /><br />

        <input type="submit" value="로그인" />
    </form>
</body>
</html>
