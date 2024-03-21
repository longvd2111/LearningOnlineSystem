<%-- 
    Document   : MyCourses
    Created on : Jan 28, 2024, 3:04:27 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>My Course</title>
    </head>
    <body>
<!-- Header -->        
        <jsp:include page="components/header.jsp"></jsp:include>
        
<!-- main screen -->
        <div class="container">
    <h1>My Courses</h1>
    <!-- Filter options -->
        <form action="MyCourse" method="get">
            <select name="sort">
                <option value="register_time">Register Time</option>

            </select>
            <select name="order">
                <option value="asc" ${param.order == 'asc' ? 'selected' : ''}>Ascending</option>
            <option value="desc" ${param.order == 'desc' ? 'selected' : ''}>Descending</option>
        </select>
            <button type="submit">Filter</button>
        </form>
    <c:forEach items="${usercourse}"  var="usercourse">
        
        <div class="MyCourse_cart">
            
            <div class="MyCourse_image">
                <a href="CourseDetails?courseid=${usercourse.course_id}">
                <img style="width: 235px;" src="${usercourse.image}">
                </a>
            </div>
                <a href="CourseDetails?courseid=${usercourse.course_id}">
            <div class="Mycourse_CourseName">${usercourse.course_name}</div>
             </a>
             <div class="MyCourse_DeleteCourse">
                 <a href="DeleteCourses?cid=${usercourse.course_id}">Delete</a>
             </div>
        </div>
             
                 
       
    </c:forEach>
                <div class="MyCourse_paging">
                    <c:forEach begin="1" end="${endP}" var="i">
                        <a style="color: red" href="MyCourse?index=${i}">${i}</a>
                    </c:forEach>
                </div>
    
</div>

        
        
        
        
        
        <!-- footer -->
    </body>
</html>
