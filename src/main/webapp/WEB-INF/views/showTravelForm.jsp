<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">  
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="./js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="./css/datepicker3.css">
<title>������</title>
<script type="text/javascript">
$(document).ready(function(){
	 $('#startDate').datepicker();
	 $('#endDate').datepicker();
}); 	
</script>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
	<form action="doWriteTravelForm">
		
		<table class="table" width="500">
			<tr>
				<td>�����̸�</td>
				<td>
					<input class='form-control' type="text">
				</td>
			</tr>
			<tr>
				<td>���۳�¥</td>
				<td>
				<div class='input-group date'>
				 <input class="form-control" type="text" id="startDate" />
				 <span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
             	 </span>
             	 </div>
				</td>
			</tr>
			<tr>
				<td>���ᳯ¥</td>
				<td>
				<div class='input-group date'>
				 <input class="form-control" type="text" id="endDate" />
				 <span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
             	 </span>
             	 </div>
				</td>
			</tr>
			<tr>
				<td>���۽ð�</td>
				<td><input type="text" class="form-control" id="startTime"></td>
			</tr>
			<tr>
				<td>����ð�</td>
				<td>
					<input type="text" class="form-control" id="endTime">
				</td>
			</tr>
			<tr>
				<td>�ּ�/�ִ��ο�</td>
				<td>
					<input  class='form-control' type="text">/<input  class='form-control' type="text">
				</td>
			</tr>
			<tr>
				<td>����</td>
				<td>
					<textarea class='form-control'>
						
					</textarea>
				</td>
			</tr>
			<tr>
				<td>����÷��</td>
				<td>
					<input type="file"/>
				</td>
			</tr>
			<tr>
				<td>
					��Ҽ���				
				</td>
				<td>
					<div><!-- �������� api �� �ڸ� --></div>
				</td>
			</tr>
		</table>
		<button type="submit" class="btn btn-default">����ϱ�</button>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>