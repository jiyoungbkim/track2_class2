<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.CommonUtil"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="/index/header.jsp"%>

	<div class="sub_title">
		<h2>FAQ</h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="company01.html"><i class="fa fa-home"></i></a></li>
					<li class="dropdown">
						<a href="board01.html">BOARD<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="company01.html">COMPANY</a>
							<a href="business01.html">BUSINESS</a>
							<a href="product01.html">PRODUCT</a>
							<a href="board01.html">BOARD</a>
							<a href="shop01.html">SHOP</a>
						</div>
					</li>
					<li class="dropdown">
						<a href="board04.html">FAQ<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="board01.html">공지사항</a>
							<a href="board02.html">이용후기</a>
							<a href="board03.html">1:1 상담</a>
							<a href="board04.html">FAQ</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 <span>${t_fCount}</span>건</p>
				</div>
				<div class="search_group">
					<form name="faq">
					<input type="hidden" name="r_page">
					<input type="hidden" name="t_faq_no">
					
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
			</div>
		</div>
		<div class="board_list">
			<div class="faq_group">
				<ul>
	<c:set var="forYN" value="true"/>
		<c:if test="${t_arrF.size() > 0}">
			<c:forEach items="${t_arrF}" var="arrF">
				<c:if test="${forYN}">
					<c:if test="${v_count == for_count}">			
					<li>
						<button class="accordion">
						${arrF.getTitle()}
						</button>
						<div class="panel">
							<p>${arrF.getContent()}</p>
							<c:if test="${session_level == 'manager'}">	
							<div class="list">
							<a href=javascript:deleteFaq("${arrF.getFaq_no()}")>Delete</a>
							<a href=javascript:FaqUpdate("${arrF.getFaq_no()}")>Modify</a>&nbsp;&nbsp;
							</div>
							</c:if>
						</div>
					</li>
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
		<c:if test="${empty t_arrF}">
			<li>
				<button class="accordion">
				등록된 내용이 없습니다.
				</button>
			</li>
		</c:if>			
				</ul>
			</div>
			
			<script>
				$(function(){
					$(".accordion").on("click",function(){
						$(".panel").not($(this).next().slideToggle()).slideUp();
						$(".accordion").removeClass("active");
						$(this).toggleClass("active");
					});
				});
			</script>

			<div class="paging">
				<%
				Integer cp = (Integer)request.getAttribute("current_page");
				int current_page = cp.intValue();
				
				Integer tp = (Integer)request.getAttribute("total_page");
				int total_page = tp.intValue();
				
				out.println(CommonUtil.pageList2(current_page, total_page));
				%>	
				<c:if test="${session_level == 'manager'}">	
					<a href="/FaqWriteForm" class="write">글쓰기</a>
				</c:if>
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
		function FaqUpdate(faqNo){
			var fm = document.faq;
			fm.t_faq_no.value = faqNo;
			fm.action = "/FaqUpdateForm";
			fm.method = "post";
			fm.submit();
			
		}
		function deleteFaq(delNo){
			var yn = confirm("정말 삭제 하겠습니까?");
			if(yn) {
				var fm = document.faq;
				fm.t_faq_no.value = delNo;
				fm.action = "/FaqDelete";
				fm.method = "post";
				fm.submit();
			}
		}
	</script>

	<?php include "footer.html"; ?>
 </body>


</html>
