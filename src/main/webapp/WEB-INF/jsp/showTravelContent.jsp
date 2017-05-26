<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>여행정보 보기</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

	<div class="container" style="margin-top:150px;margin-bottom:100px;">
	<table class="table" width=500 height=700>
		<tr>
			<td colspan=2>제목</td>
			<td>올린날</td>
			<td><button class="btn btn-default" >신청하기</button></td>
		</tr>
		<tr>
			<td>글쓴이 이름</td>
			<td colspan=2></td>
			<td rowspan=3>
				<img src="./img/kyoungbok.jpg" alt="없음" width="300" class='img-thumbnail'>
			</td>
		</tr>
		<tr>
			<td>날짜</td>
			<td colspan=2>날짜적는곳</td>
		</tr>
		<tr>
			<td>시간</td>
			<td colspan=2>시간적는곳</td>
		
		</tr>
		<tr>
			<td>설명</td>
			<td colspan=3>설명하는곳</td>
		</tr>
		<tr>
			<td>장소</td>
			<td colspan=3>
				
			</td>
		</tr>
		<tr>
			<td colspan=4>다음api 지도 들어갈 자리</td>
		</tr>
		<tr>
			<td>후기</td>
			<td colspan=3>별점 아이콘 넣는 곳</td>
		</tr>
		<tr>
			<td colspan=4>
				<textarea></textarea>
			</td>
		</tr>
		<tr>
			<td colspan=3></td>
			<td><button class="btn btn-default">등록</button></td>
		</tr>
	</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>