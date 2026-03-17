<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
  #footer {
  	margin: 3% 0px 0px 0px;
    background-color: #EAEAEA;
    padding: 10px;
    height: 300px;
    border-bottom : 1px solid #ddd;
  }
  #footer .fo2 img {
	height : 150px;
	}
	
  #footer .addrli >li{
    list-style-type: none;
    margin-left: -37px;
	}
	
	#footer .navbar-nav>li>a {
    padding-top: 0;
    color: black;
    font-family: Lato;
	}
</style>

<!-- Footer -->
<div id="footer">
	<div class="myfooter fo1">
		<div class="container">
			<nav class="navbar">
				<div class="container-fluid">
					<ul class="nav navbar-nav">
						<li><a href="http://adnerwin.cafe24.com/test/notice1board1/footer_1.jsp">고객센터</a></li>
						<li><a href="http://adnerwin.cafe24.com/test/loginBoard/footer_2.jsp">1:1 문의</a></li>
						<li><a href="http://adnerwin.cafe24.com/test/loginBoard/footer_3.jsp">이용약관</a></li>
						<li><a href="http://adnerwin.cafe24.com/test/loginBoard/map.jsp">길 찾기</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
	<div class="myfooter fo2">
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<img src="<%=request.getContextPath()%>/logo/logofoot.png" />
				</div>
				<div class="col-sm-5">
					<h4>북마켓 (주)</h4>
					<ul class="addrli">
						<li>개발자 : 이은정</li>
						<li>E-mail : adnerwin@naver.com</li>
					</ul>
					<p>Copyright ⓒ 2019 company. All Rights Reserved.</p>
				</div>
				<div class="col-sm-3">
					<img src="<%=request.getContextPath()%>/logo/qrcode.png" />
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>