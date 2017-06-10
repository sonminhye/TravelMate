<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>DB TEST</title>
</head>

<body>   
<jsp:include page="header.jsp"></jsp:include>

    <!-- Main Content -->
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="post-preview">
                <hr><hr><hr>
                    <c:forEach items="${list}" var="dto">
                        <a href="post_view.do?pId=${dto.userCode}">
                            <h2>
                                ${dto.id}
                            </h2>
                            <h3>
                                ${dto.password}
                            </h3>
                        </a>
                        <p class="post-meta">Posted by ${dto.id} </p>
                        <hr>
                    </c:forEach>
                </div>

                <!-- Pager -->
                <ul class="pager">
                    <li class="next">
                        <a href="write_view.do">write &rarr;</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>