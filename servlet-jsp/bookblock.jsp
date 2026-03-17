<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="inc/header.jsp"%>
<div class="container">
	<div id="bookmarket2">
		<div class="bb-custom-wrapper">
			<div id="bb-bookblock" class="bb-bookblock">
				<c:forEach var="i" begin="1" end="19" step="1">
					<div class="bb-item">
						<img src="${pageContext.request.contextPath}/images/sliding${i}.png" alt="sliding${i}"/>
					</div>
				</c:forEach>
			</div>
			<!-- <p>Illustrations by <a href="http://dribbble.com/kevinhowdeshell">Kevin Howdeshell</a></p> -->
			<nav>
				<a id="bb-nav-last" href="#" class="bb-custom-icon bb-custom-icon-first">First page</a>
				<a id="bb-nav-next" href="#" class="bb-custom-icon bb-custom-icon-arrow-left">Next</a>
				<a id="bb-nav-prev" href="#" class="bb-custom-icon bb-custom-icon-arrow-right">Previous</a>
				<a id="bb-nav-first" href="#" class="bb-custom-icon bb-custom-icon-last">Last page</a>
			</nav>
		</div>
	</div>
</div><!-- /container -->
<script src="${pageContext.request.contextPath}/js/modernizr.custom.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquerypp.custom.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.bookblock.js"></script>
<script>
	var Page = (function() {
		var config = {
				$bookBlock : $( '#bb-bookblock' ),
				$navNext : $( '#bb-nav-prev'  ),
				$navPrev : $( '#bb-nav-next' ),
				$navFirst : $( '#bb-nav-first' ),
				$navLast : $( '#bb-nav-last' )
			},
			init = function() {
				config.$bookBlock.bookblock( {
					speed : 800,
					direction: 'rtl',
					shadowSides : 0.8,
					shadowFlip : 0.7
				} );
				initEvents();
			},
			initEvents = function() {
				
				var $slides = config.$bookBlock.children();

				config.$navNext.on( 'click touchstart', function() {
					config.$bookBlock.bookblock( 'next' );
					return false;
				} );

				config.$navPrev.on( 'click touchstart', function() {
					config.$bookBlock.bookblock( 'prev' );
					return false;
				} );

				config.$navFirst.on( 'click touchstart', function() {
					config.$bookBlock.bookblock( 'last' );
					return false;
				} );

				config.$navLast.on( 'click touchstart', function() {
					config.$bookBlock.bookblock( 'first' );
					return false;
				} );
				
				$slides.on( {
					'swipeleft' : function( event ) {
						config.$bookBlock.bookblock( 'prev' );
						return false;
					},
					'swiperight' : function( event ) {
						config.$bookBlock.bookblock( 'next'  );
						return false;
					}
				} );

				$( document ).keydown( function(e) {
					var keyCode = e.keyCode || e.which,
						arrow = {
							left : 37,
							up : 38,
							right : 39,
							down : 40
						};

					switch (keyCode) {
						case arrow.left:
							config.$bookBlock.bookblock( 'next' );
							break;
						case arrow.right:
							config.$bookBlock.bookblock( 'prev' );
							break;
					}
				} );
			};

			return { init : init };
	})();
</script>
<script>
		Page.init();
</script>

<%@include file="inc/footer.jsp"%>