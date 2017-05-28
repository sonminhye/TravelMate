<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여행등록</title>
<script type="text/javascript">
// image check
function fileCheck(obj) {
	var filePoint = obj.value.lastIndexOf(".");
	var fileName = obj.value.substring(filePoint + 1, obj.length);
	var fileType = fileName.toLowerCase();
	
	var checkFileType = new Array();
	checkFileType = ["jpg", "gif", "png", "jpeg", "bmp"];
	
	if (checkFileType.indexOf(fileType) == -1) {
		alert("이미지 파일만 선택 가능합니다");
		var parentObj = obj.parentNode;
		var node = parentObj.replaceChild(obj.cloneNode(true), obj);
		$("#image").val("");
		return false;
	}
};
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container" style="margin-top: 150px; margin-bottom: 100px;">
		<form action="doWrite" method="post">
			<table class="table" width="500">
				<tr>
					<td>여행이름<font color="red">*</font></td>
					<td><input name="tlist[0].title" class='form-control' type="text" required></td>
				</tr>
				<tr>
					<td>설명</td>
					<td><textarea name="tlist[0].content" class='form-control'
							style="height: 100px; resize: none;"></textarea></td>
				</tr>
				<tr>
					<td>시작날짜<font color="red">*</font></td>
					<td>
						<div class='input-group date'>
							<input id="startDate" name="tdlist[0].startDate" class="form-control" type="date" required> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>시작시간</td>
					<td><input id="startTime" name="tdlist[0].startTime" type="time" class="form-control"></td>
				</tr>
				<tr>
					<td>종료날짜<font color="red">*</font></td>
					<td>
						<div class='input-group date'>
							<input id="endDate" name="tdlist[0].endDate" class="form-control" type="date" required> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>종료시간</td>
					<td><input id="endTime" name="tdlist[0].endTime" type="time" class="form-control">
					</td>
				</tr>
				<tr>
					<td>최소인원<font color="red">*</font></td>
					<td>
						<div class="col-xs-2 form-group row">
							<input name="tdlist[0].minPeople" class='form-control' type="number" min="1" required>
						</div>
					</td>
				</tr>
				<tr>
					<td>최대인원</td>
					<td>
						<div class="col-xs-2 form-group row">
							<input name="tdlist[0].maxPeople" class='form-control' type="number" min="1">
						</div>
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
					<td><input id="image" name="tilist[0].image" type="file" accept="image/*" onchange="fileCheck(this)"></td>
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
			// date
			$('#startDate').datepicker({
				format : "yyyy-mm-dd"
			});
			$('#endDate').datepicker({
				format : "yyyy-mm-dd"
			});
			// time
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