<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp" %>
<%@ page import="java.util.*,dao.News_DAO,dto.News_DTO"%>
<%	
	request.setCharacterEncoding("UTF-8");	
	News_DAO dao = new News_DAO();
	
	
%>
<div id="con">
<style>
#menu_bar ul li i{
	color : #666;
	vertical-align: middle;
	size : 5px;
	line-height : 40px;
}
#menu_bar ul li{
	border-bottom : #999 1px;
}
</style>

	<div id="menu_bar">
		<ul>
			<li><i class="fas fa-bell fa-lg"></i><a href="notice_r.jsp">&nbsp; NOTICE</a></li>
			<li><i class="fas fa-bullhorn fa-lg"></i><a href="">&nbsp; NEWS</a></li>
			<li><i class="fas fa-file-alt fa-lg"></i><a href="">&nbsp; FREEBOARD</a></li>
			<li><i class="fab fa-quora fa-lg"></i><a href="">&nbsp; QNA</a></li>
		</ul>
	</div>
<style>
.board_list { padding-top:10px; }
.board_table{ width: 100%; font-size : 12px;}

.board_list th{
	border-top:1px solid #848484;
	border-bottom: 1px solid #848484;
	padding: 10px;
}
.board_table td{
	text-align:center;
	padding : 7px;
	border-bottom:1px solid #e0e0e0;
}
td.title{
	text-align : left;
}
.home_icon{
	width : 13px;
}
#contents p {
	font-size : 11px;
}
#contents tr, a{
	font : 11px;
	color : #848484;
}
.search{
	float: right;
	margin-bottom: 5px;
}
.search select, input {
	color:#666;
	vertical-align:top;
	height : 23px;
}
.paging {
	padding-top:30px; 
	text-align:center;
}
.paging a {
	padding:10px; 
	border:1px solid #e0e0e0;
}
.paging a.active {
	background:#007dc6;
	color:#fff
}
.paging .btn_write{
	background : #123454;
	padding : 10px 16px;
	color : #fff;
	float : right;
}
</style>
	<div id="contents">
		<p>
			<img src="../images/home3.png" class="home_icon">
			 HOME | COMMUNITY | NEWS
		</p>
<script>

</script>
		<form name="notice">
			<div class="search">
				<select name="t_sel">
					<option value="title" >제목</option>
					<option value="content">내용</option>
				</select>
				<input type="text" name="t_search" style="height:17px">
				<input type="button" onClick="javascript:formSearch()" value=" 검 색 " style="width: 60px"/>
			</div>
		</form>	
		
		<div class="board_list">
			<table class="board_table">
				<colgroup>
					<col width="10%">
					<col width="*">
					<col width="10%">
					<col width="20%">
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
				<% 
				
				%>
				</tbody>
			</table>
			<div class="paging">
				<a href=""><i class="fa fa-angle-double-left"></i></a>
				<a href=""><i class="fa fa-angle-left"></i></a>
				<a href="" class="active">1</a>
				<a href="">2</a>
				<a href="">3</a>
				<a href="">4</a>
				<a href="">5</a>
				<a href=""><i class="fa fa-angle-right"></i></a>
				<a href=""><i class="fa fa-angle-double-right"></i></a>
				<a href="news_w.jsp" class="btn_write">글쓰기</a>
			</div>
		</div>
	</div>
<style>
#footer{
	background :#42464d;
	padding-top:10px;
	//padding-bottom:10px;
}
#footer .address {
	font-style:normal;
	color:#ababb1;
	margin-bottom:10px;
}
#footer .address .title{
	font-size:14px;
	margin-bottom:8px;
	color:#fff;
}
#footer .copyright{
	color:#fff;
}
</style>
	<div id="footer">
		<address class="address">
			<p class="title">본사</p>
			(우)12345 대전광역시 중구 계롱로 825
			(용두동, 희영빌딩) 5층, 6층/ 고객센터: 042-242-4412
			<br>사업자등록번호: 305-86-06709
		</address>
		<p class="copyright">Copyright &copy;
		JSL 전자개발주식회사. All rights reserved.</p>
	</div>
</div>
</body>
</html>