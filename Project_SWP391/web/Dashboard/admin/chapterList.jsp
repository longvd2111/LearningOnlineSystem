

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="././css/styles.css" rel="stylesheet" type="text/css"/>

        <title>Manage Lesson</title>
        <style>
            .body{
                overflow-x:hidden;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../../components/header.jsp"></jsp:include>


            <!--                    <div class="col-md-3">
                                    <form action="SearchSubjectLesson" method="post">
                                        <div class="form-group">
                                            <label for="search">Search Lesson</label>
                                            <input type="text" name="lessonKeyword"class="form-control" id="courseKeyword" placeholder="Search lesson..."  value="${lessonKeyword}">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-check-label" for="status">
                                            Status 
                                        </label>
                                        <select class="form-control" name="status" id="status" >
                                            <option  value="" selected="">Show All</option>
                                            <option  value="1" ${status==1 ? 'selected':''}>Active</option>
                                            <option  value="0" ${status==0 ? 'selected':''}>Inactive</option>
            
                                        </select>
            
            
                                    </div>
                                    <input hidden="" name="courseId" value="${courseId}">
                                    <input type="text" name="page"value="1" hidden>
                                    <button type="submit" class="btn btn-primary mb-2">Filter</button>
                                </form>   
            
                            </div>-->




        <div class="container-fluid "  style="display: flex;padding: 0px;padding-bottom: 15px;min-height: 100vh">
            <div class="row content " style="display: contents">
                <div class="col-sm-2 col-md-2 sidenav hidden-xs " style="background-color: white;border-radius: 15px;padding: 0px" >
                    <h2 style="text-align: center">Course Detail</h2>


                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link "  href="ShowInformationCourse?courseId=${course.course_id}&action=${action}" >Overview</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"  href="pricePackageView?courseId=${course.course_id}&action=${action}">Price Package</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled" aria-current="page" aria-disabled="true" href="ShowChapter?courseId=${course.course_id}&action=${action}" style="background-color: #38A5EE">Manage lessons</a>
                        </li>

                    </ul>

                </div>


                <div class="col-sm-10 col-md-10 "  style="padding-left: 30px;">
                    <div class="row" >
                        <div class="col" >
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="Home">Home</a></li>
                                    <li class="breadcrumb-item"><a href="SubjectsList">List Course Manage</a></li>
                                    <li class="breadcrumb-item active" aria-current="#">Manage lessons(Chapter List)</li>
                                    
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="well alert alert-primary" >
                        <h4 style="color: blue">${course.course_name}</h4>
                    </div>

                    <div class="container-fluid row">
                        <div class="col-md-3">
                            <form action="SearchChapter" method="get" style="background-color: #cfe2ff;border: solid 1px #9ec5fe;border-radius: 15px">
                                <input hidden="" name="action" value="${action}">
                                <input hidden="" name="courseId" value="${course.course_id}">

                                <div class="form-group">
                                    <label for="search">Search name chapter</label>
                                    <input type="text" name="keywordChapter"class="form-control"  placeholder="Search "  value="${keywordChapter}">
                                </div>



                                <div class="form-group">
                                    <label class="form-check-label" for="status">
                                        Status 
                                    </label>
                                    <select class="form-control" name="status" id="status" >
                                        <option  value="1" ${status==1 ? 'selected':''}>Active</option>
                                        <option  value="0" ${status==0 ? 'selected':''}>Inactive</option>

                                    </select>
                                </div>

                                <input type="text" name="page"value="1" hidden>
                                <button type="submit" class="btn btn-primary mb-2">Filter</button>
                                <a class="btn "    href="ShowChapter?courseId=${course.course_id}&action=${action}" style="background-color: #38A5EE;margin-bottom: 8px">Reset</a>


                            </form>
                        </div>
                        <div class="col-sm-12 col-md-8 container-fluid">
                            <c:if test="${numberOfChapter>0}">
                                <h4>${numberOfChapter} chapters were found</h4>
                            </c:if> 
                            <c:if test="${ numberOfChapter == 0}">
                                <h4>No chapter founded</h4>
                            </c:if>     
                            <c:if test="${ not empty message}">
                                <div class="alert alert-success">
                                    <strong>${message}!</strong> 
                                </div>
                            </c:if> 


                            <c:if test="${action eq 'edit'}">    
                                <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addChapter">Add Chapter</button>
                            </c:if>
                            <c:if test="${numberOfChapter > 0}">

                                <table style="text-align: center;" class="table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name chapter</th>
                                            <th>Status</th>
                                            <th>Action</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listChapter}" var="chapter" varStatus="loop">    
                                            <tr>
                                                <td>${chapter.chapter_id}</td>
                                                <td style="text-align: left">

                                                    ${chapter.chapter_name}

                                                </td>

                                                <td>
                                                    <c:if test="${chapter.status == 1}">
                                                        <span style="color: green;">Active</span>
                                                    </c:if>
                                                    <c:if test="${chapter.status == 0}">
                                                        <span style="color: red;">Inactive</span>
                                                    </c:if>
                                                </td>

                                                <td>

                                                    <c:if test="${action eq 'edit'}">   
                                                        <button type="button" class="btn btn-warning btn-action" data-bs-toggle="modal" data-bs-target="#editChapter${chapter.chapter_id}">Edit</button>
                                                        <button type="button" class="btn btn-danger btn-action" data-bs-toggle="modal" data-bs-target="#deleteChapter${chapter.chapter_id}">Delete</button>
                                                    </c:if>
                                                    <a type="button" class="btn btn-primary btn-action" href="ShowLesson?chapterId=${chapter.chapter_id}&courseId=${course.course_id}&action=${action}">Lesson</a>

                                                    <div class="modal" id="editChapter${chapter.chapter_id}" style="text-align: left">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content row">

                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Edit Chapter</h4>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>

                                                                <div class="modal-body">
                                                                    <form action="editChapter" method="post"> 
                                                                        <input type="text" name="page"value="1" hidden>
                                                                        <input hidden="" name="action"value="${action}" hidden>
                                                                        <input hidden="" name="courseId" value="${course.course_id}" >
                                                                        <input hidden="" name="chapter_id" value="${chapter.chapter_id}" >
                                                                        <div class="form-group">
                                                                            <label for="edit-chapter">Name</label>
                                                                            <input type="text" class="form-control" name="chapter_name" required="" value="${chapter.chapter_name}">
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <div class="form-label">
                                                                                <label for="status col-md-2">Status</label>
                                                                            </div>                                       
                                                                            <select class="form-select" name="status" id="status">
                                                                                <option value="1" style="color: green"<c:if test="${chapter.status == 1}"> selected=""</c:if> >Active</option>
                                                                                <option value="0" style="color: red"<c:if test="${chapter.status == 0}"> selected=""</c:if> >Inactive</option>
                                                                            </select>                                    
                                                                        </div> 
                                                                        <button type="submit" class="btn btn-warning" style="margin-top: 5px">Edit</button>
                                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div> 

                                                    <!-- Delete  Chapter-->
                                                    <div class="modal" id="deleteChapter${chapter.chapter_id}">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Confirm Delete</h4>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <p>Are you sure you want to delete this Chapter?</p>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <form action="deleteChapter" method="post">
                                                                        <input type="text" name="page"value="1" hidden>
                                                                        <input hidden="" name ="chapterId" value="${chapter.chapter_id}">                                                          
                                                                        <input hidden="" name="courseId" value="${course.course_id}">
                                                                        <input hidden="" name="action" value="${action}">
                                                                        <button type="submit" class="btn btn-danger">Delete</button>
                                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div> 
                                                </td>


                                            </tr>

                                            

                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>






                            <c:if test="${endPage != 0}">    
                                <div class="page">
                                    <nav aria-label="Page navigation ">
                                        <a href="#" id="searchCourseLink"></a>
                                        <ul class="pagination">
                                            <li class="page-item"><a class="page-link" href="SearchChapter?courseId=${course.course_id}&page=${(pageIndex <= 1) ? 1 : pageIndex-1}&action=${action}&keywordChapter=${keywordChapter}&status=${status}">Previous</a></li>

                                            <c:forEach begin="1" end="${endPage}" var = "i">
                                                <li class="page-item"><a class="page-link ${i == page ? 'active':'' }" href="SearchChapter?courseId=${course.course_id}&page=${i}&action=${action}&keywordChapter=${keywordChapter}&status=${status}">${i}</a></li>
                                                </c:forEach>
                                            <li class="page-item"><a class="page-link" href="SearchChapter?courseId=${course.course_id}&page=${(pageIndex + 1 <= endPage) ? pageIndex + 1 : pageIndex}&action=${action}&keywordChapter=${keywordChapter}&status=${status}">Next</a></li>
                                        </ul>
                                    </nav>  
                                </div>
                            </c:if> 
                        </div> 
                    </div>
                    <!--add Chapter -->

                    <div class="modal" id="addChapter">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title">Add Chapter</h4>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>

                                <div class="modal-body">
                                    <form action="addChapter" method="post" >
                                        <input hidden="" name="courseId" value="${course.course_id}">
                                        <input hidden="" name="action" value="${action}">
                                        <input type="text" name="page"value="1" hidden>
                                        <div class="form-group">
                                            <label for="edit-package">Name</label>
                                            <input type="text" class="form-control" name="chapter_name" required="">
                                        </div>
                                        <div class="form-group">
                                            <div class="form-label">
                                                <label for="status col-md-2">Status</label>
                                            </div>                                       
                                            <select class="form-select" name="status" id="status">
                                                <option value="1" style="color: green"selected="" >Active</option>
                                                <option value="0" style="color: red" >Inactive</option>
                                            </select>                                    
                                        </div> 

                                        <button type="submit" class="btn btn-primary" style="margin-top: 5px">Add</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>



    </body>
    <jsp:include page="../../components/footer.jsp"></jsp:include>
</html>

