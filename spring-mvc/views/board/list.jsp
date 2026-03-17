<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="/inc/header.jsp"></jsp:include>
<div id="SpringBoardProject">
	<div class="container">
		<div class="Spring_Board_List">
			<h3><strong>북! 중고거래</strong></h3>
			<div class="form-group" id="sellBookList"></div>
			<div class="form-group text-center array">
				<input type="text" id="searchspringB" name="search" title="제목 키워드를 입력하세요" placeholder="검색어를 입력하세요">
				<input type="button" id="searchBtitlespring" name="searchBtitle" title="키워드로 검색" value="검색" class="btn btn-default">
			</div>
			<div class="form-group text-right">
				<input type="button" id="spring1write" name="spring1write"  title="글작성" value="글작성" class="btn btn-default"/>
			</div>
			<div class="text-center listbutton"></div>
			<p><input type="hidden" id="userid" name="userid" value="${id}" /></p>
			<p><input type="hidden" id="alert" name="alert" value="${success}" /></p>
			<p><input type="hidden" id="pstartno" name="pstartno" value="0" /></p>
		</div>
	</div><!-- CONTAINER END -->
</div>
<script>
$(function() {
	listAll(0);
	if($("#alert").val() != "") { alert($("#alert").val()); }
	
	$(document).on("click", "#SpringBoardProject #spring1write", function(){
		if($("#userid").val() == "") {
			alert("로그인해야 이용가능한 서비스입니다.");
			location.href = "http://adnerwin.cafe24.com/test/loginBoard/login.jsp";
		} else {
			location.href="${pageContext.request.contextPath}/board/board_write?id="+$("#userid").val();
		}
	});
	
	$(document).on("click", "#SpringBoardProject .pagination a", function(){
		var str = $(this).attr("href");
		if(str.indexOf("search") > -1) {
			var search = str.split("&")[1].split("=")[1];
			var pstartno = parseInt(str.split("=")[1].split("&")[0]);
			SearchListAll(search,pstartno);
		} else {
			var pstartno = parseInt(str.split("=")[1]);
			listAll(pstartno);
		}
		return false;
	});
	
	$(document).on("click","#SpringBoardProject #searchBtitlespring", function(){
		if($("#searchspringB").val() == "") { alert("검색어를 입력하세요."); $("#searchspringB").focus(); return false; 
		} else { SearchListAll($("#searchspringB").val(), $("#pstartno").val()) }
	});
});
function SearchListAll(search, pstartno) {
	$.ajax({
		url : "${pageContext.request.contextPath}/board/search_json",
		type : "get",
		dataType : "json",
		data : { "search" : search, "pstartno" : pstartno },
		success : function(data) {				
			if(data.length == 1) {
				alert("검색 내용이 없습니다.\n다른 검색어로 검색해주세요.");
				$("#searchspringB").focus();
				listAll(0);
			} else {
				$("#SpringBoardProject #sellBookList").empty();
				$("#SpringBoardProject .listbutton").empty();
				
				var onepagelimit = data[data.length-1].onepagelimit;
				var pagetotal = data[data.length-1].pagetotal;
				var currentpage = data[data.length-1].currentpage;
				var startpage = data[data.length-1].startpage; 
				var endpage = data[data.length-1].endpage;
				var bottomlimit = data[data.length-1].bottomlimit;
				var listcount = data[data.length-1].listcount;
				var search = data[data.length-1].search;
				var prev = "";
				var next = "";

				$("#SpringBoardProject .Spring_Board_List h3").html("북마켓 '<strong>"+search+"</strong>'의 검색 결과입니다.");
				
				for(var i=0; i<data.length-1; i++) {
					var tr = $("<div class='col-sm-3'>");
					var bfile = $("<a href='${pageContext.request.contextPath}/board/board_detail?bno="+data[i].bno+"'>")
					var img = $("<img src='${pageContext.request.contextPath}/upload/"+data[i].bfile.split("/")[0]+"' style='height:200px;width:250px;' />");
					bfile.append(img);
					var btitle = $("<p>").html("<a href='${pageContext.request.contextPath}/board/board_detail?bno="+data[i].bno+"'>"+data[i].btitle+"</p>");
					var bdate = $("<p>").html(data[i].bdate);
					var bhit = $("<p>").html("<strong>조회수 </strong>| <strong>"+data[i].bhit+"</strong>");
					var bname = $("<p>").html("<strong>작성자 </strong>| <strong>"+data[i].bname+"</strong>");
					tr.append(bfile).append(btitle).append(bname).append(bhit).append(bdate);
					$("#SpringBoardProject #sellBookList").append(tr);
				}
				
				var ul = $("<ul class='pagination'>");
				if(startpage >= bottomlimit) {
					prev = $("<li>").html("<a href='${pageContext.request.contextPath}/board/search_json?pstartno="+((startpage-2)*onepagelimit)+"&search="+search+"' class='prev'>[이전]</a>");
					ul.append(prev);
				}
				
				for(var i= startpage; i<=endpage; i++) {
					var li = "";
					if(currentpage == i) {
						li = $("<li class='active'>").html("<a href='${pageContext.request.contextPath}/board/search_json?pstartno="+(i-1)*onepagelimit+"&search="+search+"' id='button' class='btn btn-default'>"+i+"</a>");
					} else {
						li = $("<li>").html("<a href='${pageContext.request.contextPath}/board/search_json?pstartno="+(i-1)*onepagelimit+"&search="+search+"' id='button' class='btn btn-default'>"+i+"</a>");
					}
					ul.append(li);
				}

				if(pagetotal> endpage ) {
					next = $("<li>").html("<a href='${pageContext.request.contextPath}/board/search_json?pstartno="+(endpage*onepagelimit)+"&search="+search+"' class='next'>[다음]</a>");
					ul.append(next);
				}
				$("#SpringBoardProject .listbutton").append(ul); }
		}, error : function(xhr, textStatus, errorThrown) { alert(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+") \n\n문제발생! 관리자에게 문의주세요!"); }
	});
}

function listAll(pstartno) {
	$.ajax({
		url : "${pageContext.request.contextPath}/board/list_json",
		type : "get",
		dataType : "json",
		data : { "pstartno" : pstartno },
		success : function(data) {
			$("#SpringBoardProject #sellBookList").empty();
			$("#SpringBoardProject .listbutton").empty();

			var onepagelimit = data[data.length-1].onepagelimit;
			var pagetotal = data[data.length-1].pagetotal;
			var currentpage = data[data.length-1].currentpage;
			var startpage = data[data.length-1].startpage; 
			var endpage = data[data.length-1].endpage;
			var bottomlimit = data[data.length-1].bottomlimit;
			var listcount = data[data.length-1].listcount;
			var prev = "";
			var next = "";

			for(var i=0; i<data.length-1; i++) {
				var tr = $("<div class='col-sm-3'>");
				var bfile = $("<a href='${pageContext.request.contextPath}/board/board_detail?bno="+data[i].bno+"'>")
				var img = $("<img src='${pageContext.request.contextPath}/upload/"+data[i].bfile.split("/")[0]+"' style='height:200px;width:250px;' />");
				bfile.append(img);
				var btitle = $("<p>").html("<a href='${pageContext.request.contextPath}/board/board_detail?bno="+data[i].bno+"'>"+data[i].btitle+"</p>");
				var bdate = $("<p>").html(data[i].bdate);
				var bhit = $("<p>").html("<strong>조회수 </strong>| <strong>"+data[i].bhit+"</strong>");
				var bname = $("<p>").html("<strong>작성자 </strong>| <strong>"+data[i].bname+"</strong>");
				tr.append(bfile).append(btitle).append(bname).append(bhit).append(bdate);
				$("#SpringBoardProject #sellBookList").append(tr);
			}
			
			var ul = $("<ul class='pagination'>");
			if(startpage >= bottomlimit) {
				prev = $("<li>").html("<a href='${pageContext.request.contextPath}/board/list_json?pstartno="+((startpage-2)*onepagelimit)+"' class='prev'>[이전]</a>");
				ul.append(prev);
			}
			
			for(var i= startpage; i<=endpage; i++) {
				var li = "";
				if(currentpage == i) {
					li = $("<li class='active'>").html("<a href='${pageContext.request.contextPath}/board/list_json?pstartno="+(i-1)*onepagelimit+"' id='button' class='btn btn-default'>"+i+"</a>");
				} else {
					li = $("<li>").html("<a href='${pageContext.request.contextPath}/board/list_json?pstartno="+(i-1)*onepagelimit+"' id='button' class='btn btn-default'>"+i+"</a>");
				}
				ul.append(li);
			}

			if(pagetotal> endpage ) {
				next = $("<li>").html("<a href='${pageContext.request.contextPath}/board/list_json?pstartno="+(endpage*onepagelimit)+"' class='next'>[다음]</a>");
				ul.append(next);
			}
			$("#SpringBoardProject .listbutton").append(ul);
		}, error : function(xhr, textStatus, errorThrown) { $("#boardList tbody").html(textStatus+" (HTTP-"+xhr.status+"/"+errorThrown+")"); }
	});
}
</script>
<jsp:include page="/inc/footer.jsp"></jsp:include>