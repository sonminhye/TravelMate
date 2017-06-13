<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>  
<%@ page import="org.springframework.security.core.Authentication" %>  
<%@ page import="com.travel.mate.security.MyUser" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Object principal = auth.getPrincipal();
	int code = 0;
	String email = "";
	String unReadCount ="";
	if(principal != null && principal instanceof MyUser){
		//code는 PK인 유저코드. 
		code = ((MyUser)principal).getUserCode();
		email = ((MyUser)principal).getUsername();
		unReadCount = request.getAttribute("unReadCount").toString();
	}
	
	
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Header</title>
    <script src="https://cdn.socket.io/socket.io-1.4.5.js"></script>
    
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
    
    
    <script type="text/javascript" src="js/httpRequest.js"></script>
<script>

	var checkFirst = false;
	var lastKeyword = '';
	var loopSendKeyword = false;
	
	var idCheck = 0;
	var passwordCheck = 0;

	
	function checkEmail() {
	 if (checkFirst == false) {
	  //0.5초 후에 sendKeyword()함수 실행
	  setTimeout("sendEmail();", 500);
	  loopSendKeyword = true;
	 }
	 checkFirst = true;
	}
	
	// 비밀번호확인 
	function checkPwd(){
	 var f1 = document.forms[0];
	 var pw1 = f1.password.value;
	 var pw2 = f1.passwordCheck.value;
	 if(pw1!=pw2){
	  document.getElementById('checkPwd').style.color = "red";
	  document.getElementById('checkPwd').innerHTML = "비밀번호와 똑같이 입력해주세요.";
	  passwordCheck = 0;
	 }else{
	  document.getElementById('checkPwd').style.color = "blue";
	  document.getElementById('checkPwd').innerHTML = "비밀번호가 확인 되었습니다.";
	  passwordCheck = 1;
	 }
	 
	}
	
	
	function sendEmail() {
	 if (loopSendKeyword == false) return;
	 var keyword = document.forms[0].id.value;
	 if (keyword == '') {
	  lastKeyword = '';
	  document.getElementById('checkMsg').style.color = "black";
	  document.getElementById('checkMsg').innerHTML = "이메일주소를 입력하세요.";
	 }

	 else if (keyword != lastKeyword) {
	  lastKeyword = keyword;

		if (keyword != '') {
		  //중복검사
 		  var params = "id="+encodeURIComponent(keyword);
	 	  sendRequest("checkSignup", params, displayResult, 'POST');
		}else {
		}
	 }
	 setTimeout("sendEmail();", 500);
	}
	
	
	function displayResult() {
	 if (httpRequest.readyState == 4) {
	  if (httpRequest.status == 200) {
	   var resultText = httpRequest.responseText;
	   var listView = document.getElementById('checkMsg');
	   if(resultText==0){
	    listView.innerHTML = "";
	    listView.style.color = "blue";
	    idCheck = 1; //아이디 입력했음을 체크
	   }else{
	    listView.innerHTML = "이미 등록된 이메일주소입니다";
	    listView.style.color = "red";
	    idCheck = 2; //중복아이디임을 체크
	   }
	  } else {
	   alert("에러 발생: "+httpRequest.status + httpRequest.responseText );
	  }
	 }
	}

 	function checkSubmit(){
 		var idEmptyCheck = document.forms[0].id.value;
 		var passcheckCheck = document.forms[0].passwordCheck.value;
		var nameCheck = document.forms[0].name.value;
		var ageCheck = document.forms[0].age.value;
		var sexCheck = document.forms[0].sex.value;
		var locationCheck = document.forms[0].location.value;
		var languageCheck = document.forms[0].language.value;
		
		if(idEmptyCheck.length==0){
			alert('이메일 주소를 입력해주세요.');
			return false;
		}
		if(idCheck=='2'){
			alert('이미 존재하는 아이디 입니다.');
			return false;
		}
		if(idCheck=='0' || passwordCheck=='0' || passcheckCheck.length==0 || nameCheck.length==0 || ageCheck.length==0 || sexCheck.length==0 || locationCheck.length==0 || languageCheck.length==0 ){
			alert('회원가입 폼을 정확히 채워 주세요.');
			return false;
		}else{
			return true;
		}
	}
	
</script>
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
	                	<a class="page-scroll"  href="myPage"><%=email%>님 반갑습니다!</a>	
	                </li>
	                <li>
	                	<a class="page-scroll"  href="j_spring_security_logout">SignOut</a>	
	                </li>
	                <li>
	                	<a class="page-scroll" href="chatList">Message</a>
	                	<div class="unreadMsg"><%=unReadCount %></div>
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
					<form action="signUp" method="POST" onsubmit="return checkSubmit()">
						<div class="form-group">
							<!-- 권한을 ROLE_USER로 설정 -->
							<input type="hidden" class="form-control" id="auth" name="authority" value="ROLE_USER">
						</div>
						<div class="form-group">
							<label for="email">이메일 주소</label>
							<input type="email" class="form-control" id="id" name="id" onblur="checkEmail()" placeholder="이메일을 입력하세요">
							<div id="checkMsg"></div>
						</div>
						<div class="form-group">
							<label for="password1">비밀번호</label>
							<input type="password" class="form-control" id="password1" name="password" placeholder="비밀번호">
						</div>
						<div class="form-group">
							<label for="password2">비밀번호 확인</label>
							<input type="password" class="form-control" id="password2" name="passwordCheck" onkeyup="checkPwd()" placeholder="비밀번호 확인">
							<div id="checkPwd"></div>
						</div>
						<div class="form-group">
							<label for="name">이름</label>
							<input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요">
						</div>
						<div class="form-group">
							<label for="age">나이</label>
							<input type="number" class="form-control" id="age" name="age" placeholder="나이를 입력하세요">
						</div>
						<div class="form-group">
							<label for="age">성별</label><br>
							<label class="radio-inline">
								<input type="radio" name="sex" id="inlineRadio1" value="male">남
							</label>
							<label class="radio-inline">
								<input type="radio" name="sex" id="inlineRadio2" value="female">여
							</label>
						</div>
						<div class="form-group">
							<label for="city">지역</label>
							<input type="text" class="form-control" id="city" name="location" placeholder="사는 지역을 입력하세요">
						</div>
						
						<div class="form-group">
							<label for="language">사용가능한 언어 </label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox1" name="langDTOList[0].languageCode" value="1">한국어
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox2" name="langDTOList[1].languageCode" value="2">영어
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox3" name="langDTOList[2].languageCode" value="3">중국어
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox4" name="langDTOList[3].languageCode" value="4">일본어
							</label>
							<label class="checkbox-inline">
							  <input type="checkbox" id="inlineCheckbox5" name="langDTOList[4].languageCode" value="5">스페인어
							</label>
							<!-- <input type="text" class="form-control" id="language" name="language" placeholder="사용 가능한 언어를 입력하세요"> -->
						</div>
						<button type="submit" class="btn btn-default">가입</button>
					</form>
					
					<!-- 입력 여부 체크하기위한 hidden input -->
					<div class="formCheck">
				        <input name="idCheck" id="idCheck" class="idCheck" type="hidden" value='0'>
				        <input name="passwordCheck" id="passwordCheck" class="passwordCheck" type="hidden" value='0'>
				    </div>
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
 	<script type="text/javascript">
	 	var socket = io('http://localhost:3000');
	 	var userCode = '<%=code%>';
	
	 	// nick name 정보를 서버에 보냄
	 	socket.emit('joinAllRooms', {
	 		userCode : userCode
	 	});
	 	
	 	// 메세지 수신 부분
	 	socket.on('msg', function(data) {
	 		//내가 보낸 메세지가 아니고, 현재 읽지 않은 메세지일 경우에만 1 증가
	 		if(data.scode != userCode && data.readFlag==0)
	 			appendCount(data);
	 	});
	
	 	// 읽지않은 메세지 개수 늘려주기
	 	appendCount = function(data) {
	 		var text = data.msg;
	 		var unread = $('.unreadMsg');
	 		
	 		if (unread.html() != "") {
	 			unread.html(parseInt(unread.html()) + 1);
	 		} else {
	 			unread.html(1);
	 		}
	 	};
 	</script>
</body>
</html>
