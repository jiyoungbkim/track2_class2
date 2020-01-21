<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ko">
<head>
<meta charset="utf-8">
<title>아이디 중복 검사</title>
<link href="/css/join.css" rel="stylesheet">
</head>
<style>
	#contain {
		width: 360px;
		margin: auto;
		padding: 10px;
		//border: 1px solid #bcbcbc;
	}
	#box1 {
		width:340px;
		height:150px;
		padding: 10px;
		//border: 1px solid #bcbcbc;
	}	
	#t2 {
		width: 340px;
		border:0px solid #CAC9D2;
	}
	.t1{
		text-align:center;
		font-size:25px;	
		color:white;
	}	

	
</style>
<script language="JavaScript">
	function confirm(){
		//var id = "<c:out value="${s_id}"/>";
		opener.document.admin.id_check_value.value="${s_id}";
		opener.document.admin.t_pw.focus();
		window.close();
	}
	function confirm_cancel(){
		opener.document.admin.id_check_value.value="";
		window.close();
	}
</script>
<body>
	<div id="contain">
		<div id="box1">
			<TABLE id="t2">
		=${s_id}=
		=${result}=
			<c:if test="${result == '0'}">
				
				<TR align=center >
					<td height="90"><p class="t1">사용 가능한 아이디입니다.</p></td>
				</TR>
				<TR align=center >
					<td>
						<a href="javascript:confirm()"><input class="id_use" type="button" value="아이디 사용"></a>
						&nbsp;&nbsp;
						<a href="javascript:confirm_cancel()"><input class="id_use" type="button" value="취소"></a>
					</td>
				</TR>
			</c:if>
			<c:if test="${result != '0'}">
				<TR align=center >
					<td height="90"><p class="t1">등록된 아이디 입니다.<br>다른 아이디를 사용하세요!</p></td>
				</TR>
				<TR align=center >
					<td>
						<a href="javascript:confirm_cancel()"><input class="id_use" type="button" value="취소"></a>
					</td>
				</TR>
			</c:if>
			</TABLE>	
		</div>
	</div>
</body>
</html>