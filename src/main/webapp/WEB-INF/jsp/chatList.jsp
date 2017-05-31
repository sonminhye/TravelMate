<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.net.URLEncoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style>
.chatlist {
	text-decoration: none;
}
.chatlist:hover{
	text-decoration: none;
}

.chatlist div{
	background-color:#fed136;
	color:black;
	font-size:13px;
	height:100px;
}

.chatlist div:hover{
	background-color:#fed935;
}

</style>

<title>채팅방 리스트 출력</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div style="margin-top:150px;">
		<c:forEach items="${list}" var="dto">
		<c:set var="send" value='${dto.send }'/>
		<c:set var="receive" value='${dto.receive }'/>
		<%
			String send = (String)pageContext.getAttribute("send");
			String receive = (String)pageContext.getAttribute("receive");
			
			session.setAttribute("send", send);
			session.setAttribute("receive", receive);
			
			send = URLEncoder.encode(send,"UTF-8");
			receive = URLEncoder.encode(receive,"UTF-8");
		%>
		<c:choose>
			<c:when test="${myCode==dto.senderCode}">
				<a class="chatlist" href="chat?rcode=${dto.receiverCode}&room=${dto.roomCode}">
				<div>
					<p>참여자 : ${dto.receive}, ${dto.latestDate}</p>
					<p>${dto.roomCode} 채팅방 입장하기</p>
				</div>
				</a>
			</c:when>
			<c:otherwise>
				<a class="chatlist" href="chat?rcode=${dto.senderCode}&room=${dto.roomCode}">
				<div>
					<p>참여자 : ${dto.send}, ${dto.latestDate}</p>
					<p>${dto.roomCode} 채팅방 입장하기</p>
				</div>
				</a>
			</c:otherwise>
		</c:choose>
		</c:forEach>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>