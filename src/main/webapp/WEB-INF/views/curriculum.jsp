<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/curriculum.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <title>學生選課系統</title>
</head>
<body>
    <header>
        <img src="/image/NJU.png" alt="">
        <p>Course Enrollment System</p>
    </header>
      
    <nav>
        <a href="${pageContext.request.contextPath}/index" class="active">首頁</a>
        <a href="${pageContext.request.contextPath}/index/courselist" class="active">課程列表</a>
        <a href="${pageContext.request.contextPath}/auth/chooseRecords" class="active">已選課程</a>
        <a href="${pageContext.request.contextPath}/auth/curriculum" class="active">課表</a>
        <a href="${pageContext.request.contextPath}/auth/logout" class="active logout-link">登出</a>
    </nav>

    <div class="welcome my-3 text-center">
        <h1 class="mt-4">課表</h1>
        <p>Welcome, ${user.name}!</p>
    </div>

    <div class="container-xl">
        <div class="row">
            <div class="table-container">
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>節次/星期</th>
                            <th>星期一</th>
                            <th>星期二</th>
                            <th>星期三</th>
                            <th>星期四</th>
                            <th>星期五</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="period" begin="1" end="10">
                            <tr>
                                <th scope="row">第${period}節</th>
                                <c:forEach var="day" items="${['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday']}">
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty timetable[day][period]}">
                                                ${timetable[day][period].subject} <br>
                                                ${timetable[day][period].place}
                                            </c:when>
                                            <c:otherwise>
                                                -
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div> 
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
    $(document).ready(function() {
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