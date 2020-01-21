<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/header.jsp"%>     
<!DOCTYPE html>
	<div class="sub_title">
		<h2>회사 연혁</h2>
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
		<div class="history_wrap">
			<h3>회사연혁</h3>
			<ul class="history_list clearfix">
				<li>
					<strong class="years">
						<span>2018</span>
						<span>년</span>
					</strong>
					<ul>
						<li>
							<strong class="year">05월</strong>
							<ul>
								<li>
									<span>05.10</span>
									<ul>
										<li>국가기간 전략산업 승인</li>
									</ul>
								</li>
							</ul>
						</li>
						<li>
							<strong class="year">05월</strong>
							<ul>
								<li>
									<span>05.10</span>
									<ul>
										<li>국가기간 전략산업 승인</li>
									</ul>
								</li>
							</ul>
						</li>
						<li>
							<strong class="year">05월</strong>
							<ul>
								<li>
									<span>05.10</span>
									<ul>
										<li>국가기간 전략산업 승인</li>
									</ul>
								</li>
							</ul>
						</li>
						<li>
							<strong class="year">05월</strong>
							<ul>
								<li>
									<span>05.10</span>
									<ul>
										<li>국가기간 전략산업 승인</li>
									</ul>
								</li>
							</ul>
						</li>

					</ul>
				</li>
			</ul>
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
 </body>
<%@ include file="/index/footer.jsp"%>

</html>
