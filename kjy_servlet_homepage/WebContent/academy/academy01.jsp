<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/header.jsp"%>      
<!DOCTYPE html>


	<div class="sub_title">
		<h2>연혁 및 비젼</h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="company01.html"><i class="fa fa-home"></i></a></li>
					<li class="dropdown">
						<a href="">비젼소개<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
						</div>
					</li>
					<li class="dropdown">
						<a href="">인사말<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="company01.html">인사말</a>
							<a href="company02.html">회사연혁</a>
							<a href="company03.html">조직도</a>
							<a href="company04.html">오시는길</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="text_center">
			<p><span class="support_text"><strong>사회적 기업을 최우선으로 하는 믿음</strong></span></p></br>
			글로벌 경쟁력을 기반으로 최고의 IT 서비스 가치를 업그레이드하며</br>
			한단계 앞서는 새로운 패러다임을 이끌어가는 기업
			<span class="col_bar"></span>
		</div>
		<div class="greeting_cont">
			<img src="/images/ceo.jpg" alt="ceo 이미지" style="float:left; padding:0px 40px;">
			<div class="text_wrap"><p>인사말이 없어요.</p>
			<strong class="sign">비젼직업전문학교 구 송 이</strong></div>
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
<%@ include file="/index/footer.jsp"%>
 </body>


</html>
