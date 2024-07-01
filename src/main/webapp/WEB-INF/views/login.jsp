<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>學生選課系統</title>
  <link rel="stylesheet" href="/css/login.css">
  <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  
  <style>
    #mycanvas{cursor: pointer;text-align:center}
    #myform{margin:0px auto;width:300px;height:200px;border:5px #FFAC55 solid;}
  </style>
</head>

<body class="text-center">
  <main class="form-signin">
    <header>
      <img src="/image/NJU.png" alt="">
      <p>Course Enrollment System</p>
    </header>
    
    <form class="mt-9" action="${pageContext.request.contextPath}/auth/login" method="post" id="loginForm">
      <div class="form-floating">
        <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="email" required="required">
        <label for="floatingInput">Email address</label>
      </div>
      <div class="form-floating">
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" required="required">
        <label for="floatingPassword">Password</label>
      </div>

       <div class="form-floating">
        <canvas id="mycanvas" width='150' height='40'></canvas>
      </div>
      
      <div class="form-floating">
        <input id="myvad" type="text" class="form-control" placeholder="請輸入驗證碼" name="vad" required="required">
        <label for="myvad">請輸入驗證碼</label>
      </div>
	 
      <div>
        <button class="w-100 btn btn-lg btn btn-primary" type="submit">Login</button>
      </div>

      <div>
        <c:if test="${not empty error}">
          <p style="color:red">${error}</p>
        </c:if>
      </div>

      <p class="mt-5 mb-3 text-muted">&copy; 2024 J. made</p>
    </form>
  </main>

  <script>
    var mycanvas = document.getElementById('mycanvas');
    var cxt = mycanvas.getContext('2d');
    var validate = "";

    var sColor = ["#B22222", "#F9F900", "#82D900", "#FFAF60"];
    var fColor = ["#000079", "#006030", "#820041", "#4B0091"];
    var indexColor = "";
    var img = new Image();
    img.src = "https://i.imgur.com/ssTQW1o.jpg";

    function randColor() {
      indexColor = "";
      indexColor = Math.floor(Math.random() * sColor.length);
      return indexColor;
    }

    function rand() {
      validate = "";
      var str = "123456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ123456789";
      var arr = str.split("");
      var ranNum;
      for (var i = 0; i < 6; i++) {
        ranNum = Math.floor(Math.random() * 66);
        validate += arr[ranNum];
      }
      return validate;
    }

    function lineX() {
      var ranLineX = Math.floor(Math.random() * 150);
      return ranLineX;
    }

    function lineY() {
      var ranLineY = Math.floor(Math.random() * 40);
      return ranLineY;
    }

    function clickChange() {
      mycanvas.width = mycanvas.width;
      mycanvas.height = mycanvas.height;

      cxt.drawImage(img, lineX(), lineY(), 150, 40, 0, 0, 150, 40);

      for (var j = 0; j < 2; j++) {
        cxt.beginPath();
        cxt.strokeStyle = sColor[randColor()];
        cxt.moveTo(0, lineY());
        cxt.lineTo(150, lineY());
        cxt.lineWidth = (Math.floor(Math.random() * (20 - 10 + 1)) + 10) / 10;
        cxt.stroke();
        cxt.closePath();
      }

      cxt.fillStyle = fColor[randColor()];
      cxt.font = 'bold 25px Verdana';
      cxt.fillText(rand(), 10, 30);
    }

    mycanvas.onclick = function(e) {
      e.preventDefault();
      clickChange();
    }

    var loginForm = document.getElementById('loginForm');
    loginForm.addEventListener("submit", function(e) {
      var vad = loginForm.vad.value;
      if (vad.toUpperCase() === validate.toUpperCase()) {
        // 验证码通过，允许提交表单
      } else {
        Swal.fire({
          icon: 'error',
          title: '驗證碼錯誤！',
          text: '請重新輸入驗證碼',
          confirmButtonText: '確定'
        }).then(() => {
          loginForm.vad.value = "";
          clickChange();
        });
        e.preventDefault();
      }
    });

    img.onload = function() {
      clickChange();
    }
  </script>
</body>
</html>