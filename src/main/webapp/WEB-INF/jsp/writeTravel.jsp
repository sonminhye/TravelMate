<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>  
<%@ page import="org.springframework.security.core.Authentication" %>  
<%@ page import="com.travel.mate.security.MyUser" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Object principal = auth.getPrincipal();
	int code = 0;
	String email = "";
	
	if(principal != null && principal instanceof MyUser){
		//code는 PK인 유저코드. 
		code = ((MyUser)principal).getUserCode();
	}
%> 
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
function startDateCheck(obj) {
	var now = new Date();
	var usDate = obj.value;
	var ueDate = $("#endDate").val();
	var temps = usDate.split("-");
	var tempe = ueDate.split("-");
	var usersDate = new Date(Number(temps[0]), Number(temps[1]) - 1, Number(temps[2]));
	var usereDate = new Date(Number(tempe[0]), Number(tempe[1]) - 1, Number(tempe[2]));
	
	if (usereDate <= usersDate) {
		alert("시작날짜가 종료날짜와 같거나  늦을 수 없습니다");
		$("#startDate").val("");
	}
	else {
		if (usersDate > now) {
			// 날짜 선택 가능
		}
		// 오늘이거나 이전
		else {
			alert("오늘이거나 오늘보다 이전 날짜는 선택할 수 없습니다");
			$("#startDate").val("");
		}	
	}
};
function endDateCheck(obj) {
	var sDate = $("#startDate").val();
	var eDate = obj.value;
	if (sDate == "" || sDate == null) {
		alert("시작날짜를 먼저 선택해주세요");
		$("#endDate").val("");
	}
	else {
		if (sDate >= eDate) {
			alert("시작날짜이거나 시작날짜보다 이전 날짜는 선택할 수 없습니다");
			$("#endDate").val("");
		}
		else {
			// 날짜선택가능
		}
	}
};
function maxPeopleCheck(obj) {
	var min = $("#minPeople").val();
	var max = obj.value;
	if (min == "" || min == null) {
		alert("최소인원을 먼저 선택해주세요");
		$("#maxPeople").val("");
	}
	else {
		if (max < min) {
			alert("최대인원은 최소인원보다 작을 수 없습니다");
			$("#maxPeople").val("");
		}
		else {
			// 인원선택가능
		}
	}
};
function minPeopleCheck(obj) {
	var min = obj.value;
	var max = $("#maxPeople").val();
	if (max == "" || max == null ) {
		// 아무것도 하지 않음
	}
	else {
		if (max < min) {
			alert("최소인원은 최대인원보다 클 수 없습니다")
			$("#minPeople").val("");
		}
		else {
			// max 값 입력되어 있고, min <= max 이므로 입력가능
		}
	}
}
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container" style="margin-top: 150px; margin-bottom: 100px;">
		<form action="doWrite" method="post" enctype="multipart/form-data">
			<table class="table" width="500">
			<input type="hidden" name="tlist[0].userCode" class="form-control" value="<%=code%>">
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
							<input onchange="startDateCheck(this);" id="startDate" name="tdlist[0].startDate" class="form-control" type="date" required> <span
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
							<input onchange="endDateCheck(this);" id="endDate" name="tdlist[0].endDate" class="form-control" type="date" required> <span
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
							<input onchange="minPeopleCheck(this);" id="minPeople" name="tdlist[0].minPeople" class='form-control' type="number" min="1" required>
						</div>
					</td>
				</tr>
				<tr>
					<td>최대인원<font color="red">*</font></td>
					<td>
						<div class="col-xs-2 form-group row">
							<input onchange="maxPeopleCheck(this);" id="maxPeople" name="tdlist[0].maxPeople" class='form-control' type="number" min="1" required>
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
					<td>사진첨부<font color="red">*</font></td>
					<td><input id="image" name="image" type="file" accept="image/*" onchange="fileCheck(this)" required></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-default">등록하기</button>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="js/datetimepicker/jquery.timepicker.js"></script>
	<link rel="stylesheet" href="js/datetimepicker/jquery.timepicker.css">
	<link rel="stylesheet"
		href="js/datetimepicker/lib/bootstrap-datepicker.css">
	<script type="text/javascript">
		$(document).ready(function() {
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