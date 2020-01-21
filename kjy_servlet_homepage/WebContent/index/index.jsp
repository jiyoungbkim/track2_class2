<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/header.jsp"%>    

	<section id="main_visual">
		<div class="visual_wrap">
			<h2 class="sr-only">메인 비쥬얼</h2>
			<ul>
				<!-- <li><img src="images/bg.png" alt="학생단체관람" class="w100"></li> -->
				<li class="one"></li>
			</ul>
			<div class="visual_inner">
				<!-- <p class="title">INTELLIGENCE IS <strong>SUREST</strong></p> 
				<p class="txt">Or yet, in wise old Ravenclaw, If you've a ready mind,  <br/> Where those of wit and learning, Will always find their kind.</p> -->
			</div>
		</div>
	</section>
	<h4>회원가입,로그인,공지사항,뉴스,FAQ</h4>
	<section class="news_group">
		<div class="news_tit">
			<p class="sub_tit">Ravenclaw</p>
			<h2>intelligence <br/>creativity</h2>
			<p class="text" style="width:200px;">A huge, rambling, quite scary-looking castle, with a jumble of towers and battlements. Like the Weasleys' house, it isn't a building that Muggles could build, because it is supported by magic.</p>
			<a href="" class="btn-border">READ MORE</a>
		</div>
		<ul class="news_list">
			<li>
				<a href="">
					<img src="/images/hog2.jpeg" alt="COMPNAY" class="w100">
					<strong>ACADEMY</strong>
					<p>전문화된 사회적 기업. 개성과 창조적인 기술을 지원합니다.</p>
					<span>2018-09-20</span>
					<div class="over">
						<strong>COMPANY</strong>
						<p>전문화된 사회적 기업. 개성과 창조적인 기술을 지원합니다.</p>
						<span>2018-09-20</span>
					</div>
				</a>
			</li>
			<li>
				<a href="">
					<img src="/images/Hogwarts_bg2.jpg" alt="BUSINESS" class="w100">
					<strong>CURRICULUM</strong>
					<p>믿음과 신뢰의 디자인 서비스. 고객님의 만족을 높여드립니다.</p>
					<span>2018-09-20</span>
					<div class="over">
						<strong>BUSINESS</strong>
						<p>믿음과 신뢰의 디자인 서비스. 고객님의 만족을 높여드립니다.</p>
						<span>2018-09-20</span>
					</div>
				</a>
			</li>
			<li class="news_end">
				<a href="">
					<img src="/images/hog2.jpg" alt="CONTACT" class="w100">
					<strong>CONTACT</strong>
					<p>제휴 및 견적의뢰를 보내어주시면 빠른 답변드리겠습니다.</p>
					<span>2018-09-20</span>
					<div class="over">
						<strong>CONTACT</strong>
						<p>제휴 및 견적의뢰를 보내어주시면 빠른 답변드리겠습니다.</p>
						<span>2018-09-20</span>
					</div>
				</a>
			</li>
		</ul>
	</section>
	<section class="main_content">
		<div class="main_left">
			<div class="box1">
			<div class="board_preview" id="preview">
			<table class="board_table">
				<colgroup>
					<col width="80%">
					<col width="20%">
				</colgroup>
				<!-- <thead>
					<tr>				
						<th>제목</th>
						<th>글쓴이</th>
						<th>작성일</th>
					</tr>
				</thead> -->
				<thead>
				<tr>
					<h3><a href="/NoticeList">NOTICE</a></h3>
				</tr>
				
				</thead>
				<tbody>
			<c:set var="forYN" value="true" />
			<c:if test="${t_arrN.size() > 0 }">
				<c:forEach items="${t_arrN}" var="arr">
					<c:if test="${forYN}">
						<c:if test="${v_count == for_count}">
							<tr>
								<td class="title"><a href=javascript:NoticeView("${arr.getNotice_no()}")>${arr.getTitle()}</td>
								<td>${arr.getReg_date()}</td>
							</tr>

							<c:set var="v_count" value="${v_count+1}" />
							<c:set var="for_count" value="${for_count+1}" />
						</c:if>
						<c:if test="${v_count != for_count}">
							<c:set var="v_count" value="${v_count+1}" />
						</c:if>
						<c:if test="${v_count == a_count}">
							<c:set var="forYN" value="false" />
						</c:if>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${empty t_arrN}">
				<TR align="center" bgcolor="white">
					<TD colspan="2">등록된 내용이 없습니다.</TD>
				</TR>
			</c:if>
		</tbody>
			</table>
			</div>
			</div>
		</div>
		<!-- <div class="main_center">
			<div class="box3">
				<h3 class="sr-only">알림마당</h3>
				<nav>
					<ul>
						<li class="active"><a href="#sam01">샘플제품</a></li>
						<li><a href="#sam02">샘플제품</a></li>
					</ul>
				</nav>

				<div class="sample01 clearfix active" id="sam01">
					<div class="sam">
						<p class="title">
							<a class="title" href="">100원</a>
							<span class="date">2018-09-27</span>
						</p>
						<p class="text">빅데이터처리, JAVA, JSP, PHP, DB를 이용한 웹사이트</p>
					</div>
					<ul>
						<li><a href="">적을게 없어</a><span>2018-09-27</span></li>
						<li><a href="">적을게 없어</a><span>2018-09-27</span></li>
						<li><a href="">적을게 없어</a><span>2018-09-27</span></li>
						<li><a href="">적을게 없어</a><span>2018-09-27</span></li>
					</ul>
				</div>

				<div class="sample02 clearfix" id="sam02">
					<div class="sam">
						<p class="title">
							<a class="title" href="">300원</a>
							<span class="date">2018-09-27</span>
						</p>
						<p class="text">빅데이터처리, JAVA, JSP, PHP, DB를 이용한 웹사이트</p>
					</div>
					<ul>
						<li><a href="">적을게 없어</a><span>2018-09-27</span></li>
						<li><a href="">적을게 없어</a><span>2018-09-27</span></li>
						<li><a href="">적을게 없어</a><span>2018-09-27</span></li>
						<li><a href="">적을게 없어</a><span>2018-09-27</span></li>
					</ul>
				</div>
			</div> box3
		</div> main_center -->

		<div class="main_right">
		</div>
	</section>
	
	<script>
		$(function(){
			$(".gnb>.nav_1depth>li").hover(function(){
				//console.log("gnb>.nav_1depth>li");
				$(this).parent().addClass("active");
				$(".taball").hide();
				$($(this).attr("href")).show();
			});
		});
	</script>

	<div class="right_quick">
		<button class="btn btn-open" type="button"></button>
	</div>

 </body>
<%@ include file="/index/footer.jsp"%>

</html>
