<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>MyPage</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<hr><hr>

    <div class="row">
	    <div class="col-lg-12 text-center">
	        <h2 class="section-heading">MyPage</h2>
	        <h3 class="section-subheading text-muted">마이페이지 입니다</h3>
	    </div>
    </div>

	
	<div class="container" style="margin-top: 150px; margin-bottom: 100px; ">
	     <form action="modifyUserDetail" method="POST" style="display: inline;">
	    	<input type="hidden" class="form-control" id="meanPoint" name="meanPoint" value="0">
	    	
	    	<div class="row">
	    	  <div class="form-group col-sm-10">
			    <label class="control-label">이메일 주소</label> 
			     <p class="form-control-static">${user.id}</p>
			  </div>
			</div>
	
			<div class="row">
				<div class="form-group col-sm-3">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="name" name="name" value="${userDetail.name }" >
				</div>
	    	</div>
	    	
	    	<div class="row">
				<div class="form-group col-sm-3">
				<label for="name">나이</label>
				<input type="number" class="form-control" id="age" name="age" value="${userDetail.age }" >
				</div>
	    	</div>
	    	
	    	<div class="row">
				<div class="form-group col-sm-3">
				<label for="name">성별&nbsp; </label>
				<c:choose>
					<c:when test="${userDetail.sex == male}">
						<label class="radio-inline">
							<input type="radio" name="sex" id="inlineRadio1" value="male" checked>남
						</label>
						<label class="radio-inline">
							<input type="radio" name="sex" id="inlineRadio2" value="female">여
						</label>
					</c:when>
					<c:otherwise>
						<label class="radio-inline">
							<input type="radio" name="sex" id="inlineRadio1" value="male" >남
						</label>
						<label class="radio-inline">
							<input type="radio" name="sex" id="inlineRadio2" value="female" checked>여
						</label>
					</c:otherwise>
				</c:choose>
				</div>
	    	</div>

	    	<div class="row">
				<div class="form-group col-sm-3">
				<label for="name">지역</label>
				<input type="text" class="form-control" id="location" name="location" value="${userDetail.location }">
				</div>
	    	</div>
	    	<hr>
	    		<button type="submit" class="btn btn-default" >수정</button>
	      </form>
	      <span>&nbsp; &nbsp; &nbsp; </span>
	      <form action="<c:url value='/myPassword' />" style="display: inline;" method="POST">
	     		<button type="submit" class="btn btn-default" >패스워드 변경</button>
	      </form>
	</div>
		 
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>