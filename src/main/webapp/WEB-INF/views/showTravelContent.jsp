<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>�������� ����</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
	<table class="table" width=500 height=700>
		<tr>
			<td colspan=2>����</td>
			<td>�ø���</td>
			<td><button class="btn btn-default" >��û�ϱ�</button></td>
		</tr>
		<tr>
			<td>ȫ�浿</td>
			<td colspan=2></td>
			<td rowspan=3>
				<img src="./img/kyoungbok.jpg" alt="����" width="300" class='img-thumbnail'>
			</td>
		</tr>
		<tr>
			<td>��¥</td>
			<td colspan=2></td>
		</tr>
		<tr>
			<td>�ð�</td>
			<td colspan=2></td>
		
		</tr>
		<tr>
			<td>����</td>
			<td colspan=3></td>
		</tr>
		<tr>
			<td>���</td>
			<td colspan=3></td>
		</tr>
		<tr>
			<td colspan=4></td>
		</tr>
		<tr>
			<td>�ı�</td>
			<td colspan=3></td>
		</tr>
		<tr>
			<td colspan=3></td>
			<td><button class="btn btn-default">���</button></td>
		</tr>
	</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>