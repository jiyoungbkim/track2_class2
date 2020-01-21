<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="Author" content="구송이">
  <meta name="Keywords" content="문화재청 덕수궁, 반응형 홈페이지">
  <meta name="Description" content="응용sw개발자를 위한 반응형 홈페이지">
  <title>멜로디 디자인</title>
  <script src="js/jquery-3.3.1.min.js"></script>
  <link href="css/font-awesome.min.css" rel="stylesheet">
	<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->
  <link href="css/common.css" rel="stylesheet">
  <link href="css/layout.css" rel="stylesheet">	
 
 	<!-- jquery언어 사용법
	1. jquery.js 기반으로 프로그램을 작성하기 때문에 jquery.js 파일을 다운 또는 CDN 방식으로 링크 건다
	2. $(function() {
	실행문;
	});
	-->
	 
	
 </head>


<script>
		$(function(){
			$(".gnb>.nav_1depth>li").hover(function(){
				//console.log("gnb>.nav_1depth>li");
				//$(".main_center .box3 nav ul li").removeClass("active");
				//$(this).addClass("active");
				//$(".clearfix").hide();

				//var activeTab = $(this).find("a").attr("href"); 
                //$(activeTab).fadeIn(); 
                //return false;

				$(this).children(".nav_2depth").stop().slideDown("fast");
				}, function(){
					$(".gnb>.nav_1depth>li").removeClass();
					$(this).children(".nav_2depth").stop().slideUp("fast");
				}
			);
				//$(".main_center .box3 nav ul li").click(function(e){
					//$(this).addClass("active")
					//var num = $(this).index();
					//$(".taball").hide().eq(num).show();
				//}
		});
	</script>

 <body>
 <!-- 웹문서 만들기 기본 공식
 1. 요소를 어떻게 묶을 것인가? 그룹만들기
 2. 그룹안에 적절한 태그 사용
 3. class 이름 붙이고 css 적용
 -->
	<div class="sr-only">
		<p><a href="#contents">본문 바로가기</a></p>
	</div>
	
	<div class="top_navigation">
		<header class="header">
			<nav class="top_left">
				<ul>
					<li class="first"><a href="index.html">HOME</a></li>
					<li><a href="#">카카오톡</a></li>
					<li><a href="#">인스타그램</a></li>
					<li><a href="#">네이버톡톡</a></li>
				</ul>
			</nav>

			<nav class="top_right">
				<ul>
					<li class="first"><a href="admin.html">LOGIN</a></li>
					<li><a href="#">JOIN</a></li>
					<li><a href="#">CART</a></li>
					<li><a href="#">ORDER</a></li>
				</ul>
			</nav>

			<div class="gnb_group">
				<h1 class="logo">로고삽입</h1>
				<nav class="gnb">
					<ul class="nav_1depth">
						<li><a href="company01.html">COMPANY</a>
							<ul class="nav_2depth">
								<li><a href="company01.html">인사말</a></li>
								<li><a href="company02.html">회사연혁</a></li>
								<li><a href="company03.html">조직도</a></li>
								<li><a href="company04.html">오시는길</a></li>
							</ul>
						</li>
						<li><a href="business01.html">BUSINESS</a>
							<ul class="nav_2depth">
								<li><a href="business01.html">사업영역</a></li>
								<li><a href="business02.html">경영 마인드</a></li>
							</ul>
						</li>
						<li><a href="product01.html">PRODUCT</a>
							<ul class="nav_2depth">
								<li><a href="product01.html">갤러리 게시판</a></li>
								<li><a href="product02.html">유튜브</a></li>
							</ul>
						</li>
						<li><a href="board01.html">BOARD</a>
							<ul class="nav_2depth">
								<li><a href="">공지사항</a></li>
								<li><a href="">NEWS</a></li>
								<li><a href="">Q&A</a></li>
								<li><a href="">EVENT</a></li>
								<li><a href="">자주하는질문</a></li>
								<li><a href="">자유게시판</a></li>							
								<li><a href="board01.html">공지사항</a></li>
								<li><a href="board02.html">이용후기</a></li>
								<li><a href="board03.html">1:1 상담</a></li>
								<li><a href="board04.html">FAQ</a></li>
								<li><a href="board05.html">포트폴리오</a></li>
							</ul>
						</li>
						<li><a href="shop01.html">SHOP</a>
							<ul class="nav_2depth">
								<li><a href="shop01.html">카테고리1</a></li>
								<li><a href="shop02.html">카테고리2</a></li>
								<li><a href="shop03.html">카테고리3</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
		</header>
		<div class="line">
		</div>
	</div>

	<div class="sub_title">
		<h2>FAQ</h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="company01.html"><i class="fa fa-home"></i></a></li>
					<li class="dropdown">
						<a href="board01.html">BOARD<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="company01.html">COMPANY</a>
							<a href="business01.html">BUSINESS</a>
							<a href="product01.html">PRODUCT</a>
							<a href="board01.html">BOARD</a>
							<a href="shop01.html">SHOP</a>
						</div>
					</li>
					<li class="dropdown">
						<a href="board04.html">FAQ<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="board01.html">공지사항</a>
							<a href="board02.html">이용후기</a>
							<a href="board03.html">1:1 상담</a>
							<a href="board04.html">FAQ</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 <span>120</span>건</p>
				</div>
				<div class="search_group">
					<form name="myform" action="">
						<select name="sel" class="select">
							<option value="1">제목</option>
							<option value="2">내용</option>
						</select>
						<input type="text" name="search" class="search_word">
						<button class="btn_search"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
					</form>
				</div>
			</div>
		</div>
		<div class="board_list">
			<div class="faq_group">
				<ul>
					<li>
						<button class="accordion">
						전문가로 대우받기 위한 경력은 최소한 몇년?
						</button>
						<div class="panel">
							<p>전문가로 대우받기 위해서는 각 분야에 직무능력과 직무기술을 습득하는 것을 기본으로 하지요. 최소한 일만시간(3년)을 투자할 생각으로
							배우고 익히고 인내하고 시간 노력을 투자하시면 됩니다.</p>
						</div>
					</li>
					<li>
						<button class="accordion">
						전문가로 대우받기 위한 경력은 최소한 몇년?
						</button>
						<div class="panel">
							<p>전문가로 대우받기 위해서는 각 분야에 직무능력과 직무기술을 습득하는 것을 기본으로 하지요. 최소한 일만시간(3년)을 투자할 생각으로
							배우고 익히고 인내하고 시간 노력을 투자하시면 됩니다.</p>
						</div>
					</li>
					<li>
						<button class="accordion">
						전문가로 대우받기 위한 경력은 최소한 몇년?
						</button>
						<div class="panel">
							<p>전문가로 대우받기 위해서는 각 분야에 직무능력과 직무기술을 습득하는 것을 기본으로 하지요. 최소한 일만시간(3년)을 투자할 생각으로
							배우고 익히고 인내하고 시간 노력을 투자하시면 됩니다.</p>
						</div>
					</li>
				</ul>
			</div>

			<script>
				$(function(){
					$(".accordion").on("click",function(){
						$(".panel").not($(this).next().slideToggle()).slideUp();
						$(".accordion").removeClass("active");
						$(this).toggleClass("active");
					});
				});
			</script>

			<div class="paging">
				<a href=""><i class="fa fa-angle-double-left"></i></a>
				<a href=""><i class="fa fa-angle-left"></i></a>
				<a href="">1</a>
				<a href="">2</a>
				<a href="">3</a>
				<a href="">4</a>
				<a href="">5</a>
				<a href=""><i class="fa fa-angle-left"></i></a>
				<a href=""><i class="fa fa-angle-double-right"></i></a>
				<a href="board01_write.html" class="write">글쓰기</a>
			</div>
		</div>
	</div>

	<script>
		$(function(){
			$(".location .dropdown>a").on("click",function(e){
				e.preventDefault();
				if($(this).next().is(":visible")){
					$(".location .dropdown > a").next().hide();
				}else{
					$(".location .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
		});
	</script>

	<?php include "footer.html"; ?>
 </body>


</html>
