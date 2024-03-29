
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="././css/styles.css" rel="stylesheet" type="text/css"/>

        <style>
            .form-group{
                padding-bottom: 15px;
                padding-left: 5px;
            }
            .body{
                overflow-x:hidden;
            }
        </style>
    </head>
    <jsp:include page="../../components/header.jsp"></jsp:include>
        <body>



            <div class="container">
                <div class="row" >
                    <div class="col" >
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="Home">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="#">List Course Manage</li>

                            </ol>
                        </nav>
                    </div>
                </div>
                <div class="row" style="min-height: 100vh;">
                    <div class="col-sm-2">
                        <form action="SearchSubjectList" method="get">
                            <div class="form-group">
                                <label for="search">Search course</label>
                                <input type="text" name="courseKeyword"class="form-control" id="courseKeyword" placeholder="Search course..."  value="${courseKeyword}">
                        </div>

                        <div class="form-group">
                            <label for="cateID">Category</label>
                            <select class="form-control" name="cateID" id="cateID" >
                                <option  value="" >Show All</option>
                                <c:forEach items="${listCate}" var="cate">
                                    <option value="${cate.category_id}" ${cateID == cate.category_id ? 'selected' : ''}>${cate.category_name}</option>
                                </c:forEach>

                            </select>
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
                        <a class="btn "    href="SubjectsList" style="background-color: #38A5EE;margin-bottom: 8px">Reset</a>


                    </form>

                    <div>

                    </div>
                </div>

                <div class="col-md-10 col-sm-3">

                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCourse"style="margin-bottom: 5px">
                        Add new course
                    </button>

                    <div class="modal fade" id="addCourse" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Add new course</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form class="overview" action="addCourse" method="post" enctype="multipart/form-data"  style="border: solid 2px black;border-radius: 15px">
                                        <input type="text" name="page"value="1" hidden>
                                        <div class="row" style="margin: 10px">                                           
                                            <div class=form-group " style="margin-bottom: 20px;" >
                                                <div class="form-label">
                                                    <label for="courseName" >Course Name</label>
                                                </div>
                                                <input type="text" name="courseName" class="form-control" value="">
                                            </div>



                                            <div class="form-group" style="margin-bottom: 20px;display: flex;align-items: center">
                                                <div class="form-label" style="padding-right: 20px ">
                                                    <label  for="cateID" >Category
                                                    </label> 
                                                </div>
                                                <select class="form-select" name="cate_ID">                                                   
                                                    <c:forEach var="Cate" items="${listCate}">
                                                        <option value="${Cate.category_id}" selected="">${Cate.category_name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>



                                            <div class="form-group" style="margin-bottom: 20px;">
                                                <div class="form-label">
                                                    <label class="" for="courseTagLine">Tagline</label>
                                                </div>

                                                <textarea class="form-control" name="courseTagLine"  rows="4" ></textarea>
                                            </div>

                                            <div class="form-group" style="margin-bottom: 20px">
                                                <div class="form-label">
                                                    <label for="expert col-md-2" >Expert
                                                        </label>
                                                </div>

                                                
                                                    <select class="form-select" id="expert" name="expertId">
                                                        <!-- Iterate over a list of experts and generate options -->
                                                        <c:forEach var="expert" items="${listUserByRole}">
                                                            <option value="${expert.user_id}">${expert.user_name}</option>
                                                        </c:forEach>
                                                    </select>
                                                
                                            </div>

                                            <div class="form-group">
                                                <div class="form-label">
                                                    <label for="feature col-md-2">Feature
                                                    </label>
                                                </div>

                                                <select class="form-select" name="feature" id="feature">
                                                    <option value="1" style="color: green" >Active</option>
                                                    <option value="0" style="color: red"> Inactive</option>
                                                </select>


                                            </div>  
                                            
                                            <div class="form-group">
                                                <div class="form-label">
                                                    <label for="feature col-md-2" style="color:red ">Publish: Inactive
                                                    </label>
                                                </div>
                                            </div>  
                                            
                                            <div class="form-group" style="margin-bottom: 20px">
                                                <div class="form-label">
                                                    <label for="description" class="form-label">Description</label>
                                                </div>
                                                <textarea class="form-control" name="description" id="description" style="height:  150px;" required ></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label style="margin: 5px;">Thumbnail</label> <br/>                                                                                                     
                                                <input style="width: 100%" type="file" 
                                                       class="form-control" name="image" required="">                                                   
                                            </div>
                                        </div>
                                        <div class="text-center">
                                            <button type="submit" class="btn btn-primary" >Save</button>
                                            <a href="SubjectsList" class="btn btn-secondary">Back</a>
                                        </div>
                                    </form> 
                                </div>

                            </div>
                        </div>
                    </div>
                    <c:if test="${numberOfCourse==0}">
                        <p>No course found</p>
                    </c:if>

                    <c:if test="${not empty message}">
                                <div class="alert alert-primary well" role="alert">
                                    ${message}
                                </div>
                    </c:if>       
                        
                    <c:if test="${numberOfCourse > 0}">
                        <p>${numberOfCourse} courses was founded</p>

                    
                        <table style="text-align: center;" class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Category</th>
                                    <th>Owner</th>
                                    <th>Created</th>
                                    <th>Status</th>

                                    <th>Actions</th>


                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listCourse}" var="course">
                                    <tr>
                                        <td>${course.course_id}</td>
                                        <td style="align-items: left">

                                            ${course.course_name}

                                        </td>
                                        <c:forEach items="${listCate}" var="cate">
                                            <c:if test="${course.category_id==cate.category_id}">
                                                <td>${cate.category_name}</td>
                                            </c:if>
                                        </c:forEach>
                                        <c:forEach items="${listUser}" var="user">
                                            <c:if test="${user.user_id == course.expert_id}">
                                                <td>
                                                    ${user.user_name}
                                                </td>
                                            </c:if>
                                        </c:forEach>
                                        <td>${course.created_at}</td>
                                        <td>
                                            <c:if test="${course.status == 1}">
                                                <span style="color: green;">Active</span>
                                            </c:if>
                                            <c:if test="${course.status == 0}">
                                                <span style="color: red;">Inactive</span>
                                            </c:if>
                                        </td>

                                        <td>
                                            <div class="btn-group btn-group-sm" role="group" aria-label="Button group">
                                                <a href="ShowInformationCourse?courseId=${course.course_id}&action=view" class="btn btn-outline-info">View</a>
                                                <c:if test="${userRole.role_id==1||userRole.role_id==4}">
                                                    <a href="ShowInformationCourse?courseId=${course.course_id}&action=edit" class="btn btn-outline-warning">Edit</a>
                                                    <button type="button" class="btn btn-outline-danger btn-action" data-bs-toggle="modal" data-bs-target="#deleteCourse${course.course_id}">Delete</button>
                                                </c:if>

                                                <!-- Delete  Course-->
                                                <div class="modal" id="deleteCourse${course.course_id}">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h4 class="modal-title">Confirm Delete</h4>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <p>Are you sure you want to delete this Course?</p>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <form action="deleteCourse" method="post">
                                                                    <input type="text" name="page"value="1" hidden>
                                                                    <input hidden="" name="courseId" value="${course.course_id}">
                                                                    <input hidden="" name="action" value="${action}">
                                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                                </form>
                                                            </div>
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
                                <a href="#" id="SubjectsList"></a>
                                <ul class="pagination">
                                    <li class="page-item"><a class="page-link" href="SearchSubjectList?courseKeyword=${courseKeyword}&cateID=${cateID}&status=${status}&page=${(pageIndex <= 1) ? 1 : pageIndex-1}&sale=${sale}">Previous</a></li>

                                    <c:forEach begin="1" end="${endPage}" var = "i">
                                        <li class="page-item"><a class="page-link ${i == pageIndex ? 'active':'' }" href="SearchSubjectList?courseKeyword=${courseKeyword}&cateID=${cateID}&status=${status}&sale=${sale}&page=${i}">${i}</a></li>
                                        </c:forEach>
                                    <li class="page-item"><a class="page-link" href="SearchSubjectList?courseKeyword=${courseKeyword}&cateID=${cateID}&status=${status}&page=${(pageIndex + 1 <= endPage) ? pageIndex + 1 : pageIndex}&sale=${sale}">Next</a></li>
                                </ul>
                            </nav>  
                        </div>
                    </c:if>     

                </div>    
            </div>

        </div>







        <jsp:include page="../../components/footer.jsp"></jsp:include>


    </body>
</html>