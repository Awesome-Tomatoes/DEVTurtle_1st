<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--  
지시어
import
taglib
 -->    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/my.css">
</head>
<body>

&lt;%@ : 지시어 <br>
% : 스크립트릿  <br>
<%  
out.println( request.getContextPath() );
%>
가입폼<hr>

<form name="myForm" id="myForm" method="post" action="${pageContext.request.contextPath}/HelloServlet">
<table class="blueTable">
<thead> 
<tr>
	<th>가입정보</th>
</tr>
</thead>
<tbody>
<tr>
	<td>
		아이디 : <input type="text" 	  name="userid" id="userid"> <br>  <!--   value="hong"  -->
		비번 : <input type="password"  name="userpw" id="userpw"> <br>
		성별 : <select name="gen">
			<option value="m">남</option>
			<option value="f">여</option>
			<option value="o">그외</option>
		</select><br>
		취미 : <input type="checkbox" name="habit" value="fish"> 낚시
		<input type="checkbox" name="habit" value="mnt"> 등산
		<input type="checkbox" name="habit" value="run"> 조깅 <br>
		
		혈액형 :  <input type="radio" name="blood" value="A">A
				<input type="radio" name="blood" value="B">B
				<input type="radio" name="blood" value="AB">AB
				<input type="radio" name="blood" value="O">O <br>
				
		첨부 :  <input type="file" name="ufile">	<br>			
		히든 : <input type="hidden" name="rdate" value="2024-12-17">
	</td>
</tr>
<tr>
	<td align="center">
		<input type="button" name="btn" id="btn" value="일반버튼">
		<input type="submit" name="saveBtn" value="저장">
		<input type="reset" name="resetBtn" value="취소">
	</td>
</tr>
</tbody>
</table>
</form>

</body>
</html>