<%-- 
    Document   : MyCourse2
    Created on : Feb 24, 2024, 8:54:38 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <style>
            img{
                padding-left: 10px;
                height: 151px;
                max-width: 241px
            }
        </style>
        <jsp:include page="components/header.jsp"></jsp:include>
            <br>
            <div class="container">
                <div class="row" >
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="Home">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="#">My Course</li>

                            </ol>
                        </nav>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <form action="/Project_SWP391/MyCourse2" method="get">
                            <div class="form-group">
                                <label for="search">Search course</label>
                                <input type="text" name="search"class="form-control" id="search" placeholder="Search course..." oninput="updateLink()" value="${search}">
                        </div>
                        <div class="form-group">
                            <label for="category">Category</label>
                            <select class="form-control" name="cateId"id="category" onchange="updateLink()">
                                <option value="-1" ${cid == -1 ? 'selected' : ''}>All</option>
                                <c:forEach items="${listCategory}" var="cate">
                                    <option value="${cate.category_id}" ${cid == cate.category_id ? 'selected' : ''}>${cate.category_name}</option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="time">Newest or oldest</label>
                            <select class="form-control" name="time"id="time" onchange="updateLink()">
                                <option value="1" ${time == 1? 'selected' : ''}>Newest</option>
                                <option value="2" ${time == 2? 'selected' : ''}>Oldest</option>

                            </select>
                        </div>
                        <input type="text" name="page"value="1" hidden>
                        <button type="submit" class="btn btn-primary mb-2">Filter</button>
                    </form>                    
                </div>

                <div class="col-sm-9">

                    <div class="row">
                        <c:forEach items="${listCourse}" var="course">

                            <div class="card mb-3" style="height: 152px" >
                                <div class="row ">
                                    <div class="col-md-3" style=" align-items: center;">
                                        <!--                                            <img src="" class="img-fluid" style="max-height: 152px;max-width: 241px" alt="Thumbnail">-->
                                        <img src="img/course/${course.image}" class="card-img-top" alt="...">
                                    </div>
                                    <div class="col-md-7 ">
                                        <div class="card-body">
                                            <a href="CourseDetails?courseid=${course.course_id}" style="text-decoration: none"><h5 class="card-title">${course.course_name}</h5></a>
                                            <p class="card-text">${course.tagline} </p>
                                            <p class="card-text"><small class="text-body-secondary">Enroll date: ${course.created_at}</small></p>

                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <br>
                                        <span><a href="CourseLearn?courseId=${course.course_id}" class="btn btn-outline-primary" style="width: 83px">Learn</a>
                                            
                                            <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#DeleteModal${course.course_id}"  style="width: 83px">
                                                Remove
                                            </button>

                                            <div class="modal fade" id="DeleteModal${course.course_id}" tabindex="-1" aria-labelledby="DeleteModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Remove this course?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <a class="btn btn-danger mb-2" href="MyCourseRemove?course_id=${course.course_id}">Remove</a>
                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                            
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </span>
                                    </div>
                                        </div>
                                    </div>

                        </c:forEach>
                        <a href="#" id="searchLink"></a>
                        <ul class="pagination">


                            <c:forEach begin="1" end="${endPage}" var = "i">
                                <li class="page-item"><a class="page-link" href="#" onclick="updateLink();window.location.href = updateLinkHref(${i})">${i}</a></li>
                                </c:forEach>                           
                        </ul>                       
                    </div>
                </div>
            </div>
        </div>
        <script>
            function updateLink() {
                var searchValue = document.getElementById('search').value;
                var cateIdValue = document.getElementById('category').value;
                var timeValue = document.getElementById('time').value;
                var link = 'MyCourse2?search=' + encodeURIComponent(searchValue) + "&cateId=" + encodeURIComponent(cateIdValue) + "&time=" + encodeURIComponent(timeValue);
                document.getElementById('searchLink').href = link;
            }
            function updateLinkHref(page) {

                var searchLink = document.getElementById('searchLink').href;
                var linkAll = searchLink + "&page=" + page.toString();
                return linkAll;
            }
        </script>
    </body>
</html>
