<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="container" style="margin-top: 150px; margin-bottom: 100px;">
		<form action='j_spring_security_check' method='post'>

			<h3>로그인</h3>
			<div class="form-group">
			    <label for="exampleInputEmail1" class="col-sm-2 control-label">Email address</label>
			     <div class="col-sm-10">
			    	<input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email" name="j_username">
				</div>
			</div>
			<hr><hr>
			<div class="form-group">
			    <label for="exampleInputPassword1" class="col-sm-2 control-label">Password</label>
			     <div class="col-sm-10">
			  	 	<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="j_password">
				 </div>
			</div>
			<c:if test="${not empty param.fail }">
				<div>
					<font color = "red">
						<p>로그인에 실패하였습니다.</p>
					</font>
					<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>
				</div>
			</c:if>
			<button type="submit" class="btn btn-default">로그인</button>
		</form>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>