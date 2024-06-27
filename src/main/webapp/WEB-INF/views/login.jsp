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
  
</head>

  <body class="text-center">
    <main class="form-signin">
      
     
      <header>
        <img src="/image/NJU.png" alt="">
        <p>Course Enrollment System</p>
      </header>
      	
    	
    	<form class="mt-9" action="${pageContext.request.contextPath}/auth/login" method="post">
	        <div class="form-floating">
	          <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="email" required="required">
	          <label for="floatingInput">Email address</label>
	        </div>
	        <div class="form-floating">
	          <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" required="required">
	          <label for="floatingPassword">Password</label>
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

  </body>
</html>








