<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
    pageEncoding="UTF-8"%>
<!-- Tomcat 10.x JSTL -->    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"  href="/css/courselist.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
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
        <a href="${pageContext.request.contextPath}/auth/logout" class="active logout-link">登出</a>
    </nav>
	
	<div class="welcome my-3 text-center">
    	<h1 class="mt-4">查詢課程</h1>
		<p>Welcome, ${user.name}!</p>
	</div>
		
	<form class="pure-form mx-3 text-center" method="get" action="/index/search" >
		<fieldset>
				課程序號: <input class="my-2" type="number" id="courseid" name="courseid" />
				科目: <input class="my-2" type="text" id="subject" name="subject" />
				教師姓名: <input class="my-2" type="number" id="teacherid" name="teacherid" /><p />	
				必/選修: <input class="my-2" type="text" id="coursetype" name="coursetype" />	
				學分數:	<input class="my-2" type="number" id="credits" name="credits" />
				上課時間: <input class="my-2" type="text" id="time" name="time" /><p />	
			  <button type="submit" class="pure-button pure-button-primary">搜尋</button>
		</fieldset>
	</form>
		
		
		
	
	<h2 align="center" class="my-3">課程列表</h2>
 	
 	
    <c:if test="${userType == 'teacher'}">
    		<form class="pure-form mx-3" method="post" action="${pageContext.request.contextPath}/index/courselist/add" >
				<fieldset>
					<legend>新增課程</legend>
						課程序號: <input class="my-2" type="number" id="id" name="id" required="required" />
						科目名稱: <input class="my-2" type="text" id="subject" name="subject" required="required" />
						教師ID: <input class="my-2" type="number" id="teacherId" name="teacherId" required="required" /><p />
						必/選修: <input class="my-2" type="text" id="courseType" name="courseType" required="required" />
						上課地點: <input class="my-2" type="text" id="place" name="place" required="required" />
						上課時間: <input class="my-2" type="text" id="time" name="time" required="required" /><p />
						人數上限: <input class="my-2" type="number" id="quota" name="quota" required="quota" />
						學分數: <input class="my-2" type="number" id="credits" name="credits" required="required" />
						備註: <input class="my-2" type="text" id="remark" name="remark" required="required" /><p />
						<button type="submit" class="pure-button pure-button-primary">送出</button>
				</fieldset>
			</form>
    </c:if>
    
 
    
    <table align="center" class="pure-table pure-table-bordered" border="1">
        <thead>
            <tr>
                <th>課程序號</th>
                <th>科目</th>
                <th>教師姓名</th>
                <th>必/選修</th>
                <th>上課地點</th>
                <th>上課時間</th>
                <th>人數上限</th>
                <th>學分數</th>
                <th>備註</th>
                <th>動作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="course" items="${ course }">
                <tr>
                    <td>${course.id}</td>
                    <td>${course.subject}</td>
                    <td id="teacher-name-${course.id}">${course.teacherId}</td>
                    <td>${course.courseType}</td>
                  	<td>${course.place}</td>
                    <td>${course.time}</td>
                    <td>${course.quota}</td>
                    <td>${course.credits}</td>
                    <td>${course.remark}</td>
                	
                	<td>
                	<c:if test="${userType == 'teacher'}">
                	   	<form action="${pageContext.request.contextPath}/index/courselist/delete/${course.id}" method="POST" style="display:inline;">
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="button" class="btn btn-outline-danger delete-button">刪除課程</button>
                        </form>
                    </c:if>   
                    
                    <c:if test="${userType == 'student'}">
                       <form action="${pageContext.request.contextPath}/index/chooseRecords/add" method="POST" style="display:inline;">
                            <input type="hidden" name="studentid" value="${user.id}">
                            <input type="hidden" name="courseid" value="${course.id}">
                            <input type="hidden" name="credits" value="${course.credits}">
                            <input type="hidden" name="action" value="Enroll">
                           <button type="button" class="btn btn-outline-primary enroll-button">加選</button>
                        </form>
                     </c:if>     
                    </td>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table>
   
<script>
$(document).ready(function() {
    $("td[id^='teacher-name-']").each(function() {
        var teacherId = $(this).text();
        var tdElement = $(this);
        $.ajax({
            url: '${pageContext.request.contextPath}/api/teacher/' + teacherId,
            method: 'GET',
            success: function(response) {
                tdElement.text(response);
            },
            error: function() {
                tdElement.text('N/A');
            }
        });
    });

    $(".delete-button").click(function(e) {
        e.preventDefault();
        var form = $(this).closest("form");
        Swal.fire({
            title: '你確定要刪除這門課程嗎?',
            text: "此操作不能撤銷!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '是的, 刪除!',
            cancelButtonText: '取消'
        }).then((result) => {
            if (result.isConfirmed) {
                form.submit();
            }
        });
    });

    $(".enroll-button").click(function(e) {
        e.preventDefault();
        var form = $(this).closest("form");
        Swal.fire({
            title: '你確定要加選這門課程嗎?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '是的, 加選!',
            cancelButtonText: '取消'
        }).then((result) => {
            if (result.isConfirmed) {
                form.submit();
            }
        });
    });

    $(".logout-link").click(function(e) {
        e.preventDefault();
        var href = $(this).attr('href');
        Swal.fire({
            title: '你確定要登出嗎?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '是的, 登出!',
            cancelButtonText: '取消'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = href;
            }
        });
    });
});
</script>
</body>
</html>    