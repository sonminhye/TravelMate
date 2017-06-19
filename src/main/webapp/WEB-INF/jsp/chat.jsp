<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.core.Authentication"%>
<%@ page import="com.travel.mate.security.MyUser"%>
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

		if (principal != null && principal instanceof MyUser) {
			//code는 PK인 유저코드. 
			code = ((MyUser) principal).getUserCode();
			email = ((MyUser) principal).getUsername();
		}
	%>
	
	<jsp:include page="header.jsp"></jsp:include>

	<link rel="stylesheet" href="css/chatboot.css">
	<div class="chat_window">
		<div class="top_menu">
			<div class="title">Chat 
			<canvas id="myCanvas" width="15" height="15">
			</canvas>
			</div>
		</div>
		<div>
			<!-- 메세지 뷰 -->
			<ul class="messages">
				<c:forEach items="${list }" var="dto">
					<c:choose>
						<c:when test="${dto.senderCode==scode }">
							<li class="message right appeared" id="${dto.id }">
						</c:when>
						<c:otherwise>
							<li class="message left appeared" id="${dto.messageCode }">
						</c:otherwise>
					</c:choose>
					<div class="avatar">${dto.senderName }</div>
					<div class="text_wrapper">
						<div class="text">${dto.content}</div>
						<c:choose>
							<c:when test="${dto.readFlag==false}">
								<div class="unread">읽지않음</div>
							</c:when>
							<c:otherwise>
								<div class="read"></div>
							</c:otherwise>
						</c:choose>
					</div>
					</li>
				</c:forEach>
			</ul>

			<div class="bottom_wrapper clearfix">
				<div class="message_input_wrapper">
					<form>
						<textarea class="message_input" placeholder="메세지를 입력하세요" maxlength="300"></textarea>
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
					<div class="unread">읽지않음</div>
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
		var sendMessage, scrollTop;
		var message_side = 'right';
		var inchatRoom = false;
		var nick = encodeURI(nickname); // 사용자의 닉네임을 utf-8 형식으로 바꿔서 보냄
		
		//nick name, 방정보 등 정보를 서버에 보냄
		socket.emit('chat', {
			nickname : nick,
			room : room,
			scode : scode,
			rcode : rcode
		});

		//메세지 스크롤을 맨 위로 다 올렸을 때
		$('.messages').scroll(function() {
			if($('.messages').scrollTop() == 0){
				var messageCode = $('.message:first-child').attr('id');
				
				if(messageCode!=undefined){
					$.ajax({
						type: 'post',		// post로 요청
						url: 'getMoreChats',	// 요청할 url
						headers: {
							"Content-Type" : "application/json"
						},
						data: JSON.stringify({
							room : room,
							messageCode : messageCode
						}),
						dataType:'json',
						success: function(data){prependMessage(data)}
					});
				}
			}
		});
		
		//상대방이 채팅창에 들어와있는지 안있는지 판단하는 아이콘
		online = function(data){
			var c=document.getElementById("myCanvas");
			var ctx = c.getContext("2d");
			ctx.beginPath();
			ctx.arc(c.width / 2, c.height / 2, 5, 0, 2 * Math.PI);
			if(data)	
				ctx.fillStyle ="#339933";	
			else
				ctx.fillStyle="#ff4d4d";
			ctx.fill();
		}
		
		//내가 입장했을 때, 환영메세지가 서버로부터 오는 부분
		socket.on('welcome', function(data) {
			inchatRoom = data.participate;
			online(inchatRoom);
			scrollTop();
		});
		
		//누군가 입장했을 때, 알려주는 부분
		socket.on('join', function(data) {
			inchatRoom = data.participate;
			online(inchatRoom);
			//채팅방에 들어왔으니까, 읽은 상태이므로 class 를 read 상태로 바꿔준다.
			if (inchatRoom == true) {
				$('.messages').find('.unread').empty();
				$('.messages').find('.unread').attr('class', 'read');
			}
		});

		//메세지 수신
		socket.on('msg', function(data) {
			appendMessage(data);
		});
	
		//타 유저가 떠났을 때
		socket.on('left', function(data) {
			inchatRoom = data.participate;
			online(inchatRoom);
		});
		
		prependMessage = function(data){
			
			$.each(data, function(index, element) {
				var message;
				if (element.senderName == nickname) //만약 내가 쓴 메세지라면
					message_side = 'right';
				else
					message_side = 'left';

				//메세지 객체 생성
				message = new Message({
					messageCode : element.id,
					text : element.content,
					message_side : message_side,
					nick : element.senderName,
					readFlag : element.readFlag,
					old : 'y'
				});
				
				message.draw();
			});
		}
		
		appendMessage = function(data) {
			
			var message;
			var nick = decodeURI(data.nickname);
			
			if (nick == nickname) //만약 내가 쓴 메세지라면
				message_side = 'right';
			else
				message_side = 'left';
			
			//메세지 객체 생성
			message = new Message({
				messageCode : data.messageCode,
				text : data.msg,
				message_side : message_side,
				nick : nick,
				readFlag : data.readFlag,
				old : 'n'
			});
			message.draw();
			return scrollTop();
		};

		//스크롤 자동 구현부
		scrollTop = function() {
			var $messages = $('.messages');
			$messages.animate({
				scrollTop : $messages.prop('scrollHeight')
			}, 0);
		};

		//메세지 클래스
		Message = function(arg) {
			//받은 변수로 부터 클래스 객체들을 초기화
			this.messageCode = arg.messageCode;
			this.text = arg.text, this.message_side = arg.message_side;
			this.nick = arg.nick, this.readFlag = arg.readFlag;
			this.old = arg.old;
			this.draw = function(_this) {
				return function() {
					var $message;
					//메세지 템플릿을 복사
					$message = $($('.message_template').clone().html());
					//메세지의 id 지정
					$message.attr('id', _this.messageCode);
					//text 클래스를 찾아서 메세지 오른쪽/왼쪽 정해주기
					$message.addClass(_this.message_side).find('.text').html(
							_this.text);
					//avatar 클래스 찾아서 닉네임 써주기
					$message.find('.avatar').append(_this.nick);
					
					if(_this.old=='n')
						$('.messages').append($message);
					else{
						//스크롤 현재 상태 고정하기
					    var curOffset = $('.messages').scrollTop();
					    $('.messages').prepend($message);
					    curOffset += $message.height() + 20;
					    $('.messages').scrollTop(curOffset);
					}
					
					//현재 이 메세지가 읽음 상태이면, 클래스명을 read 로 바꿔주기
					if (_this.readFlag == 1) {
						$message.find('.unread').empty();
						$message.find('.unread').attr('class', 'read');
					} 

					//appeard 는 보이고 안보이게 해주는 클래스
					return setTimeout(function() {
						return $message.addClass('appeared');
					}, 0);
					
				};
			}(this)
		};
		
		sendMessage = function(){
			
			var msg = $(".message_input").val();
			
			if(msg.length==0){
				alert("메세지를 입력해주세요.");
				return false;
			}
			
			var readFlag = 0;
			if (inchatRoom)
				readFlag = 1;
			
			//date 형식
			var date = (new Date()).toISOString().substring(0, 19).replace('T', ' ');
			
			socket.emit('msg', {
				msg : msg,
				date : date,
				readFlag : readFlag
			});
			
			$('.message_input').val('');
		};
		
		//버튼을 클릭했을 때 (마우스 클릭)
		$('.send_message').click(function(e) {
				sendMessage();
				return false;
		});

		//textarea 엔터 쳤을 때 submit
		$('.message_input').keydown(function(event) {
			if (event.keyCode == 13 && !event.shiftKey)
		    {
				console.log('hello');
		    	sendMessage();
		    	return false;
		    }
		});

	</script>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>