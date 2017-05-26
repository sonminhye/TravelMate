<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div style="margin-top:150px;">
		<c:forEach items="${list}" var="dto">                    
			<div>
				참여자 : ${dto.receiverCode} , ${dto.senderCode} , ${dto.latestDate}
				<a href="chat?name=joy&room=${dto.roomCode}">${dto.roomCode}채팅방 입장하기 </a>
			</div>
		</c:forEach>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>