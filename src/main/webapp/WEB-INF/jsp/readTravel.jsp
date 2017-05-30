<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

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
					</c:choose> <c:choose>
						<c:when test="${fn:length(route) > 0}">
							<c:forEach items="${route }" var="routes">
								<script type="text/javascript">
								// 마커 하나를 지도위에 표시합니다
									addMarker(new daum.maps.LatLng(${routes.lat}, ${routes.lng}));
								</script>
							</c:forEach>
						</c:when>
					</c:choose>
				</td>
			</tr>
		</table>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>