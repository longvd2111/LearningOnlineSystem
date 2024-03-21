<%-- 
    Document   : BlogManager
    Created on : Jan 29, 2024, 9:20:42 PM
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
                width: auto;
                max-width: 220px;
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
                                <li class="breadcrumb-item active" aria-current="#">Blog Manager</li>
                                
                            </ol>
                        </nav>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <form action="/Project_SWP391/BlogManager" method="get">
                            <div class="form-group">
                                <label for="search">Search post</label>
                                <input type="text" name="search"class="form-control" id="search" placeholder="Search post..." oninput="updateLink()" value="${search}">
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
                            <label for="active">Status</label>
                            <select class="form-control" name="active"id="active" onchange="updateLink()">
                                <option value="-1" ${active == -1 ? 'selected' : ''}>All</option>

                                <option value="1" ${active == 1? 'selected' : ''}>Active</option>
                                <option value="0" ${active == 0? 'selected' : ''}>Inactive</option>

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
                    <a class="btn btn-primary mb-2" href="CreatePost">Create Post</a>
                    <div class="row">
                        <c:forEach items="${listPost}" var="post">
                            <a href="BlogEdit?postId=${post.post_id}" style="text-decoration: none">
                                <div class="card mb-3" style="height: 152px" >
                                    <div class="row ">
                                        <div class="col-md-3" style=" align-items: center;">
<!--                                            <img src="" class="img-fluid" style="max-height: 152px;max-width: 241px" alt="Thumbnail">-->
                                            ${post.thumbnail}
                                        </div>
                                        <div class="col-md-8">
                                            <div class="card-body">
                                                <h5 class="card-title">${post.title}</h5>
                                                <div class="d-flex" style="display: flex;justify-content: space-between">
                                                    <p class="card-text"><small class="text-body-secondary">${post.authorName}</small></p>

                                                    <p class="card-text" style="color:${post.is_feature == 1?'green':'red'} ">${post.is_feature == 1?'Active':'Inactive'}</p>
                                                </div>
                                                <p class="card-text">${post.sub_content}</p>

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
                var cateIdValue = document.getElementById('category').value;
                var activeValue = document.getElementById('active').value;
                var timeValue = document.getElementById('time').value;
                var link = 'BlogManager?search=' + encodeURIComponent(searchValue) + "&cateId=" + encodeURIComponent(cateIdValue) + "&active=" + encodeURIComponent(activeValue)+ "&time=" + encodeURIComponent(timeValue)                ;
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
