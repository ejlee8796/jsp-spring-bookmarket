<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/inc/header.jsp"></jsp:include>
<script>
$(document).ready(function() {
	$("#modifyform").submit(function() {
		var key  = [ "#bpass", "#btitle", "#bcontent", ];
		var value = [ "비밀번호", "제목", "내용"];
		
		for(var i=0; i<key.length; i++) {
			if( $(key[i]).val() == "") {
				alert(value[i]+"을(를) 입력하세요.");
				$(key[i]).focus();
				return false;
			}
		}
	});
});
</script>
<div id="modifybookstory">
	<div class="container">
		<div class="form-group" id="modifystorydiv">
			<h4><strong>[북 콜렉션] ${vo.bname}님의 글 수정</strong></h4>
		</div>
		<form action="${pageContext.request.contextPath}/board/board_modify" method="post" id="modifyform" enctype="multipart/form-data" >
			<input type="hidden" id="bno" name="bno" value="${vo.bno}" />					
			<input type="hidden"   name="bname"   id="bname" value="${vo.bname}" /> 
			<div class="form-group">
				<label for="btitle" >제목</label>
				<input type="text"   name="btitle"   id="btitle"   class="form-control"  value="${vo.btitle}" /> 
			</div>	
			<div class="form-group">
				<label for="bpass"  >비밀번호</label>
				<input type="password" name="bpass" id="bpass" class="form-control" /> 
			</div>
			<div class="form-group">
				<label for="bcontent" >내용</label>
			 	<textarea name="bcontent"  cols="60"  rows="10"   class="form-control" >${vo.bcontent}</textarea>
			</div>
			<div class="form-group">
				<label for="file"><span class="glyphicon glyphicon-plus"> 파일업로드</span></label>
				<input type="file" name="mulfile" id="mulfile" class="form-control" multiple/>
				<input type="text" name="bfile" id="bfile" class="form-control" value="${vo.bfile}" readonly/>
			</div>	
			<div class="form-group  text-right">
				 <input type="submit"   value="수정"  class="btn btn-default" />  
				 <a href="javascript:history.go(-1);"   class="btn btn-default" >취소</a>
				 <a href="${pageContext.request.contextPath}/board/list"   class="btn btn-default" >목록보기</a>
			</div>	
		</form>
	</div>
</div>
<jsp:include page="/inc/footer.jsp"></jsp:include>
