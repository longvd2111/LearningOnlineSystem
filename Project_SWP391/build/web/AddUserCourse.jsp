<%-- 
    Document   : CreatePost
    Created on : Jan 20, 2024, 10:23:03 PM
    Author     : caomi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.util.List"%>

<%@page import ="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="ckeditor5-41.0.0-fawpjqxdt1w9/build/ckeditor.js"></script>
        <title>JSP Page</title>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <style>
            .ck-editor__editable_inline:not(.ck-comment__input ) {
                min-height: 100px;
                overflow-y: auto;
            }
        </style>
        <jsp:include page="components/header.jsp"></jsp:include>
            <div class="container">
                <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="Home">Home</a></li>
                        <li class="breadcrumb-item active" aria-current=""><a href="RegistrationList">List Registration</a></li>
                        <li class="breadcrumb-item active" aria-current=""><a href="selectCourse">Select Course to Register</a></li>
                        <li class="breadcrumb-item active" aria-current=""><a href="AddUserCourse">Register Course</a></li>
                    </ol>
                
                <div class="col-md-10 offset-md-1">
                    <form></form>
                    <h1>Create course registration</h1>
                    <h3 style="color:red;" >${infor}</h3>
                    <h3 style="color:red;" >${p}</h3>
                    <br>
                    <form action="AddUserCourse" method="post">
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="category" class="col-form-label">Subject:</label>
                            </div>
                            <div class="col-9">
                                <input value="${course.course_id}" type="text" hidden id="courseId" name="courseId" class="form-control" readonly required>${course.course_name}
                            </div>
                        </div>
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Email" class="col-form-label">Email:</label>
                            </div>
                            <div class="col-9">
                                <input type="text" id="email" name="email" class="form-control" placeholder="Email..." required>
                            </div>
                            <p class="text-danger ">${existed}</p>
                        </div>
                       
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Name" class="col-form-label">Name:</label>
                            </div>
                            <div class="col-9">
                                <input type="text" id="name" name="name" class="form-control" placeholder="Name..." required>
                            </div>

                        </div>
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Phone" class="col-form-label">Phone:</label>
                            </div>
                            <div class="col-9">
                                <input type="text" id="phone" name="phone" class="form-control" placeholder="Phone..." required>
                            </div>

                        </div>
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Created_at" class="col-form-label">Created at:</label>
                            </div>
                            <div class="col-9">
                                <input type="date" id="phone" name="created_at" class="form-control" placeholder="Created_at..." required>
                            </div>

                        </div>
                        
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Phone" class="col-form-label">Gender:</label>
                            </div>
                            <div class="col-9">
                               <select id="gender" name="gender" class="form-control" required>
                                    <option value="" disabled selected hidden>Choose gender</option>
                                    <option value="0">Male</option>
                                    <option value="1">Female</option>
                                </select><br/>
                            </div>

                        </div>
                        
                        
                    
<style>
    .save-button-container {
        text-align: right; 
        margin-top: 20px; 
    }
</style>
                        
                        
                    <div class="save-button-container">
    
                    <button type="submit" class="btn btn-primary mb-2">Save</button>
                    </div>
                </form>
                
            </div>
        </div>
        <script>



            ClassicEditor
                    .create(document.querySelector('#editor1'));
            ClassicEditor
                    .create(document.querySelector('#image'),{
                         toolbar: ['imageInsert']
            });  
        </script>
    </body>
</html>
