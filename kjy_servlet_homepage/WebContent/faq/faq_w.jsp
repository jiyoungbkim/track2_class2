<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/index/header.jsp"%> 
	<div class="sub_title">
		<h2>FAQ write</h2>
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
	<div class="container">
		<div class="write_wrap">
		<h2 class="sr-only">공지사항 글쓰기</h2>
			<form name="faq" method="post" action="/FaqSave" onSubmit="return check();">
			<!-- action을 처리하기 전에 check()사용자 함수를 실행하고 되돌아 와라-->
			<div class="board_list">
				<table class="board_table">
					<caption class="sr-only">공지사항 입력 표</caption>
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>글쓴이</th>
							<td><input type="hidden" name="writer" value="${session_id}">${session_name}</td>
						</tr>
						<tr>
							<th>질문</th>
							<th><input type="text" name="title"></th>
						</tr>
						<tr>
							<th>내용</th>
							<th><textarea name="contents"></textarea></th>
						</tr>

					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="submit" value="등록" class="btn_ok">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn_list" onClick="javascript:history.back();">
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
	
	function check(){
		if(faq.title.value==""){
			alert("제목을 입력");
			faq.title.focus();
			return false;
		}
		if(faq.contents.value==""){
			alert("내용을 입력");
			faq.contents.focus();
			return false;
		}						
		return true;
	}
		
	</script>



 </body>
<%@ include file="/index/footer.jsp"%>

</html>