<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${session_level != 'manager'}">
	<script>
		alert("관리자 화면입니다.");
		location.href = "/index/index.jsp";
	</script>
</c:if>