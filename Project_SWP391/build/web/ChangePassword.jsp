<%-- 
    Document   : ChangePassword
    Created on : Jan 27, 2024, 4:23:33 AM
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
        <div class="modal-body">
            <form class ="form-signin" action="changePassword" method="post">
                <h1 class="Headname" style="text-align: center">Change Password</h1>
                <p class="">${mess}</p>
                <p class="">${oldpass}</p>
                
                <input name="userId"  type="hidden" id="inputEmail" class="form-control" value=""><br/>
                <input name="oldPassword"  type="password" id="oldPassword" class="form-control" placeholder="Old password" required="" autofocus=""><br/>
                <input name="newPassword"  type="password" id="newPassword" class="form-control" placeholder="New Password" required="" autofocus=""><br/>
                <input name="rePassword"  type="password" id="rePassword" class="form-control" placeholder="Re-enter Password" required=""><br/>
                <button class="btn btn-success btn-block" type="submit"></i>Change Password</button>

            </form>   
        </div>
    </body>
</html>
