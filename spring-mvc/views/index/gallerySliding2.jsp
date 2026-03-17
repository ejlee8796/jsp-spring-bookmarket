<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="GallerySliding2">
	<div class="container" >
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
			<ol class="carousel-indicators">
			      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			      <li data-target="#myCarousel" data-slide-to="1" class=""></li>
			      <li data-target="#myCarousel" data-slide-to="2" class=""></li>
			</ol>
			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
			<c:set var="check" value="true"/>
			<c:forEach items="${vo}" var="item" >
				<c:choose>
					<c:when test="${check eq 'true'}">
						<div class="item active">
							<a href="<%=request.getContextPath()%>/board/board_detail?bno=${item.bno}">
								<img src="<%=request.getContextPath()%>/upload/${fn:split(item.bfile,'/')[0]}" class="text-center"/>
							</a>
						</div>
						<c:set var="check" value="false" />
					</c:when>
					<c:otherwise>
						<div class="item">
							<a href="<%=request.getContextPath()%>/board/board_detail?bno=${item.bno}">
								<img src="<%=request.getContextPath()%>/upload/${fn:split(item.bfile,'/')[0]}" class="text-center"/>
							</a>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
	  		</div>
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
