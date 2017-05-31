<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="https://cdn.socket.io/socket.io-1.4.5.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- <link rel="stylesheet" href="css/chat.css">-->

<title>ä�ù�</title>
<script type="text/javascript">
	
</script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<!-- ��Ʈ��Ʈ�� -->
	<link rel="stylesheet" href="css/chatboot.css">
	<div class="chat_window">
		<div class="top_menu">
			<div class="title">Chat</div>
		</div>
		<div>
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

		<div class="message_template">
			<li class="message">
				<div class="avatar"></div>
				<div class="text_wrapper">
					<div class="text"></div>
				</div>
			</li>
		</div>
</div>
		<script type="text/javascript">
			$messages = $('.messages');
			$messages.animate({
				scrollTop : $messages.prop('scrollHeight')
			}, 100);
			$('.message_input').focus();
		</script>
		<script type="text/javascript">
			
			var socket = io('http://localhost:3000');
			var room = '${room}';
			var nickname = '${name}';
			var rcode = '${rcode}';
			var scode = '${scode}';
			var Message;
			var getMessageText, message_side, sendMessage;
			message_side = 'right';
			var nick = encodeURI(nickname);

			//nick name, ������ �� ������ ������ ����
			socket.emit('join', {
				nickname : nick,
				room : room,
				scode : scode,
				rcode : rcode
			}); // ���� �̺�Ʈ

			//���� ����
			socket.on('welcome', function(data) {
				var nick = decodeURI(data.nickname);
				$('.messages').append(
						$('<li class="noti">').text(nick + '�� ȯ���մϴ�'));
			});

			//���� ����
			socket.on('join', function(data) {
				var nick = decodeURI(data.nickname);
				$('.messages').append(
						$('<li class="noti">').text(nick + '���� �����ϼ̽��ϴ�'));
			});

			//�޼��� ���� �κ�
			socket.on('msg', function(data) {
				var nick = decodeURI(data.nickname);
				appendMessage(data);
			});

			socket.on('left', function(data) { //�� �κ��� ���߿� �¶��� Ȥ�� ��¶��� ������ �����൵ �� ��
				var nick = decodeURI(data.nickname);
				$('.messages').append(
						$('<li class="noti">').text(nick + '���� �����ϼ̽��ϴ�'));
			});
			
			appendMessage = function(data) {
				var $messages, message;
				var text = data.msg;
				
				var nick = decodeURI(data.nickname);
				if(nick==nickname) message_side='right';
				else message_side='left';
				
				$messages = $('.messages');
				message = new Message({
					text : text,
					message_side : message_side,
					nick : nick
				});
				
				message.draw();
				return $messages.animate({
					scrollTop : $messages.prop('scrollHeight')
				}, 300);
			};
			
			
			Message = function(arg) {
				
				this.text = arg.text, this.message_side = arg.message_side;
				this.nick = arg.nick;
				
				this.draw = function(_this) {
					return function() {
						var $message;
						$message = $($('.message_template').clone().html());
						$message.addClass(_this.message_side).find('.text')
								.html(_this.text);
						$('.messages').append($message);
						$message.find('.avatar').append(_this.nick);

						//appeard �� ���̰� �Ⱥ��̰� ���ִ� Ŭ����
						return setTimeout(function() {
							return $message.addClass('appeared');
						}, 0);
					};
				}(this)};

			//��ư�� Ŭ������ �� (���콺 Ŭ��)
			$('.send_message').click(function(e) {
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
			
			//���� ���� �� (Ű����)
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