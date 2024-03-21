<%-- 
    Document   : ForgotPassword
    Created on : Jan 21, 2024, 6:42:10 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <title>ForgotPassword Form</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"></jsp:include>
        <div id ="logreg-form">
            <form action="forgot" method="post" class="form-signup">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Forgot Password</h1>   
            <p class="">${mess}</p>
            <input name="email" type="text" id="user-name" class="form-control" placeholder="Email" required ><br/>
            <button class="btn btn-success btn-block" type="submit"> Reset Password</button>
            <a href="Login.jsp" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
        </form>
    </body>
</html>
