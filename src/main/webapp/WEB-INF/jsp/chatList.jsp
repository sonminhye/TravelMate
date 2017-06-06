<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>  
<%@ page import="org.springframework.security.core.Authentication" %>  
<%@ page import="com.travel.mate.security.MyUser" %> 
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="https://cdn.socket.io/socket.io-1.4.5.js"></script>
<style>
.chatlist {
	text-decoration: none;
}

.chatlist:hover {
	text-decoration: none;
}

.chatlist div {
	background-color: #fed136;
	color: black;
	font-size: 13px;
	height: 100px;
}

.chatlist div:hover {
	background-color: #fed935;
}
</style>

<title>채팅방 리스트 출력</title>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div style="margin-top: 150px;">
		<c:choose>
			<c:when test="${fn:length(list) <= 0}">
				채팅방이 존재하지 않습니다.
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="dto">
					<c:set var="send" value='${dto.send }' />
					<c:set var="receive" value='${dto.receive }' />

					<%
						String send = (String) pageContext.getAttribute("send");
						String receive = (String) pageContext.getAttribute("receive");

						session.setAttribute("send", send);
						session.setAttribute("receive", receive);

						send = URLEncoder.encode(send, "UTF-8");
						receive = URLEncoder.encode(receive, "UTF-8");
					%>

					<!-- 리스트의 parameter를 post 형식으로 보내주기 위한 form -->
					<form name="hiddenForm" id="hiddenForm" action="chat" method="POST">
						<input type="hidden" name="rcode" value=""/> 
						<input type="hidden" name="room" value=""/>
					</form>
					<script type="text/javascript">
						function goChat(receiverCode, roomCode) {
							var form = document.getElementById("hiddenForm");
							form.rcode.value = receiverCode;
							form.room.value = roomCode;
							form.submit();
						}
					</script>
					<c:choose>
						<c:when test="${myCode==dto.senderCode}">
							<a class="chatlist" href="#"
								onclick="goChat('${dto.receiverCode}','${dto.roomCode }');">
								<div>
									<p>참여자 : ${dto.receive}, ${dto.latestDate}</p>
									<p>${dto.roomCode}채팅방 입장하기</p>
									<p id="${dto.roomCode }"></p>
								</div>
							</a>
						</c:when>
						<c:otherwise>
							<a class="chatlist" id="'${dto.roomCode}'"
								onclick="goChat('${dto.senderCode}','${dto.roomCode }');">
								<div>
									<p>참여자 : ${dto.send}, ${dto.latestDate}</p>
									<p>${dto.roomCode}채팅방 입장하기</p>
									<p id="${dto.roomCode }"></p>
								</div>
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
	
	<script type="text/javascript">
	
		var socket = io('http://localhost:3000');
		var userCode = '<%=code%>';
		var roomList = '${list}';
		
		//nick name, 방정보 등 정보를 서버에 보냄
		socket.emit('joinAllRooms', {
			userCode : userCode,
			roomList : roomList
		});
			
		//메세지 수신 부분
		socket.on('msg', function(data) {
			appendMessage(data);
		});
		
		appendMessage = function(data) {
			var text = data.msg;
			var p = document.getElementById(data.roomCode);
			if(p.innerHTML!="")
				p.innerHTML = parseInt(p.innerHTML) + 1;
			else
				p.innerHTML = 1;
			console.log(text);
		};
		
	</script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>