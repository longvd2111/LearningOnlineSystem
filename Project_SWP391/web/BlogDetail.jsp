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
                <div class="row">
                    <div class="col-md-3">
                        <form action="/Project_SWP391/BlogList2" method="get">
                            <div class="form-group">
                                <label for="search">Search</label>
                                <input type="text" name="search"class="form-control" id="search" placeholder="Search post..." >
                            </div>
                            <div class="form-group">
                                <label for="category">Category</label>
                                <select class="form-control" name="cateId"id="category" >
                                    <option value="-1" ${cid == -1 ? 'selected' : ''}>All</option>
                                <c:forEach items="${listCategory}" var="cate">
                                    <option value="${cate.category_id}" ${cid == cate.category_id ? 'selected' : ''}>${cate.category_name}</option>
                                </c:forEach>

                            </select>
                        </div>
                        <input type="text" name="page"value="1" hidden>
                        <button type="submit" class="btn btn-primary mb-2">Filter</button>
                    </form>  
                </div>
                <div class="col-md-6 offset-md-1">
                    <h2 style="font-family: 'Nunito Sans', sans-serif;
                        font-family: 'Open Sans', sans-serif;
                        font-size: 50px">${post.title}</h2>
                    <h3>${post.sub_content}</h3>
                    <br>
                    <h4 style="opacity: 75%">${post.authorName}</h4>
                    <br>
                    ${post.content}


                </div>
            </div>
        </div>
    </body>
</html>
