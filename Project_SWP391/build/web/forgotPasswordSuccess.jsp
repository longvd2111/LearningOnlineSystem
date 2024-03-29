<%-- 
    Document   : forgotPasswordSuccess
    Created on : Jan 21, 2024, 9:48:47 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"></jsp:include>
        <div id="logreg-form">
                <form class ="form-signin" action="login" method="post">
                    <h1 class="Headname" style="text-align: center">The password reset link has been sent to your email. Please check your email to change your password</h1>
                </form>
        </div>
    </body>
</html>
