

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
                overflow-x: hidden;
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
                                    <li class="breadcrumb-item"><a href="ShowChapter?courseId=${course.course_id}&action=${action}">Manage lessons(Chapter List)</a></li>
                                    <li class="breadcrumb-item active" aria-current="#">Lesson List</li>
                                    
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="well alert alert-primary" >
                        <h4 style="color: blue">Course: ${course.course_name}</h4>
                        <h5 style="color: blue">Chapter: ${chapter.chapter_name}</h5>
                    </div>

                    <div class="container-fluid row">
                        <div class="col-md-3">
                            <form action="SearchSubjectLesson" method="get" style="background-color: #cfe2ff;border: solid 1px #9ec5fe;border-radius: 15px">
                                <input hidden="" name="action" value="${action}">
                                <input hidden="" name="courseId" value="${course.course_id}">
                                <input hidden="" name="chapterId" value="${chapter.chapter_id}">
                                <div class="form-group">
                                    <label for="search">Search lesson</label>
                                    <input type="text" name="keywordLesson"class="form-control"  placeholder="Search "  value="${keywordLesson}">
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
                                <a class="btn " href="ShowLesson?courseId=${course.course_id}&action=${action}&chapterId=${chapter.chapter_id}" style="background-color: #38A5EE;margin-bottom: 8px">Reset</a>


                            </form>
                        </div>
                        <div class="col-sm-12 col-md-8 container-fluid">
                            <c:if test="${ numberOfLesson > 0}">
                                <h4>${numberOfLesson} lessons were found</h4>
                            </c:if> 
                            <c:if test="${ numberOfLesson == 0}">
                                <h4>No lesson founded</h4>
                            </c:if>     
                            <c:if test="${ not empty message}">
                                <div class="alert alert-success">
                                    <strong>${message}!</strong> 
                                </div>
                            </c:if> 
                            <c:if test="${action eq 'edit'}">   
                                <a type="button" class="btn btn-primary mb-3" href="addLesson?courseId=${course.course_id}&action=${action}&chapterId=${chapter.chapter_id}">Add Lesson</a>
                            </c:if>
                            <c:if test="${numberOfLesson > 0}">

                                <table style="text-align: center;" class="table">
                                    <thead>
                                        <tr>
                                            <th>Index</th>
                                            <th>Name</th>
                                            <th>Status</th>
                                            <th>Action</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listLesson}" var="lesson" varStatus="loop">    
                                            <tr>
                                                <td>${lesson.lesson_id}</td>
                                                <td style="text-align: left">

                                                    ${lesson.lesson_name}

                                                </td>

                                                <td>
                                                    <c:if test="${lesson.status == 1}">
                                                        <span style="color: green;">Active</span>
                                                    </c:if>
                                                    <c:if test="${lesson.status == 0}">
                                                        <span style="color: red;">Inactive</span>
                                                    </c:if>
                                                </td>

                                                <td>

                                                    <a type="button" class="btn btn-primary btn-action" href="viewLessonDetail?lesson_id=${lesson.lesson_id}&action=${action}&actionLesson=view&courseId=${course.course_id}">View</a>
                                                    <c:if test="${action eq 'edit'}">   
                                                        <a type="button" class="btn btn-warning btn-action" href="editLesson?courseId=${course.course_id}&action=${action}&chapterId=${chapter.chapter_id}&lessonId=${lesson.lesson_id}">Edit</a>
                                                        <button type="button" class="btn btn-danger btn-action" data-bs-toggle="modal" data-bs-target="#deleteLesson${lesson.lesson_id}">Delete</button>
                                                    </c:if>

                                                    <!-- Delete  Lesson-->
                                                    <div class="modal" id="deleteLesson${lesson.lesson_id}">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Confirm Delete</h4>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <p>Are you sure you want to delete this Lesson?</p>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <form action="deleteLesson" method="post">
                                                                        <input type="text" name="page"value="1" hidden>
                                                                        <input hidden="" name="action" value="${action}">
                                                                        <input hidden="" name="courseId" value="${course.course_id}">
                                                                        <input hidden="" name="chapterId" value="${chapter.chapter_id}">
                                                                        <input hidden="" name ="lessonId" value="${lesson.lesson_id}">
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
                                            <li class="page-item"><a class="page-link" href="SearchSubjectLesson?courseId=${course.course_id}&page=${(pageIndex <= 1) ? 1 : pageIndex-1}&action=${action}&keywordLesson=${keywordChapter}&status=${status}&chapterId=${chapter.chapter_id}">Previous</a></li>

                                            <c:forEach begin="1" end="${endPage}" var = "i">
                                                <li class="page-item"><a class="page-link ${i == pageIndex ? 'active':'' }" href="SearchSubjectLesson?courseId=${course.course_id}&page=${i}&action=${action}&keywordLesson=${keywordChapter}&status=${status}&chapterId=${chapter.chapter_id}">${i}</a></li>
                                                </c:forEach>
                                            <li class="page-item"><a class="page-link" href="SearchSubjectLesson?courseId=${course.course_id}&page=${(pageIndex + 1 <= endPage) ? pageIndex + 1 : pageIndex}&action=${action}&keywordLesson=${keywordChapter}&status=${status}&chapterId=${chapter.chapter_id}">Next</a></li>
                                        </ul>
                                    </nav>  
                                </div>
                            </c:if> 
                        </div> 
                    </div>
                    <!--add Chapter -->



                </div>
            </div>
        </div>



    </body>
    <jsp:include page="../../components/footer.jsp"></jsp:include>
</html>

