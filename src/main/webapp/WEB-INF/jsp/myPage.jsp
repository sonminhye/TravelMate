<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>  
<%@ page import="org.springframework.security.core.Authentication" %>  
<%@ page import="com.travel.mate.security.MyUser" %> 
<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Object principal = auth.getPrincipal();
	int code = 0;
	if(principal != null && principal instanceof MyUser){
		code = ((MyUser)principal).getUserCode();
	}

%>
<html>
<head>
	<title>MyPage</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<hr><hr>

    <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">MyPage</h2>
                    <h3 class="section-subheading text-muted">마이페이지 입니다</h3>
                </div>
    </div>
    
    <!-- 탭 -->
	<div role="tabpanel"style="margin-left: 2%;">
		 <!-- Nav tabs -->
        <!-- <ul class="nav nav-tabs" role="tablist"> -->
		<!--    <li role="presentation" class="active">
		       <a href="#makeTravel" aria-controls="makeTravel" role="tab" data-toggle="tab">내가 만든 여행</a>
		   </li>
		   <li role="presentation">
		       <a href="#applyTravel" aria-controls="applyTravel" role="tab" data-toggle="tab">내가 신청한 여행</a>
		   </li> -->
		   <hr>
		<div class="container" style="width: 70%; margin:50px auto; height:30%; text-align: center;">
		   <form action="<c:url value='/myInfo' />" method="POST">
		 	  <button type="submit" class="btn btn-default">내 정보 수정하기</button>
		   </form>
		</div>
		
		
		 <!-- Tab panes -->
		
<!--         <div class="tab-content">
 -->        
        	<!-- 내가 만든 여행 -->
	<!-- 	    <div role="tabpanel" class="tab-pane active" id="makeTravel">
		    	<section id="portfolio" class="bg-light-gray">
                    <div class="container">
                        카드 레이아웃 출력
                        <div class="row">
                            <div class="col-md-4 col-sm-6 portfolio-item">
                                <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content">
                                            <i class="fa fa-plus fa-3x"></i>
                                        </div>
                                    </div>
                                    <img src="img/portfolio/roundicons.png" class="img-responsive" alt="">
                                </a>
                                <div class="portfolio-caption">
                                    <h4>Round Icons</h4>
                                    <p class="text-muted">Graphic Design</p>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6 portfolio-item">
                                <a href="#portfolioModal2" class="portfolio-link" data-toggle="modal">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content">
                                            <i class="fa fa-plus fa-3x"></i>
                                        </div>
                                    </div>
                                    <img src="img/portfolio/startup-framework.png" class="img-responsive" alt="">
                                </a>
                                <div class="portfolio-caption">
                                    <h4>Startup Framework</h4>
                                    <p class="text-muted">Website Design</p>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6 portfolio-item">
                                <a href="#portfolioModal3" class="portfolio-link" data-toggle="modal">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content">
                                            <i class="fa fa-plus fa-3x"></i>
                                        </div>
                                    </div>
                                    <img src="img/portfolio/treehouse.png" class="img-responsive" alt="">
                                </a>
                                <div class="portfolio-caption">
                                    <h4>Treehouse</h4>
                                    <p class="text-muted">Website Design</p>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6 portfolio-item">
                                <a href="#portfolioModal4" class="portfolio-link" data-toggle="modal">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content">
                                            <i class="fa fa-plus fa-3x"></i>
                                        </div>
                                    </div>
                                    <img src="img/portfolio/golden.png" class="img-responsive" alt="">
                                </a>
                                <div class="portfolio-caption">
                                    <h4>Golden</h4>
                                    <p class="text-muted">Website Design</p>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6 portfolio-item">
                                <a href="#portfolioModal5" class="portfolio-link" data-toggle="modal">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content">
                                            <i class="fa fa-plus fa-3x"></i>
                                        </div>
                                    </div>
                                    <img src="img/portfolio/escape.png" class="img-responsive" alt="">
                                </a>
                                <div class="portfolio-caption">
                                    <h4>Escape</h4>
                                    <p class="text-muted">Website Design</p>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6 portfolio-item">
                                <a href="#portfolioModal6" class="portfolio-link" data-toggle="modal">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content">
                                            <i class="fa fa-plus fa-3x"></i>
                                        </div>
                                    </div>
                                    <img src="img/portfolio/dreams.png" class="img-responsive" alt="">
                                </a>
                                <div class="portfolio-caption">
                                    <h4>Dreams</h4>
                                    <p class="text-muted">Website Design</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
				
		    </div>
		    
		    내가 신청한 여행
		    <div role="tabpanel" class="tab-pane" id="applyTravel">...</div>
		    
	
		  
        </div>	 -->
	</div><!--탭-->
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>