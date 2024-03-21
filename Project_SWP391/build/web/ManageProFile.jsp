<%-- 
    Document   : ManageProFile.jsp
    Created on : Jan 27, 2024, 8:39:09 AM
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
        <title>SignUp Form</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"></jsp:include>
        
        
        <form action="manage" method="post" class="form-signup">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Manage Profile</h1>
                <p class="text-danger ">${mess}</p>
                <input name="userId"  type="hidden" id="inputEmail" class="form-control" value=""><br/>
                <input name="user_name"  type="text" id="username" class="form-control" placeholder="Username" required="" autofocus=""><br/>
                <input name="image"  type="text" id="image" class="form-control" placeholder="Avatar" required="" autofocus=""><br/>
                <input name="full_name"  type="text" id="fullname" class="form-control" placeholder="Full Name" required="" autofocus=""><br/>
                <input name="phone_number"  type="text" id="phonenumber" class="form-control" placeholder="Phone number" required=""><br/>
                <select id="gender" name="gender" class="form-control" required>
                    <option value="" disabled selected hidden>Choose gender</option>
                    <option value="0">Male</option>
                    <option value="1">Female</option>
                </select><br/>
                <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i>Save</button>


        </form>
        </div>
    </body>
</html>
