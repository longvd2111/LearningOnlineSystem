<%-- 
    Document   : CourseRegister
    Created on : Jan 15, 2024, 4:56:01 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .register-screen{
            display: grid;
            grid-template-columns: 1fr 1fr;

        }

        .registration-form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            max-width: 100%;
            box-sizing: border-box;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
        </style>
    </head>
    <body>
<!-- Thong tin course -->
        <div class="register-screen">
            <c:if test="${course ne null}">
        <div class="register-course-info">
            <img class="CourseDetails-img-head" src="${course.image}">
            <div class="CourseDetails-head-info">
                <div class="CourseDetails-title">${course.course_name}</div>
                <div class="CourseDetails-headdescription">Tagline (Small description about course)</div>
                <div class="CourseDetails-headdescription">Brief Info (Small description about course)</div>
                <div class="CourseDetails-headdescription">${course.price_package_id}$</div>
            </div>
        </div>
            </c:if>
<!-- Form nhap thong tin -->
        <div class="registration-form">
            <h2>Course Registration Form</h2>
            <form action="CourseRegister" method="post">
                <div class="form-group">
                    <label for="fullName">Full Name:</label>
                    <input type="text" id="fullName" name="fullName" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>
                <div class="form-group">
                    <label for="gender">Gender:</label>
                    <select id="gender" name="gender" required>
                        <option value="1">Male</option>
                        <option value="2">Female</option>
                        <option value="3">Other</option>
                    </select>
                </div>
                <input type="hidden" name="courseId" value="1"> <!-- Replace with dynamic courseId -->
                <div class="form-group">
                    <button type="submit">Register</button>
                </div>
            </form>
        </div>          
    </div>
    </body>
</html>
