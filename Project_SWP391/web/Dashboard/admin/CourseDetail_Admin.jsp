<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.util.List"%>

<%@page import ="java.util.ArrayList"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


        <!------ Include the above in your HEAD tag ---------->


        <link rel="stylesheet" href="././css/styles.css" type="text/css"/>
        <script src="ckeditor5-41.0.0-fawpjqxdt1w9/build/ckeditor.js"></script>
        <style>
            .form-group{

                border-radius: 5px;
                margin: 20px;


            }
            .form-label {

                align-items: center;

            }
            .body{
                overflow-x:hidden;
            }

        </style>
    </head>
    <body>

        <jsp:include page="../../components/header.jsp"></jsp:include>

            <div class="container-fluid "  style="display: flex;padding: 0px;padding-bottom: 15px">
                <div class="row content " style="display: contents">
                    <div class="col-sm-2 col-md-2 sidenav hidden-xs " style="background-color: white;border-radius: 15px;padding: 0px" >
                        <h2 style="text-align: center">Course Detail</h2>


                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link disabled" aria-current="page" aria-disabled="true" href="ShowInformationCourse?courseId=${course.course_id}&action=${action}" style="background-color: #38A5EE">Overview</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"  href="pricePackageView?courseId=${course.course_id}&action=${action}">Price Package</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ShowChapter?courseId=${course.course_id}&action=${action}">Manage lessons</a>
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
                                    <li class="breadcrumb-item active" aria-current="#">Subject Detail</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    
                    
                    <c:if test="${not empty message}">
                                <div class="well alert alert-primary col-md-9" role="alert">
                                    ${message}
                                </div>
                    </c:if>  


                    <div class="container-fluid ">
                        <form class="overview" action="UpdateOverviewCourse?courseId=${course.course_id}" method="post" enctype="multipart/form-data"  style="border: solid 2px black;border-radius: 15px" >
                            <input hidden="" name="action" value="${action}">

                            <div class="row" >                                           
                                <div class="col-md-9">
                                    <div class=form-group " style="margin-bottom: 20px;" >
                                        <div class="form-label">
                                            <label for="courseName" >Course Name<c:if test="${action eq 'view'}">: ${course.course_name}</c:if></label>
                                            </div>
                                        <c:if test="${action eq 'edit'}">
                                            <input type="text" name="courseName" class="form-control" value="${course.course_name}" >
                                        </c:if>
                                    </div>
                                    <div class="form-group" style="margin-bottom: 20px;display: flex;align-items: center">
                                        <div class="form-label" style="padding-right: 20px ">
                                            <label  for="cateID" >Category
                                                <c:if test="${action eq 'view'}"> 
                                                    <c:forEach var="Cate" items="${listCate}">
                                                        <c:if test="${Cate.category_id eq course.category_id}">: ${Cate.category_name}</c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </label> 
                                        </div>
                                        <c:if test="${action eq 'edit'}">
                                            <select class="form-select" id="cateID" name="cateID">

                                                <c:forEach var="Cate" items="${listCate}">
                                                    <option value="${Cate.category_id}" <c:if test="${Cate.category_id eq course.category_id}">selected</c:if> >${Cate.category_name}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                    </div>

                                    <div class="form-group" style="margin-bottom: 20px;">
                                        <div class="form-label">
                                            <label class="" for="courseTagLine">Tagline<c:if test="${action eq 'view'}">: ${course.tagline}</c:if></label>
                                            </div>
                                        <c:if test="${action eq 'edit'}">
                                            <textarea class="form-control" name="courseTagLine" id="courseTagLine" rows="4" >${course.tagline}</textarea>
                                        </c:if>
                                    </div>

                                    <div class="form-group" style="margin-bottom: 20px">
                                        <div class="form-label">
                                            <label for="expert col-md-2" >Expert<c:if test="${userRole.role_id!=1}">: ${acc.user_name}</c:if>
                                                <c:if test="${userRole.role_id==1 && action ne 'edit'}">
                                                    <c:forEach var="expert" items="${listUserByRole}">
                                                        <c:if test="${expert.user_id eq course.expert_id}" >: ${expert.user_name}</c:if> 
                                                    </c:forEach>

                                                </c:if></label>
                                        </div>

                                        <c:if test="${userRole.role_id==1 && action eq 'edit'}">
                                            <select class="form-select" id="expert" name="expertId">
                                                <!-- Iterate over a list of experts and generate options -->
                                                <c:forEach var="expert" items="${listUserByRole}">
                                                    <option value="${expert.user_id}" <c:if test="${expert.user_id eq course.expert_id}" >selected</c:if> >${expert.user_name}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                    </div>

                                    <div class="form-group">
                                        <div class="form-label">
                                            <label for="feature col-md-2">Feature<c:if test="${ action eq 'view'}">

                                                    <c:if test="${course.feature == 1}">
                                                        <span style="color: green;">: Active</span>
                                                    </c:if>
                                                    <c:if test="${course.feature == 0}">
                                                        <span style="color: red;">: Inactive</span>
                                                    </c:if>
                                                </c:if>
                                            </label>
                                        </div>
                                        <c:if test="${ action eq 'edit'}">
                                            <select class="form-select" name="feature" id="feature">
                                                <option value="1" style="color: green" <c:if test="${course.feature == 1}">selected=""</c:if> >Active</option>
                                                <option value="0" style="color: red" <c:if test="${course.feature == 0}">selected=""</c:if>>Inactive</option>
                                                </select>

                                        </c:if> 
                                    </div>    
                                    <div class="form-group">
                                        <div class="form-label">
                                            <label for="publish col-md-2">Publish
                                                <c:if test="${ action eq 'view'}">

                                                    <c:if test="${course.status == 1}">
                                                        <span style="color: green;">: Active</span>
                                                    </c:if>
                                                    <c:if test="${course.status == 0}">
                                                        <span style="color: red;">: Inactive</span>
                                                    </c:if>
                                                </c:if>
                                            </label>
                                        </div>
                                        <c:if test="${  action eq 'edit'}">
                                            <select class="form-select" name="publish" id="publish">
                                                <option value="1" selected=""style="color: green" <c:if test="${course.status == 1}">selected=""</c:if>>Active</option>
                                                <option value="0" style="color: red" <c:if test="${course.status == 0}">selected=""</c:if>>Inactive</option>
                                                </select>

                                        </c:if>
                                    </div>     
                                    <div class="form-group" style="margin-bottom: 20px">
                                        <div class="form-label">
                                            <label for="description" class="form-label">Description</label>
                                        </div>
                                        <c:if test="${action eq 'view' }">
                                            ${course.description}
                                        </c:if>
                                        <c:if test="${action eq 'edit' }">   
                                            <textarea class="form-control" name="description" id="description" style="height:  150px;" required >${course.description}</textarea>
                                        </c:if> 
                                    </div>

                                </div>
                                <div class="col-md-3">


                                    <div class="form-group">
                                        <label style="margin: 5px;">Thumbnail</label> <br/>
                                        <img src="././img/course/${course.image}"  width="100%" height="auto">
                                        <c:if test="${  action eq 'edit'}"> 
                                            <input style="margin: 5px;width: 100%" type="file" 
                                                   class="form-control" name="image" >
                                        </c:if>
                                    </div>

                                </div>
                            </div>

                            <c:if test="${  action eq 'edit'}">          
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary" >Save</button>
                                    <a href="SubjectsList" class="btn btn-secondary">Back</a>
                                </div>
                            </c:if>  
                        </form> 
                    </div>

                    <!--                                <div class="tab-pane fade" id="dimension" role="tabpanel" aria-labelledby="dimension-tab" style="height: 100vh;">-->



                    <!--                                     Dimension tab content 
                                                        <div class="container-fluid">
                    <c:if test="${userRole.user_id==1}">
                        <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addDataModal">Add Data</button>
                    </c:if>
                    <table class="table">
                        <thead>
                            <tr>

                                <th>Type</th>
                                <th>Dimension</th>
                                <th>Description</th>
                    <c:if test="${userRole.user_id==1}">
                    <th>Action</th>
                    </c:if>
            </tr>

        </thead>
        <tbody>
                    <c:forEach items="${listDimen}" var="Dimen">
                         Add Dimension Modal 

                        <tr>

                            <td><c:forEach items="${listCateDimen}" var="cateDimen">
                            <c:if test="${cateDimen.cate_dimension_id == Dimen.cate_dimension_id}">${cateDimen.cate_dimension_name}</c:if>
                        </c:forEach></td>
                    <td>${Dimen.dimension_name}</td>
                    <td><button type="button" class="btn btn-info btn-action" data-bs-toggle="modal" data-bs-target="#descriptionDimen${Dimen.dimension_id}">Description
                        </button> Description Modal 
                        <div class="modal" id="descriptionDimen${Dimen.dimension_id}">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Description</h4>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <p>${Dimen.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div></td>
                        <c:if test="${userRole.user_id==1}">
                        <td>
                            <button type="button" class="btn btn-warning btn-action" data-bs-toggle="modal" data-bs-target="#editDimen${Dimen.dimension_id}">Edit</button>
                            <button type="button" class="btn btn-danger btn-action" data-bs-toggle="modal" data-bs-target="#deleteDimen${Dimen.dimension_id}">Delete</button>




                             Edit Dimension 
                            <div class="modal" id="editDimen${Dimen.dimension_id}">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">Edit Dimension</h4>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="ShowInformationCourse?dimenId=${Dimen.dimension_id}&action=editDimesion&courseId=${course.course_id}" method="post">
                                                <div class="form-group">
                                                    <label for="edit-type">Type</label>
                                                    <select class="form-control" id="edit-type" name="cate_dimension_id">
                            <c:forEach items="${listCateDimen}" var="cateDimen">
                                <option value="${cateDimen.cate_dimension_id}" 
                                <c:if test="${cateDimen.cate_dimension_id == Dimen.cate_dimension_id}">selected</c:if>>
                                ${cateDimen.cate_dimension_name}
                            </option>
                            </c:forEach>
                        </select>
                    </div>                                                               
                    <div class="form-group">
                        <label for="edit-dimension">Dimension</label>
                        <input type="text" class="form-control"  name="dimensionName"value="${Dimen.dimension_name}">
                    </div>
                    <div class="form-group">
                        <label for="edit-description">Description</label>
                        <textarea class="form-control"  name="description">${Dimen.description}</textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
    </div>
</div>

 Delete  Dimension
<div class="modal" id="deleteDimen${Dimen.dimension_id}">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Confirm Delete</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete this Dimension?</p>
            </div>
            <div class="modal-footer">
                <form action="ShowInformationCourse?dimenId=${Dimen.dimension_id}&action=deleteDimesion&courseId=${course.course_id}" method="post">
                    <button type="submit" class="btn btn-danger">Delete</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </form>
            </div>
        </div>
    </div>
</div>
</td>
                        </c:if>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>-->

                </div>
            </div>
        </div>



    </body>
    <jsp:include page="../../components/footer.jsp"></jsp:include>
    <script>
        ClassicEditor
                .create(document.querySelector('#description'));
        ClassicEditor
                .create(document.querySelector('#imageCourseDetail'), {
                    toolbar: ['imageInsert']

                });
    </script>
</html>

