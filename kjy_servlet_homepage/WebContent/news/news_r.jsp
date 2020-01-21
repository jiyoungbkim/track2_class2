<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.CommonUtil"%>
<!DOCTYPE html>
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
							<a href="/news/news_r.jsp">뉴스</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="search_wrap clearfix">
			<div class="record_group">
				<p>총게시글 <span>${t_wCount}</span>건</p>
			</div>
			<div class="search_group">
				<form name="news">
					<input type="hidden" name="r_page">
					<input type="hidden" name="t_newsNo">
					
						<div class="search">
							<select name="t_sel" class="select">
							
								<option value="title" <c:if test="${t_sel} eq 'title'">out.print("selected");</c:if>>&nbsp;제 목</option>
								<option value="content" <c:if test="${t_search} eq 'content'">out.print("selected");</c:if>>&nbsp;내 용</option>
							</select>						
							<input type="text"  value="${t_search}" name="t_search" class="search_word">
						<button class="btn_search" onClick="javascript:formSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
						</div>
				</form>
			</div>
		</div> <!-- search_wrap -->
			
	<c:set var="forYN" value="true"/>
		<c:if test="${t_arrW.size() > 0}">
			<c:forEach items="${t_arrW}" var="arrW">
				<c:if test="${forYN}">
					<c:if test="${v_count == for_count}">
						<div class="port">
							<div class="date">
								<h2>${arrW.getReg_date().substring(8)}</h2>
								<p>${arrW.getReg_date().substring(0,7)}</p>
							</div>
							<c:if test="${arrW.getFile_name_1() ne null}">
							<div class="img">
								<a href=javascript:NewsView("${arrW.getNews_no()}")>
								<img src="/file_room/news/${arrW.getFile_name_1()}"></a>
							</div>
							</c:if>
							<c:if test="${arrW.getFile_name_1() eq null}">
							<div class="img">
								<a href=javascript:NewsView("${arrW.getNews_no()}")>
								<img src="/images/no-image-800x511.png"></a>
							</div>
							</c:if>
							
							<div class="content">
							
								<span>No.${arrW.getNews_no()} <i class="fa fa-eye"></i> ${arrW.getHit()}</span>
								<h2><a href=javascript:NewsView("${arrW.getNews_no()}")>${arrW.getTitle()}</a></h2>
								<p>${arrW.getContent()}</p>
							</div>
						</div>
						
							
						<c:set var="v_count" value="${v_count+1}"/>
						<c:set var="for_count" value="${for_count+1}"/>
					</c:if>
					<c:if test="${v_count != for_count}">
						<c:set var="v_count" value="${v_count+1}"/>
					</c:if>
					<c:if test="${v_count == a_count}">
						<c:set var="forYN" value="false"/>
					</c:if>
				</c:if>
			</c:forEach>
		</c:if>

			<c:if test="${empty t_arrW}">
				<TR align="center" bgcolor="white" >
						<TD colspan="5" >등록된 내용이 없습니다.</TD>
				</TR>
			</c:if>	
		<div class="board_list">
			<ul>
				<li>
				</li>
			</ul>
			
			<div class="paging">
				<%
				Integer cp = (Integer)request.getAttribute("current_page");
				int current_page = cp.intValue();
				
				Integer tp = (Integer)request.getAttribute("total_page");
				int total_page = tp.intValue();
				
				out.println(CommonUtil.pageList2(current_page, total_page));
				%>	
				<c:if test="${session_level == 'manager'}">	
					<a href="/NewsWriteForm" class="write">글쓰기</a>
				</c:if>
			</div>
		</div>
	</div> <!-- container -->
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
	function NewsView(newsNo){
		var fm = document.news;
		fm.t_newsNo.value = newsNo;
		fm.action = "/NewsView";
		fm.method = "post";
		fm.submit();
		
	}
	function goPage(pageNum){
		var fm = document.news;
		fm.r_page.value = pageNum;
		fm.action = "/NewsList";
		fm.method = "post";
		fm.submit();		
	}
	</script>
 </body>
<%@ include file="/index/footer.jsp"%>

</html>
