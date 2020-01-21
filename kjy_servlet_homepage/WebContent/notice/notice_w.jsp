<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/index/header.jsp"%> 
	<div class="sub_title">
		<h2>NOTICE Write</h2>
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
		<div class="write_wrap">
		<h2 class="sr-only">공지사항 글쓰기</h2>
			<form name="notice" enctype="multipart/form-data" method="post" action="/NoticeSave" onSubmit="return check();">
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
							<th>제목</th>
							<th><input type="text" name="title"></th>
						</tr>
						<tr>
							<th>내용</th>
							<th><textarea name="contents"></textarea></th>
						</tr>
						<tr>
							<th>첨부</th>
							<th><input type="file" name="fileName_a"></th>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="submit" value="등록" class="btn_ok">&nbsp;&nbsp;
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
		if(notice.title.value==""){
			alert("제목을 입력");
			notice.title.focus();
			return false;
		}
		if(notice.contents.value==""){
			alert("내용을 입력");
			notice.contents.focus();
			return false;
		}
		
		var file = notice.fileName_a;
		if(file.value != "") {
			//alert(file.value);
			var position = file.value.lastIndexOf("\\");
			//alert("position :"+position);
			
			//var len = file.value.length;
			var fName = file.value.substr(position+1);
			var len = fName.length;
			//alert("len : "+len);
			if(len > 20) {
				alert("첨부파일명 길이 20자리 이내로"+len);
				return;
			}
			// 사이즈체크
			var maxSize  = 1 * 1024 * 1024    //2MB
			var fileSize = 0;

			// 브라우저 확인
			var browser=navigator.appName;
			
			// 익스플로러일 경우
			if (browser=="Microsoft Internet Explorer")
			{
				var oas = new ActiveXObject("Scripting.FileSystemObject");
				fileSize = oas.getFile(file.value).size;
			}
			// 익스플로러가 아닐경우
			else
			{
				fileSize = file.files[0].size;
			}


			//alert("파일사이즈 : "+ fileSize);

			if(fileSize > maxSize){
				alert("첨부파일 사이즈는 2MB 이내로 등록 가능합니다.");
				return;
			}
		}
		return true;
	}
		
	</script>



 </body>
<%@ include file="/index/footer.jsp"%>

</html>