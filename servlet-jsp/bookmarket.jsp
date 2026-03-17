<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="bookmarket1">
	<!-- Top Navigation -->
	<div class="bb-custom-wrapper">
		<div id="bb-bookblock" class="bb-bookblock">
			<div class="bb-item">
				<img src="${pageContext.request.contextPath}/images/book001.png" alt="image01"/>
			</div>
			<div class="bb-item">
				<img src="${pageContext.request.contextPath}/images/book002.png" alt="image02"/>
			</div>
			<div class="bb-item">
				<img src="${pageContext.request.contextPath}/images/book003.png" alt="image03"/>
			</div>
			<div class="bb-item">
				<img src="${pageContext.request.contextPath}/images/book004.png" alt="image04"/>
			</div>
			<div class="bb-item">
				<img src="${pageContext.request.contextPath}/images/book005.png" alt="image05"/>
			</div>
			<div class="bb-item">
				<img src="${pageContext.request.contextPath}/images/book006.png" alt="image06"/>
			</div>
			<div class="bb-item">
				<img src="${pageContext.request.contextPath}/images/book007.png" alt="image07"/>
			</div>
		</div>
		<!-- <p>Illustrations by <a href="http://dribbble.com/kevinhowdeshell">Kevin Howdeshell</a></p> -->
		<nav>
			<a id="bb-nav-last" href="#" class="bb-custom-icon bb-custom-icon-first">First page</a>
			<a id="bb-nav-next" href="#" class="bb-custom-icon bb-custom-icon-arrow-left">Next</a>
			<a id="bb-nav-prev" href="#" class="bb-custom-icon bb-custom-icon-arrow-right">Previous</a>
			<a id="bb-nav-first" href="#" class="bb-custom-icon bb-custom-icon-last">Last page</a>
		</nav>
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

				// add navigation events
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
				
				// add swipe events
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

				// add keyboard events
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