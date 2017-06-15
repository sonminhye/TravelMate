<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Enumeration" %>
    
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
          
          <%
          Enumeration se = session.getAttributeNames();
          
          while(se.hasMoreElements()){
           String getse = se.nextElement()+"";
           System.out.println("@@@@@@@ session : "+getse+" : "+session.getAttribute(getse));
          }

          %>
            <!-- 회원 목록 출력 -->
            <div class="box-header">
              <h3 class="box-title">회원 목록</h3>
            </div>
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
	                <th></th>
                </tr>
                </thead>

                <tbody>         
                <!-- 유저 디테일 리스트 출력 -->
                <c:forEach items="${userDetailList }" var="userDetail">
                <tr>
               		<td>${userDetail.userCode }</td>
               		
               		<!-- 유저코드를 통해 유저리스트에서 id를 가져옴 -->
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
                	
                	<!-- 유저 랭귀지 리스트 출력 -->
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
                	
               	    <!-- 현재 행 유저의 권한정보를 변수에 저장 -->
               		<c:forEach items="${userAuthList }" var="userAuth">
               			<c:if test="${userAuth.userCode == userDetail.userCode }">
               				<c:set var="userAuthority" value="${userAuth.authority}" />
               			</c:if>
               		</c:forEach>
	
                	<!-- 유저 권한 수정 -->        	
                	<form action="modifyUserAuth" method="POST">
                	<td>
	                	<select class="form-control" name="authority">
		                	<!-- 전체 권한 목록 출력 -->
		                	<c:forEach items="${authList}" var="auth">
		                	<!-- 현재 행의 유저가 가진 권한정보는 selected 로 설정 -->
			                	<c:choose>
				                	<c:when test="${auth.authority == userAuthority}">
				                		<option value="${auth.authority}" selected="selected">${auth.authorityName}</option>
				                    </c:when>
				                    <c:otherwise>
				                		<option value="${auth.authority}">${auth.authorityName}</option>
				                	</c:otherwise>
			                	</c:choose>
		                	</c:forEach>
	                	</select>
                	</td>
                	<td>
                		<input type="hidden" name="userCode" value="${userDetail.userCode }"/>
              			<button type="submit" class="btn btn-default">수정</button>
              		</td>
              		</form>
                </tr>              
                </c:forEach>                 
                </tbody>              
              </table>
            </div>
        
            <!-- 권한 목록 출력 -->
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


            <!-- 리소스 목록 및 권한 출력 -->
    		<hr>
            <div class="box-header">
              <h3 class="box-title">접근제한 리소스 목록</h3>
            </div>
            <div class="box-body" style="padding-bottom:20px; padding-top:20px;">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
	                <th>코드</th>
	                <th>url</th>
	                <th>접근가능권한</th>
	                <th></th>
	                
	                <th>적용순서</th>        
	                <th></th>       
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${securedResourceList}" var="secResource">
                <tr>
              		<td>${secResource.resourceCode}</td>
              		<td>${secResource.resourcePattern}</td>
              		<!-- 접근가능권한 -->
              		<td>
              		<c:forEach items="${securedResourceAuthList}" var="secResourceAuth">
              			<c:if test="${secResourceAuth.resourceCode == secResource.resourceCode }">
              				 ${secResourceAuth.authority } /
              			</c:if>
              		</c:forEach>
              		</td>
              		
              		<form action="modifySecuredResourceAuth" method="POST">
              		<td width>
              		<label class="checkbox-inline">
              		<input type="hidden" name="securedResourceAuthDTOList[0].resourceCode" value="${secResource.resourceCode}"/>
              		<input type="checkbox" id="inlineCheckbox1" name="securedResourceAuthDTOList[0].authority" value="none">없음
              		</label>
              			<c:forEach items="${authList }" var="auth" varStatus="status">     
              			<label class="checkbox-inline">  			    			
	                	  <input type="hidden" name="securedResourceAuthDTOList[${status.index + 1}].resourceCode" value="${secResource.resourceCode}"/>
						  <input type="checkbox" id="inlineCheckbox1" name="securedResourceAuthDTOList[${status.index + 1}].authority" value="${auth.authority}">${auth.authorityName}        	
	                	</label>
	                	</c:forEach>
	               
	              		
              		</td>
              		<td>
              			<input type="hidden" name="resourceCode" value="${secResource.resourceCode}">
              			<input type="number" class="form-control" name="sortOrder" value="${secResource.sortOrder}" >
              		</td>
              		<td>
              			<button type="submit" class="btn btn-default">수정</button>
              		</td>
              		</form>
              		
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