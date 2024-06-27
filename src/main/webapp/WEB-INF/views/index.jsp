<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>學生選課系統</title>
</head>
<body>
    <header>
        <img src="/image/NJU.png" alt="">
        <p>Course Enrollment System</p>
      </header>
      
	 <nav>
        <a href="${pageContext.request.contextPath}/index" class="active">首頁</a>
        <a href="${pageContext.request.contextPath}/index/courselist"  class="active">課程列表</a>
        <a href="${pageContext.request.contextPath}/auth/chooseRecords"  class="active">已選課程</a>
        <a href="${pageContext.request.contextPath}/auth/curriculum" class="active">課表</a>
        <a href="${pageContext.request.contextPath}/auth/logout" class="active">登出</a>
    </nav>
	
	<div class="welcome my-3 text-center">
		<h1>個人資訊</h1>
		<p>Welcome, ${user.name}!</p>
	</div>
	

	
	<table align="center" class="pure-table pure-table-bordered" border="1">
        <thead>
            <tr>
             	<c:if test="${userType == 'student'}">
	                <th>學號</th>
	                <th>學生姓名</th>
	                <th>年級</th>
	                <th>科系</th>
                </c:if>
                <c:if test="${userType == 'teacher'}">
	                <th>教職編號</th>
	                <th>老師姓名</th>
                </c:if>
                
            </tr>
        </thead>
        <tbody>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                 <c:if test="${userType == 'student'}">
                    <td>${user.grade}</td>
                    <td>${user.major}</td>
                </c:if>
                  	
                    </td>
                </tr> 
        </tbody>
    </table>
	
	<h2 align="center" class="mt-3">公告</h2>
	<div class="container-xl">
		<table align="center" class="table table-sm" border="1" style="width: 600px;">
		 <tbody>
		  <tr>
		  	<th>選課階段</th>
		  	<th>系統開放時間</th>
		  	<th>系統結束時間</th>
		  </tr>
		  
		  <tr>
			  <th>初選第一階段</th>
			  <th>2024/07/01 上午09:00</th>
			  <th>2024/07/10 上午09:00</th>
		  </tr>
		  
		  <tr>
		  	  <th>初選第二階段</th>
			  <th>2024/07/11 上午09:00</th>
			  <th>2024/07/20 上午09:00</th>
		  </tr>
		  
		 </tbody>
		</table>
	</div>

</body>
</html>