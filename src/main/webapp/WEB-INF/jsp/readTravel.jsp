<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>  
<%@ page import="org.springframework.security.core.Authentication" %>  
<%@ page import="com.travel.mate.security.MyUser" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>여행 보기</title>
	<link href="<c:url value='/css/starPoint.css' />" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<%
		int travelCode = (Integer) request.getAttribute("travelCode");
		int userCode = (Integer) request.getAttribute("userCode");
		String applyButtonStart = null;
		String applyButtonEnd = null;
		String applyCancelButtonStart = null;
		String applyCancelButtonEnd = null;
		// 로그인
		if (userCode > 0) {
			applyButtonStart = "<a href='#this' name='apply' style='float: right;'>"
			+ "<input type='hidden' name='userCode' class='form-class' value="
			+ userCode
			+ ">"
			+ "<input type='hidden' name='travelCode'class='form-class' value="
			+ travelCode
			+ ">"
			+ "<button type='submit' class='btn btn-primary btn-lg btn-info'>여행신청";
			applyButtonEnd ="</button></a>";
			
			applyCancelButtonStart = "<a href='#this' name='apply' style='float: right;'>"
			+ "<input type='hidden' name='userCode' class='form-class' value="
			+ userCode
			+ ">"
			+ "<input type='hidden' name='travelCode'class='form-class' value="
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
	<c:set var="userCode" value="<%= userCode %>"></c:set>


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
							<td><a data-toggle="modal" data-dismiss="modal" href="#userInfo">${row.name }</a></td>
							<c:set var="writerCode" value="${row.userCode }"></c:set>
						</tr>
						<c:if test="${row.userCode!= userCode}">
						<tr>
							<td>채팅링크</td>
							<td><a href="<c:url value='/checkChatRoom?userCode=${row.userCode }' />">채팅걸기</a></td>
						</tr>
						</c:if>
						<tr>
							<td>설명</td>
							<td><textarea readonly="readonly" style="resize: none; border: none; width: 100%;">${row.content }</textarea></td>
						</tr>
						<tr>
							<td>시간</td>
							<td>${row.startDate } ${row.startTime } ~ ${row.endDate } ${row.endTime }</td>
							<c:set var="startDate" value="${row.startDate }"></c:set>
						</tr>
						<tr>
							<td>최소인원/최대인원/신청인원</td>
							<td>${row.minPeople }/${row.maxPeople }/${fn:length(applyCount) }</td>
							<c:set var="maxPeople" value="${row.maxPeople }"></c:set>
						</tr>
						
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
												var mapContainer = document.getElementById('map'),
												mapOption = {
													center : new daum.maps.LatLng(${init.lat}, ${init.lng}),
													level : 7
												};
				
												var map = new daum.maps.Map(mapContainer, mapOption);
												
												var markers = [];
												
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
								</c:choose>
								
								<script type="text/javascript">
									function addMarker(position, location, locOrder) {
									
										var marker = new daum.maps.Marker({
											position : position
										});
									
										marker.setMap(map);
										
										var iwContent = '<div style="padding:5px;">' +location + '(' + (locOrder+1) + ')' + '</div>',
									    iwPosition = position;
				
										var infowindow = new daum.maps.InfoWindow({
										    position : iwPosition, 
										    content : iwContent 
										});
										  
										infowindow.open(map, marker);
									
										markers.push(marker);
									}
								</script>
								
								<c:choose>
									<c:when test="${fn:length(route) > 0}">
										<c:forEach items="${route }" var="routes">
											<script type="text/javascript">
												addMarker(new daum.maps.LatLng(${routes.lat}, ${routes.lng}), '${routes.location}', ${routes.locOrder});
											</script>
											${routes.location }(${routes.locOrder + 1}번째)
										</c:forEach>
									</c:when>
									<c:otherwise>
										<!-- 아무것도 하지않음 -->
									</c:otherwise>
								</c:choose>
							</td>
						</tr>	
					</c:forEach>
				</c:when>
				<c:otherwise>
					없는 여행정보입니다
				</c:otherwise>
			</c:choose>
		</table>
		<%-- 여행 시작일이 오늘보다 크다면, 신청가능 --%>
		<%-- 신청가능하다면, 여행신청했는지에 따라 버튼 생성 --%>
		
		<jsp:useBean id="now" class="java.util.Date" />
		<fmt:formatDate var="toDay" value="${now }" pattern="yyyy-MM-dd"/>
		
		<c:choose>
			<c:when test="${toDay < startDate }">
				<c:choose>
					<c:when test="${fn:length(applyList) > 0}">
						<c:choose>
							<c:when test="${userCode == writerCode}">
								<%--글쓴이는 자동적으로 신청되며, 글수정 및 삭제 기능은 예정 없음 --%>
							</c:when>
							<c:otherwise>
								<form action="<c:url value='/doCancel'/>" method="post">
									<%=applyCancelButtonStart %><%=applyCancelButtonEnd %>
								</form>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${fn:length(applyCount) != maxPeople }">
								<form action="<c:url value='/doApply'/>" method="post">
									<%=applyButtonStart %><%=applyButtonEnd %>
								</form>
							</c:when>
							<c:otherwise>
								<p>신청 인원이 최대 인원입니다.</p>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<%-- 시작일이 지났으므로 신청 불가 --%>
			</c:otherwise>
		</c:choose>
		<hr>
		<%-- review 출력 --%>
		<%-- 작성된 리뷰가 있다면 리뷰를 출력하며, 그렇지 않으면 리뷰가 없음을 출력 --%>
		<%-- 해당 여행을 신청한 사람인지 확인 후, 신청한 사람이라면 여행리뷰를 남길 수 있게함 --%>
		<%-- 단, 리뷰를 남기는 것은 여행이 끝나야하므로 endDate와 비교하며 오늘이 endDate가 지났을 때만 리뷰를 남길 수 있도록 생성 --%>
		<%-- 리뷰는 단 한번만 작성할 수 있다 --%>
		<div id="review">
		<br><br>
			<c:choose>
				<c:when test="${fn:length(reviewList) > 0 }">
					<c:forEach items="${reviewList }" var="review">
						<div>${review.name }(${review.writeTime })<textarea readonly="readonly" style="width: 100%; resize: none; border: none; display: inline;">${review.content }</textarea>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<%-- 리뷰 없음 --%>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${fn:length(applyList) > 0}">
					<c:choose>
						<c:when test="${fn:length(reviewWriteCheck) > 0}">
							<c:choose>
								<c:when test="${fn:length(reviewWrite) > 0}">
									<%-- 이미 리뷰를 작성했습니다. --%>
								</c:when>
								<c:otherwise>
									<form action="<c:url value='/doWriteReview'/>" method="post">
										<p class="star_rating">
										    <a href="#this" id="point1">★</a>
										    <a href="#this" id="point2">★</a>
										    <a href="#this" id="point3">★</a>
										    <a href="#this" id="point4">★</a>
										    <a href="#this" id="point5">★</a>
										</p>
										<input type="hidden" name="point" value="0">
										<input type="hidden" name="travelCode" value="<%=travelCode %>">
										<textarea name="content" placeholder="최대 300자 입력" class='form-control' style="width: 80%; height: 20%; resize: none; display: inline;" maxlength="300"></textarea>
										<input type="hidden" name="userCode" value="<%=userCode %>">
										<button type="submit" class="btn btn-primary btn-lg btn-link" style="margin-top: -40px;">리뷰작성</button>
									</form>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<%-- 여행 마감일이 끝나지 않았으므로 리뷰를 작성할 수 없습니다. --%>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<%-- 신청하지 않았던 여행이므로 리뷰 작성권한이 없습니다. --%>
				</c:otherwise>
			</c:choose>	
		</div>
	</div>
	
	<script type="text/javascript" src="<c:url value='/js/starPoint.js'/>"></script>

	<c:choose>
		<c:when test="${fn:length(userInfo) > 0}">
			<c:forEach items="${userInfo }" var="userInfo">
				<c:set var="sex" value="${userInfo.sex }"></c:set>
				<c:choose>
					<c:when test="${sex == 'male' }">
						<c:set var="sex" value="남성"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="sex" value="여성"></c:set>
					</c:otherwise>
				</c:choose>
				<div class="modal fade" id="userInfo" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-body">
								<div class="form-group">
									<label>${userInfo.name }</label>
								</div>
								<div class="form-group">
									<label>${sex }</label>
								</div>
								<div class="form-group">
									<label>${userInfo.age }세</label>
								</div>
								<div class="form-group">
									<label>${userInfo.location } 거주</label>
								</div>
								<div class="form-group">
									<label>★ ${userInfo.meanPoint }</label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:when>
	</c:choose>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>