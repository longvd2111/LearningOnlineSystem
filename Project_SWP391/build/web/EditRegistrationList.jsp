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
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="Home">Home</a></li>
                        <li class="breadcrumb-item active" aria-current=""><a href="RegistrationList">List Registration</a></li>
                        <li class="breadcrumb-item active" aria-current=""><a href="EditRegistration?course_id=${course.course_id}&user_id=${user.user_id}&price_package_id=${pack.price_package_id}">Edit registration course</a></li>
                    </ol>
                </nav>
                <div class="col-md-10 offset-md-1">
                    <form></form>
                    <h1>Edit Registration Course</h1>
                    <h3 style="color:red;" >${infor}</h3>
                    <h3 style="color:red;" >${p}</h3>
                    <br>
                    <form action="EditRegistration" method="post">
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="category" class="col-form-label">Subject:</label>
                            </div>
                            <div class="col-5">
                                <input value="${course.course_name}" type="text" id="subject" name="subject" class="form-control" placeholder="Package..." required disabled>
                            </div>
                    </div>
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Package" class="col-form-label">Package: </label>
                            </div>
                            <div class="col-9">
                                <input value="${pack.name}" type="text" id="package" name="package" class="form-control" placeholder="Package..." required readonly disabled>
                            </div>

                        </div>
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Package" class="col-form-label">Price: </label>
                            </div>
                            <div class="col-9">
                                <input value="${pack.price}" type="text" id="price" name="price" class="form-control" placeholder="Price..."  required readonly disabled>
                            </div>

                        </div>
                        
                        <div class="row mb-3 align-items-center">
                           
                            <div class="col-9">
                                <input value="${course.course_id}" type="text" id="course_id" name="course_id" hidden class="form-control" placeholder="Name..." >
                            </div>

                        </div>  
                            
                        <div class="row mb-3 align-items-center">
                           
                            <div class="col-9">
                                <input value="${pack.price_package_id}" type="text" id="price_package_id" name="price_package_id" hidden class="form-control" placeholder="Name...">
                            </div>

                        </div>      
                            
                            
                        <div class="row mb-3 align-items-center">
                           
                            <div class="col-9">
                                <input value="${user.user_id}" type="text" id="user_id" name="user_id" hidden class="form-control" placeholder="Name...">
                            </div>

                        </div>    
                            
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Email" class="col-form-label">Email:</label>
                            </div>
                            <div class="col-9">
                                <input value="${user.email}" type="text" id="email" name="email" class="form-control" placeholder="Email..." readonly required disabled>
                            </div>
                            <p class="text-danger ">${existed}</p>
                        </div>
                       
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Name" class="col-form-label">Name:</label>
                            </div>
                            <div class="col-9">
                                <input value="${user.user_name}" type="text" id="name" name="name" class="form-control" placeholder="Name..." readonly required disabled>
                            </div>

                        </div>
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Phone" class="col-form-label">Phone:</label>
                            </div>
                            <div class="col-9">
                                <input value="${user.phone}" type="text" id="phone" name="phone" class="form-control" placeholder="Phone..." readonly required disabled>
                            </div>

                        </div>
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Created_at" class="col-form-label">Created at:</label>
                            </div>
                            <div class="col-9">
                                <input value="${user.create_at}" type="date" id="phone" name="created_at" class="form-control" placeholder="Created_at..." readonly  required disabled>
                            </div>

                        </div>
                        
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Gender" class="col-form-label">Gender:</label>
                            </div>
                            <div class="col-9">
                                <select id="gender" name="gender" class="form-control" required disabled>
                                    <option value="0">Male</option>
                                    <option value="1">Female</option>
                                </select><br/>
                            </div>

                        </div>
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Valid_From" class="col-form-label">Valid From:</label>
                            </div>
                            <div class="col-9">
                                <input value="${user_course.join_time}" type="date" id="valid_from" name="valid_from" class="form-control" placeholder="Valid From..." readonly disabled required>
                            </div>

                        </div>
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Valid_To" class="col-form-label">Valid To:</label>
                            </div>
                            <div class="col-9">
                                <input value="${user_course.timeTo}" type="date" id="valid_to" name="valid_to" class="form-control" placeholder="Valid To..." readonly disabled required>
                            </div>

                        </div>
                        
                        <div class="row mb-3 align-items-center">
                            <div class="col-2">
                                <label for="Status" class="col-form-label">Status:</label>
                            </div>
                            <div class="col-9">
                               <select class="form-control" name="status"id="status" onchange="OnclickStatus()">
                                    <option value="0" ${status == 0 ? 'selected' : ''}>Paid</option>
                                    <option value="1" ${status == 1 ? 'selected' : ''}>Unpaid</option>
                            </select>
                            </div>

                        </div>
                        

<script>
    var status = "${course.course_id}";
    var selectElement = document.getElementById('course');
    var options = selectElement.getElementsByTagName('option');
    for (var i = 0; i < options.length; i++) {
        if (options[i].value === status) {
            options[i].selected = true;
            break;
        }
    }
</script>

<script>
    var status = "${user.gender}";
    var selectElement = document.getElementById('gender');
    var options = selectElement.getElementsByTagName('option');
    for (var i = 0; i < options.length; i++) {
        if (options[i].value === status) {
            options[i].selected = true;
            break;
        }
    }
</script>

<script>
    var status = "${user_course.is_archived}";
    var selectElement = document.getElementById('status');
    var options = selectElement.getElementsByTagName('option');
    for (var i = 0; i < options.length; i++) {
        if (options[i].value === status) {
            options[i].selected = true;
            break;
        }
    }
</script>
                        
                    
<style>
    .save-button-container {
        text-align: right; 
        margin-top: 20px; 
    }
</style>
                        
                        
                    <div class="save-button-container">
    
                    <button type="submit" class="btn btn-primary mb-2">Change the Status</button>
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
