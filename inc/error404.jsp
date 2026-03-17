<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" 
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h3>404 ERROR</h3>
		<p>관리자에게 문의바랍니다.</p>
	</div>		
</body>
</html>














<!-- 
  	1) web.xml
	  <error-page>
		<error-code>404</error-code>
		<location>/error404.jsp</location>
	  </error-page>
	  <error-page>
		<error-code>500</error-code>
		<location>/error500.jsp</location>
	  </error-page>
	2) error404.jsp
	3) error500.jsp
	4) 테스트파일 : error01.jsp
	5) 테스트파일 : error02.jsp

 -->