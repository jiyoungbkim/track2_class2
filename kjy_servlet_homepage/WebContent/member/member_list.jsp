<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.CommonUtil"%>
<!DOCTYPE html>
<%@ include file="/index/header.jsp"%>  
	<div class="sub_title">
		<h2>MEMBER</h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="/Index"><i class="fa fa-home"></i></a></li>
					<li class="dropdown">
						<a href="">ADMIN<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
						
						</div>
					</li>
					<li class="dropdown">
						<a href="">회원목록<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							
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
					<%-- <form name="notice">
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
					</form> --%>
				
					<form name="member">
					<input type="hidden" name="r_page">
					<input type="hidden" name="t_id">
					<div class="search">
						
						<input type="radio" class="search_sel" value="reg_date" name="t_check" <c:if test="${t_checkValue eq 'reg_date'}">checked</c:if>>가입일
						<input type="radio" class="search_sel" value="name"     name="t_check" <c:if test="${t_checkValue eq 'name'}">checked</c:if>>성명
						<input type="radio" class="search_sel" value="id" 		 name="t_check" <c:if test="${t_checkValue eq 'id'}">checked</c:if>>아이디&nbsp;
						
						<select name="t_sel" class="select" onChange="t_selChange()">
							<option value="all"  <c:if test="${t_selValue eq 'all'}">selected</c:if>>전체</option>
							<option value="name" <c:if test="${t_selValue eq 'name'}">selected</c:if>>성명</option>
							<option value="id"   <c:if test="${t_selValue eq 'id'}">selected</c:if>>아이디</option>
						</select>
						<input type="text" value="${t_txtValue}" name="t_search" class="search_word" <c:if test="${t_selValue eq 'all'}">disabled='disabled'</c:if>>
						<select name="t_area" class="select">
							<option value="" <c:if test="${t_areaValue eq ''}">selected</c:if>> 지역선택 </option>
							<option value="서울" <c:if test="${t_areaValue eq '서울'}">selected</c:if>> 서울 </option>
							<option value="수원" <c:if test="${t_areaValue eq '수원'}">selected</c:if>> 수원 </option>
							<option value="대전" <c:if test="${t_areaValue eq '대전'}">selected</c:if>> 대전 </option>
							<option value="광주" <c:if test="${t_areaValue eq '광주'}">selected</c:if>> 광주 </option>
						</select>
						<button class="btn_search" onClick="javascript:formSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
						<button class="btn_search" onClick="javascript:goExcel()" value=" Excel "/>Excel</button>
					</div>
				</form>	
				</div>
			</div>
		</div>
		<div class="board_list">
			<table class="board_table" summary="이표는 번호, 제목, 글쓴이, 날자, 조회수로 구성되어 있습니다">
				<caption class="sr-only">멤버 리스트</caption>
				<colgroup>
					<col width="5%">
					<col width="10%">
					<col width="10%">
					<col width="*">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="10%">
					<col width="7%">
				</colgroup>
				<thead>
					<tr>
						<th>ID</th>
						<th>이름</th>
						<th>생년월일</th>
						<th>주소</th>
						<th>통신사</th>
						<th>전화번호</th>
						<th>이메일</th>
						<th>가입일</th>
						<th>회원유무</th>
					</tr>
				</thead>
				<tbody>
			<c:set var="forYN" value="true" />
			<c:if test="${t_arrM.size() > 0 }">
				<c:forEach items="${t_arrM}" var="arr">
					<c:if test="${forYN}">
						<c:if test="${v_count == for_count}">
							<tr>
								<td>${arr.getId()}</td>
								<td class="name"><a href=javascript:MemberView("${arr.getId()}")>${arr.getName()}</td>							
								<td>${arr.getBirth()}</td>
								<td>${arr.getArea()}&nbsp;${arr.getAddress()}</td>
								<td>${arr.getTelecom()}</td>
								<td>${arr.getPhone_1()}-${arr.getPhone_2()}-${arr.getPhone_3()}</td>
								<td>${arr.getEmail_1()}@${arr.getEmail_2()}</td>
								<td>${arr.getReg_date()}</td>
								<td>${arr.getStatus()}</td>
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
			<c:if test="${empty t_arrM}">
				<TR align="center" bgcolor="white">
					<TD colspan="9">등록된 내용이 없습니다.</TD>
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
			
			</div>
		</div>
	</div>

	<script>
	function formSearch(){
		var fm = document.member;
		fm.action = "/MemberList";
		fm.method = "post";
		fm.submit();
	}
	function MemberView(memberId){
		var fm = document.member;
		fm.t_id.value = memberId;
		fm.action = "/MemberView";
		fm.method = "post";
		fm.submit();
	}
	function goPage(pageNum){
		//alert("==="+pageNum);
		var fm = document.member;
		fm.r_page.value = pageNum;
		fm.action = "/MemberList";
		fm.method = "post";
		fm.submit();
	}
	function goExcel(){
		var fm = document.member;
		fm.action = "/MemberExcel";
		fm.method = "post";
		fm.submit();
	}
	function t_selChange(){
		var fm = document.member;
		if(fm.t_sel.value == "all"){
			fm.t_search.disabled="disabled";
		} else {
			fm.t_search.disabled="";
		}
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
