<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Header</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
 
    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!-- CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/agency.css?ver=1" rel="stylesheet">
</head>

<body>

    <!-- Nav Bar-->
    <nav id="mainNav" class="navbar navbar-default navbar-custom  navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand" href="main">Travle Mate</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                
                <!-- 로그인 정보가 존재하지 않을 때. 익명의 사용자 -->
				<sec:authorize access="isAnonymous()">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" data-toggle="modal" data-dismiss="modal" href="#signInModal">SignIn</a>
                    </li>
                    <li>
                        <a class="page-scroll" data-toggle="modal" data-dismiss="modal" href="#signUpModal">SignUp</a>
                    </li>
                </sec:authorize>
                <!-- 로그인 정보가 존재할 때 -->
                <sec:authorize access="isAuthenticated()">
	                <li>
	                	<a class="page-scroll"  href="j_spring_security_logout">SignOut</a>	
	                </li>
	                <li>
	                	<a class="page-scroll" href="#about">Message</a>
	                </li>
                </sec:authorize>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
	
	<!-- 회원가입 Modal -->
	<div class="modal fade" id="signUpModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">회원가입</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="email">이메일 주소</label>
							<input type="email" class="form-control" id="email" placeholder="이메일을 입력하세요">
						</div>
						<div class="form-group">
							<label for="password1">비밀번호</label>
							<input type="password" class="form-control" id="password1" placeholder="비밀번호">
						</div>
						<div class="form-group">
							<label for="password2">비밀번호 확인</label>
							<input type="password" class="form-control" id="password2" placeholder="비밀번호 확인">
						</div>
						<div class="form-group">
							<label for="name">이름</label>
							<input type="text" class="form-control" id="name" placeholder="이름을 입력하세요">
						</div>
						<div class="form-group">
							<label for="city">지역</label>
							<input type="text" class="form-control" id="city" placeholder="사는 지역을 입력하세요">
						</div>
						<div class="form-group">
							<label for="age">나이</label>
							<input type="number" class="form-control" id="age" placeholder="나이를 입력하세요">
						</div>
						<div class="form-group">
							<label for="language">사용가능한 언어</label>
							<input type="text" class="form-control" id="language" placeholder="사용 가능한 언어를 입력하세요">
						</div>
						<button type="submit" class="btn btn-default">가입</button>
					</form>
				</div>
			</div>
		</div>
	</div>
 
 
 
    <!-- 로그인 Modal -->
	<div class="modal fade" id="signInModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">로그인</h4>
				</div>
				<div class="modal-body">
					<form action='j_spring_security_check' method='post'>
						<div class="form-group">
							<label for="email">이메일 주소</label>
							<input type="email" class="form-control" id="email" placeholder="이메일을 입력하세요" name="j_username">
						</div>
						<div class="form-group">
							<label for="password1">비밀번호</label>
							<input type="password" class="form-control" id="password1" placeholder="비밀번호" name="j_password">
						</div>
						
						<button type="submit" class="btn btn-default">Sign In</button>
					</form>
				</div>
			</div>
		</div>
	</div>
 
</body>
</html>
