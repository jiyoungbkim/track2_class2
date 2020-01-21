<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/index/header.jsp"%>  

	<div class="sub_title">
		<h2>공지사항</h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="company01.html"><i class="fa fa-home"></i></a></li>
					<li class="dropdown">
						<a href="">BOARD<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
						</div>
					</li>
					<li class="dropdown">
						<a href="">공지사항<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<form name="notice">
		<input type="hidden" name="t_notice_no" value="${dtoN.getNotice_no()}">
		<input type="hidden" name="t_fileName" value="${dtoN.getFile_name_1()}">
	</form>
	<div class="container">
		<div class="board_view">
			<h2>[공지] ${dtoN.getTitle()}</h2>
			<p class="info"><span class="user">${dtoN.getReg_id()}</span> | ${dtoN.getReg_date()} | <i class="fa fa-eye"> ${dtoN.getHit()}</i>
			<div class="board_body">
				<p>${dtoN.getContent()}</p>

			</div>
			<c:if test="${dtoN.getFile_name_1() ne null}">
			<div class="board_bottom">
				첨부
				<a href="/common/filedown.jsp?t_file=${dtoN.getFile_name_1()}&t_gubun=notice">${dtoN.getFile_name_1().substring(8)}</a>	
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
					<a href="/NoticeList">목록</a> 
					<a href=javascript:NoticeUpdate("${dtoN.getNotice_no()}")>수정</a> 
					<a href="javascript:deleteNotice()">삭제</a>
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
		
		function NoticeUpdate(noticeNo){
			var fm = document.notice;
			fm.t_notice_no.value = noticeNo;
			fm.action = "/NoticeUpdateForm";
			fm.method = "post";
			fm.submit();
			
		}
		function deleteNotice(){
			var yn = confirm("정말 삭제 하겠습니까? ");
			if(yn) {
				var fm = document.notice;
				fm.action = "/NoticeDelete";
				//fm.action = "notice_proc.jsp";
				fm.method = "post";
				fm.submit();
			}
		}
	</script>

 </body>
<%@ include file="/index/footer.jsp"%>

</html>