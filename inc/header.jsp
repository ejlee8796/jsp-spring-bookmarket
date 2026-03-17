<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>북마켓 | BOOK MARKET</title>
<!-- 파비콘 -->
<link rel="shortcut icon" href="<%=request.getContextPath()%>/logo/logo.png" type="image/x-icon" >
<link rel="icon" href="<%=request.getContextPath()%>/logo/logo.png" type="image/x-icon" >
<!-- CSS : 꾸미기 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bookblock.css" />
<!-- custom demo style -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo3.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
a { color : #333; }
body {
    font: 400 15px/1.8 Lato, sans-serif;
    color: #000000;
}
legend {
    position: absolute;
    left: -9999%;
    width: 1px;
    height: 1px;
    overflow: hidden;
    font-size: 0;
    line-height: 0;
}
textarea { resize: none; }

caption {
    position: absolute;
    left: -9999%;
    width: 1px;
    height: 1px;
    overflow: hidden;
    font-size: 0;
    line-height: 0;
}
div#bookmark5 {

    height: auto;

    min-height: 1200px;

}
#header .he3 {
font-style: Lato; font-weight: bold;
   color: #000; border-radius: 0; 
   font-size: 15px; 
   padding: 5px; 
   border-top : 1px solid #ddd;
border-bottom : 2px solid #333;
   }
   
input#search {
    padding: 5px;
    margin-top: 6%;
    margin-right: 2%;
    width: 60%;
    border: 1px solid #ccc;
    border-radius: 6px;
}
#bookmarket2 img{
    height: 600px;
    width: 1100px;
}
input#search #button{ vertical-align: middle; }
#logoimg {
    width: 130px;
    margin-bottom: 13%;
}
#slide { width: 1140px; height: 300px; }

#main { margin: 5% 0px 0px 0px;}

#bookmarket1 img{
    height: 300px;
    width : 400px;
}
#bookmarket1 .demo-3 body {
   color: #87968e;
   background: white;
}

#bookmarket2 {
    margin-top: 2%;
}

#bookstory { margin: 1% 1% 0px 0px; }
#bookstorycomment { margin: 1% 0px 0px 0px; }
#bookstory #mainbookborder {
    margin-bottom: 20px;
    background-color: #fff;
    border: 1px solid transparent;
    box-shadow: 0 1px 1px rgba(0,0,0,0.05);
}
#bookstory .panel {
	box-shadow: 0 0 0 transparent;
	-webkit-box-shadow: 0 1px 1px transparent;
}
#modifybookstory { margin: 1% 1% 0px 0px; }
#modifybookstory #modifystorydiv {
	margin: 1% 1% 0px 0px;
    margin-bottom: 20px;
    background-color: #fff;
    border: 1px solid transparent;
    box-shadow: 0 1px 1px rgba(0,0,0,0.05);
}
#springWrite1 { 
	margin: 1% 0px 0px 0px;
}
#SpringBoardProject div#sellBookList {
    overflow: hidden;
}

#SpringBoardProject .Spring_Board_List .col-sm-3 a {
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
    display: inline-block;
    width: 100%;
}

#SpringBoardProject #sellBookList{
	margin: 3% 0px 0px 0px;
}
#SpringBoardProject .array{
	margin: 2% 0px 0px 0px;
}

#SpringBoardProject input#searchBtitlespring {
   	vertical-align: middle;
}

#SpringBoardProject input#searchspringB {
   	vertical-align: middle;
   	padding : 5px;
	margin : 3%;
	width : 70%;
	border : 1px solid #ccc;
	border-radius : 6px;
}
#GallerySliding1 #moveList {
   	vertical-align: middle;
   	margin : 5%;
}

#GallerySliding1 .row {
   	margin: 3% 0px 0px 0px;
}

div#GallerySliding1  .container{
    height: 600px;
    background-color: aliceblue;
    border-radius: 40px;
    margin-top: 30px;
}

div#myCarousel {
    overflow: hidden;
    margin-top: 30px;
    border-radius: 38px;
}

div#myCarousel img {
    height: 500px;;
    width: 100%;
}
textarea { resize: none; }
#photoComment #file {
	height : 54px;
}

#photoComment div#comment_list form {
    border-bocttom: 3px solid #ddd;
    margin-bottom: 5%;
}

#photoComment textarea.form-control {
    height: 100px;
}
#photoComment .recomandTitle {
    margin-bottom: 2%;
}
#photoComment .hum {
    margin-bottom: 2%;
}
</style>
</head>
<body>
<div id="header">
	<div class="cheader he1">
		<div class="container">
			<div class="collapse navbar-collapse">
				 <ul class="nav navbar-nav navbar-right">
				  <%if((String)session.getAttribute("id") == null) {%>
			        <li><a href="http://adnerwin.cafe24.com/test/loginBoard/login.jsp">로그인</a></li>
			        <li><a href="http://adnerwin.cafe24.com/test/loginBoard/join_agree.jsp">회원가입</a></li>
			      <%} else {%>
			        <li><a href="<%=request.getContextPath()%>/board/logout">로그아웃</a></li>
			        <li><a href="http://adnerwin.cafe24.com/test/Mypage?mbid=<%=(String)session.getAttribute("id")%>&page=mypage">마이페이지</a></li>
			       <%}%>
			        <li><a href="http://adnerwin.cafe24.com/test/notice1board1/footer_1.jsp">고객센터</a></li>
			     </ul>
			</div>
		</div>
	</div> <!-- end he1 -->
	<div class="cheader he2">
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<a href="http://adnerwin.cafe24.com/test/index.jsp"><img src="<%=request.getContextPath()%>/logo/logo.png" alt="" id="logoimg" /></a>
				</div>
				<div class="col-sm-9">
					<input type="search" id="search" name="search" size="20" title="북마켓 검색어 입력" placeholder="검색어를 입력하세요." required>
			 		<input type="button" id="button" value="검색" title="북마켓  검색" class="btn btn-default"/>
			 	</div>
			</div>
		</div>
	</div> <!-- end he2 -->
	<div class="cheader he3">
		<div class="container">
			<div class="row">
				<div class="col-sm-2">
					<ul class="nav navbar-nav">
				      <li class="dropdown">
				        <a class="dropdown-toggle" data-toggle="dropdown" href="#">국내/외국 도서</a>
				        <ul class="dropdown-menu">
				          <li><a href="http://adnerwin.cafe24.com/test/search_index/poem.jsp">소설/시/희곡</a></li>
				          <li><a href="http://adnerwin.cafe24.com/test/search_index/essay.jsp">에세이</a></li>
				          <li><a href="http://adnerwin.cafe24.com/test/search_index/toon.jsp">만화/라이트노벨</a></li>
				          <li><a href="http://adnerwin.cafe24.com/test/search_index/foreign.jsp">외국어</a></li>
				          <li><a href="http://adnerwin.cafe24.com/test/search_index/economic.jsp">경제 경영</a></li>
				          <li><a href="http://adnerwin.cafe24.com/test/search_index/self.jsp">자기계발</a></li>
				          <li><a href="http://adnerwin.cafe24.com/test/search_index/computer.jsp">컴퓨터</a></li>
				          <li><a href="http://adnerwin.cafe24.com/test/search_index/religion.jsp">종교</a></li>
				        </ul>
				      </li>
				    </ul>
				</div>
				<div class="col-sm-2">
				<%if((String)session.getAttribute("id") == null){%>
	  				<a href="http://adnerwin.cafe24.com/project0709/cal/calendar">출석체크</a>
	  			<%} else {%>
					<a href="http://adnerwin.cafe24.com/project0709/cal/calendar?id=<%=(String)session.getAttribute("id")%>">출석체크</a>
				<%}%>
				</div>
				<div class="col-sm-2"><a href="<%=request.getContextPath()%>/index/galleryRoom">eBook Gallery</a></div>
				<div class="col-sm-2"><a href="http://adnerwin.cafe24.com/test/index1view1/eventList.jsp">이벤트</a></div>
				<div class="col-sm-2">
				<%if((String)session.getAttribute("id") == null){%>
	  				<a href="http://adnerwin.cafe24.com/spring0716">중고거래</a>
	  			<%} else {%>
					<a href="http://adnerwin.cafe24.com/spring0716/index/galleryIndex?id=<%=(String)session.getAttribute("id")%>">중고거래</a>
				<%}%>
				</div>
				<div class="col-sm-2"><a href="http://adnerwin.cafe24.com/spring0716/index/bookblock">포트폴리오</a></div>
			</div>
		</div>
	</div> <!-- end he3 -->
</div><!-- end header -->
<script>
$(function(){
	$("#button").click(function(){
		if($("#search").val() == "") { alert("검색어를 입력하세요."); $("#search").focus(); 
		} else { location.href="http://adnerwin.cafe24.com/test/search_index/searchTotal.jsp?search="+$("#search").val(); }
	});
});
</script>