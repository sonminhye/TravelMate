<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="https://cdn.socket.io/socket.io-1.4.5.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="css/chat.css">
<title>채팅방</title>
<script type="text/javascript">

</script>
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	<div style="margin-top:120px;">
		<div id="msgcontainer">
			
			<div id="msgdisplay">
				<c:forEach items="${list }" var="dto">
					<c:choose>
						<c:when test="${dto.senderCode==scode }">
							<li class="arrow_box">
						</c:when>
						<c:otherwise>
							<li class="arrow_box_oth">
						</c:otherwise>
					</c:choose>
					<span class="nickname">${dto.senderName }</span>
					${dto.content }
				</c:forEach>
			</div>
			<div>
				<form>
					<input size="60" id="msg"/><input id="sendbtn" type="submit" value="Send"/>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		var socket = io('http://localhost:3000');	
		var room = '${room}';
		var nickname = '${name}';
		var rcode = '${rcode}';
		var scode = '${scode}';
		
		var nick =  encodeURI(nickname);
		
		//nick name, 방정보 등 정보를 서버에 보냄
		socket.emit('join', {
			nickname : nick,
			room : room,
			scode : scode,
			rcode : rcode
		}); // 접속 이벤트
		
		$('#msg').focus();
	
		//내가 입장
		socket.on('welcome',
				function(data) {
				var nick = decodeURI(data.nickname);
					$('#msgdisplay').append(
							$('<li class="noti">').text(nick + '님 환영합니다'));
				});

		//내가 입장
		socket.on('join',
				function(data) {
				var nick = decodeURI(data.nickname);
					$('#msgdisplay').append(
							$('<li class="noti">').text(nick + '님이 입장하셨습니다'));
				});

		// 온 메세지
		$('form').submit(
				function() {
					var date = (new Date()).toISOString().substring(0, 19)
							.replace('T', ' ');

					socket.emit('msg', {
						msg : $("#msg").val(),
						date : date
					});

					$('#msg').val('');
					$('#msg').focus();
					return false; // submit 취소
				});
		
		//메세지 수신 부분
		socket.on('msg', function(data) {
			var nick = decodeURI(data.nickname);
			var span = $('<span class="nickname">').text(nick);
			var li;

			if (nick == nickname) {
				li = $('<li class="arrow_box">');
			} else {
				li = $('<li class="arrow_box_oth">');
			}

			li.append(span).append(data.msg);
			$('#msgdisplay').append(li);
		});

		socket.on('left', function(data) { //이 부분은 나중에 온라인 혹은 비온라인 식으로 고쳐줘도 될 듯
			var nick = decodeURI(data.nickname);
			$('#msgdisplay').append($('<li class="noti">').text(nick + '님이 퇴장하셨습니다'));
		});
		
	</script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>