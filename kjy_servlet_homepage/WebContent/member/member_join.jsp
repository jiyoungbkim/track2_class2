<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/header.jsp"%>
<!DOCTYPE html>

	<div class="sub_title">
		<h2>입학신청서</h2>
		
	<div class="bg_admin">
		<div class="container">
			<div class="grap">
				<form name="admin">
					<fieldset>
						
						<label for="id" class="sr-only">아이디입력</label>
						<input type="text" name="t_id" placeholder="아이디를 입력하세요" id="id">
						<input type="button" value="중복검사" id="id_check" onClick="javascript:check_id()">
						<input type="hidden" name="id_check_value">											

						<label for="pw" class="sr-only">패스워드입력</label>
						<input type="password" name="t_pw" placeholder="패스워드를 입력하세요" id="pw">
						
						<label for="pw" class="sr-only">패스워드확인</label>
						<input type="password" name="pw_re" placeholder="패스워드를 재입력하세요" id="pw">
						
						<label for="name" class="sr-only">이름입력</label>
						<input type="text" name="name" placeholder="이름을 입력하세요" id="name">
						
						<label for="birth" class="sr-only">생년월일입력</label>
						<input type="text" name="birth" placeholder="생년월일을 입력하세요" id="birth">
						
						<label for="area" class="sr-only">지역입력</label>
						<select name="area" id="area">
							 <option value="선택안함"> 지역선택 </option>
							 <option value="서울"> 서울 </option>
							 <option value="수원"> 수원 </option>
							 <option value="대전"> 대전 </option>
							 <option value="광주"> 광주 </option>
						</select>
                        
						<label for="address" class="sr-only">주소입력</label>
						<input type="text" name="address" placeholder="주소를 입력하세요" id="address">
						
						<label for="telecom" class="sr-only">통신사입력</label>
							<input type = "radio" value="SKT" name = "telecom" id="telecom">&#83;&#75;&#84;
							<input type = "radio" value="KT" name = "telecom" id="telecom"> KT
							<input type = "radio" value="LGU+" name = "telecom" id="telecom"> LGU+
							&nbsp;<br>
							<input name="phone_1" type = "text" size = "1" maxlength="3" placeholder="000" id="phone">&nbsp;-&nbsp;
							<input name="phone_2" type = "text" size = "3" maxlength="4" placeholder="0000" id="phone">&nbsp;-&nbsp;
							<input name="phone_3" type = "text" size = "3" maxlength="4" placeholder="0000" id="phone">
						
						<label for="email" class="sr-only">번호입력</label>
						<input type="text" name="email_1" placeholder="이메일 입력" id="email_1">&nbsp;&#64;
						<input type="text" name="email_2" placeholder="이메일 주소를 입력하세요" id="email_2">
						<select name="emailtype" onChange="selectEmail()" id="email" >
							<option value="직접입력">직접입력</option>
							<option value="naver.com">naver.com</option>
							<option value="daum.net">daum.net</option>
							<option value="gmail.com">gmail.com</option>
						</select>

						<a href="javascript:save();" class="btn_admin">회원가입</a>
					</fieldset>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function check_id() {
			var fm = document.admin;
			var id = fm.t_id.value;
			if(id == ""){
				alert("입력된 아이디가 없습니다.");
				fm.t_id.focus();
			} else {				
				/* window.open("check_id.jsp?t_id="+id,
							"아이디 중복검사",
							"width=400, height=210"); */
				var url = "/IdCheck";
				var title = "아이디 중복검사";
				window.open("",
						title,
						"width=400, height=210");
				fm.target = title;
				fm.action = url;
				fm.method = "post";
				fm.submit();
			}
		}
		function selectEmail(){
			var mailcheck = document.admin.emailtype.value;
			if(mailcheck == "직접입력"){
				document.admin.email_2.value = "";
			}else{
				document.admin.email_2.value = mailcheck;
			}
		}		
	
		function save(){
			if(admin.t_id.value==""){
				alert("아이디입력");
				admin.t_id.focus();
				return;
			}
			if(admin.id_check_value.value == ""){
				alert("아이디 중복 체크 하세요.");
				return;
			}
			if(admin.t_id.value != admin.id_check_value.value){
				alert("아이디 변경 후 중복검사를 하지 않았습니다.");
				form.t_id.focus();
			}
			if(admin.t_pw.value==""){
				alert("패스워드입력");
				admin.t_pw.focus();
				return;
			}
			if(admin.pw_re.value==""){
				alert("패스워드 확인 입력");
				admin.pw_re.focus();
				return;
			}
			if(admin.t_pw.value != admin.pw_re.value){
				alert("패스워드가 틀립니다.");
				admin.pw_re.focus();
				return;
			}
			if(admin.name.value==""){
				alert("이름 입력");
				admin.name.focus();
				return;
			}
			if(admin.birth.value==""){
				alert("생년월일 입력");
				admin.birth.focus();
				return;
			}
			if(admin.birth.value.length != 8){
				alert("생년월일 8자리로 입력");
				admin.birth.focus();
				return;
			}
			if(admin.area.value=="선택안함"){
				alert("지역 입력");
				admin.area.focus();
				return;
			}
			if(admin.address.value==""){
				alert("주소 입력");
				admin.address.focus();
				return;
			}
			if(admin.telecom.value==""){
				alert("통신사 선택");
				admin.telecom.focus();
				return;
			}
			if(admin.phone_1.value==""){
				alert("전화번호 입력");
				admin.phone_1.focus();
				return;
			}
			if(admin.phone_2.value==""){
				alert("전화번호 입력");
				admin.phone_2.focus();
				return;
			}
			if(admin.phone_3.value==""){
				alert("전화번호 입력");
				admin.phone_3.focus();
				return;
			}
			
			if(admin.phone_1.value.length != 3) {
				alert("3자리로 입력!");
				form.phone_1.focus();
				return;
			}
			if(admin.phone_2.value.length != 4) {
				alert("4자리로 입력!");
				form.phone_2.focus();
				return;
			}
			if(admin.phone_3.value.length != 4) {
				alert("4자리로 입력!");
				form.phone_3.focus();
				return;
			}
			if(admin.email_1.value=="") {
				alert("이메일 입력!");
				form.email_1.focus();
				return;
			}
			if(admin.email_2.value=="") {
				alert("이메일 입력!");
				form.email_2.focus();
				return;
			}
		
			
			admin.action="/Join";
			admin.method="post";
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
