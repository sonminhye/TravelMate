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
<title>Home</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<c:url value='/js/travelCommon.js'/>" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<hr>
	<hr>
	
	<form id="commonForm" name="commonForm"></form>
	<%
		String writeButtonStart = null;
		String writeButtonEnd = null;
		// 로그인 x
		if (code > 0) {
			writeButtonStart = "<a href='writeTravel' style='float: right;'><button type='button' class='btn btn-primary btn-lg btn-info'>여행 등록하기";
			writeButtonEnd ="</button></a>";
		}
		// 로그인
		else {
			writeButtonStart = "";
			writeButtonEnd = "";
		}
	%>
	<div class="row">
		<div class="col-lg-12 text-center"></div>
	</div>
	
	<div role="tabpanel">
		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="makeTravel">
			<%=writeButtonStart %><%=writeButtonEnd %>
				<section id="portfolio" class="bg-light-gray">
					<div class="container">
						<div class="row">
							<c:choose>
								<c:when test="${fn:length(list) > 0}">
									<c:forEach items="${list }" var="row">
									
										<div class="col-md-4 col-sm-6 portfolio-item">
											<a href="#this" name="title" class="portfolio-link" data-toggle="modal">
												<input type="hidden" class="travelCode scrolling" data-tcode="${row.travelCode }" value="${row.travelCode }">
												<div class="portfolio-hover">
													<div class="portfolio-hover-content">
														<i class="fa fa-plus fa-3x"></i>
													</div>
												</div>
												<img src="img/portfolio/roundicons.png" class="img-responsive" alt="">
											</a>
											<div class="portfolio-caption">
												<h2>${row.title }</h2>
												<h4>${row.content }</h4>
												<p class="text-muted">${row.writeDate }</p>
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

							<script type="text/javascript">
								$(document).ready(function() {
									$("a[name='title']").on("click", function(e) {
										e.preventDefault();
										readTravel($(this));
									});
								});
								function readTravel(obj) {
									var comSubmit = new ComSubmit();
									comSubmit.setUrl("<c:url value='/readTravel' />");
									comSubmit.addParam("travelCode", obj.parent().find(".travelCode").val());
									comSubmit.submit();
								}
							</script>

						</div>
					</div>
				</section>
			</div>
			<div role="tabpanel" class="tab-pane" id="applyTravel">...</div>
		</div>
	</div>
	
	<script>
		var lastScrollTop = 0;
		var easeEffect = "easeInQuint";
		// 1. 스크롤 발생
		$(window).scroll(function() {
			var currentScrollTop = $(window).scrollTop();
			// 스크롤 다운
			if (currentScrollTop - lastScrollTop > 0) {
				console.log("scorll down");
				
				// 2. 현재 스크롤의 top 좌표 > (글을 불러올 화면 height - 윈도우 height) 인 순간
				if ($(window).scrollTop() >= ($(document).height() - $(window).height())) {
					// 3. class가 scroll인 것 중 마지막 요소를 선택, data-tcode를 가져옴
					// 뿌려진 글의 마지막 코드를 읽어 다음 글을 읽기 위함
					var lasttcode = $(".scrolling:first").attr("data-tcode");
					// alert(lasttcode);
					// 4. ajax를 사용해서 해당 코드값을 서버로 보내 3개를 더 읽어온다
					$.ajax({
						type: 'post',		// post로 요청
						url: 'scrollDown',	// 요청할 url
						headers: {
							"Content-Type" : "application/json",
							"X-HTTP-Method-Override" : "POST"
						},
						dataType: 'json',	// 서버로부터 되돌려 받을 데이터타입 명시
						data: JSON.stringify({
							travelCode : lasttcode
						}),
						success: function(data) { // ajax 성공시 수행할 함수
							alert(data);
						},
						complete: function(data) {
							console.log(data);
						}
					});
				}
			}
		});
	</script>
	
	

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>