<%-- 
    Document   : SubjectsList
    Created on : Jan 29, 2024, 1:38:19 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/SubjectsList.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        
                <jsp:include page="components/header.jsp"></jsp:include>
        
        
        <div class="container mt-5">
    <div>
        <div>
            <h2>Manager Product</h2>
        </div>
        <div>
            <form action="SubjectsList" method="post">
                <input value="${txtSearch}" id="txtSearch" class="searchBox" type="text" name="txtSearch" size="15">
            <select name="categoryId">
                <option value="">All Categories</option>
                <c:forEach items="${listCate}" var="listCate">
                    <option value="${listCate.category_id}" name="cateId">${listCate.category_name}</option>
                </c:forEach>
            </select>
            <input class="searchButton" type="submit" name="btnGo" value="Go">
        </form>

            <script>
                function validateSearch() {
                    var searchInput = document.getElementById('txtSearch').value.trim();
                    if (searchInput === "") {
                        document.getElementById('txtSearch').value = "all";
                    }
                    return true;
                }
            </script>
        </div>
        <div class="SubjectList_add">
            <a class="btn btn-success" href="CourseCreate">Add Product</a>
        </div>
    </div>

    <c:choose>
        <c:when test="${not empty list}">
            <table style="text-align: center;" class="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Image</th>
<!--                    <th>Category</th>-->
                    <th>Tagline</th>
                    <th>Owner</th>
                    <th>Created time</th>
                    <c:if test="${userRole.user_id==1||userRole.user_id==4}">
                    <th>Actions</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="course">
                    <tr>
                        <td>${course.course_id}</td>
                        <td>
                            <a href="CourseDetails?courseid=${course.course_id}">
                                ${course.course_name}
                            </a>
                        </td>
                        <td>${course.image}</td>
<!--                        <td>${course.sub_category_id}</td>-->
                        <td>${course.tagline}</td>
                        <td>Expert</td>
                        <td>${course.created_at}</td>
                        
                        <td style="display: flex;">
                            <a  href="ShowInformationCourse?courseId=${course.course_id}" class="btn btn-warning btn-sm">Update</a>
                            <a style="margin-left: 5px;" href="#" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                        
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>No course found</p>
        </c:otherwise>
    </c:choose>

    <c:forEach begin="1" end="${end}" var="i">
        <a id="${i}" href="SubjectsList?index=${i}&txtSearch=${txtSearch}">${i}</a>
    </c:forEach>

</div>
                <script>
                    document.getElementById('${index}').style.color = "red";
                </script>
                
                
</body>
</html>