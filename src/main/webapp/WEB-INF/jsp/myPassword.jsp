<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>MyPage</title>
	 <script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="<c:url value='/vendor/bootstrap/js/bootstrap.min.js' />"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<hr><hr>

    <div class="row">
        <div class="col-lg-12 text-center">
            <h2 class="section-heading">MyPage</h2>
            <h3 class="section-subheading text-muted">비밀번호 변경</h3>
        </div>
    </div>
   
   <!-- 비밀번호 인증에 실패했을 경우 -->
   <c:if test="${fail == true }">
	   <p style="color:red; text-align:center; margin-top:30px;">
	   		 비밀번호를 정확히 입력해 주세요
	   </p>
   </c:if>

     <div class="container" style="margin-top: 150px; margin-bottom: 100px;">
	     <form action="<c:url value='/modifyPassword' />" method="POST" name="myForm">  	
	    	<div class="row">
	    	  <div class="form-group col-sm-10">
			    <label class="control-label">이메일 주소</label> 
			    <input type="hidden" name="id" value="${user.id}">
			     <p class="form-control-static" >${user.id}</p>
			  </div>
			</div>
	    	<div class="row">
	    	  <div class="form-group col-sm-10">
			    <label class="control-label">기존 비밀번호</label> 
			     <input type="password" class="form-control" id="originalPassword" name="originalPassword" >
			  </div>
			</div>
			<div class="row">
	    	  <div class="form-group col-sm-10">
			    <label class="control-label">새 비밀번호</label> 
			     <input type="password" class="form-control" id="newPassword" name="newPassword" >
			  </div>
			</div>
			<div class="row">
	    	  <div class="form-group col-sm-10">
			    <label class="control-label">새 비밀번호 확인</label> 
			     <input type="password" class="form-control" id="newPasswordConfirm" onkeyup="checkPwd()" name="newPasswordConfirm" >
			     <div id="checkPwd2"></div>
			  </div>
			</div>
			<button type="submit" class="btn btn-default">수정</button>
	      </form>
      </div>
	  <script type="text/javascript">
		var passwordCheck = 0;
			// 비밀번호확인 
		function checkPwd(){
		 var f1 = document.forms["myForm"];
		 var pw1 = f1.newPassword.value;
		 var pw2 = f1.newPasswordConfirm.value;
		 console.log(pw1);
		 if(pw1!=pw2){
		  document.getElementById('checkPwd2').style.color = "red";
		  document.getElementById('checkPwd2').innerHTML = "비밀번호와 똑같이 입력해주세요.";
		  passwordCheck = 0;
		 }else{
		  document.getElementById('checkPwd2').style.color = "blue";
		  document.getElementById('checkPwd2').innerHTML = "비밀번호가 확인 되었습니다.";
		  passwordCheck = 1;
		 }
		 
		}
      </script>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>