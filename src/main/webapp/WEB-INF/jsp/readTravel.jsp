<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>  
<%@ page import="org.springframework.security.core.Authentication" %>  
<%@ page import="com.travel.mate.security.MyUser" %>

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
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	
	<%
		String travelCode = request.getParameter("travelCode");
		
		String applyButtonStart = null;
		String applyButtonEnd = null;
		String applyCancelButtonStart = null;
		String applyCancelButtonEnd = null;
		// 로그인
		if (code > 0) {
			applyButtonStart = "<a href='#this' name='apply' style='float: right;'>"
			+ "<input type='hidden' name='alist[0].userCode' class='form-class' value="
			+ code
			+ ">"
			+ "<input type='hidden' name='alist[0].travelCode'class='form-class' value="
			+ travelCode
			+ ">"
			+ "<button type='submit' class='btn btn-primary btn-lg btn-info'>여행신청";
			applyButtonEnd ="</button></a>";
			
			applyCancelButtonStart = "<a href='#this' name='apply' style='float: right;'>"
			+ "<input type='hidden' name='alist[0].userCode' class='form-class' value="
			+ code
			+ ">"
			+ "<input type='hidden' name='alist[0].travelCode'class='form-class' value="
			+ travelCode
			+ ">"
			+ "<button type='submit' class='btn btn-primary btn-lg btn-info'>여행취소";
			applyCancelButtonEnd ="</button></a>";
		}
		// 로그인 x
		else {
			applyButtonStart = "";
			applyButtonEnd = "";
			applyCancelButtonStart = "";
			applyCancelButtonEnd = "";
		}
	%>

	<div class="container" style="margin-top: 150px; margin-bottom: 100px;">
		<table class="table" width="500">
			<c:choose>
				<c:when test="${fn:length(list) > 0}">
					<c:forEach items="${list }" var="row">
						<tr>
							<td>여행이름</td>
							<td>${row.title }</td>
						</tr>
						<tr>
							<td>작성자</td>
							<td>${row.name }</a></td>
						</tr>
						<tr>
							<td>채팅링크</td>
							<td><a href="checkChatRoom?userCode=${row.userCode }">채팅걸기</a></td>
						</tr>
						<tr>
							<td>설명</td>
							<td>${row.content }</td>
						</tr>
						<tr>
							<td>시간</td>
							<td>${row.startDate } ${row.startTime } ~ ${row.endDate } ${row.endTime }</td>
						</tr>
						<tr>
							<td>최소인원/최대인원</td>
							<td>${row.minPeople }/${row.maxPeople }</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>

			<tr>
				<td>장소</td>
				<td>
					<div id="map" style="width: 500px; height: 400px;"></div>
					<script
						type="text/javascript"
						src="//apis.daum.net/maps/maps3.js?apikey=aa223a53480a208175ae1675e33e4193&libraries=services"></script>
					<c:choose>
						<c:when test="${fn:length(route) > 0}">
							<c:forEach items="${route }" var="init" begin="0" end="1">
								<script type="text/javascript">
									var count = 0;
									var mapContainer = document.getElementById('map'), // 지도를 표시할 div
									mapOption = {
										center : new daum.maps.LatLng(${init.lat}, ${init.lng}), // 지도의 중심좌표
										level : 7
									// 지도의 확대 레벨
									};
	
									var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
									// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
									var markers = [];
	
									
									// 마커를 생성하고 지도위에 표시하는 함수입니다
									function addMarker(position) {
									
										// 마커를 생성합니다
										var marker = new daum.maps.Marker({
											position : position
										});
									
										// 마커가 지도 위에 표시되도록 설정합니다
										marker.setMap(map);
									
										// 생성된 마커를 배열에 추가합니다
										markers.push(marker);
										// 하단에 좌표 값을 등록
									}
									// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
									function setMarkers(map) {
										for (var i = 0; i < markers.length; i++) {
											markers[i].setMap(map);
										}
									}
								</script>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<script type="text/javascript">
								$("#map").remove();
							</script>
							등록된 장소가 없습니다.
						</c:otherwise>
					</c:choose> <c:choose>
						<c:when test="${fn:length(route) > 0}">
							<c:forEach items="${route }" var="routes">
								<script type="text/javascript">
								// 마커 하나를 지도위에 표시합니다
									addMarker(new daum.maps.LatLng(${routes.lat}, ${routes.lng}));
								</script>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<!-- 아무것도 하지않음 -->
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<!-- 신청했다면 취소버튼 그렇지 않으면 신청 버튼 -->
		<c:choose>
			<c:when test="${fn:length(applyList) > 0}">
				<form action="doCancel" method="post">
					<%=applyCancelButtonStart %><%=applyCancelButtonEnd %>
				</form>
			</c:when>
			<c:otherwise>
				<form action="doApply" method="post">
					<%=applyButtonStart %><%=applyButtonEnd %>
				</form>
			</c:otherwise>
		</c:choose>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>