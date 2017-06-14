<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

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
	<title>여행 보기</title>
	<link href="<c:url value='/css/starPoint.css' />" rel="stylesheet">
</head>
<body>
	<c:set value="<%=code %>" var="mCode"></c:set>
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
							<td><a data-toggle="modal" data-dismiss="modal" href="#userInfo">${row.name }</a></td>
							<c:set var="writerCode" value="${row.userCode }"></c:set>
						</tr>
						<c:if test="${row.userCode!= mCode}">
						<tr>
							<td>채팅링크</td>
							<td><a href="checkChatRoom?userCode=${row.userCode }">채팅걸기</a></td>
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
					</c:choose>
					
					<script type="text/javascript">
					
					// 마커를 생성하고 지도위에 표시하는 함수입니다
					function addMarker(position, location, locOrder) {
					
						// 마커를 생성합니다
						var marker = new daum.maps.Marker({
							position : position
						});
					
						// 마커가 지도 위에 표시되도록 설정합니다
						marker.setMap(map);
						
						
						var iwContent = '<div style="padding:5px;">' +location + '(' + (locOrder+1) + ')' + '</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
					    iwPosition = position; //인포윈도우 표시 위치입니다

						// 인포윈도우를 생성합니다
						var infowindow = new daum.maps.InfoWindow({
						    position : iwPosition, 
						    content : iwContent 
						});
						  
						// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
						infowindow.open(map, marker);
					
						// 생성된 마커를 배열에 추가합니다
						markers.push(marker);
						// 하단에 좌표 값을 등록
					}
					</script>
					
					<c:choose>
						<c:when test="${fn:length(route) > 0}">
							<c:forEach items="${route }" var="routes">
								<script type="text/javascript">
								// 마커 하나를 지도위에 표시합니다
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
		</table>
		<!-- 여행 시작일이 오늘보다 크다면, 신청가능 -->
		<!-- 신청가능하다면, 여행신청했는지에 따라 버튼 생성 -->
		<c:set var="userCode" value="<%= code %>"></c:set>
		
		<jsp:useBean id="now" class="java.util.Date" />
		<fmt:formatDate var="toDay" value="${now }" pattern="yyyy-MM-dd"/>
		
		<c:choose>
			<c:when test="${toDay < startDate }">
				<c:choose>
					<c:when test="${fn:length(applyList) > 0}">
						<c:choose>
							<c:when test="${userCode == writerCode}">
								<p>글쓴이는 자동적으로 신청되며, 글수정 및 삭제 기능은 예정 없음</p>
							</c:when>
							<c:otherwise>
								<form action="doCancel" method="post">
									<%=applyCancelButtonStart %><%=applyCancelButtonEnd %>
								</form>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${fn:length(applyCount) != maxPeople }">
								<form action="doApply" method="post">
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
				<!-- 시작일이 지났으므로 신청 불가 -->
				<p>시작일이 지나서 신청이 불가능합니다.</p>
			</c:otherwise>
		</c:choose>
		<hr>
		<!-- review 출력 -->
		<!-- 작성된 리뷰가 있다면 리뷰를 출력하며, 그렇지 않으면 리뷰가 없음을 출력 -->
		<!-- 해당 여행을 신청한 사람인지 확인 후, 신청한 사람이라면 여행리뷰를 남길 수 있게함 -->
		<!-- 단, 리뷰를 남기는 것은 여행이 끝나야하므로 endDate와 비교하며 오늘이 endDate가 지났을 때만 리뷰를 남길 수 있도록 생성 -->
		<!-- 리뷰는 단 한번만 작성할 수 있다 -->
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
					<div>작성된 리뷰가 없습니다.</div>
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
									<form action="doWriteReview" method="post">
										<p class="star_rating">
										    <a href="#this" id="point1">★</a>
										    <a href="#this" id="point2">★</a>
										    <a href="#this" id="point3">★</a>
										    <a href="#this" id="point4">★</a>
										    <a href="#this" id="point5">★</a>
										</p>
										<input type="hidden" name="point" value="0">
										<input type="hidden" name="alist[0].travelCode" value="<%=travelCode %>">
										<textarea name="content" placeholder="최대 20000자 입력" class='form-control' style="width: 80%; height: 20%; resize: none; display: inline;" maxlength="20000"></textarea>
										<input type="hidden" name="alist[0].userCode" value="<%=code %>">
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