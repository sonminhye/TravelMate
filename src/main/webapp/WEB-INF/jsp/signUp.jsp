<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<link href="css/style.css?ver=1" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

	<div class="container" style="margin-top: 150px; margin-bottom: 100px;">
		<form action="doSignUp" method="POST" >
			<div class="form-group">
				<!-- 권한을 ROLE_USER로 설정 -->
				<input type="hidden" class="form-control" id="auth" name="authority" value="ROLE_USER">
			</div>
			<div class="form-group">
				<label for="email">이메일 주소</label>
				<form:errors path="userDTO.id" cssClass="error"/>
				<form:input path="userDTO.id" type="email" class="form-control" id="id" name="id" onblur="checkEmail()" placeholder="이메일을 입력하세요"/>
				<div id="checkMsg"></div>
			</div>
			<div class="form-group">
				<label for="password1">비밀번호</label>
				<form:errors path="userDTO.password" cssClass="error"/>
				<form:input path="userDTO.password" type="password" class="form-control" id="password1" name="password" placeholder="비밀번호"/>
			</div>
			<div class="form-group">
				<label for="password2">비밀번호 확인</label>
				<input type="password" class="form-control" id="password2" name="passwordCheck" onkeyup="checkPwd()" placeholder="비밀번호 확인">
				<div id="checkPwd"></div>
			</div>
			<div class="form-group">
				<label for="name">이름</label>
				<form:errors path="userDetailDTO.name" cssClass="error"/>
				<form:input path="userDetailDTO.name" type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요"/>
			</div>
			<div class="form-group">
				<label for="age">나이</label>
				<form:errors path="userDetailDTO.age" cssClass="error"/>
				<form:input path="userDetailDTO.age" type="number" class="form-control" id="age" name="age" placeholder="나이를 입력하세요"/>
			</div>
			<div class="form-group">
				<label for="age">성별</label>
				<form:errors path="userDetailDTO.sex" cssClass="error"/><br>
				<label class="radio-inline">
					<input type="radio" name="sex" id="inlineRadio1" value="male">남
				</label>
				<label class="radio-inline">
					<input type="radio" name="sex" id="inlineRadio2" value="female">여
				</label>
			</div>
			<div class="form-group">
				<label for="city">지역</label>
				<form:errors path="userDetailDTO.location" cssClass="error"/>
				<form:input path="userDetailDTO.location" type="text" class="form-control" id="city" name="location" placeholder="사는 지역을 입력하세요"/>
			</div>
			
			<div class="form-group">
				<label for="language">사용가능한 언어 </label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="inlineCheckbox1" name="langDTOList[0].languageCode" value="1">한국어
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="inlineCheckbox2" name="langDTOList[1].languageCode" value="2">영어
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="inlineCheckbox3" name="langDTOList[2].languageCode" value="3">중국어
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="inlineCheckbox4" name="langDTOList[3].languageCode" value="4">일본어
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="inlineCheckbox5" name="langDTOList[4].languageCode" value="5">스페인어
				</label>
			</div>
			
			<button type="submit" class="btn btn-default">가입</button>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>