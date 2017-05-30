<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
	
	<div class="row">
		<div class="col-lg-12 text-center"></div>
	</div>
	<div role="tabpanel">
		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="makeTravel">
				<section id="portfolio" class="bg-light-gray">
					<div class="container">
						<div class="row">
							<c:choose>
								<c:when test="${fn:length(list) > 0}">
									<c:forEach items="${list }" var="row">
									
										<div class="col-md-4 col-sm-6 portfolio-item">
											<a href="#this" name="title" class="portfolio-link" data-toggle="modal">
												<input type="hidden" class="travelCode" value="${row.travelCode }"></td>
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
				</section>
			</div>
			<div role="tabpanel" class="tab-pane" id="applyTravel">...</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>