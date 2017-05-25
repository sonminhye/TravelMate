<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

li {
	margin: 15px;
	list-style: none;
}

.noti {
	text-align: center;
	color: blue;
}

.arrow_box {
	position: relative;
	background: #88b7d5;
}

.arrow_box:after {
	left: 100%;
	top: 50%;
	border: solid transparent;
	content: " ";
	height: 0;
	width: 0;
	position: absolute;
	pointer-events: none;
	border-color: rgba(136, 183, 213, 0);
	border-left-color: #88b7d5;
	border-width: 7px;
	margin-top: -7px;
}

.arrow_box_oth {
	position: relative;
	background: #99d58a;
}

.arrow_box_oth:after {
	right: 100%;
	top: 50%;
	border: solid transparent;
	content: " ";
	height: 0;
	width: 0;
	position: absolute;
	pointer-events: none;
	border-color: rgba(153, 213, 138, 0);
	border-right-color: #99d58a;
	border-width: 7px;
	margin-top: -7px;
}
</style>

<title>채팅창</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div style="margin-top: 150px;">

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
		var room = '${room}';
		var nickname = '${name}'; //유니크한 이름으로 표현해야 함. 나중에 계정으로 하던가 아이디로 해야될 듯
		
		//실제로 구현할 때는, 페이지에 저장되어있는 나의 계정정보 혹은 아이디값이 nickname 이 되도록 함
		socket.emit('join', {
			nickname : nickname,
			room : room
		}); // 접속 이벤트
		
		$('#msg').focus();

		//내가 입장
		socket.on('welcome', function(data) {
			$('#msgdisplay').append(
					$('<li class="noti">').text(nickname + '님 환영합니다.'));
		});
		
		
		
		// 송신: 메시지
		$('form').submit(function() {
			
			var date = (new Date()).toISOString().substring(0, 19).replace('T', ' ');
			
			socket.emit('msg', {
		          name: $("#name").val(),
		          room: $("#room").val(),
		          msg: $("#msg").val(),
		          date: date
		    });

			$('#msg').val('');
			$('#msg').focus();
			return false;
		});

		// 수신: 메시지
		socket.on('msg', function(data) {
			var span = $('<span class="nickname">').text(data.nickname);
			var li;

			if (data.nickname == nickname){
				li = $('<li class="arrow_box">');
			}
			else{
				li = $('<li class="arrow_box_oth">');
			}
			
			li.append(span).append(data.msg);
			$('#msgdisplay').append(li);
			$('#msgdisplay').scrollTop($('#msgdisplay').scrollHeight);
		});
		
		socket.on('left',function(data){ //이 부분은 나중에 온라인 혹은 비온라인 식으로 고쳐줘도 될 듯
			var nick = data.nickname;
			$('#msgdisplay').append(
					$('<li class="noti">').text(nick + '님이 퇴장하셨습니다.'));
		});
	</script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>