<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


        <!------ Include the above in your HEAD tag ---------->
        <style>

            .body{
                overflow-x:hidden;
            }
            .card {
                width: 720px;
                height: 200px;
                margin: 20px;
                border-radius: 20px
            }
            .thumbnail {

                height: 100%;
                background-color: #36494d;
            }
            .content {

                padding: 10px;
            }
            .title {
                font-weight: bold;
                
            }
            .tagline, .date, .price {
                margin-bottom: 5px;
                font-size: 15px;
            }
            .price del {
                color: red;
            }
        </style>
    </head>
    <body >

        <header>
            <jsp:include page="../components/header.jsp"></jsp:include>
            </header>

            <div class="container" style="min-height: 100vh">
                <div class="row" >
                    <div class="col-md-4">
                        <div class="row" >
                            <div class="col" >
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="Home">Home</a></li>
                                        <li class="breadcrumb-item active" aria-current="#">List Course</li>                                  
                                    </ol>
                                </nav>
                            </div>
                        </div>

                        <div class="col-md-9">
                            <form action="searchCourse" method="get">
                                <div class="form-group">
                                    <label for="search">Search course</label>
                                    <input type="text" name="courseKeyword"class="form-control" id="courseKeyword" placeholder="Search course..."  value="${courseKeyword}">
                            </div>

                            <div class="form-group">
                                <label for="cateID">Category</label>
                                <select class="form-control" name="cateID"id="cateID" >
                                    <option  value="">Show All</option>
                                    <c:forEach items="${listCate}" var="cate">
                                        <option value="${cate.category_id}" ${cateID == cate.category_id ? 'selected' : ''}>${cate.category_name}</option>
                                    </c:forEach>

                                </select>
                            </div>
                            <div class="form-group">
                                <label for="filter">Short By</label>
                                <select name="filter" id="filter" class="form-control ">

                                    <option  <c:if test="${filter =='latest'}"> selected=""</c:if> value="latest" >Latest</option>
                                    <option <c:if test="${filter=='asc'}"> selected=""</c:if> value="asc">Price: low to high</option>
                                    <option <c:if test="${filter=='desc'}"> selected=""</c:if>value="desc">Price: high to low</option>                                          
                                    </select>
                                </div>       

                                <div class="form-group">
                                    <label class="form-check-label" for="sale">
                                        On sale 
                                    </label>
                                    <input class="form-check-input" name="sale" type="checkbox"  value="1" id="sale" ${sale == 1 ? 'checked' : ''}>

                            </div>
                            <input type="text" name="page"value="1" hidden>
                            <button type="submit" class="btn btn-primary mb-2">Filter</button>


                        </form> 
                        <div  class="bg-light " style="text-decoration: none;background-color: black">
                            <div class="card-header bg-success text-white text-uppercase" style="align-items: center;text-align: center;margin-top: 20px">Best Seller</div>
                            <div class="card-body">
                                <div>                                        
                                    <img src="././img/course/${FCourse.image}" class="img-fluid rounded-start" style="width: 100%; height: 100%  ;align-items: center">
                                </div>
                                <a href="CourseDetails?courseid=${FCourse.course_id}" >
                                    <h6 class="card-title">${FCourse.course_name}</h6>
                                </a>


                            </div>
                        </div>

                            </div>
                    </div>

                <div class="col-md-8 col-sm-3">

                    <c:forEach items="${listCourse}" var="Course">

                        <!--                        Card start-->
                        <div class="card" style="">
                            <div class="row">
                                <div class="col-5" style="padding: 0px">
                                    <div class="thumbnail d-flex justify-content-center align-items-center">
                                        <img src="././img/course/${Course.image}" class="img-fluid rounded-start" style="width: 100%; height: 100%  ;align-items: center">
                                    </div>
                                </div>
                                <div class="col-7">
                                    <div class="content">
                                        <div class="title">${Course.course_name}</div>
                                        <div class="tagline">${Course.tagline}</div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="date">Created at: ${Course.created_at}</div>
                                                <div class="price">
                                                    <c:forEach items="${listPrice}" var="Pk">
                                                        <c:if test="${Pk.status_sale == 1&& Pk.course_id==Course.course_id}"> 
                                                            <del>Price: ${Pk.price}$</del>
                                                            <strong>${Pk.sale_price}$</strong>
                                                        </c:if> 
                                                        <c:if test="${Pk.status_sale == 0&& Pk.course_id==Course.course_id}"> 
                                                            <Strong>Price: ${Pk.price}$</Strong>                                                          
                                                        </c:if>      
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="col-md-6 d-flex justify-content-center align-items-center">
                                                <a class="btn btn-primary" href="CourseDetails?courseid=${Course.course_id}" >More information</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>                                                   
                        <!--                       card-end             -->



                    </c:forEach>
                    <c:if test="${endPage != 0}">    
                        <div class="page">
                            <nav aria-label="Page navigation ">
                                <a href="#" id="searchCourseLink"></a>
                                <ul class="pagination">
                                    <li class="page-item"><a class="page-link" href="searchCourse?courseKeyword=${courseKeyword}&cateID=${cateID}&filter=${filter == null || filter.trim() == '' ? "latest" : filter}&sale=${sale}&page=${(pageIndex <= 1) ? 1 : pageIndex-1}">Previous</a></li>

                                    <c:forEach begin="1" end="${endPage}" var = "i">
                                        <li class="page-item"><a class="page-link ${i == pageIndex ? 'active':'' }" href="searchCourse?courseKeyword=${courseKeyword}&cateID=${cateID}&filter=${filter}&sale=${sale}&page=${i}">${i}</a></li>
                                        </c:forEach>
                                    <li class="page-item"><a class="page-link" href="searchCourse?courseKeyword=${courseKeyword}&cateID=${cateID}&filter=${filter == null || filter.trim() == '' ? "latest" : filter}&sale=${sale}&page=${(pageIndex + 1 <= endPage) ? pageIndex + 1 : pageIndex}">Next</a></li>
                                </ul>
                            </nav>  
                        </div>
                    </c:if>        

                </div>        
            </div>                    


        </div>

        <jsp:include page="../components/footer.jsp"></jsp:include>         






    </body>
</html>

