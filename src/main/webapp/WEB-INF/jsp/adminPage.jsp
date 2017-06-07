<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


<div class="container">
	<h3>관리자 페이지</h3>
</div>
  

</head>

<body>

<div style="padding-top:60px; padding-left:60px; padding-right:60px;">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">회원 목록</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>회원코드</th>
                  <th>아이디</th>
                 
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userList}" var="user">           
                <tr>
                	<td>${user.userCode }</td>
                	<td>${user.id }</td>           
                </tr>
                </c:forEach>
                
                <thead>
                <tr>
                  <th>회원코드</th>
                  <th>이름</th>
                  <th>나이</th>
                  <th>성별</th>
                  <th>지역</th>
                  <th>언어</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${userDetailList }" var="userDetail">
                <tr>
               		<td>${userDetail.userCode }</td>
               		<td>${userDetail.name }</td>
                	<td>${userDetail.age }</td>
                	<td>${userDetail.sex }</td>
                	<td>${userDetail.location }</td>
                </tr>
                </c:forEach>
               </tbody>
                <tfoot>
                <tr>
                  <th>Rendering engine</th>
                  <th>Browser</th>
                  <th>Platform(s)</th>
                  <th>Engine version</th>
                  <th>CSS grade</th>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
</div>
</body>


</html>