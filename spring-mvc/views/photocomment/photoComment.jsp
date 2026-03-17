<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/inc/header.jsp"/>
<div id="photoComment">
	<div class="container">
		<h3 class="hum"><strong>Book Market , </strong>지금 당신이 읽고 있는 책</h3>
	</div>
	<div class="container panel panel-default">
		<h4 class="recomandTitle text-right"><strong>#북마켓 #실시간_추천 #내_추천_독서 #내가_가장_좋아하는_문구 #오늘은_이_책</strong></h4>
		<div class="panel-body">
		<form action="${pageContext.request.contextPath}/photocomment/comment_insert" method="post" enctype="multipart/form-data" id="uploadPC">
		   	<div class="form-group row">
		   		<div class="col-sm-3">
		   			<input type="text" name="writer" id="writer" class="form-control" placeholder="우리말 애칭을 적어주세요!"/>
		   		</div>
		   		<div class="col-sm-4">
			   		<input type="password" name="pass0" id="pass0" class="form-control" placeholder="암호"/>
			   		<input type="hidden" name="secret" id="secret" class="form-control" value="y" />
			  	</div>
			  	<div class="col-sm-5">
			   		<input type="button" name="uploadPhotoComment" id="uploadPhotoComment" value="댓글달기" class="btn btn-default" />
			  	</div>
		   	</div>
		   	<div class="form-group row">
			   	<div class="col-sm-3">
			   		<p><label for="file">후기에 어울리는 이미지를 올려주세요!</label></p>
			   		<input type="file" name="file" id="file" class="form-control" />
		   		</div>
		   		<div class="col-sm-9">
			   		<textarea name="comment" id="comment" class="form-control" placeholder="여러분의 소중한 댓글을 달아주세요"></textarea>
		   		</div>
		   	</div>
	   	</form>
	   	</div>
   </div>
   <div class="container panel panel-default">
	   	<h3 class="text-center"><strong>독자의 후기</strong></h3>
		<div class="panel-body" id="comment_list"></div>
   </div>
</div>
</body>
<script>
$(function(){
	listAll("false", 0);
	
	$("#uploadPhotoComment").on("click", function(){
		var key  = [ "#writer", "#pass0", "#comment", "#file"];
		var value = [ "작성자", "비밀번호", "내용", "이미지 파일"];
		var req = /^[가-힣\s]+$/;
		
		for(var i=0; i<key.length; i++) {
			
			if( $(key[i]).val() == "") {
				alert(value[i]+"을(를) 입력하세요.");
				$(key[i]).focus();
				return false;
			}
			
			if( key[i] == "#writer") {
				if(!req.test($("#writer").val())) {
					alert("북마켓은 보다 더 많은 우리말 사용을 응원해요 ㅜ_ㅜ! \n도서 코멘트 추천은 한글로 작성해주세요!");
					return false;
				}
			}
			
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath}/photocomment/comment_insert",
			type : "post",
			dataType : "json",
			data : new FormData(document.getElementById("uploadPC")),
			processData : false,
			contentType : false,
			success : function(data) {
				listAll("false", 0);
				alert(data.success);
				$("#writer").val(""); $("#pass0").val(""); $("#secret").val(""); $("#comment").val(""); $("#file").val("");
			},
			error : function(xhr, textStatus, errorThrown) { alert(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
		});
	});
	
	$(document).on("click",".col-sm-5 a", function(){
		var str = $(this).attr("href");
		var cno = parseInt(str.split("=")[1]);
		$.ajax({
			url : "${pageContext.request.contextPath}/photocomment/checkPass",
			type : "post",
			dataType : "json",
			data : { "cno" : cno, "comment" : $("#comment"+cno).val(), "pass": $("#pass"+cno).val(), "check" : "true" },
			success : function(data) {
				if(data.ck == "true") { listAll(data.ck, data.ckno); } else { listAll("false", 0); }
				alert(data.check);
			},
			error : function(xhr, textStatus, errorThrown) { alert(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
		});
		return false;
	});
	
	$(document).on("click","#confirmComment", function(){
		var str = $(this).attr("name").split("name")[1];
		var cno = parseInt(str);
		$.ajax({
			url : "${pageContext.request.contextPath}/photocomment/comment_update",
			type : "post",
			dataType : "json",
			data : new FormData(document.getElementById("upload_ajax"+cno)),
			processData : false,
			contentType : false,
			success : function(data) { alert(data.confirm); listAll("false", 0); },
			error : function(xhr, textStatus, errorThrown) { alert(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
		});
	});
	
	$(document).on("click", "#deleteComment", function(){
		if(confirm("정말 삭제하시겠습니까?")) {
			var str = $(this).attr("name").split("name")[1];
			var cno = parseInt(str);
			$.ajax({
				url : "${pageContext.request.contextPath}/photocomment/comment_delete",
				type : "post",
				dataType : "json",
				data : { "cno" : cno },
				success : function(data) { alert(data.del); listAll("false", 0); },
				error : function(xhr, textStatus, errorThrown) { alert(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
			});
		} else { alert("취소합니다."); }
	});
});

function listAll(ck, ckno) {
	$.ajax({
		url : "${pageContext.request.contextPath}/photocomment/comment_list_json",
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
					var divCol4_1 = $("<div class='col-sm-3'>").html("<input type='text' name='writer' id='writer"+data[i].cno+"' class='form-control' value='"+data[i].writer+"' readonly/>");
					var divCol4_2 = $("<div class='col-sm-4'>").html("<input type='password' name='pass' id='pass"+data[i].cno+"' class='form-control' placeholder='수정/삭제 시 비밀번호를 입력해주세요'/>");
					var divCol4_3 = $("<div class='col-sm-5'>");
					var del = $("<a href='${pageContext.request.contextPath}/photocomment/comment_delete?cno="+data[i].cno+"' class='btn btn-default'>").html("[삭제]");
					var up = $("<a href='${pageContext.request.contextPath}/photocomment/comment_update?cno="+data[i].cno+"' class='btn btn-default'>").html("[수정]");
					var con = $("<input type='button' value='[확인]' name='name"+data[i].cno+"' class='btn btn-default' id='confirmComment'>");
					var date = $("<p>").html(data[i].cdate);
					var cno = parseInt(data[data.length-1].ckno);
					var form_group = $("<div class='form-group row'>");
					
					var divCol4 = $("<div class='col-sm-3 text-center'>");
					var divCol8 = $("<div class='col-sm-9'>");
					
					var useform = $("<form action = '${pageContext.request.contextPath}/photocomment/comment_update' method='post' enctype='multipart/form-data' id='upload_ajax"+data[i].cno+"'>");

					if(data[data.length-1].ck == "true" && cno == data[i].cno) {
						var refile = $("<input type='file' name='file' id='file"+data[i].cno+"' class='form-control' />"); 
						var img = $("<img src='${pageContext.request.contextPath}/upload/"+data[i].file+"' style='height:100px; width:100px' />");
						var hiddenImg =  $("<input type='hidden' name='orginfile' id='orginfile' value='"+data[i].file+"' />");
						var hiddenCno = $("<input type='hidden' name='no' id='no' value='"+data[i].cno+"' />");

						divCol4.append(img).append(refile).append(hiddenImg).append(hiddenCno);
						divCol8.html("<textarea name='comment' id='comment"+cno+"' class='form-control'>"+data[i].comment+"</textarea>");
						
						del = $("<input type='button' value='[삭제]' name='name"+data[i].cno+"' class='btn btn-default' id='deleteComment'>");
						divCol4_3.append(con).append(del);						
					} else {
						divCol4.html("<img src='${pageContext.request.contextPath}/upload/"+data[i].file+"' style='height:100px; width:100px' class='text-center' />");
						divCol8.append("<textarea name='comment' id='comment"+data[i].cno+"' class='form-control' readonly>"+data[i].comment+"</textarea>");
						divCol8.append(date);
						divCol4_3.append(up).append(del);
					}
					
					divRow.append(divCol4_1).append(divCol4_2).append(divCol4_3);
					form_group.append(divCol4).append(divCol8);
					useform.append(divRow).append(form_group)
					$("#comment_list").append(useform);
				}
			}
		},
		error : function(xhr, textStatus, errorThrown) { $("#comment_list").html(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
	});
}
</script>
<jsp:include page="/inc/footer.jsp"/>