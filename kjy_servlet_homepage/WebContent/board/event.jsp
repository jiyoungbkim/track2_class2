<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/index/header.jsp"%>  
	<div class="sub_title">
		<h2>EVENT</h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="company01.html"><i class="fa fa-home"></i></a></li>
					<li class="dropdown">
						<a href="">BOARD<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a><%@ include file="/index/header.jsp"%>  
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
			<table class="board_table" summary="이표는 번호, 제목, 글쓴이, 날자, 조회수로 구성되어 있습니다">
				<caption class="sr-only">공지사항 리스트</caption>
				<colgroup>
					<col width="10%">
					<col width="*">
					<col width="10%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>8</td>
						<td class="title"><a href="board01_view.html">표작성방법을 알아보자</td>
						<td>관리자</td>
						<td>2018-10-16</td>
						<td>187</td>
					</tr>
					<tr>
						<td>8</td>
						<td class="title"><a href="">표작성방법을 알아보자</td>
						<td>관리자</td>
						<td>2018-10-16</td>
						<td>187</td>
					</tr>
					<tr>
						<td>8</td>
						<td class="title"><a href="">표작성방법을 알아보자</td>
						<td>관리자</td>
						<td>2018-10-16</td>
						<td>187</td>
					</tr>
				</tbody>
			</table>
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

 </body>
<%@ include file="/index/footer.jsp"%>

</html>
