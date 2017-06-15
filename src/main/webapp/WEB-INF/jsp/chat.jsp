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
<title>ä�ù�</title>
</head>
<body>
	<%
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		int code = 0;
		String email = "";

		if (principal != null && principal instanceof MyUser) {
			//code�� PK�� �����ڵ�. 
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
			<!-- �޼��� �� -->
			<ul class="messages">
				<c:forEach items="${list }" var="dto">
					<c:choose>
						<c:when test="${dto.senderCode==scode }">
							<li class="message right appeared" id="${dto.messageCode }">
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
								<div class="unread">��������</div>
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
						<input class="message_input" placeholder="�޼����� �Է��ϼ���" />
				</div>
				<div class="send_message">
					<div class="icon"></div>
					<div class="text">Send</div>
				</div>
				</form>
			</div>
		</div>

		<!-- �޼��� ��� -->
		<div class="message_template">
			<li class="message">
				<div class="avatar"></div>
				<div class="text_wrapper">
					<div class="text"></div>
					<div class="unread">��������</div>
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
		var nick = encodeURI(nickname); // ������� �г����� utf-8 �������� �ٲ㼭 ����
		console.log(room);
		//nick name, ������ �� ������ ������ ����
		socket.emit('chat', {
			nickname : nick,
			room : room,
			scode : scode,
			rcode : rcode
		});

		$('.messages').scroll(function() {
			if($('.messages').scrollTop() == 0){
				var messageCode = $('.message:first-child').attr('id');
				if(messageCode.length!=0){
					$.ajax({
						type: 'post',		// post�� ��û
						url: 'getMoreChats',	// ��û�� url
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
		
		//������ ä��â�� �����ִ��� ���ִ��� �Ǵ��ϴ� ��
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
		
		//���� �������� ��, ȯ���޼����� �����κ��� ���� �κ�
		socket.on('welcome', function(data) {
			var nick = decodeURI(data.nickname);
			inchatRoom = data.participate;
			online(inchatRoom);
			scrollTop();
		});
		
		//������ �������� ��, �˷��ִ� �κ�
		socket.on('join', function(data) {
			var nick = decodeURI(data.nickname);
			inchatRoom = data.participate;
			online(inchatRoom);
			//ä�ù濡 �������ϱ�, ���� �����̹Ƿ� class �� read ���·� �ٲ��ش�.
			if (inchatRoom == true) {
				$('.messages').find('.unread').empty();
				$('.messages').find('.unread').attr('class', 'read');
			}
			scrollTop();
		});

		//�޼��� ���� �κ�
		socket.on('msg', function(data) {
			appendMessage(data);
		});

		socket.on('left', function(data) { //�� �κ��� ���߿� �¶��� Ȥ�� ��¶��� ������ �����൵ �� ��
			var nick = decodeURI(data.nickname);
			inchatRoom = data.participate;
			online(inchatRoom);
			scrollTop();
		});
		
		prependMessage = function(data){
			$.each(data, function(index, element) {
				var message;
				if (element.senderName == nickname) //���� ���� �� �޼������
					message_side = 'right';
				else
					message_side = 'left';

				//�޼��� ��ü ����
				message = new Message({
					messageCode : element.messageCode,
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
			
			if (nick == nickname) //���� ���� �� �޼������
				message_side = 'right';
			else
				message_side = 'left';
			
			//�޼��� ��ü ����
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

		//��ũ�� �ڵ� ������
		scrollTop = function() {
			var $messages = $('.messages');
			$messages.animate({
				scrollTop : $messages.prop('scrollHeight')
			}, 0);
		};

		//�޼��� Ŭ����
		Message = function(arg) {
			//���� ������ ���� Ŭ���� ��ü���� �ʱ�ȭ
			this.messageCode = arg.messageCode;
			this.text = arg.text, this.message_side = arg.message_side;
			this.nick = arg.nick, this.readFlag = arg.readFlag;
			this.old = arg.old;
			this.draw = function(_this) {
				return function() {
					
					var $message;
					
					//�޼��� ���ø��� ����
					$message = $($('.message_template').clone().html());
					//�޼����� id ����
					$message.attr('id', _this.messageCode);
					//text Ŭ������ ã�Ƽ� �޼��� ������/���� �����ֱ�
					$message.addClass(_this.message_side).find('.text').html(
							_this.text);
					//avatar Ŭ���� ã�Ƽ� �г��� ���ֱ�
					$message.find('.avatar').append(_this.nick);
					
					if(_this.old=='n')
						$('.messages').append($message);
					else{
					    var curOffset = $('.messages').scrollTop();
					    $('.messages').prepend($message);
					    curOffset += $message.height() + 20;
					    $('.messages').scrollTop(curOffset);
					}
					
					//���� �� �޼����� ���� �����̸�, Ŭ�������� read �� �ٲ��ֱ�
					if (_this.readFlag == 1) {
						$message.find('.unread').empty();
						$message.find('.unread').attr('class', 'read');
					} 

					//appeard �� ���̰� �Ⱥ��̰� ���ִ� Ŭ����
					return setTimeout(function() {
						return $message.addClass('appeared');
					}, 0);
					
				};
			}(this)
		};
		
		//��ư�� Ŭ������ �� (���콺 Ŭ��)
		$('.send_message').click(function(e) {
					var readFlag = 0;
					if (inchatRoom)
						readFlag = 1;
					//date ����
					var date = (new Date()).toISOString().substring(0, 19)
							.replace('T', ' ');
					var msg = $(".message_input").val();

					socket.emit('msg', {
						msg : msg,
						date : date,
						readFlag : readFlag
					});
					
					$('.message_input').val('');
					return false;
				});

		//���� ���� �� (Ű����)
		$('form').submit(function(e) {
					var readFlag = 0;
					if (inchatRoom)
						readFlag = 1;
					
					//date ����
					var date = (new Date()).toISOString().substring(0, 19)
							.replace('T', ' ');
					var msg = $(".message_input").val();

					socket.emit('msg', {
						msg : msg,
						date : date,
						readFlag : readFlag
					});

					$('.message_input').val('');
					return false;
				});
	</script>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>