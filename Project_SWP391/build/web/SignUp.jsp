<%-- 
    Document   : SignUp
    Created on : Jan 15, 2024, 3:56:37 AM
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
        <div id ="logreg-form">
            <form action="signup" method="post" class="form-signup">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign up</h1>
                <p class="text-danger ">${mess}</p>
                <p class="text-danger ">${existed}</p>
                <input name="email" type="text"  class="form-control" placeholder="Email"><br/>
                <input name="user_name" type="text"  class="form-control" placeholder="User Name"><br/>
                <input name="full_name" type="text"  class="form-control" placeholder="Full name"><br/>
                <input name="phone_number" type="text"  class="form-control" placeholder="Phone number"><br/>
                <input name="created_at" type="date"  class="form-control" placeholder="Created_at"><br/>
                <select id="gender" name="gender" class="form-control" required>
                    <option value="" disabled selected hidden>Choose gender</option>
                    <option value="1">Male</option>
                    <option value="0">Female</option>
                </select><br/>
                <input name="pass" type="password" id="user-pass" class="form-control" placeholder="Password" required ><br/>
                <input name="repass" type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" required ><br/>                

                <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i>Sign Up</button><br>
                <a href="Home" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
            </form>
        </div>
        <br/>
    </body>
</html>
