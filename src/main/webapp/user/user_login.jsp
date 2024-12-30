<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="x" 	uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sql" 	uri="http://java.sun.com/jsp/jstl/sql" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 페이지</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user/user_login.css">
</head>
<body>
    <div class="container">
        <div class="logo">
            <a href="index.html">
                <img src="${pageContext.request.contextPath}/assets/rabTuttle.webp" alt="로고 이미지">
                <h1>DEVTurtle</h1>
            </a>
        </div>
        <form class="login-form">
            <label for="username">아이디</label>
            <input type="text" id="userid" name="userid" placeholder="placeholder">
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" placeholder="placeholder">
            <button type="submit" class="login-btn">로그인</button>
        </form>
        <div class="register-link">
            <p>아직 회원이 아니신가요? <a href="signin.jsp">회원가입</a></p>
        </div>
    </div>
</body>
</html>
</html>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
$( document ).ready(function() {
	//$("#btn").click( function() {  
	//    	$("#input").val();
	//});
});
</script>
