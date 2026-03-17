<%@page import="com.company.dto.BDto"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="project_index.IndexList"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="inc/header.jsp" %>
<%
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8");

String category = request.getParameter("category");
String writer  = request.getParameter("author");

String id = ((String)session.getAttribute("id")==null)? "" : (String)session.getAttribute("id");
%>
<div id="index1board3Detail">
	<div class="container">
		<div class="ib3d1">
	<%
		IndexList list = new IndexList();
		 	ArrayList<BDto> allList = list.List("index1board3", category, writer);
			Iterator<BDto> iter = allList.iterator();
	%><h3 class="needHr"><strong><%=writer%> 특집</strong></h3>
	<%	while(iter.hasNext()) {
		BDto dto = iter.next();%>
		<div class="thumbnail">
			<img class="bfile" src="maintabimg/<%=dto.getBfile().split("/")[0]%>">
			<p class="text-center"><br><span class="content img_content"><%=dto.getBtitle()%></span></p>
			<p class="text-center"><br><span class="content img_content"><%=dto.getBcontent().split("/")[0]%></span></p>
			<img class="bfile" src="maintabimg/<%=dto.getBfile().split("/")[1]%>">
			<p class="text-center"><br><span class="content img_content"><%=dto.getBtitle()%></span></p>
		</div>
	  <%}%>
  		</div>
  		<div class="ib3d2">
  			<input type="hidden" value="<%=id%>" id="id1" name="id1" />
  			<input type="hidden" value="nope" id="pass1" name="pass1" />
  			<input type="hidden" value="<%=writer%>" id="writer1" name="writer1" />
  			<textarea class="form-control" id="content1" name="content1" placeholder="여러분의 소중한 댓글을 달아주세요."></textarea>
  			<div class="text-right ibutton">
				<p><input type="button" value="댓글달기" class="btn btn-default" name="button1" id="button1"/></p>
			</div>
		</div>
		<div class="commentList"></div>
	</div>
</div>
<script>
$(function(){
	searchAjaxdoPost(); //초기값 설정
	
	$(document).on("click", "#button1", function(){
		var arr = ["#id1","#content1"];
		var value = ["로그인 시 입력 가능합니다.","내용을 입력해주세요."];
		for(var i=0; i<arr.length; i++) {
			if($(arr[i]).val() == "") {
				alert(value[i]);
				$(arr[i]).focus();
				return false;
			}
		}
		$.ajax({
			url : "Cservlet", type : "post", dataType : "json",
			data : { "id" : $("#id1").val(), "pass" : $("#pass1").val(), "secret" : $("#secret").val(), "content" : $("#content1").val(), "cpost" : $("#writer1").val() },
			success : function(data) { searchAjaxdoPost(); },
			error : function(xhr, textStatus, errorThrown) { alert(errorThrown+" HTTP-"+xhr.status+textStatus); }
		});
	});
	
	$(document).on("click", "#button2", function(){
		var str = $(this).attr("name");
		var writer = str.split("/")[2];
		var cno = parseInt(str.split("/")[1]);
		$.ajax({
			url : "Cservlet", type : "post", dataType : "json",
			data : { "cno" : cno, "check" : "delete", "cpost" : writer },
			success : function(data) {
				for(var i=0; i<data.length; i++) { alert(data[i].alert); }
				searchAjaxdoPost();	
			},
			error : function(xhr, textStatus, errorThrown) { alert(errorThrown+" HTTP-"+xhr.status+textStatus); }
		});
	});
	
	function searchAjaxdoPost() { 
		$.ajax({
			url : "Cservlet", type : "post", dataType : "json",
			data : { "cpost" : $("#writer1").val() },
			success : function(data) {
				$(".commentList").empty();
				
				for(var i=0; i<data.length; i++) {
					var writer = data[i].writer;
					var comment = data[i].comment.replace("<br>","\r\n");
					var date = data[i].date;
					var no = data[i].cno;
					
					var div = $("<div class='comment_section'>");
					var id = $("<h5>").html("<strong>"+writer+"님의 댓글</strong><span class='text-right'>"+date+"</span>");
					var textarea = $("<textarea class='form-control' id='content"+no+"' name='content"+no+"' readonly>").html(comment);
					
					var div2 = $("<div class='text-right'>");
					var del = $("<input type='button' value='삭제' title='삭제' name='button/"+no+"/"+writer+"' id='button2' class='btn btn-default' />");
					div2.append(del);
					
					if(writer == $("#id1").val()) { div.append(id).append(textarea).append(div2);
					} else { div.append(id).append(textarea); }
					
					$(".commentList").append(div);
				}
			},
			error : function(xhr, textStatus, errorThrown) { alert(errorThrown+" HTTP-"+xhr.status+textStatus); }
		});
	};
});
</script>

<%@ include file="inc/footer.jsp" %>
