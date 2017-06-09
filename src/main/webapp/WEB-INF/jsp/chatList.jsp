<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.core.Authentication"%>
<%@ page import="com.travel.mate.security.MyUser"%>
<%	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Object principal = auth.getPrincipal();
	int code = 0;
	String email = "";

	if (principal != null && principal instanceof MyUser) {
		//code�� PK�� �����ڵ�. 
		code = ((MyUser) principal).getUserCode();
		email = ((MyUser) principal).getUsername();
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
	margin:0;
}

.chatlist div:hover {
	background-color: #fed935;
}

.unread {
	color: red;
}
</style>

<title>ä�ù� ����Ʈ ���</title>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div style="margin-top: 150px;" id="chatList">
		<c:choose>
			<c:when test="${fn:length(list) <= 0}">
				ä�ù��� �������� �ʽ��ϴ�.
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="dto">
					<!-- ����Ʈ�� parameter�� post �������� �����ֱ� ���� form -->
					<form name="hiddenForm" id="hiddenForm" action="chat" method="POST">
						<input type="hidden" name="rcode" value="" /> <input type="hidden"
							name="room" value="" />
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
							<a class="chatlist" href="#" id="${dto.roomCode }"
								onclick="goChat('${dto.receiverCode}','${dto.roomCode }');">
								<div>
									<p>������ : ${dto.receive}</p>
									<p class="latestdate">${dto.latestDate}</p>
									<p>${dto.roomCode}</p>
									<p class="unread">${dto.unread }</p>
								</div>
							</a>
						</c:when>
						<c:otherwise>
							<a class="chatlist" id="${dto.roomCode}"
								onclick="goChat('${dto.senderCode}','${dto.roomCode }');">
								<div>
									<p>������ : ${dto.send}</p>
									<p class="latestdate">${dto.latestDate}</p>
									<p>${dto.roomCode}</p>
									<p class="unread">${dto.unread }</p>
								</div>
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>

	<script type="text/javascript">
		
		// �޼��� ���� �κ�
		socket.on('msg', function(data) {
			// ���� ���� �޼����� �ƴϸ�
			// ������ �������� �޼������
			if(data.scode != userCode && data.readFlag==0)
				//�ش� ���� ������ ���� �ø���
				appendCountByRoom(data); 
		});

		// �������� �޼��� ���� �÷��ֱ�
		appendCountByRoom = function(data) {
			var text = data.msg;
			var parent = $('#' + data.roomCode);
			var date = parent.find('.latestdate');
			var unread = parent.find('.unread');
			if (unread.html() != "") {
				unread.html(parseInt(unread.html()) + 1);
			} else {
				unread.html(1);
			}
			
			date.html(data.date);
			parent.prependTo("#chatList");
			console.log(text);
		};
	</script>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>