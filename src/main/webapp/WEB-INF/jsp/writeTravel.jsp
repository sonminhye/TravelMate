<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>  
<%@ page import="org.springframework.security.core.Authentication" %>  
<%@ page import="com.travel.mate.security.MyUser" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Object principal = auth.getPrincipal();
	int code = 0;
	
	if (principal != null && principal instanceof MyUser) {
		code = ((MyUser)principal).getUserCode();
	}
%> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>여행등록</title>
	<script type="text/javascript" src="<c:url value='/js/writeCheck.js' />"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container" style="margin-top: 150px; margin-bottom: 100px;">
		<form action="<c:url value='/doWrite' />" method="post" enctype="multipart/form-data">
			<table class="table" width="500">
			<input type="hidden" name="userCode" class="form-control" value="<%=code%>">
				<tr>
					<td>여행이름<font color="red">*</font></td>
					<td><input name="title" class='form-control' type="text" maxlength="15" placeholder="여행제목을 입력하세요(15자이내)" required></td>
				</tr>
				<tr>
					<td>설명</td>
					<td><textarea placeholder="최대 20000자 입력" name="content" class='form-control'
							style="height: 100px; resize: none;" maxlength="20000" ></textarea></td>
				</tr>
				<tr>
					<td>시작날짜<font color="red">*</font></td>
					<td>
						<div class='input-group date'>
							<input onchange="startDateCheck(this);" id="startDate" name="startDate" class="form-control" type="date" required> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>시작시간</td>
					<td><input id="startTime" name="startTime" type="time" class="form-control" value="00:00:00"></td>
				</tr>
				<tr>
					<td>종료날짜<font color="red">*</font></td>
					<td>
						<div class='input-group date'>
							<input onchange="endDateCheck(this);" id="endDate" name="endDate" class="form-control" type="date" required> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>종료시간</td>
					<td><input id="endTime" name="endTime" type="time" class="form-control" value="00:00:00">
					</td>
				</tr>
				<tr>
					<td>최소인원<font color="red">*</font></td>
					<td>
						<div class="col-xs-2 form-group row">
							<input onblur="minPeopleCheck(this);" id="minPeople" name="minPeople" class='form-control' type="number" min="1" required>
						</div>
					</td>
				</tr>
				<tr>
					<td>최대인원<font color="red">*</font></td>
					<td>
						<div class="col-xs-2 form-group row">
							<input onblur="maxPeopleCheck(this);" id="maxPeople" name="maxPeople" class='form-control' type="number" min="1" required>
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
					<td>사진첨부(2MB미만)<font color="red">*</font></td>
					<td><input id="image" name="image" type="file" accept="image/*" onchange="fileCheck(this)" required></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-default">등록하기</button>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<link rel="stylesheet" href="<c:url value='/css/timePicker.css' />">
	<link rel="stylesheet" href="<c:url value='/css/datePicker.css' />">
	<script src="<c:url value='/js/timePicker.js' />"></script>
	<script src="<c:url value='/js/datePicker.js' />"></script>
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
			$('#startDate').datepicker({
				format: 'yyyy-mm-dd',
				autoclose: true
			});
			$('#endDate').datepicker({
				format: 'yyyy-mm-dd',
				autoclose: true
			});
		});
	</script>
</body>
</html>