<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/index/header.jsp"%>  

	<div class="sub_title">
		<h2>NEWS</h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="company01.html"><i class="fa fa-home"></i></a></li>
					<li class="dropdown">
						<a href="">BOARD<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="/NoticeList">공지사항</a>
							<a href="/NewsList">뉴스</a>
							<a href="board03.html">이벤트</a>
							<a href="board04.html">자유게시판</a>
						</div>
					</li>
					<li class="dropdown">
						<a href="">뉴스<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="/NewsList">뉴스</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<form name="news">
		<input type="hidden" name="t_news_no" value="${dtoW.getNews_no()}">
		<input type="hidden" name="t_fileName" value="${dtoW.getFile_name_1()}">
	</form>
	<div class="container">
		<div class="board_view">
			<h2>[뉴스] ${dtoW.getTitle()}</h2>
			<p class="info"><span class="user">${dtoW.getReg_id()}</span> | ${dtoW.getReg_date()} | <i class="fa fa-eye"> ${dtoW.getHit()}</i>
			<div class="board_body">
				<c:if test="${dtoW.getFile_name_1() ne null}">
				<p><img src="/file_room/news/${dtoW.getFile_name_1()}" class="img"></p>
				</c:if>
				<p>${dtoW.getContent()}</p>

			</div>
			<c:if test="${dtoW.getFile_name_1() ne null}">
			<div class="board_bottom">
				첨부
				<a href="/common/filedown.jsp?t_file=${dtoW.getFile_name_1()}&t_gubun=news">${dtoW.getFile_name_1().substring(8)}</a>	
			</div>
			</c:if>
			<div class="prev_next">
				<a href="" class="btn_prev">
					<i class="fa fa-angle-left"></i>
					<span class="prev_wrap">
						<strong>이전글</strong>
						<span>이전글제목표시</span>
					</span>
				</a>
				<div class="btn_3wrap">
					<a href="/NewsList">목록</a> 
					<a href=javascript:NewsUpdate("${dtoW.getNews_no()}")>수정</a> 
					<a href="javascript:deleteNews()">삭제</a>
				</div>
				<a href="" class="btn_next">
					<span class="next_wrap">
						<strong>다음글</strong>
						<span>다음글제목표시</span>
					</span>
					<i class="fa fa-angle-right"></i>
				</a>
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
		
		function NewsUpdate(newsNo){
			var fm = document.news;
			fm.t_news_no.value = newsNo;
			fm.action = "/NewsUpdateForm";
			fm.method = "post";
			fm.submit();
			
		}
		function deleteNews(){
			var yn = confirm("정말 삭제 하겠습니까? ");
			if(yn) {
				var fm = document.news;
				fm.action = "/NewsDelete";
				fm.method = "post";
				fm.submit();
			}
		}
	</script>

 </body>
<%@ include file="/index/footer.jsp"%>

</html>