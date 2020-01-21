<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.CommonUtil"%>
<!DOCTYPE html>
<%@ include file="/index/header.jsp"%>  
	<div class="sub_title">
		<h2>NOTICE</h2>
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

	<div class="container">
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 <span>${t_nCount}</span>건</p>
				</div>
				<div class="search_group">
					<form name="notice">
					<input type="hidden" name="r_page">
					<input type="hidden" name="t_noticeNo">
					
						<div class="search">
							<select name="t_sel" class="select">
							
								<option value="title" <c:if test="${t_sel} eq 'title'">out.print("selected");</c:if>>&nbsp;제 목</option>
								<option value="content" <c:if test="${t_search} eq 'content'">out.print("selected");</c:if>>&nbsp;내 용</option>
							</select>						
							<input type="text"  value="${t_search}" name="t_search" class="search_word">
						<button class="btn_search" onClick="javascript:formSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
						</div>
					</form>
				
					<!-- <form name="myform" action="">
						<select name="sel" class="select">
							<option value="1">제목</option>
							<option value="2">내용</option>
						</select>
						<input type="text" name="search" class="search_word">
						<button class="btn_search"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
					</form> -->
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
						<th>첨부</th>
						<th>글쓴이</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
			<c:set var="forYN" value="true" />
			<c:if test="${t_arrN.size() > 0 }">
				<c:forEach items="${t_arrN}" var="arr">
					<c:if test="${forYN}">
						<c:if test="${v_count == for_count}">
							<tr>
								<td>${arr.getNotice_no()}</td>
								<td class="title"><a href=javascript:NoticeView("${arr.getNotice_no()}")>${arr.getTitle()}</td>
								<c:if test="${not empty arr.getFile_name_1() }">
									<td><i class="fas fa-paperclip"></i></td>
								</c:if>
								<c:if test="${empty arr.getFile_name_1() }">
									<td>-</td>
								</c:if>
								<td>${arr.getReg_id()}</td>
								<td>${arr.getReg_date()}</td>
								<td>${arr.getHit()}</td>
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
					<TD colspan="6">등록된 내용이 없습니다.</TD>
				</TR>
			</c:if>
		</tbody>
			</table>
			<div class="paging">
		<%
			Integer cp = (Integer) request.getAttribute("current_page");
			int current_page = cp.intValue();

			Integer tp = (Integer) request.getAttribute("total_page");
			int total_page = tp.intValue();

			out.println(CommonUtil.pageList2(current_page, total_page));
		%>
		<!-- <a href=""><i class="fa fa-angle-double-left"></i></a>
				<a href=""><i class="fa fa-angle-left"></i></a>
				<a href="">1</a>
				<a href="">2</a>
				<a href="">3</a>
				<a href="">4</a>
				<a href="">5</a>
				<a href=""><i class="fa fa-angle-left"></i></a>
				<a href=""><i class="fa fa-angle-double-right"></i></a> -->
			<c:if test="${session_level == 'manager'}">	
				<a href="/NoticeWriteForm" class="write">글쓰기</a>
			</c:if>
			</div>
		</div>
	</div>

	<script>
	function formSearch(){
		var fm = document.notice;
		fm.action = "/NoticeList";
		fm.method = "post";
		fm.submit();
	}
	function NoticeView(noticeNo){
		var fm = document.notice;
		fm.t_noticeNo.value = noticeNo;
		fm.action = "/NoticeView";
		fm.method = "post";
		fm.submit();
	}
	function goPage(pageNum){
		//alert("==="+pageNum);
		var fm = document.notice;
		fm.r_page.value = pageNum;
		fm.action = "/NoticeList";
		fm.method = "post";
		fm.submit();
	}
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
