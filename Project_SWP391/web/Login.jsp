<%-- 
    Document   : Login
    Created on : Jan 15, 2024, 2:18:12 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/login.css" type="text/css"/> 
        <title>Login Form</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"></jsp:include>
        <div id="logreg-form">
                <form class ="form-signin" action="login" method="post">
                    <h1 class="Headname" style="text-align: center">Log in</h1>
                    <p class="">${mess}</p>

                    <input name="email"  type="text" id="inputEmail" class="form-control" placeholder="Email" required="" autofocus=""><br/>
                    <input name="pass"  type="password" id="inputPassword" class="form-control" placeholder="Password" required=""><br/>

                    <button class="btn btn-success btn-block" type="submit">Login</button>
                    <button class="btn btn-block" type="button"><a href="ForgotPassword.jsp"> Forgot password? </a></button>
                    <a href="Home" id="cancel_signup"><i class="fas fa-angle-left"></i>Back</a>
                </form>
        </div>
        <script src="login.js"></script>
    </body>
</html>
