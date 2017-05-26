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

	<div class="container" style="margin-top: 150px; margin-bottom: 100px;">
		<form action="doWrite" method="post">
			<table class="table" width="500">
				<tr>
					<td>여행이름<font color="red">*</font></td>
					<td><input name="travels[0].title" id="travelTitle" class='form-control' type="text" value="234" required></td>
				</tr>
				<tr>
					<td>설명</td>
					<td><textarea name="travels[0].content" class='form-control'
							style="height: 100px; resize: none;"></textarea></td>
				</tr>
				<tr>
					<td>최소<font color="red">*</font>/최대인원</td>
					<td>
						<div class="col-xs-2 form-group row">
							<input name="travelDetails[0].minPeople" class='form-control' type="number" min="1" required>/
							<input name="travelDetails[0].maxPeople" class='form-control' type="number" min="1">
						</div>
					</td>
				</tr>
				<tr>
					<td>시작날짜<font color="red">*</font></td>
					<td>
						<div class='input-group date'>
							<input id="startDate" name="travelDetails[0].startDate" class="form-control" type="text" required> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>시작시간</td>
					<td><input id="startTime" name="travelDetails[0].startTime" type="text" class="form-control"></td>
				</tr>
				<tr>
					<td>종료날짜<font color="red">*</font></td>
					<td>
						<div class='input-group date'>
							<input id="endDate" name="travelDetails[0].endDate" class="form-control" type="text" required> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>종료시간</td>
					<td><input id="endTime" name="travelDetails[0].endTime" type="text" class="form-control">
					</td>
				</tr>
				<tr>
					<td>장소선택</td>
					<td>
						<div id="map" style="width: 500px; height: 400px;"></div> <script
							type="text/javascript"
							src="//apis.daum.net/maps/maps3.js?apikey=aa223a53480a208175ae1675e33e4193&libraries=services"></script>
						<script type="text/javascript" src=./js/writeMap.js></script>
						<div id="add"></div>
					</td>
				</tr>
				<tr>
					<td>사진첨부</td>
					<td><input name="travelImages[0].image" type="file" /></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-default">등록하기</button>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="js/datetimepicker/jquery.timepicker.js"></script>
	<link rel="stylesheet" href="js/datetimepicker/jquery.timepicker.css">
	<script src="js/datetimepicker/lib/bootstrap-datepicker.js"></script>
	<link rel="stylesheet"
		href="js/datetimepicker/lib/bootstrap-datepicker.css">
	<script type="text/javascript">
		$(document).ready(function() {
			$('#startDate').datepicker({
				format : "yyyy-mm-dd"
			});
			$('#endDate').datepicker({
				format : "yyyy-mm-dd"
			});
		});
		$(document).ready(function() {
			$('#startTime').timepicker({
				'scrollDefault' : 'now',
				'timeFormat' : "H:i:s"
			});
			$('#endTime').timepicker({
				'scrollDefault' : 'now',
				'timeFormat' : "H:i:s"
			});
		});
	</script>
</body>
</html>