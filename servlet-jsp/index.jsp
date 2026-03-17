<%@page import="com.company.dto.BDto"%>
<%@page import="java.util.*"%>
<%@page import="org.w3c.dom.*"%>
<%@page import="project_index.IndexList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/header.jsp"%>
<div id="visual">
	<div class="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
		      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		      <li data-target="#myCarousel" data-slide-to="1" class=""></li>
		      <li data-target="#myCarousel" data-slide-to="2" class=""></li>
		    </ol>
			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
     	 	<%
     	 		IndexList list = new IndexList();
     	 		ArrayList<BDto> allList = list.List("index1board1", "sliding", "");
     	 		Iterator<BDto> iter = allList.iterator();
     	 		
     	 		boolean check = true;
     	 		while(iter.hasNext()) {
     	 		BDto dto = iter.next();		
     	 		if(check) {
     	 	%>
				<div class="item active"><%check = false; } else {%>
				<div class="item"><%}%><a href="<%=dto.getBlink()%>" ><img src="maintabimg/<%=dto.getBfile()%>" /></a>
			    </div><%}%>
			    <!-- Left and right controls -->
			    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
			      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			      <span class="sr-only">Previous</span>
			    </a>
			    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
			      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			      <span class="sr-only">Next</span>
			    </a>
				</div>
			</div>
		</div>
	</div>
</div> <!-- end visual -->
<div id="new">
	<div class="container">
		<div class="cnew ne1">
			<ul class="nav nav-tabs">
			  <li><a data-toggle="tab" href="#indexmenu0" title="BookMarket's Choice">BookMarket's Choice</a></li>
			  <li><a data-toggle="tab" href="#indexmenu1" title="스테디 셀러">스테디 셀러</a></li>
			  <li><a data-toggle="tab" href="#indexmenu2" title="화제의 만화">화제의 만화</a></li>
			  <li><a data-toggle="tab" href="#indexmenu3" title="eBook 눈에 띄는 책">eBook 눈에 띄는 책</a></li>
			  <li><a data-toggle="tab" href="#indexmenu4" title="Book Story">Book Story</a></li>
			</ul>
			<div class="tab-content">
		<%	boolean indexView = true;
			String indexmenu[] = {"bookMarketChoice","steadySeller","hotBook","eBook"};
			for(int i=0; i<indexmenu.length; i++) {
				list = new IndexList();
				allList = list.tab("index1board2", indexmenu[i]);
				iter = allList.iterator();
			%><div id="indexmenu<%=i%>"<%if(indexView){%>class="tab-pane fade in active"<%indexView = false;} else{%>class="tab-pane fade"<%}%> ><%
			while(iter.hasNext()) {
				BDto dto = iter.next(); %>
					<div class="col-sm-4">
						<div class="text-center">
							<a href="search_index/bookDetail.jsp?isbn=<%=dto.getBisbn()%>"><img class="bfile" src="<%=dto.getBfile()%>"></a>
							<h4><strong><a href="search_index/bookDetail.jsp?isbn=<%=dto.getBisbn()%>"><%=dto.getBtitle()%></a></strong></h4>
							<blockquote class="quote_book"><a href="search_index/bookDetail.jsp?isbn=<%=dto.getBisbn()%>>"><%=dto.getBcontent()%></a></blockquote>
						</div>
			      	</div><%}%>
			      </div><%}
				if((String)session.getAttribute("id") == null){%>
		  		<div id="indexmenu4" class="tab-pane fade"><a href="http://adnerwin.cafe24.com/project0709/board/list"><img class="indexfile" src="maintabimg/indexmenu4.png"></a></div>
				<%} else {%>
				<div id="indexmenu4" class="tab-pane fade"><a href="http://adnerwin.cafe24.com/project0709/board/list?id=<%=(String)session.getAttribute("id")%>"><img class="indexfile" src="maintabimg/indexmenu4.png"></a></div>
				<%}%>
			</div> <!-- end tab-content -->
		</div> <!-- end ne1 -->
	</div>
</div> <!-- end new -->
<div id="choice"> <!-- ######## 책 1위 -->
	<div class="container">
		<div class="nav nav-tabs text-right">
			<h3><a data-toggle="tab" href="#menu11" title="주간 베스트셀러"><strong>주간 베스트셀러 |</strong></a></h3>
			<h3><a data-toggle="tab" href="#menu22" title="월간 베스트셀러">월간 베스트셀러</a></h3>
		</div>
		<div class="tab-content">
			<div id="menu11" class="tab-pane fade in active"><%@ include file= "bestSeller.jsp" %></div>
		  	<div id="menu22" class="tab-pane fade"><%@ include file="bestSellerM.jsp"%></div>
		</div>
	</div> <!-- end container -->
</div> <!-- end choice -->
<div id="post">
	<div class="cpost po1">
		<div class="container">
		<%  String indexmenu2title[] = {"#작가_특집","#여성_특집","#남성_특집"};
			String indexmenu2[] = {"writer","woman","man"};
			for(int i=0; i<indexmenu2.length; i++) {
			list = new IndexList();
			allList = list.List("index1board1", indexmenu2[i], "");
			iter = allList.iterator();
		%><h3><strong><%=indexmenu2title[i]%></strong></h3><div class="row"><%
			while(iter.hasNext()) {
				BDto dto = iter.next(); %>
				<div class="col-sm-2">
					<div class="thumbnail">
						<a href="indexDetail.jsp?category=<%=indexmenu2[i]%>&author=<%=dto.getBtitle()%>">
							<img class="bfile" src="maintabimg/<%=dto.getBfile()%>">
							<span class="content img_content"><%=dto.getBtitle()%></span>
						</a>
					</div>
		       </div><%}%>
			</div><%}%>
		</div>
	</div> <!-- end po1 -->
</div> <!-- end post -->
<div id="review">
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<h3><strong>#실시간 #지금_이_책 </strong></h3>
				<div id="mianRss">
					<div class="container mr1">
						<div class="form-group">
							<table class="table">
								<caption>책 추천</caption>
								<thead><tr><th scope="row">오늘 이 책 어때?</th></tr></thead>
								<tbody id="mr1body"></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<h3><strong>#실시간 #오늘의_독자_추천 #지금_무슨_책_읽고있나요?</strong></h3>
				<c:import url="http://adnerwin.cafe24.com/spring0716/photocomment/recommendComment"></c:import>
			</div>
		</div>
	</div>
</div> <!-- end review -->
<div id="card">
	<div class="container">
		<div class="row">
			<div class="cservice se1">
				<div class="col-sm-8">
					<h4><strong>북마켓 카드 리뷰</strong></h4>
					<c:import url="http://adnerwin.cafe24.com/test/bookmarket.jsp"></c:import>
				</div>
			</div>
			<div class="cservice se2">
				<div class="col-sm-4">
					<h4><strong>필! 독서 이벤트</strong></h4>
					<div class="row">
						<c:import url="http://adnerwin.cafe24.com/test/index1view1/eventIndex.jsp"></c:import>
					</div>
				</div>
			</div>
		</div>
	</div>
</div> <!-- end card -->
<script>
$(function(){
	$(".mr1 table").hide(); //table 숨기기
	
	$("li").addClass("select");
	$("li").mouseover(function(){
		$("li").removeClass("select");
		$(this).addClass("select");
	});
	
	if($("#mr1body").val() == "") {
		$.ajax({
			url : "loginBoard/book_name_rss.jsp", type : "get", dataType : "xml",
			success : function(xml) {
				$(".mr1 table").show(); //ajax에 연결 성공하면 테이블 보이기
				$(".mr1 tbody").empty(); //초기화
				var item = $(xml).find("item");
				for(var i=0; i<item.length; i++) {
					var tr = $("<tr>");
					var title = $(item[i]).find("title").text();
					var link = $(item[i]).find("link").text();
					title = $("<td>").html("<a href='"+link+"'>"+title+"</a>");
					link = $("<td>").html(link);
					tr.append(title)
					$(".mr1 tbody").append(tr); }
			}, error : function(xhr, textStatus, errorThrown) { $(".mr1").html(textStatus+" HTTP-"+xhr.status+errorThrown); }
		});
	}
});
</script>
<%@include file="inc/footer.jsp"%>