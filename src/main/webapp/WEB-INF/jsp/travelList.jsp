<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>Home</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<hr>
	<hr>
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
                    <tr>
                        <td>${row.IDX }</td>
                        <td>${row.TITLE }</td>
                        <td>${row.HIT_CNT }</td>
                        <td>${row.CREA_DTM }</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4">조회된 결과가 없습니다.</td>
                </tr>
            </c:otherwise>
            </c:choose>

							<div class="col-md-4 col-sm-6 portfolio-item">
								<a href="#portfolioModal1" class="portfolio-link"
									data-toggle="modal">
									<div class="portfolio-hover">
										<div class="portfolio-hover-content">
											<i class="fa fa-plus fa-3x"></i>
										</div>
									</div> <img src="img/portfolio/roundicons.png" class="img-responsive"
									alt="">
								</a>
								<div class="portfolio-caption">
									<h4>Round Icons</h4>
									<p class="text-muted">Graphic Design</p>
								</div>
							</div>
							<div class="col-md-4 col-sm-6 portfolio-item">
								<a href="#portfolioModal2" class="portfolio-link"
									data-toggle="modal">
									<div class="portfolio-hover">
										<div class="portfolio-hover-content">
											<i class="fa fa-plus fa-3x"></i>
										</div>
									</div> <img src="img/portfolio/startup-framework.png"
									class="img-responsive" alt="">
								</a>
								<div class="portfolio-caption">
									<h4>Startup Framework</h4>
									<p class="text-muted">Website Design</p>
								</div>
							</div>


					</div>
				</section>

			</div>
			<div role="tabpanel" class="tab-pane" id="applyTravel">...</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>