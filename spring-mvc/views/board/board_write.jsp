<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/inc/header.jsp"></jsp:include>
<script>
$(document).ready(function() {
	$("#writeform").submit(function() {
		var key  = [ "#bname", "#bpass", "#btitle", "#bcontent", "#file"];
		var value = [ "이름", "비밀번호", "제목", "내용", "파일업로드"];
		
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
<div id="springWrite1">
	<div class="container">
		<h3>북 <strong>콜렉션</strong> 글 등록</h3>
		<form action="${pageContext.request.contextPath}/board/board_write" method="post" id="writeform" enctype="multipart/form-data">
			<div class="form-group">
			  <label for="bname"><span class="glyphicon glyphicon-plus"> 작성자</span></label>
			  <input type="text" name="bname" id="bname" class="form-control" value="${bid}" readonly/>
			</div>						
			<div class="form-group">
			  <label for="btitle"  ><span class="glyphicon glyphicon-pencil"> 제목</span></label>
			  <input type="text" name="btitle" id="btitle" class="form-control" /> 
			</div>
			<div class="form-group">
			  <label for="bpass"  ><span class="glyphicon glyphicon-pencil"> 비밀번호</span></label>
			  <input type="password" name="bpass" id="bpass" class="form-control" placeholder="*****"  /> 
			</div>
			<div class="form-group">
			  <label for="bcontent" ><span class="glyphicon glyphicon-pencil"> 내용</span></label>
			  <textarea name="bcontent" cols="60" rows="10" id="bcontent" class="form-control" placeholder="무고,욕설,비방,광고 등의 내용은 통보없이 삭제될 수 있습니다."></textarea>
			</div>
			<div class="form-group">
			  <label for="file"><span class="glyphicon glyphicon-plus"> 파일업로드</span></label>
			  <input type="file" name="mulfile" id="mulfile" class="form-control" multiple/>
			  <span class="uploadfiles"></span>
			</div>
			<script>
			$(function(){
				$("#mulfile").change(function(){
					var files = document.getElementById("mulfile").files;
					if(files) {
						for(var i=0; i<files.length; i++) {
							var tt = "";
							if((i*1+1) == 1) { tt = "["+(i*1+1)+"]"+files[i].name+"<strong> : 대표이미지(사진의 첫번째 순서)</strong>";
							} else { tt = "["+(i*1+1)+"]"+files[i].name; }
							
							$(".uploadfiles").append(tt+"<br/>");
						}
					}
				});
			});
			</script>
			<div class="form-group  text-right">
				 <input type="submit"   value="입력"  class="btn btn-default">  
				 <a href="${pageContext.request.contextPath}/board/list"   class="btn btn-default">취소</a>
				 <a href="${pageContext.request.contextPath}/board/list"   class="btn btn-default">목록보기</a>
			</div>	
		</form>
	</div>
</div>
<jsp:include page="/inc/footer.jsp"></jsp:include>
