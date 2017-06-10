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
            <div class="box-body" style="padding-bottom:20px; padding-top:20px;">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
	                <th>회원코드</th>
	                <th>계정</th>
	                <th>이름</th>
	                <th>나이</th>
	                <th>성별</th>
	                <th>지역</th>
	                <th>언어</th>
	                <th>권한</th> 
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userDetailList }" var="userDetail">
                <tr>
               		<td>${userDetail.userCode }</td>
               		
               		<td>
               		<c:forEach items="${userList }" var="user">
               			<c:if test="${user.userCode == userDetail.userCode }">
               				 ${user.id }
               			</c:if>
               		</c:forEach>
               		</td>
               		
               		<td>${userDetail.name }</td>
                	<td>${userDetail.age }</td>
                	<td>${userDetail.sex }</td>
                	<td>${userDetail.location }</td>
                	
                	<td>
                	<c:forEach items="${userLanguageList}" var="userLang">
               			<c:if test="${userLang.userCode == userDetail.userCode }">
               			<c:forEach items="${languageList}" var="lang">
	               			<c:if test="${userLang.languageCode == lang.languageCode }">
	               		 		${lang.language} /
	               			</c:if>           				
               			</c:forEach>
               			</c:if>
               		</c:forEach>
                	</td>
                	
                	<td>
               		<c:forEach items="${userAuthList }" var="userAuth">
               			<c:if test="${userAuth.userCode == userDetail.userCode }">
               				 ${userAuth.authority}
               			</c:if>
               		</c:forEach>
                	</td>          	
                </tr>
                </c:forEach>      
                </tbody>
              </table>
            </div>
            <!-- /.box-body -->
            <hr>
            <div class="box-header">
              <h3 class="box-title">권한 목록</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body" style="padding-bottom:20px; padding-top:20px;">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>권한</th>
                  <th>이름</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${authList}" var="auth">
                <tr>
               		<td>${auth.authority}</td>
               		<td>${auth.authorityName}</td>
                </tr>
                </c:forEach>            
               </tbody>
              </table>
            </div>
            <!-- /.box-body -->
            
    		<hr>
            <div class="box-header">
              <h3 class="box-title">접근제한 리소스 목록</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body" style="padding-bottom:20px; padding-top:20px;">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
	                <th>코드</th>
	                <th>url</th>
	                <th>접근가능권한</th>
	                <th>적용순서</th>               
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${securedResourceList}" var="secResource">
                <tr>
              		<td>${secResource.resourceCode}</td>
              		<td>${secResource.resourcePattern}</td>
              		<td>
              		<c:forEach items="${securedResourceAuthList}" var="secResourceAuth">
              			<c:if test="${secResourceAuth.resourceCode == secResource.resourceCode }">
              				 ${secResourceAuth.authority } /
              			</c:if>
              		</c:forEach>
              		</td>
              		<td>${secResource.sortOrder}</td>
                </tr>
                </c:forEach>             
                </tbody>
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