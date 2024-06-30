<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"  href="/css/chooseRecords.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <title>選課紀錄列表</title>
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
        <h1 class="mt-4">選課紀錄列表</h1>
        <p>Welcome, ${user.name}!</p>
    </div>

    <table align="center" class="pure-table pure-table-bordered" border="1">
        <thead>
            <tr>
                <th>選課序號</th>
                <th>學生學號</th>
                <th>課程序號</th>
                <th>課程名稱</th>
                <th>學分數</th>
                <th>選課時間</th>
                <th>狀態</th>
                <th>動作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="chooseRecord" items="${ chooseRecordsDtos }">
                <tr>
                    <td>${chooseRecord.id}</td>
                    <td>${chooseRecord.studentid}</td>
                    <td class="course-id" data-courseid="${chooseRecord.courseid}">${chooseRecord.courseid}</td>
                    <td class="course-name">Loading...</td>
                    <td>${chooseRecord.credits}</td>
                    <td>${chooseRecord.choosetime}</td>
                    <td>${chooseRecord.action}</td>
                    <td>   
                        <form action="${pageContext.request.contextPath}/auth/chooseRecords/delete/${chooseRecord.id}" method="POST" style="display:inline;" class="delete-form">
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="button" class="btn btn-outline-danger delete-button">退選</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <script>
    $(document).ready(function() {
        $(".course-id").each(function() {
            var courseId = $(this).data("courseid");
            var courseNameElement = $(this).next(".course-name");
            $.ajax({
                url: '${pageContext.request.contextPath}/api/courses/name/' + courseId,
                method: 'GET',
                success: function(response) {
                    courseNameElement.text(response);
                },
                error: function() {
                    courseNameElement.text('載入課程名稱出錯');
                }
            });
        });

        $(".delete-button").click(function(e) {
            e.preventDefault();
            var form = $(this).closest("form");
            Swal.fire({
                title: '你確定要退選這門課嗎?',
                text: "此操作不能撤銷!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '是的, 退選!',
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