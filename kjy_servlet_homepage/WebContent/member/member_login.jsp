<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/header.jsp"%>
<!DOCTYPE html>
<!-- jquery 라이브러리 -->
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<script type="text/javascript" language="javascript">
	$(document).ready(function() { //제이쿼리 실행
		$("#pw").keyup(function() { //아이디btnOK인 버튼을 누르면 실행
			$("#ajaxJoin").empty();  
			if($('#pw').val() != ""){
				var urlLocation = "/IdPwCheck"; //어떤 jsp파일을 부를것인가 변수 선언
				var params = "t_id=" + $('#t_id').val()+"&t_pw="+$('#pw').val(); //아이디가 t_no인 값을 no변수에 담아놓는다
				//ajax 실행되는 부분
				$.ajax({
					type : "POST",
					url : urlLocation,
					data : params,
					dataType : "text", //"form1_2.jsp"을 실행한 결과의 리턴타입을 텍스트로 받겠다
					error : function() {
						alert('통신실패!!');
					},
					success : function(data) {
						//alert("통신데이터 값 : " + data);
						$("#ajaxJoin").html(data); //html안의 내용이 리턴
						
					}					
				});
				
			}
		});
	});
</script>
	<div class="sub_title">
		<h2>로그인</h2>
		
	</div>

	<div class="bg_admin">
		<div class="container">
			<div class="grap">
				<form name="admin" method="post" action="/Login">
					<fieldset>
						<legend class="sr-only">로그인</legend>

						<label for="id" class="sr-only">아이디입력</label>
						<input type="text" name="t_id" placeholder="아이디를 입력하세요" id="t_id">
												
						<label for="pw" class="sr-only">패스워드입력</label>
						<input type="password" name="t_pw" placeholder="패스워드를 입력하세요" id="pw">						
						<span id="ajaxJoin">${msg}</span>

						<a href="javascript:admin_check();" class="btn_admin">로그인</a>
					</fieldset>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function admin_check(){
			if(admin.id.value==""){
				alter("아이디입력");
				admin.id.focus();
				return;
			}
			if(admin.pw.value==""){
				alter("패스워드입력");
				admin.pw.focus();
				return false;
			}
		admin.submit();
		}
	</script>
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
