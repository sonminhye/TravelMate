<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<a class="chatlist" href="chat?name=${dto.senderCode }&room=${dto.roomCode }">                 
				<div>
				참여자 : ${dto.receiverCode} , ${dto.senderCode} , ${dto.latestDate}
				
					${dto.roomCode} 채팅방 입장하기
				</div>
			</a>
		</c:forEach>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>