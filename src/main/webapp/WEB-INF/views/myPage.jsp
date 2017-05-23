<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

	<h1>Hello world!</h1>
	
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