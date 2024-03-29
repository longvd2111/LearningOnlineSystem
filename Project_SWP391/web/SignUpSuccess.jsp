<%-- 
    Document   : SignUpSuccess
    Created on : Jan 22, 2024, 12:18:53 AM
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

        <link href="css/styles.css" rel="stylesheet" type="text/css"/>

        <title>ResetPassword Form</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"></jsp:include>
        <div id ="logreg-form">
            <form action="success" method="post" class="form-signup">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Account registration successful</h1>
            <button class="btn btn-success btn-block" type="submit"></i>Back to home</button>
        </form>
        
        </div>
    </body>
</html>
