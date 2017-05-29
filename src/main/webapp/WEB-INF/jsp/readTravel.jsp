<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<table class="board_view">
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="35%" />
		</colgroup>
		<caption>게시글 상세</caption>
		<tbody>

			<c:choose>
				<c:when test="${fn:length(list) > 0}">
					<c:forEach items="${list }" var="row">
						<tr>
							<th scope="row">글 번호</th>
							<td>${row.travelCode }</td>
						</tr>
						<tr>
							<th scope="row">작성자</th>
							<td>${row.name }</td>
							<th scope="row">작성시간</th>
							<td>${row.writeDate }</td>
						</tr>
						<tr>
							<th scope="row">제목</th>
							<td colspan="3">${row.title }</td>
						</tr>
						<tr>
							<td colspan="4">${row.content }</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
	</table>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>