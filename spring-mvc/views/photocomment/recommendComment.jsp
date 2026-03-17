<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<div id="recommendComment">
   <div class="container panel panel-default">
	   	<h3 class="text-right"><a href="${pageContext.request.contextPath}/photocomment/photoComment">
		   	<span class="glyphicon glyphicon-share-alt" title="더보러 가기"></span>
		</a></h3>
		<div class="panel-body" id="comment_list"></div>
   </div>
</div>
<script>
$(function(){ listAll("false", 0); });

function listAll(ck, ckno) {
	$.ajax({
		url : "${pageContext.request.contextPath}/photocomment/comment_limit_json",
		type : "get",
		dataType : "json",
		data : { "check" : ck, "ckno" : ckno },
		success : function(data) {
			$("#comment_list").empty();
			if(data.length == 1) {
				$("#photoComment #comment_list").html("<p class='text-center'>댓글이 존재하지 않습니다. 입력해주세요.</p>");
			} else {
				for(var i=0; i<data.length-1; i++) {
					var divRow = $("<div class='form-group row'>");
					var h4 = $("<div class='col-sm-2 text-right'>").html("<h5><strong>"+data[i].writer+"님</strong></h5>");
					var date = $("<div class='col-sm-10 text-right'>").html("<span>"+data[i].cdate+"</span>");
					
					var form_group = $("<div class='form-group row'>");
					var img = $("<img src='${pageContext.request.contextPath}/upload/"+data[i].file+"' style='height:100px; width:100px' />");
					var divCol4 = $("<div class='col-sm-3 text-center'>").append(img);
					var divCol8 = $("<div class='col-sm-9'>").append("<textarea name='comment' class='form-control' disabled>"+data[i].comment+"</textarea>");;

					divRow.append(h4).append(date);
					form_group.append(divCol4).append(divCol8);					
					$("#comment_list").append(divRow).append(form_group);
				}
			}
		},
		error : function(xhr, textStatus, errorThrown) { $("#comment_list").html(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
	});
}
</script>