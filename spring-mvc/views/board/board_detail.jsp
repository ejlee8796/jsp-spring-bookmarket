<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/inc/header.jsp"></jsp:include>
<% request.setCharacterEncoding("UTF-8"); %>
<div id="bookstory">
	<div class="container">
		<input type="hidden" id="needBno" name="needBno" value="${vo.bno}"/>
		<div class="form-group row" id="mainbookborder">
			<div class="col-sm-8"><h4><strong>[북 콜렉션] ${vo.btitle}</strong></h4></div>
			<div class="col-sm-4"><h5>DATE : ${vo.bdate}</h5></div>
		</div>
		<div class="form-group">
		  <div  class="panel-heading"> <span class="glyphicon glyphicon-user">  작성자</span> ${vo.bname}님</div>
		</div>
		<div class="form-group">
			<c:forTokens items="${vo.bfile}" delims="/" var="i" >
			<%-- <img src='${pageContext.request.contextPath}/upload/"+data[i].bfile.split("/")[0] --%>
				<div class="panel-img text-center"><img src="${pageContext.request.contextPath}/upload/${i}" /></div>
			</c:forTokens>
			<div  class="panel-content">${vo.bcontent}</div>
		</div>
		<div class="row  text-right">
		<%if((String)session.getAttribute("id") == null){%>
			 <a href="${pageContext.request.contextPath}/board/list" class="btn btn-default" >목록보기</a> 
		<%} else {%>
			<a href="${pageContext.request.contextPath}/board/board_modify?bno=${vo.bno}"  class="btn btn-default" >수정</a> 
			<a href="${pageContext.request.contextPath}/board/board_delete?bno=${vo.bno}"  class="btn btn-default" >삭제</a>
			<a href="${pageContext.request.contextPath}/board/list?id=<%=(String)session.getAttribute("id")%>" class="btn btn-default" >목록보기</a> 
		<%}%>
		</div>
	</div>
</div>
<div id="bookstorycomment">
	<div class="container panel panel-default">
		<h4 class="panel-heading"><strong>댓글</strong>을 입력해주세요!</h4>
		<%if((String)session.getAttribute("id") != null){%>
	   	<div class="form-group row">
	   		<div class="col-sm-4">
	   			<input type="text" name="writer" id="writer" class="form-control" placeholder="작성자" value="<%=(String)session.getAttribute("id")%>" readonly/>
	   		</div>
	   		<div class="col-sm-8">
		   		<input type="password" name="pass" id="pass0" class="form-control" placeholder="암호"/>
		   		<input type="hidden" name="secret" id="secret" class="form-control" value="y" />
		  	</div>
	   	</div>	
	    <div class="form-group">
	   		<textarea name="comment" id="comment" class="form-control" placeholder="여러분의 소중한 댓글을 달아주세요"></textarea>
	   	</div>	   	  		
	   	<div class="form-group">
	   		<p><input type="button" name="button" id="button" value="댓글달기" class="form-control" /></p>
	   	</div>
	</div>
	<%} else {%>
	   	 <div class="form-group">
	   		<textarea name="comment" id="comment" class="form-control" placeholder="로그인시 이용가능합니다." readonly></textarea>
	   	</div>
	<%}%>
	<div class="container panel panel-default" id="commentIndex">
		<div class="panel-body" id="comment_list"></div>
	</div>
</div>
<script>
$(function(){
	listCommentAll("false", 0);
	
	$(document).on("click","#button", function(){
		var key  = [ "#writer", "#pass0", "#comment"];
		var value = [ "작성자", "비밀번호", "내용"];
		
		for(var i=0; i<key.length; i++) {
			if( $(key[i]).val() == "") {
				alert(value[i]+"을(를) 입력하세요.");
				$(key[i]).focus();
				return false;
			}
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath}/board/comment_insert",
			type : "post",
			dataType : "json",
			data : { "writer" : $("#writer").val(), "pass":$("#pass0").val(), "secret":$("#secret").val(), "comment":$("#comment").val(), "bno":$("#needBno").val()  },
			success : function(data) { 
				alert(data.success); listCommentAll("false", 0);
				$("#writer").val(""); $("#pass0").val(""); $("#comment").val("");	
			},
			error : function(xhr, textStatus, errorThrown) { alert(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
		});
	});
	
	$(document).on("click",".col-sm-4 a", function(){
		var str = $(this).attr("href");
		var cno = parseInt(str.split("=")[1]);
		$.ajax({
			url : "${pageContext.request.contextPath}/board/checkCommentPass",
			type : "post",
			dataType : "json",
			data : { "cno" : cno, "comment" : $("#comment"+cno).val(), "pass": $("#pass"+cno).val(), "check" : "true" },
			success : function(data) {
				if(data.ck == "true") { listCommentAll(data.ck, data.ckno); } else { listCommentAll("false", 0); }
				alert(data.check);
			},
			error : function(xhr, textStatus, errorThrown) { alert(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
		});
		return false;
	});
	
	$(document).on("click","#confirmComment", function(){
		var str = $(this).attr("name");
		var cno = parseInt(str);
		$.ajax({
			url : "${pageContext.request.contextPath}/board/comment_update",
			type : "post",
			dataType : "json",
			data : { "cno" : cno, "comment" : $("#comment"+cno).val() },
			success : function(data) { alert(data.confirm); listCommentAll("false", 0); },
			error : function(xhr, textStatus, errorThrown) { alert(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
		});
	});
	
	$(document).on("click", "#deleteComment", function(){
		if(confirm("정말 삭제하시겠습니까?")) {
			var str = $(this).attr("name");
			var cno = parseInt(str);
			$.ajax({
				url : "${pageContext.request.contextPath}/board/comment_delete",
				type : "post",
				dataType : "json",
				data : { "cno" : cno },
				success : function(data) { alert(data.del); listCommentAll("false", 0); },
				error : function(xhr, textStatus, errorThrown) { alert(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
			});
		} else { alert("취소합니다."); }
	});
});

function listCommentAll(ck, ckno) {
	$.ajax({
		url : "${pageContext.request.contextPath}/board/comment_list_json",
		type : "get",
		dataType : "json",
		data : { "check" : ck, "ckno" : ckno, "bno":$("#needBno").val()  },
		success : function(data) {
			$("#commentIndex #comment_list").empty();
			if(data.length == 1) {
				$("#commentIndex #comment_list").html("<p class='text-center'>댓글이 존재하지 않습니다. 입력해주세요.</p>");
			} else {
				for(var i=0; i<data.length-1; i++) {
					var divRow = $("<div class='form-group row'>");
					var divCol4_1 = $("<div class='col-sm-4'>").html("<input type='text' name='writer' id='writer"+data[i].cno+"' class='form-control' value='"+data[i].writer+"' readonly/>");
					var divCol4_2 = $("<div class='col-sm-4'>").html("<input type='password' name='pass' id='pass"+data[i].cno+"' class='form-control' placeholder='수정/삭제 시 비밀번호를 입력해주세요'/>");
					var divCol4_3 = $("<div class='col-sm-4'>");
					var del = $("<a href='${pageContext.request.contextPath}/board/comment_delete?cno="+data[i].cno+"' class='btn btn-default'>").html("[삭제]");
					var up = $("<a href='${pageContext.request.contextPath}/board/comment_update?cno="+data[i].cno+"' class='btn btn-default'>").html("[수정]");
					var con = $("<input type='button' value='[확인]' name='"+data[i].cno+"' class='btn btn-default' id='confirmComment'>");
					
					var cno = parseInt(data[data.length-1].ckno);
					var divTextArea = "";
					
					if(data[data.length-1].ck == "true" && cno == data[i].cno) {
						divTextArea = $("<div class='form-group'>").html("<textarea name='comment' id='comment"+data[i].cno+"' class='form-control'>"+data[i].comment+"</textarea>");
						del = $("<input type='button' value='[삭제]' name='"+cno+"' class='btn btn-default' id='deleteComment'>");
						divCol4_3.append(con).append(del);
					} else {
						divTextArea = $("<div class='form-group'>").html("<textarea name='comment' id='comment"+data[i].cno+"' class='form-control' readonly>"+data[i].comment+"</textarea>");
						divCol4_3.append(up).append(del);
					}
					
					divRow.append(divCol4_1).append(divCol4_2).append(divCol4_3);
					$("#commentIndex #comment_list").append(divRow).append(divTextArea);
				}
			}
		},
		error : function(xhr, textStatus, errorThrown) { $("#comment_list").html(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
	});
}
</script>
<jsp:include page="/inc/footer.jsp"></jsp:include>