<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://cdn.socket.io/socket.io-1.4.5.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="css/chat.css">
<title>채팅창</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div style="margin-top: 120px;">
			<div id="msgcontainer">
				<div id="msgdisplay">
					<c:forEach items="${list}" var="dto">
						<c:choose>
							<c:when test="${dto.senderCode==name}">
								<li class="arrow_box">
							</c:when>
							<c:otherwise>
								<li class="arrow_box_oth">
							</c:otherwise>
						</c:choose>
						<span class="${dto.senderCode}">${dto.content}</span>
						</li>
					</c:forEach>

				</div>
				<div>
					<form>
						<input size="35" id="msg" /> <input id="sendbtn" type="submit"
							value="Send" />
					</form>
				</div>
			</div>
	
	</div>

	<script type="text/javascript">
		var socket = io('http://localhost:3000');
		var room = '${room}';
		var nickname = '${name}'; //유니크한 이름으로 표현해야 함. 나중에 계정으로 하던가 아이디로 해야될 듯

		//실제로 구현할 때는, 페이지에 저장되어있는 나의 계정정보 혹은 아이디값이 nickname 이 되도록 함
		socket.emit('join', {
			nickname : nickname,
			room : room
		}); // 접속 이벤트

		$('#msg').focus();

		//내가 입장
		socket.on('welcome',
				function(data) {
					$('#msgdisplay').append(
							$('<li class="noti">').text(nickname + ''));
				});

		// 온 메세지
		$('form').submit(
				function() {

					var date = (new Date()).toISOString().substring(0, 19)
							.replace('T', ' ');

					socket.emit('msg', {
						name : $("#name").val(),
						room : $("#room").val(),
						msg : $("#msg").val(),
						date : date
					});

					$('#msg').val('');
					$('#msg').focus();
					return false; // submit 취소
				});

		//메세지 수신 부분
		socket.on('msg', function(data) {
			var span = $('<span class="nickname">').text(data.nickname);
			var li;

			if (data.nickname == nickname) {
				li = $('<li class="arrow_box">');
			} else {
				li = $('<li class="arrow_box_oth">');
			}

			li.append(span).append(data.msg);
			$('#msgdisplay').append(li);
			$("#msgdisplay").scrollTop($("#msgdisplay").scrollheight());

		});

		socket.on('left', function(data) { //이 부분은 나중에 온라인 혹은 비온라인 식으로 고쳐줘도 될 듯
			var nick = data.nickname;
			$('#msgdisplay').append($('<li class="noti">').text(nick + ''));
		});
	</script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>