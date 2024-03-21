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
         <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Slider Page</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"></jsp:include>
            <br>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="Home">Home</a></li>
                                <li class="breadcrumb-item active" aria-current=""><a href="slider">List Slider</a></li>

                            </ol>
                        </nav>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-sm-3">
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
                                <option value="0" ${status == 0 ? 'selected' : ''}>Hidden</option>
                                <option value="1" ${status == 1 ? 'selected' : ''}>Show</option>

                            </select>
                        </div>
                        <input type="text" name="page"value="1" hidden>
                        <button type="submit" class="btn btn-primary  mb-2 search-btn">Search</button>
                        </form>                    
                    </div>
<style>
    .search-btn {
    margin-top: 20px; /* Điều chỉnh khoảng cách giữa nút tìm kiếm và phần Status */
}

    .card {
        border: 1px solid #ddd;
        border-radius: 8px;
        overflow: hidden;
        margin-bottom: 20px;
    }

    .row-g-0 {
        display: flex;
        align-items: center;
    }

    .col-md-3 {
        flex: 0 0 auto;
    }

    .col-md-3 img {
        max-height: 150px;
        width: 100%;
        height: auto;
        border-radius: 8px 0 0 8px;
    }

    .col-md-9 {
        padding: 15px;
    }

    .card-body {
        display: flex;
        flex-direction: column;
        height: 100%;
    }

    .card-title {
        font-size: 1.2rem;
        margin-bottom: 8px;
    }

    .card-text {
        margin-bottom: 8px;
    }

    .text-body-secondary {
        color: #888;
    }

    .btn-primary {
        background-color: #007bff;
        color: #fff;
        border: 1px solid #007bff;
        padding: 6px 12px;
        border-radius: 4px;
        transition: background-color 0.3s;
        align-self: flex-start;
        text-decoration: none;
    }

    .btn-primary:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }
</style>    
 <div class="col-sm-9">
                    <div class="row">
                        
                        <c:forEach items="${listSlider}" var="list">
                            <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-md-3" style="align-items: center;">
                                            <a href="sliderList?listId=${list.slider_id}" style="text-decoration: none">
                                                ${list.image}
                                            </a>
                                        </div>
                                        <div class="col-md-9">
                                            <div class="card-body">
                                                <h5 class="card-title">${list.title}</h5>
                                                <p class="card-text">${list.backlink}</p>
                                                <p class="card-text">
                                                    <small id="statusText" class="text-body-secondary">
                                                       <c:choose>
                                                            <c:when test="${list.status eq 1}">
                                                                <span style="color: blue;">Show</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span style="color: red;">Hidden</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </small>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                
                                <div style="text-align: right;">
                                    <a class="btn btn-primary mb-2" href="loadSlider?listId=${list.slider_id}">Edit</a>
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

    <jsp:include page="components/footer.jsp"></jsp:include>    
            
        <script>
            
            function updateLink() {
                var searchValue = document.getElementById('search').value;
                var cateIdValue = document.getElementById('category').value;
                var link = 'sliderDetail?search=' + encodeURIComponent(searchValue) + "&sliderId=" + encodeURIComponent(cateIdValue);
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
