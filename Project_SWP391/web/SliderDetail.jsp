<%-- 
    Document   : BlogDetail
    Created on : Jan 15, 2024, 10:56:25 PM
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

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <style>
        img {
            max-width: 100%;
            height: auto;
        }

        .language-python {
            display: block;
            background-color: #f4f4f4;
            color: #008000;
            padding: 10px;
            margin: 10px 0;
            font-size: 14px;
            border-radius: 5px;
            overflow-x: auto;
            font-family: 'Courier New', monospace;
        }
    </style>
        <jsp:include page="components/header.jsp"></jsp:include>
            <br>
            
            <div class="container">
                <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="Home">Home</a></li>
                                <li class="breadcrumb-item active" aria-current=""><a href="slider">List Slider</a></li>
                                <li class="breadcrumb-item active" aria-current=""><a href="sliderList?listId=${detailS.slider_id}">${detailS.title}</a></li>

                            </ol>
                        </nav>
                <div class="row">
                    <div class="col-sm-3">
                        <div class="col">
                        
                    </div>
                        
                        <form action="/Project_SWP391/sliderDetail" method="get">
                            <div class="form-group">
                                
                                <label for="search">Search slider</label>
                                <input type="text" name="search"class="form-control" id="search" placeholder="Search post..." oninput="updateLink()" value="${search}">
                            </div>
                            
                            <div class="form-group">
                                <label for="search">Search backlink</label>
                                <input type="text" name="backlink"class="form-control" id="backlink" placeholder="Search backlink..." oninput="updateLink()" value="${backlink}">
                            </div>
                            
                        <div class="form-group">
                            <label for="category">Status</label>
                            <select class="form-control" name="status"id="category" onchange="updateLink()">
                                <option value="-1" ${status == -1 ? 'selected' : ''}>All</option>
                                <option value="0">Hidden</option>
                                <option value="1">Show</option>

                            </select>
                        </div>
                        <input type="text" name="page"value="1" hidden>
                        <button type="submit" class="btn btn-primary mb-2">Search</button>
                        </form>                    
                    </div>
                <div class="col-md-6 offset-md-1">
                    <h2 style="font-family: 'Nunito Sans', sans-serif;
                        font-family: 'Open Sans', sans-serif;
                        font-size: 50px">${detailS.title}</h2>
                    <h3>${detailS.backlink}</h3>
                    <br>
                    <h4 style="opacity: 75%">Status: 
                        <c:choose>
                            <c:when test="${detailS.status eq 1}">
                                Show
                            </c:when>
                            <c:otherwise>
                                Hidden
                            </c:otherwise>
                        </c:choose>
                    </h4>
                    <br>
                    <h4 style="opacity: 75%">Note: ${detailS.notes}</h4>
                    <br>
                    <div class="" style="align-items: center;">
                    <h4 style="opacity: 75%">Image:</h4>
                        <img src="${detailS.image}" class="img-fluid" style="width: 100%; height: auto;">
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
