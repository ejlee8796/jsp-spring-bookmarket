<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<link rel="shortcut icon" href="<%=request.getContextPath()%>/logo/logo.png" type="image/x-icon" >
		<link rel="icon" href="<%=request.getContextPath()%>/logo/logo.png" type="image/x-icon" >
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>북마켓 | BOOK MARKET</title>
		<meta name="description" content="Add a description" />
		<meta name="keywords" content="Add keywords" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"> 
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default1.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/component2.css" />
		<script src="${pageContext.request.contextPath}/js/modernizr.custom.js"></script>
	</head>
	<body>
		<div class="container">
			<h1>eBook Room <a href="http://adnerwin.cafe24.com/test" title="메인화면" >BookMarket</a></h1>
			<div id="gr-gallery" class="gr-gallery">
				<div class="gr-main">
					<figure>
						<div>
							<img src="${pageContext.request.contextPath}/images/1.jpg" alt="img01" />
						</div>
						<figcaption>
							<h2><span>언니, 내가 남자를 죽였어</span></h2>
							<div>
								<dl>
									<dt>Writer</dt><dd>오인칸 브레이스웨이트 Oyinkan Braithwaite</dd>
									<dt>Genre</dt><dd>추리/미스터리/스릴러</dd>
									<dt>Publisher</dt><dd>천문장</dd>
									<dt>Comment</dt><dd>사려 깊은 그녀에게 살인습관 여동생이 있다</dd>
								</dl>
							</div>
						</figcaption>
					</figure>
					<figure>
						<div>
							<img src="${pageContext.request.contextPath}/images/2.jpg" alt="img02" />
						</div>
						<figcaption>
							<h2><span>상수리나무 아래</span></h2>
							<div>
								<dl>
									<dt>Writer</dt><dd>김수지</dd>
									<dt>Genre</dt><dd>로맨스/판타지</dd>
									<dt>Publisher</dt><dd>로즈엔</dd>
									<dt>Comment</dt><dd>너를 생각하면 생각할수록 나는 고독하고 외로워져 </dd>
									<dd>이렇게나 괴로운데도 그만 둘 수 없는 이유를 도무지 모르겠어</dd>
								</dl>
							</div>
						</figcaption>
					</figure>
					<figure>
						<div>
							<img src="${pageContext.request.contextPath}/images/3.jpg" alt="img03" />
						</div>
						<figcaption>
							<h2><span>아몬드</span></h2>
							<div>
								<dl>
									<dt>Writer</dt><dd>손원병</dd>
									<dt>Genre</dt><dd>어린이/청소년</dd>
									<dt>Publisher</dt><dd>창비</dd>
									<dt>Comment</dt><dd>그러니까 마지막으로 변명을 하자면 사실 어떤 이야기가 비극인지 희극인지는 당신도 나도 누구도, 영원히 알 수 없는 일이다</dd>
								</dl>
							</div>
						</figcaption>
					</figure>
					<figure>
						<div>
							<img src="${pageContext.request.contextPath}/images/4.jpg" alt="img04" />
						</div>
						<figcaption>
							<h2><span>아가씨와 밤</span></h2>
							<div>
								<dl>
									<dt>Writer</dt><dd>기욤 뮈소 Guillaume Musso</dd>
									<dt>Genre</dt><dd>스릴러/프랑스소설</dd>
									<dt>Publisher</dt><dd>도서출판 밝은세상</dd>
									<dt>Comment</dt><dd>완벽하게 숨긴 25년 전 살인, 누군가 그 비밀을 알고 있다</dd>
								</dl>
							</div>
						</figcaption>
					</figure>
					<figure>
						<div>
							<img src="${pageContext.request.contextPath}/images/5.jpg" alt="img01" />
						</div>
						<figcaption>
							<h2><span>최선의 삶</span></h2>
							<div>
								<dl>
									<dt>Writer</dt><dd>임솔아</dd>
									<dt>Genre</dt><dd>한국소설</dd>
									<dt>Publisher</dt><dd>문학동네</dd>
									<dt>Comment</dt><dd>더 나아지기 위해서 우리는 기꺼이 더 나빠졌다</dd>
									<dd>떠나거나 버려지거나 망가뜨리거나 망가지거나</dd>
								</dl>
							</div>
						</figcaption>
					</figure>
					<figure>
						<div>
							<img src="${pageContext.request.contextPath}/images/6.jpg" alt="img02" />
						</div>
						<figcaption>
							<h2><span>나니아 연대기</span></h2>
							<div>
								<dl>
									<dt>Writer</dt><dd>C. S. 루이스 C.S. Lewis, Clive Staples Lewis</dd>
									<dt>Genre</dt><dd>판타지/외국소설</dd>
									<dt>Publisher</dt><dd>시공주니어</dd>
									<dt>Comment</dt><dd>모든 것이 시작된 질문, “옷장 안에 무엇이 있는데요?”</dd>	
								</dl>
							</div>
						</figcaption>
					</figure>
					<figure>
						<div>
							<img src="${pageContext.request.contextPath}/images/7.jpg" alt="img03" />
						</div>
						<figcaption>
							<h2><span>MAD 매드</span></h2>
							<div>
								<dl>
									<dt>Writer</dt><dd>클로이 에스포지토(Chloé Esposito)</dd>
									<dt>Genre</dt><dd>추리/스릴러/미스터리</dd>
									<dt>Publisher</dt><dd>북폴리오</dd>
									<dt>Comment</dt><dd>“내 남편이 갖고싶어?” 친언니가 말했다</dd>	
								</dl>
							</div>
						</figcaption>
					</figure>
					<figure>
						<div>
							<img src="${pageContext.request.contextPath}/images/8.jpg" alt="img08" />
						</div>
						<figcaption>
							<h2><span>양영순의 천일야화</span></h2>
							<div>
								<dl>
									<dt>Writer</dt><dd>양연순</dd>
									<dt>Genre</dt><dd>기타국가소설/만화</dd>
									<dt>Publisher</dt><dd>김영사</dd>
									<dt>Comment</dt><dd>첫날밤의 맹세</dd>	
								</dl>
							</div>
						</figcaption>
					</figure>
					<figure>
						<div>
							<img src="${pageContext.request.contextPath}/images/9.jpg" alt="img09" />
						</div>
						<figcaption>
							<h2><span>빨간 머리 앤</span></h2>
							<div>
								<dl>
									<dt>Writer</dt><dd>루시 모드 몽고메리 Lucy Maud Montgomery</dd>
									<dt>Genre</dt><dd>영미소설/고전문학</dd>
									<dt>Publisher</dt><dd>허밍버스</dd>
									<dt>Comment</dt><dd>우리는 어느 시절 모두 앤이었다</dd>
									<dd>그러니 그 아이를 사랑할밖에, 앤의 이름을 듣는 것만으로도 눈물이 날밖에</dd>	
								</dl>
							</div>
						</figcaption>
					</figure>
					<figure>
						<div>
							<img src="${pageContext.request.contextPath}/images/10.jpg" alt="img10" />
						</div>
						<figcaption>
							<h2><span>잠중록</span></h2>
							<div>
								<dl>
									<dt>Writer</dt><dd>처처칭한</dd>
									<dt>Genre</dt><dd>중국소설</dd>
									<dt>Publisher</dt><dd>arte</dd>
									<dt>Comment</dt><dd>너 역시 나처럼 운명을 믿지 않는구나</dd>
								</dl>
							</div>
						</figcaption>
					</figure>
				</div>
				<div class="gr-man">
					<img src="${pageContext.request.contextPath}/images/man.png" alt="img01" />
				</div>
			</div>
		</div><!-- /container -->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/wallgallery.js"></script>
		<script>
			$(function() {
				Gallery.init( {
					layout : [3,2,3,2]
				} );

			});
		</script>
	</body>
</html>