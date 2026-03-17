<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="GallerySliding1">
	<div class="container">
		<h3 class="">북마켓 <strong>지금, 인기 판매글</strong></h3>
		<div class="nav nav-tabs text-right">
			<a data-toggle="tab" href="#menu1" title="조회순">조회순</a>
			<a data-toggle="tab" href="#menu2" title="최신순">최신순</a>
		</div>
		<div class="tab-content">
			<div id="menu1" class="tab-pane fade in active">
				<div class="row">
					<c:forEach items="${vo}" var="i">
						<div  class="col-sm-4">
							<div class="modal fade" id="m${i.bno}" role="dialog">
								<div class="modal-dialog">
							    <!-- Modal content-->
							    	<div class="modal-content">
							    		<div class="modal-header">
							    			<span>내용 미리보기</span>
							    			<button type="button" class="close" data-dismiss="modal">×</button>
							    		</div>
								        <div class="modal-body">
								        	<p class="text-center"><img src="<%=request.getContextPath()%>/upload/${fn:split(i.bfile,'/')[0]}" style="height:200px;width:300px;"/></p>
							        		<div class="panel-group" id="accordion">
												<div class="panel panel-default">
												    <div class="panel-heading">
											     		<h4 class="panel-title">
											        		<a data-toggle="collapse" data-parent="#accordion" href="#collapse${i.bno}">${i.bname}님의 작성글</a>
											      		</h4>
												    </div>
												    <div id="collapse${i.bno}" class="panel-collapse collapse">${i.bcontent}
												    <p class="text-center"><a href="<%=request.getContextPath()%>/board/board_detail?bno=${i.bno}">게시글 보러가기</a></p>
												    </div>
												</div>
											</div>
								        </div>
							    	</div>
							    </div>
							</div>
							<img src="<%=request.getContextPath()%>/upload/${fn:split(i.bfile,'/')[0]}" style="height:200px;width:300px;" data-toggle="modal" data-target="#m${i.bno}"/>				
							<p><a href="<%=request.getContextPath()%>/board/board_detail?bno=${i.bno}"><strong>${i.btitle}</strong></a></p>
							<p><strong>조회수</strong> | <strong>${i.bhit}</strong></p>
							<p><strong>작성자</strong> | <strong>${i.bname}</strong></p>
							<p>${i.bdate}</p>
						</div>
					</c:forEach>
				</div>
			</div>
		  	<div id="menu2" class="tab-pane fade">
		  		<div class="row">
					<c:forEach items="${tab2}" var="i">
						<div  class="col-sm-4">
							<div class="modal fade" id="mm${i.bno}" role="dialog">
								<div class="modal-dialog">
							    <!-- Modal content-->
							    	<div class="modal-content">
							    		<div class="modal-header">
							    			<span>내용 미리보기</span>
							    			<button type="button" class="close" data-dismiss="modal">×</button>
							    		</div>
								        <div class="modal-body">
								        	<p class="text-center"><img src="<%=request.getContextPath()%>/upload/${fn:split(i.bfile,'/')[0]}" style="height:200px;width:300px;"/></p>
							        		<div class="panel-group" id="accordion">
												<div class="panel panel-default">
												    <div class="panel-heading">
											     		<h4 class="panel-title">
											        		<a data-toggle="collapse" data-parent="#accordion" href="#collapse${i.bno}">${i.bname}님의 작성글</a>
											      		</h4>
												    </div>
												    <div id="collapse${i.bno}" class="panel-collapse collapse">${i.bcontent}
												    <p class="text-center"><a href="<%=request.getContextPath()%>/board/board_detail?bno=${i.bno}">게시글 보러가기</a></p>
												    </div>
												</div>
											</div>
								        </div>
							    	</div>
							    </div>
							</div>
							<img src="<%=request.getContextPath()%>/upload/${fn:split(i.bfile,'/')[0]}" style="height:200px;width:300px;" data-toggle="modal" data-target="#mm${i.bno}"/>				
							<p><a href="<%=request.getContextPath()%>/board/board_detail?bno=${i.bno}"><strong>${i.btitle}</strong></a></p>
							<p><strong>조회수</strong> | <strong>${i.bhit}</strong></p>
							<p><strong>작성자</strong> | <strong>${i.bname}</strong></p>
							<p>${i.bdate}</p>
						</div>
					</c:forEach>
				</div>
		  	</div>
		</div>
		<div class="form-group text-center">
			<%if((String)session.getAttribute("id") == null){%>
  				<a href="<%=request.getContextPath()%>/board/list" id="moveList" class="btn btn-success" ><span class="glyphicon glyphicon-hand-right"> 더보러가기</span></a>
  			<%} else {%>
				<a href="<%=request.getContextPath()%>/board/list?id=<%=(String)session.getAttribute("id")%>" id="moveList" class="btn btn-success" ><span class="glyphicon glyphicon-hand-right"> 더보러가기</span></a>
			<%}%>
		</div>
	</div>
</div>