<%-- 
    Document   : Home
    Created on : Jan 11, 2024, 11:44:51 PM
    Author     : caomi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.util.List"%>
<%@page import="model.users" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">




        <!------ Include the above in your HEAD tag ---------->


    </head>
    <body>
        <style>
            .body{
                overflow-x: hidden;
            }
            .carousel-item{
                height: 32rem;
                background: black;
                color: white;
            }
            .title_color{
                color: #43EE20;
            }
            .course-item img{
                padding-left: 10px;
                max-height: 152px;
                width: auto;
                max-width: 250px;
            }

            
            .carousel-item .image img {
                height: auto !important;
                width: 100% !important;
            }
            
            
        </style>
        
        <jsp:include page="components/header.jsp"></jsp:include>
            <div class="container" >
                <div id="carouselExampleCaptions" class="carousel slide col-lg-10 offset-lg-1"  data-bs-ride="carousel">

                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <a href="#" class="disabled">
                                <img src="https://images.unsplash.com/photo-1516979187457-637abb4f9353?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" class="d-block w-100" alt="..."
                                     >
                                <div class="carousel-caption d-none d-md-block">
                                    <h1>Register Now</h1>
                                    <p></p>
                                </div>
                            </a>

                        </div>
                    <c:forEach items="${listSlider}" var="slider">
                        <a class="carousel-item citem" href="${slider.backlink}">
                            ${slider.image}
                            <div class="carousel-caption d-none d-md-block">
                                <h3 class="title_color">${slider.title}</h3>

                            </div>
                        </a>
                    </c:forEach>

                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <h1>Feature Subject</h1>
                    <div class="row">
                        <c:forEach items="${listCourse}" var="listCourse">
                            <div class="col-lg-4 col-md-12 course-item" style="padding-bottom: 10px">
                                <div class="card h-100 d-flex flex-column">
                                    <!--                                    <img class="card-img-top" src="" alt="Card image cap">-->
                                    <img src="img/course/${listCourse.image}" class="card-img-top" alt="..."
                                     >
                                    
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="CourseDetails?courseid=${listCourse.course_id}" title="View Product" style="text-decoration: none">${listCourse.course_name}</a></h4>
                                        <!--<p class="card-text show_txt" style="text-decoration: none"></p>-->
                                        <div class="row">
                                            <div class="col">
                                                <!--                                                <p class="btn btn-danger btn-block"> $</p>-->
                                            </div>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div> 

                    <h1>Hot Post</h1>
                    <div class="row">
                        <c:forEach items="${listPost}" var="post">
                            <a href="BlogDetail?postId=${post.post_id}" class="card border-top-0 border-end-0 border-start-0"  style="text-decoration: none">
                                <div class="card-body">
                                    <h5 class="card-title">${post.title}</h5>

                                    <p class="card-text">${post.sub_content}</p>

                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-4">
                    <h1 class="">Latest Post</h1>
                    <div class="row">
                        <c:forEach items="${listLastestPost}" var="post">
                            <a href="BlogDetail?postId=${post.post_id}" class="card border-top-0 border-end-0 border-start-0"  style="width: 18rem; text-decoration: none">
                                <div class="card-body">
                                    <h5 class="card-title">${post.title}</h5>

                                    <p class="card-text">${post.sub_content}</p>

                                </div>
                            </a>
                        </c:forEach>
                    </div>

                </div>
            </div>

        </div>
        <jsp:include page="components/footer.jsp"></jsp:include>
    </body>
</html>
