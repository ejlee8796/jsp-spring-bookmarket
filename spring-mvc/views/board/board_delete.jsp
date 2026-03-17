<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/inc/header.jsp"></jsp:include>
<div class="container">
	<h3>북 콜렉션 글 삭제</h3>
	<form action="${pageContext.request.contextPath}/board/board_delete" method="post">
		<div class="form-group">
		  <label for="bpass" >비밀번호</label>
		  <input type="password" name="bpass" id="bpass" class="form-control" /> 
		  <p><input type="hidden" id="bno" name="bno" value="${bno}" /></p>
		</div>
		<div class="form-group  text-right">
			 <input type="submit"   value="삭제"  class="btn btn-default"    />
			 <a href="javascript:history.go(-1);"   class="btn btn-default" >취소</a>
			 <a href="${pageContext.request.contextPath}/board/list"   class="btn btn-default" >목록보기</a>
		</div>	
	</form>
</div>
<jsp:include page="/inc/footer.jsp"></jsp:include>