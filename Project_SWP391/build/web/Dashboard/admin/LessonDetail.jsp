

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="././css/styles.css" rel="stylesheet" type="text/css"/>
        <script src="ckeditor5-41.0.0-fawpjqxdt1w9/build/ckeditor.js"></script>

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
                                    <li class="breadcrumb-item" ><a href="ShowChapter?courseId=${course.course_id}&action=${action}">Manage lessons/Chapter List</a></li>
                                    <li class="breadcrumb-item" ><a href="ShowLesson?chapterId=${chapter.chapter_id}&courseId=${course.course_id}&action=${action}">Manage lessons/Lesson List</a></li>
                                    <li class="breadcrumb-item active" aria-current="#">Lesson Detail</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="well alert alert-primary" >
                        <h4 style="color: blue">Course: ${course.course_name}</h4>
                        <h5 style="color: blue">Chapter: ${chapter.chapter_name}</h5>
                    </div>

                    <div class="container-fluid row">

                        <form class="overview" <c:if test="${actionLesson eq 'add'}">action="addLesson"</c:if> 
                              <c:if test="${actionLesson eq 'edit'}">action="editLesson"</c:if>
                                  method="post" enctype="multipart/form-data"  style="border: solid 2px black;border-radius: 15px" >
                                  <input hidden="" name="action" value="${action}">
                              <input hidden="" name="courseId" value="${course.course_id}">
                              <input hidden="" name="chapterId" value="${chapter.chapter_id}">
                              <input hidden="" name="lessonId" value="${lesson.lesson_id}">

                              <div class="row" >                                           
                                  <div class="col-md-9">
                                      <div class=form-group " style="margin-bottom: 20px;" >
                                          <div class="form-label">
                                              <label for="name" >Lesson Name<c:if test="${actionLesson eq 'view'}">: ${lesson.lesson_name}</c:if></label>
                                              </div>
                                          <c:if test="${action eq 'edit' && actionLesson != 'view'}">
                                              <input type="text" name="name" class="form-control" <c:if test="${action eq 'edit'&&actionLesson eq 'edit'}">value="${lesson.lesson_name}" </c:if>>
                                          </c:if>

                                      </div>

                                      <div class="form-group">
                                          <div class="form-label">
                                              <label for="status col-md-2">Status
                                                  <c:if test="${ actionLesson eq 'view'}">
                                                      <c:if test="${lesson.status == 1}">
                                                          <span style="color: green;">: Active</span>
                                                      </c:if>
                                                      <c:if test="${lesson.status == 0}">
                                                          <span style="color: red;">: Inactive</span>
                                                      </c:if>
                                                  </c:if>
                                              </label>
                                          </div>
                                          <c:if test="${  action eq 'edit' && actionLesson eq 'edit'}">
                                              <select class="form-select" name="status" id="feature">
                                                  <option value="1" style="color: green" <c:if test="${lesson.status == 1}">selected=""</c:if> >Active</option>
                                                  <option value="0" style="color: red" <c:if test="${lesson.status == 0}">selected=""</c:if>>Inactive</option>
                                                  </select>

                                          </c:if> 
                                          <c:if test="${ actionLesson eq 'add' && action eq 'edit'}">
                                              <select class="form-select" name="status" id="feature">
                                                  <option value="1" style="color: green" selected="" >Active</option>
                                                  <option value="0" style="color: red" >Inactive</option>
                                              </select>

                                          </c:if>
                                      </div> 


                                      <div class=form-group " style="margin-bottom: 20px;" >
                                          <div class="form-label">
                                              <label for="video" >Link Youtube</label>
                                          </div>
                                          <c:if test="${actionLesson eq 'edit'}">
                                            <input type="text" name="video" class="form-control" value="${LinkYouTubeEmbed}${lesson.content}">
                                            <iFrame src="${LinkYouTubeEmbed}${lesson.content}" width="680" height="480" allowfullscreen></iFrame>


                                          </c:if>
                                          <c:if test="${actionLesson eq 'add'}">
                                              <input type="text" name="video" class="form-control">


                                          </c:if>  
                                          <c:if test="${actionLesson eq 'view'}">
                                            <iFrame src="${LinkYouTubeEmbed}${lesson.content}" width="680" height="480" allowfullscreen></iFrame>
        
                                          </c:if>    
                                      </div>        


                                      <div class="form-group" style="margin-bottom: 20px">
                                          <div class="form-label">
                                              <label for="description" class="form-label">Content</label>
                                          </div>
                                          <c:if test="${actionLesson eq 'view' }">
                                              ${lesson.content_text}
                                          </c:if>
                                          <c:if test="${actionLesson ne 'view' }">
                                          <textarea class="form-control" name="description" id="description" style="height:  150px;" ><c:if test="${ actionLesson eq 'edit' }">  ${lesson.content_text}</c:if> </textarea>
                                          </c:if>

                                          </div>

                                      </div>                              
                                  </div>

                                           
                                  <div class="text-center">
                                      <c:if test="${  action eq 'edit' && actionLesson ne 'view'}">   
                                      <button type="submit" class="btn btn-primary" >Save</button>
                                      </c:if> 
                                      <a href="ShowLesson?chapterId=${chapter.chapter_id}&courseId=${course.course_id}&action=${action}" class="btn btn-secondary">Back</a>
                                  </div>
                               
                        </form> 

                    </div>




                </div>
            </div>
        </div>



    </body>
    <jsp:include page="../../components/footer.jsp"></jsp:include>

    <script>
        ClassicEditor
                .create(document.querySelector('#description'));
    </script>
</html>

