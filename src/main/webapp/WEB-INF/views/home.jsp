<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

	<h1>Hello world!</h1>
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#myModal">회원가입</button>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">회원가입</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="email">이메일 주소</label>
							<input type="email" class="form-control" id="email" placeholder="이메일을 입력하세요">
						</div>
						<div class="form-group">
							<label for="password1">비밀번호</label>
							<input type="password" class="form-control" id="password1" placeholder="비밀번호">
						</div>
						<div class="form-group">
							<label for="password2">비밀번호 확인</label>
							<input type="password" class="form-control" id="password2" placeholder="비밀번호 확인">
						</div>
						<div class="form-group">
							<label for="name">이름</label>
							<input type="text" class="form-control" id="name" placeholder="이름을 입력하세요">
						</div>
						<div class="form-group">
							<label for="city">지역</label>
							<input type="text" class="form-control" id="city" placeholder="사는 지역을 입력하세요">
						</div>
						<div class="form-group">
							<label for="age">나이</label>
							<input type="number" class="form-control" id="age" placeholder="나이를 입력하세요">
						</div>
						<div class="form-group">
							<label for="language">사용가능한 언어</label>
							<input type="text" class="form-control" id="language" placeholder="사용 가능한 언어를 입력하세요">
						</div>
						<button type="submit" class="btn btn-default">가입</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<hr><hr>
	
	<div role="tabpanel"style="margin-left: 2%;">
		 <!-- Nav tabs -->
		 <ul class="nav nav-tabs" role="tablist">
		   <li role="presentation" class="active"><a href="#makeTravel" aria-controls="makeTravel" role="tab" data-toggle="tab">내가 만든 여행</a></li>
		   <li role="presentation"><a href="#applyTravel" aria-controls="applyTravel" role="tab" data-toggle="tab">내가 신청한 여행</a></li>
		</ul>
		
		 <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="makeTravel">
		    	<div class="col-xs-6 col-lg-3" style="border: 1px solid gray; margin-left: 5%; margin-top: 3%;">
					<img style="width: 100%; margin-top: 10px;" src="https://www.w3schools.com/css/img_fjords.jpg">
					<hr>
					<p>여행 제목 미리보기</p>
				</div>
				<div class="col-xs-6 col-lg-3" style="border: 1px solid gray; margin-left: 5%; margin-top: 3%;">
					<img style="width: 100%; margin-top: 10px;" src="https://www.w3schools.com/css/img_forest.jpg">
					<hr>
					<p>여행 제목 미리보기</p>
				</div>
				<div class="col-xs-6 col-lg-3" style="border: 1px solid gray; margin-left: 5%; margin-top: 3%;">
					<img style="width: 100%; margin-top: 10px;" src="https://www.w3schools.com/css/img_lights.jpg">
					<hr>
					<p>여행 제목 미리보기</p>
				</div>
				<div class="col-xs-6 col-lg-3" style="border: 1px solid gray; margin-left: 5%; margin-top: 3%;">
					<img style="width: 100%; margin-top: 10px;" src="https://www.w3schools.com/css/img_mountains.jpg">
					<hr>
					<p>여행 제목 미리보기</p>
				</div>
				<div class="col-xs-6 col-lg-3" style="border: 1px solid gray; margin-left: 5%; margin-top: 3%;">
					<img style="width: 100%; margin-top: 10px;" src="https://www.w3schools.com/css/img_fjords.jpg">
					<hr>
					<p>여행 제목 미리보기</p>
				</div>
				<div class="col-xs-6 col-lg-3" style="border: 1px solid gray; margin-left: 5%; margin-top: 3%;">
					<img style="width: 100%; margin-top: 10px;" src="https://www.w3schools.com/css/img_forest.jpg">
					<hr>
					<p>여행 제목 미리보기</p>
				</div>
				
		    </div>
		    <div role="tabpanel" class="tab-pane" id="applyTravel">...</div>
		  </div>
		
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>