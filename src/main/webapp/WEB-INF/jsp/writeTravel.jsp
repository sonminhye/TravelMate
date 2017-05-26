<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여행등록</title>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<script src="./js/bootstrap-datepicker.js"></script>
	<link rel="stylesheet" href="./css/datepicker3.css">
	<script type="text/javascript">
		$(document).ready(function() {
			$('#startDate').datepicker();
			$('#endDate').datepicker();
		});
	</script>

	<div class="container" style="margin-top: 150px; margin-bottom: 100px;">
		<form action="writeTravel">
			<table class="table" width="500">
				<tr>
					<td>여행이름</td>
					<td><input class='form-control' type="text"></td>
				</tr>
				<tr>
					<td>시작날짜</td>
					<td>
						<div class='input-group date'>
							<input class="form-control" type="text" id="startDate" /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>종료날짜</td>
					<td>
						<div class='input-group date'>
							<input class="form-control" type="text" id="endDate" /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>시작시간</td>
					<td><input type="text" class="form-control" id="startTime"></td>
				</tr>
				<tr>
					<td>종료시간</td>
					<td><input type="text" class="form-control" id="endTime">
					</td>
				</tr>
				<tr>
					<td>최소/최대인원</td>
					<td><input class='form-control' type="text">/<input
						class='form-control' type="text"></td>
				</tr>
				<tr>
					<td>설명</td>
					<td><textarea class='form-control'>
						
					</textarea></td>
				</tr>
				<tr>
					<td>사진첨부</td>
					<td><input type="file" /></td>
				</tr>
				<tr>
					<td>장소선택</td>
					<td>
						<div>
							<!-- 다음지도 api 들어갈 자리 -->
						</div>
					</td>
				</tr>
			</table>
			<button type="submit" class="btn btn-default">등록하기</button>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>