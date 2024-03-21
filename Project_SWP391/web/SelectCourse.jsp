<%-- 
    Document   : BlogList
    Created on : Jan 15, 2024, 12:54:38 PM
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
                max-height: 152px;
                max-width: 241px
            }
        </style>
        <jsp:include page="components/header.jsp"></jsp:include>
            <br>
            <div class="container">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="Home">Home</a></li>
                        <li class="breadcrumb-item active" aria-current=""><a href="RegistrationList">List Registration</a></li>
                        <li class="breadcrumb-item active" aria-current=""><a href="selectCourse">Select Course to Register</a></li> 
                    </ol>
                </nav>
                
                
                <div class="row">
                    <div class="col-sm-3">
                        <form action="selectCourse" method="get">
                            <div class="form-group">
                                <label for="search">Search course</label>
                                <input type="text" name="search"class="form-control" id="search" placeholder="Search post..." oninput="updateLink()" value="${search}">
                        </div>
                        <input type="text" name="page"value="1" hidden>
                        <button type="submit" class="btn btn-primary mb-2">Search</button>
                    </form>                    
                </div>

                <div class="col-sm-9">
                    <h1>Select Course</h1>
                    <div class="row">
                        <c:forEach items="${listCourse}" var="o">
                            <a href="AddUserCourse?course_id=${o.course_id}" style="text-decoration: none">
                                <div class="card mb-3" style="height: 152px" >
                                    <div class="row ">
                                        <div class="col-md-3" style=" align-items: center;">
                                            <!--                                            <img src="" class="img-fluid" style="max-height: 152px;max-width: 241px" alt="Thumbnail">-->
                                            ${o.image}
                                        </div>
                                        <div class="col-md-8 ">
                                            <div class="card-body">
                                                <h5 class="card-title">${o.course_name}</h5>
                                                <p class="card-text">${o.tagline}</p>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </a>    
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
                var link = 'selectCourse?search=' + encodeURIComponent(searchValue);
                document.getElementById('searchLink').href = link;
            }
            function updateLinkHref(page) {
                // Cập nhật href của thẻ a
                var searchLink = document.getElementById('searchLink').href;
                var linkAll = searchLink + "&page=" + page.toString();
                return linkAll;
            }
        </script>
    </body>
</html>
