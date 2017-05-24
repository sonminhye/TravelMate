<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://cdn.socket.io/socket.io-1.4.5.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<style>
#msgdisplay {
	overflow: scroll;
	height: 500px;
	padding: 10px;
	background-color: gold;
}

#msgdisplay span.nickname {
	font-weight: bold;
	font-size: 120%;
	display: inline-block;
	width: 100px;
}

.noti {
	text-align: center;
	color: blue;
}

.arrow_box {
	position: relative;
	background: #82afcc;
	border: 2px solid #48baba;
	margin: 15px;
	width: 50%;
}

.arrow_box:after, .arrow_box:before {
	left: 100%;
	top: 50%;
	border: solid transparent;
	content: " ";
	height: 0;
	width: 0;
	position: absolute;
	pointer-events: none;
}

.arrow_box:after {
	border-color: rgba(130, 175, 204, 0);
	border-left-color: #82afcc;
	border-width: 4px;
	margin-top: -4px;
}

.arrow_box:before {
	border-color: rgba(72, 186, 186, 0);
	border-left-color: #48baba;
	border-width: 7px;
	margin-top: -7px;
}

.arrow_box_oth {
	position: relative;
	background: #82afcc;
	border: 2px solid #baa51a;;
}

.arrow_box_oth:after, .arrow_box:before {
	right: 100%;
	top: 50%;
	border: solid transparent;
	content: " ";
	height: 0;
	width: 0;
	position: absolute;
	pointer-events: none;
}

.arrow_box_oth:after {
	border-color: rgba(130, 175, 204, 0);
	border-right-color: #adcc12;
	border-width: 4px;
	margin-top: -4px;
}

.arrow_box_oth:before {
	border-color: rgba(72, 186, 186, 0);
	border-right-color: #baa51a;;
	border-width: 7px;
	margin-top: -7px;
}
</style>

<title>채팅창</title>
</head>
<body>
	<div>
		<h3>채팅창</h3>

		<div id="msgcontainer">
			<div id="msgdisplay"></div>
			<form>
				<input size="35" id="msg" /> <input id="sendbtn" type="submit"
					value="Send" />
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
	
		var socket = io('http://localhost:3000');
		var nickname = "ss"; //유니크한 이름으로 표현해야 함. 나중에 계정으로 하던가 아이디로 해야될 듯

		//실제로 구현할 때는, 페이지에 저장되어있는 나의 계정정보 혹은 아이디값이 nickname 이 되도록 함
		socket.emit('join', nickname); // 접속 이벤트
		$('#msg').focus();

		//내가 입장
		socket.on('welcome', function(data) {
			$('#msgdisplay').append(
					$('<li class="noti">').text(nickname + '님 환영합니다.'));
		});

		// 송신: 메시지
		$('form').submit(function() {
			socket.emit('msg', $('#msg').val());
			$('#msg').val('');
			$('#msg').focus();
			return false;
		});

		// 수신: 메시지
		socket.on('msg', function(data) {
			var span = $('<span class="nickname">').text(data.nickname);
			var li;

			if (data.nickname == nickname)
				li = $('<li class="arrow_box">');
			else
				li = $('<li class="arrow_box_oth">');

			li.append(span).append(data.msg);
			$('#msgdisplay').append(li);
		});
	</script>
</body>

</html>