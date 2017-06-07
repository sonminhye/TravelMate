<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>  
<%@ page import="org.springframework.security.core.Authentication" %>  
<%@ page import="com.travel.mate.security.MyUser" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="https://cdn.socket.io/socket.io-1.4.5.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>채팅방</title>
</head>
<body>
<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Object principal = auth.getPrincipal();
	int code = 0;
	String email = "";
	
	if(principal != null && principal instanceof MyUser){
		//code는 PK인 유저코드. 
		code = ((MyUser)principal).getUserCode();
		email = ((MyUser)principal).getUsername();
	}
%>
	<jsp:include page="header.jsp"></jsp:include>
	
	<link rel="stylesheet" href="css/chatboot.css">
	<div class="chat_window">
		<div class="top_menu">
			<div class="title">Chat</div>
		</div>
		<div>
			<!-- 메세지 뷰 -->
			<ul class="messages">
				<c:forEach items="${list }" var="dto">
					<c:choose>
						<c:when test="${dto.senderCode==scode }">
							<li class="message right appeared">
						</c:when>
						<c:otherwise>
							<li class="message left appeared">
						</c:otherwise>
					</c:choose>
					<div class="avatar">${dto.senderName }</div>
					<div class="text_wrapper">
						<div class="text">${dto.content}</div>
						<c:choose>
						<c:when test="${dto.readFlag==false}">
							<div class="unread">O</div>
						</c:when>
						<c:otherwise>
							<div class="read">O</div>
						</c:otherwise>
					</c:choose>
					</div>
					</li>
				</c:forEach>
			</ul>
			
			<div class="bottom_wrapper clearfix">
				<div class="message_input_wrapper">
				<form>
					<input class="message_input" placeholder="메세지를 입력하세요" />
				</div>
				<div class="send_message">
					<div class="icon"></div>
					<div class="text">Send</div>
				</div>
				</form>
			</div>
		</div>

		<!-- 메세지 양식 -->
		<div class="message_template">
			<li class="message">
				<div class="avatar"></div>
				<div class="text_wrapper">
					<div class="text"></div>
					<div class="unread">O</div>
				</div>
			</li>
		</div>
</div>

<script type="text/javascript">
			
			var room = '${room}';
			var nickname = '${name}';
			var rcode = '${rcode}';
			var scode = '${scode}';
			var Message;
			var getMessageText, message_side, sendMessage, scrollTop;
			var message_side = 'right';
			var inchatRoom = false;
			var nick = encodeURI(nickname); // 사용자의 닉네임을 utf-8 형식으로 바꿔서 보냄
			
			//nick name, 방정보 등 정보를 서버에 보냄
			//보내면서 내 코드와 룸에서 읽지않은 것이 있다면 읽음 표시를 해줌
			socket.emit('join', {
				nickname : nick,
				room : room,
				scode : scode,
				rcode : rcode
			});
			
			//내가 입장했을 때, 환영메세지가 서버로부터 오는 부분
			socket.on('welcome', function(data) {
				var nick = decodeURI(data.nickname);
				$('.messages').append(
						$('<li class="noti">').text(nick + '님 환영합니다'));
				scrollTop();
			});
			
			//누군가 입장했을 때, 알려주는 부분
			socket.on('join', function(data) {	
				var nick = decodeURI(data.nickname);
				inchatRoom = true;
				
				$('.messages').append(
						$('<li class="noti">').text(nick + '님이 입장하셨습니다'));
			});

			//메세지 수신 부분
			socket.on('msg', function(data) {
				var nick = decodeURI(data.nickname);
				appendMessage(data);
			});
			
			socket.on('left', function(data) { //이 부분은 나중에 온라인 혹은 비온라인 식으로 고쳐줘도 될 듯
				var nick = decodeURI(data.nickname);
				inchatRoom = false;
				$('.messages').append(
						$('<li class="noti">').text(nick + '님이 퇴장하셨습니다'));
				scrollTop();
			});
			
			
			appendMessage = function(data) {
				
				var $messages, message;
				var text = data.msg;
				var nick = decodeURI(data.nickname);
				if(nick==nickname) message_side='right';
				else message_side='left';
				
				$messages = $('.messages');
				
				//메세지 객체 생성
				message = new Message({
					text : text,
					message_side : message_side,
					nick : nick
				});
				
				message.draw();
				return scrollTop();
			};
			
			//스크롤 자동 새로고침 구현부분
			scrollTop = function(){
				var $messages = $('.messages');
				$messages.animate({
					scrollTop : $messages.prop('scrollHeight')
				}, 300);
			};
			
			//메세지 클래스
			Message = function(arg) {
				//받은 변수로 부터 클래스 객체들을 초기화,
				this.text = arg.text, this.message_side = arg.message_side;
				this.nick = arg.nick;
				this.draw = function(_this) {
					return function() {
						var $message;
						//메세지 템플릿을 복사
						$message = $($('.message_template').clone().html());
						
						//text 클래스를 찾아서 메세지 오른쪽/왼쪽 정해주기
						$message.addClass(_this.message_side).find('.text')
								.html(_this.text);
						//avatar 클래스 찾아서 닉네임 써주기
						$message.find('.avatar').append(_this.nick);
						
						$('.messages').append($message);
						
						//appeard 는 보이고 안보이게 해주는 클래스
						return setTimeout(function() {
							return $message.addClass('appeared');
						}, 0);
					};
				}(this)};

			//버튼을 클릭했을 때 (마우스 클릭)
			$('.send_message').click(function(e) {
				
				//date 형식
				var date = (new Date()).toISOString().substring(0, 19)
				.replace('T', ' ');
				var msg = $(".message_input").val();
				
				socket.emit('msg', {
					msg : msg,
					date : date
				});
				
				$('.message_input').val('');
				return false;
			});
			
			//엔터 쳤을 때 (키보드)
			$('form').submit(function(e) {
				
				var date = (new Date()).toISOString().substring(0, 19)
				.replace('T', ' ');
				
				var msg = $(".message_input").val();
				socket.emit('msg', {
					msg : msg,
					date : date
				});
				
				$('.message_input').val('');
				return false;
			});
</script>

		<jsp:include page="footer.jsp"></jsp:include>
		
</body>
</html>