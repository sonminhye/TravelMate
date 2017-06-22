<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>  
<%@ page import="org.springframework.security.core.Authentication" %>  
<%@ page import="com.travel.mate.security.MyUser" %>

<html>
<head>
	<title>여행 목록</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="<c:url value='/js/calculateDay.js' />"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<hr>
	<hr>

	<%
		String writeButtonStart = "<a href='writeTravel' style='float: right;'><button type='button' class='btn btn-primary btn-lg btn-info'>여행 등록하기";
		String writeButtonEnd ="</button></a>";
	%>
	
	<div role="tabpanel">
		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="makeTravel">
			<%=writeButtonStart %><%=writeButtonEnd %>
				<section id="portfolio" class="bg-light-gray">
					<div class="container">
						<div class="row scrollLocation">
							<c:choose>
								<c:when test="${fn:length(list) > 0}">
									<c:forEach items="${list }" var="row">
										<div class="travel col-md-4 col-sm-6 portfolio-item" style="height: 500">
											<a href="<c:url value='/readTravel' />/${row.travelCode}" class="portfolio-link" data-toggle="modal">
												<input type="hidden" class="travelCode scrolling" data-tcode="${row.travelCode }" value="${row.travelCode }">
												<div class="portfolio-hover">
													<div class="portfolio-hover-content">
														<i class="fa fa-plus fa-3x"></i>
													</div>
												</div>
												<img width="400" src="/userimg/${row.image}" class="img-responsive">
											</a>
											<div class="portfolio-caption">
												<h2 class="travelTitle">${row.title }</h2>
												<h4 class="writerName">${row.name }</h4>
												<p class="writeDay">작성일 : ${row.writeDate }</p>
												<p class="startDay">시작일 : ${row.startDate }</p>
												<h4 class="dDay" id="${row.travelCode }"></h4>
												<!-- D-Day 계산 -->
												<script type="text/javascript">
													var between = getDiffDay("${row.startDate}", getToday());
													$("#${row.travelCode}").html(between);
												</script>
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="4">조회된 결과가 없습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>

						</div>
					</div>
				</section>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var lastScrollTop = 0;
		$(window).scroll(function() {
			var currentScrollTop = $(window).scrollTop();
			if (currentScrollTop - lastScrollTop > 0) {
				if ($(window).scrollTop() >= ($(document).height() - $(window).height())) {
					var lasttcode = $(".scrolling:last").attr("data-tcode");
					$.ajax({
						type: 'post',
						url: 'scrollDown',
						headers: {
							"Content-Type" : "application/json",
							"X-HTTP-Method-Override" : "POST"
						},
						dataType: 'json',
						data: JSON.stringify({
							travelCode : lasttcode
						}),
						success: function(data) {
							var str = "";
							var ddayResult = "";
							var ajaxResult = [];
							if (data != "") {
								$(data).each(
									function() {
										ddayResult = getDiffDay(this.startDate, getToday());
										
										str = $(".travel").clone();
										str.removeClass("travel");
										str.find(".portfolio-link").attr("href", "<c:url value='/readTravel' />" + "/" + this.travelCode);
										str.find(".travelCode").attr("data-tcode", this.travelCode);
										str.find(".travelCode").attr("value", this.travelCode);
										str.find("img").attr("src", "/userimg/"+this.image);
										str.find(".travelTitle").html(this.title);
										str.find(".writerName").html(this.name);
										str.find(".writeDay").html("작성일: " + this.writeDate);
										str.find(".startDay").html("시작일: " + this.startDate);
										str.find(".dDay").attr("id", this.travelCode);
										str.find(".dDay").html(ddayResult);
										ajaxResult.push(str[0]);
									}
								);
								$(".scrollLocation").append(ajaxResult);
							}
							else {
								;
							}
						}
					});
				}
			}
		});
	</script>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>