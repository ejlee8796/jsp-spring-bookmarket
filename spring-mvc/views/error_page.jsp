<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h3>ERROR_PAGE</h3>
		<p>관리자에게 문의바랍니다.</p>
		<p><a href="javascript:history.go(-1);" class="btn btn-danger">HOME</a></p>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<h4><c:out value="${exception.getMessage()}" /></h4>
		<ul>
			<c:forEach items="${exception.getStackTrace()}" var="stack" >
				<li><c:out value="${stack}" /></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>