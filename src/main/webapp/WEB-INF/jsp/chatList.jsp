<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("euc-kr"); %>
<% response.setContentType("text/html"); %> 
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
		<c:choose>
			<%
				String sname = URLEncoder.encode("${dto.send}", "UTF-8");
				String rname = URLEncoder.encode("${dto.receive}", "UTF-8");
			%>
			<c:when test="${dto.myCode==dto.sCode}">
				
				<a class="chatlist" href="chat?mcode=${dto.myCode}&name=&room=${dto.roomCode}">
				<div>
					참여자 : ${dto.receive}, ${dto.latestDate}
						${dto.roomCode} 채팅방 입장하기
				</div>
			</c:when>
			<c:otherwise>
				<a class="chatlist" href="chat?mcode=${dto.myCode}&name=<%=rname %>&room=${dto.roomCode}">
				<div>
					참여자 : ${dto.send}, ${dto.latestDate}
						${dto.roomCode} 채팅방 입장하기
				</div>
			</c:otherwise>
		</c:choose>
				
			</a>
		</c:forEach>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>