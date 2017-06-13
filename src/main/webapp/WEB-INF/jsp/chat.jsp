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
			<div class="title">Chat</div>
		</div>
		<div>
			<!-- �޼��� �� -->
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

		//���� �������� ��, ȯ���޼����� �����κ��� ���� �κ�
		socket.on('welcome', function(data) {
			var nick = decodeURI(data.nickname);
			inchatRoom = data.participate;
			$('.messages')
					.append($('<li class="noti">').text(nick + '�� ȯ���մϴ�'));
			scrollTop();
		});

		//������ �������� ��, �˷��ִ� �κ�
		socket.on('join', function(data) {
			var nick = decodeURI(data.nickname);
			
			inchatRoom = data.participate;

			//���� ä�ù濡 ������ �д´ٰ� �����ؾ� �� �� �׷��Ƿ� class �� read ���·� �ٲ��ش�.
			if (inchatRoom == true) {
				$('.messages').find('.unread').empty();
				$('.messages').find('.unread').attr('class', 'read');
			}
			
			//�����ϼ̽��ϴ� �κ��� ���߿� ������ �� ����.
			$('.messages').append(
					$('<li class="noti">').text(nick + '���� �����ϼ̽��ϴ�'));
			scrollTop();
		});

		//�޼��� ���� �κ�
		socket.on('msg', function(data) {
			appendMessage(data);
		});

		socket.on('left', function(data) { //�� �κ��� ���߿� �¶��� Ȥ�� ��¶��� ������ �����൵ �� ��
			var nick = decodeURI(data.nickname);
			inchatRoom = data.participate;
			$('.messages').append(
					$('<li class="noti">').text(nick + '���� �����ϼ̽��ϴ�'));
			scrollTop();
		});

		appendMessage = function(data) {
			var $messages, message;
			var text = data.msg;
			var nick = decodeURI(data.nickname);
			var readFlag = data.readFlag;

			if (nick == nickname)
				message_side = 'right';
			else
				message_side = 'left';

			$messages = $('.messages');

			//�޼��� ��ü ����
			message = new Message({
				text : text,
				message_side : message_side,
				nick : nick,
				readFlag : readFlag
			});

			message.draw();
			return scrollTop();
		};

		//��ũ�� �ڵ� ������
		scrollTop = function() {
			var $messages = $('.messages');
			$messages.animate({
				scrollTop : $messages.prop('scrollHeight')
			}, 300);
		};

		//�޼��� Ŭ����
		Message = function(arg) {
			//���� ������ ���� Ŭ���� ��ü���� �ʱ�ȭ,
			this.text = arg.text, this.message_side = arg.message_side;
			this.nick = arg.nick, this.readFlag = arg.readFlag;
			this.draw = function(_this) {
				return function() {
					var $message;
					//�޼��� ���ø��� ����
					$message = $($('.message_template').clone().html());

					//text Ŭ������ ã�Ƽ� �޼��� ������/���� �����ֱ�
					$message.addClass(_this.message_side).find('.text').html(
							_this.text);
					
					//avatar Ŭ���� ã�Ƽ� �г��� ���ֱ�
					$message.find('.avatar').append(_this.nick);

					$('.messages').append($message);

					if (_this.readFlag == 1) {
						$('.messages').find('.unread').empty();
						$('.messages').find('.unread').attr('class', 'read');
					}

					//appeard �� ���̰� �Ⱥ��̰� ���ִ� Ŭ����
					return setTimeout(function() {
						return $message.addClass('appeared');
					}, 0);
				};
			}(this)
		};

		//��ư�� Ŭ������ �� (���콺 Ŭ��)
		$('.send_message').click(
				function(e) {

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
		$('form').submit(
				function(e) {

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