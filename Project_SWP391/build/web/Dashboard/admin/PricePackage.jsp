<%-- 
    Document   : PricePackage
    Created on : Mar 8, 2024, 10:24:43 AM
    Author     : MH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="././css/styles.css" type="text/css"/>
        <script src="ckeditor5-41.0.0-fawpjqxdt1w9/build/ckeditor.js"></script>

        <style>
            .form-group{
                padding-bottom: 5px;
                padding-top: 5px
            }
            .body{
                overflow-x: hidden;
            }
        </style>


    </head>
    <body>
        <jsp:include page="../../components/header.jsp"></jsp:include>
            <div class="container-fluid vh-100"  style="display: flex;padding: 0px;padding-bottom: 15px">
                <div class="row content " style="display: contents">
                    <div class="col-sm-2 col-md-2 sidenav hidden-xs " style="background-color: white;border-radius: 15px;padding: 0px" >
                        <h2 style="text-align: center">Course Detail</h2>


                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link " aria-current="page" href="ShowInformationCourse?courseId=${course.course_id}&action=${action}" >Overview</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled"  aria-disabled="true"  href="pricePackageView?courseId=${course.course_id}&action=${action}" style="background-color: #38A5EE">Price Package</a>
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
                                    <li class="breadcrumb-item " aria-current="#">Subject Detail</li>
                                    <li class="breadcrumb-item active" aria-current="#">Price package</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="alert alert-primary" >
                        <h4 style="color: blue">${course.course_name}</h4>
                    </div>



                    <div class="container-fluid row">

                        <div class="col-md-2">
                            <form action="SearchPricePackage" method="get"  style="background-color: #cfe2ff;border: solid 1px #9ec5fe;border-radius: 15px">
                                <input hidden="" name="action" value="${action}">
                                <input hidden="" name="courseId" value="${course.course_id}">
                                <input hidden="" name="action" value="${action}">
                                <div class="form-group">
                                    <label for="search">Search name</label>
                                    <input type="text" name="priceKeyword"class="form-control"  placeholder="Search "  value="${priceKeyword}">
                                </div>

                                <div class="form-group">
                                    <label for="duration">Duration</label>
                                    <select class="form-control" name="duration" id="cateID" >

                                        <option value="-1"  disabled="" selected="">Choose duration</option>
                                        <option  value="1"<c:if test="${duration==1}"> selected=""</c:if> >1-3 months</option>                                                                           
                                        <option  value="2"<c:if test="${duration==2}"> selected=""</c:if>>3-6 months</option>                                                                                
                                        <option  value="3"<c:if test="${duration==3}"> selected=""</c:if> >6-12 months</option>                                                                          



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
                                <a class="btn "    href="pricePackageView?courseId=${course.course_id}&action=${action}" style="background-color: #38A5EE;margin-bottom: 8px">Reset</a>


                            </form>
                        </div>
                        <div class="col-md-10">
                            <c:if test="${userRole.user_id==1&&action eq 'edit'}">
                                <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addPricePackage">Add Price Package</button>
                            </c:if>

                            <c:if test="${not empty message}">
                                <div class="alert alert-primary well" role="alert">
                                    ${message}
                                </div>
                            </c:if>
                           <c:if test="${ numberOfPk <=0 }">
                                <h4>No Price package founded</h4>
                            </c:if>  
                             <c:if test="${numberOfPk>0}">
                            <table class="table">
                                <thead>
                                    <tr >

                                        <th>Name</th>
                                        <th>Duration(Months)</th>
                                        <th>Price</th>
                                        <th>Sale Price</th>
                                        <th>Status Sale Package</th>
                                        <th>Status</th>
                                            <c:if test="${userRole.user_id==1&&action eq 'edit'}">
                                            <th>Action</th>
                                            </c:if>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listPrice}" var="Price">
                                        <tr >

                                            <td>${Price.name}</td>
                                            <td>
                                                <c:if test="${Price.duration == 0}">
                                                    Unlimited
                                                </c:if>
                                                <c:if test="${Price.duration != 0}">
                                                    ${Price.duration}
                                                </c:if>
                                            </td>
                                            <td>${Price.price}</td>
                                            <td>${Price.sale_price}</td>
                                            <td>
                                                <c:if test="${Price.status_sale == 1}">
                                                    <span style="color: green;">Active</span>
                                                </c:if>
                                                <c:if test="${Price.status_sale == 0}">
                                                    <span style="color: red;">Inactive</span>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${Price.status == 1}">
                                                    <span style="color: green;">Active</span>
                                                </c:if>
                                                <c:if test="${Price.status == 0}">
                                                    <span style="color: red;">Inactive</span>
                                                </c:if>
                                            </td>
                                            <c:if test="${userRole.user_id==1&&action eq 'edit'}">
                                                <td>
                                                    <button type="button" class="btn btn-warning btn-action" data-bs-toggle="modal" data-bs-target="#editPricePackage${Price.price_package_id}">Edit</button>
                                                    <button type="button" class="btn btn-danger btn-action" data-bs-toggle="modal" data-bs-target="#deleteConfirmationPackage${Price.price_package_id}">Delete</button>

                                                    <!--edit price-->
                                                    <div class="modal edit-price" id="editPricePackage${Price.price_package_id}">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Edit Price Package</h4>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <form action="pricePackageUpdate?pk_id=${Price.price_package_id}&action=edit&courseId=${course.course_id}" method="post" name="edit-price">

                                                                        <div class="form-group">
                                                                            <label for="edit-package">Name</label>
                                                                            <input type="text" class="form-control" name="pk_name" required="" value="${Price.name}">
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="edit-duration">Duration(month)</label>
                                                                            <input type="number" class="form-control" name="pk_duration" required="" value="${Price.duration}" step="1" min="1" max="12">
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="edit-list-price">Price</label>
                                                                            <input type="number" class="form-control" name="pk_price" required="" value="${Price.price}" min="1" step="1">
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="edit-list-price">Sale Price</label>
                                                                            <input type="number" class="form-control" name="pk_saleprice" required="" value="${Price.sale_price}" min="1" step="1">
                                                                        </div>

                                                                        <div class="form-group">
                                                                            <label for="pk_statusSale">Status Sale</label>
                                                                            <select class="form-select" name="pk_statusSale" >
                                                                                <option value="1" <c:if test = "${Price.status_sale==1}">selected=""</c:if> style="color:green">Active</option>
                                                                                <option value="0" <c:if test = "${Price.status_sale==0}">selected=""</c:if> style="color: red">Inactive</option>
                                                                                </select>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label for="pk_status">Status</label>
                                                                                <select class="form-select" name="pk_status">
                                                                                    <option value="1" <c:if test = "${Price.status==1}">selected=""</c:if> >Active</option>
                                                                                <option value="0" <c:if test = "${Price.status==0}">selected=""</c:if> >Inactive</option>
                                                                                </select>
                                                                            </div>        
                                                                            <button type="submit" class="btn btn-primary">Save</button>
                                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <!--Delete Price Package-->

                                                        <div class="modal" id="deleteConfirmationPackage${Price.price_package_id}">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Confirm Delete</h4>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <p>Are you sure you want to delete this Price Package?</p>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <form action="pricePackageDelete?pk_id=${Price.price_package_id}&action=edit&courseId=${course.course_id}" method="post">

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
                               
                            <c:if test="${endPage != 0}">    
                                <div class="page">
                                    <nav aria-label="Page navigation ">
                                        <a href="#" id="SubjectsList"></a>
                                        <ul class="pagination">
                                            <li class="page-item"><a class="page-link" href="SearchPricePackage?courseId=${course.course_id}&priceKeyword=${priceKeyword}&status=${status}&page=${(pageIndex <= 1) ? 1 : pageIndex-1}&duration=${duration}&action=${action}">Previous</a></li>

                                            <c:forEach begin="1" end="${endPage}" var = "i">
                                                <li class="page-item"><a class="page-link ${i == pageIndex ? 'active':'' }" href="priceKeyword?courseId=${course.course_id}&courseKeyword=${courseKeyword}}&status=${status}&page=${i}&duration=${duration}&action=${action}">${i}</a></li>
                                                </c:forEach>
                                            <li class="page-item"><a class="page-link" href="SearchPricePackage?courseId=${course.course_id}&priceKeyword=${priceKeyword}&status=${status}&page=${(pageIndex + 1 <= endPage) ? pageIndex + 1 : pageIndex}&duration=${duration}&action=${action}">Next</a></li>
                                        </ul>
                                    </nav>  
                                </div>
                            </c:if> 
                             </c:if>
                        </div>
                        <div class="modal" id="addPricePackage">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Add Price Package</h4>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="pricePackageAdd?pk=${Price.price_package_id}&action=edit&courseId=${course.course_id}" method="post" name="edit-price" >

                                            <div class="form-group">
                                                <label for="edit-package">Name</label>
                                                <input type="text" class="form-control" name="pk_name" required="">
                                            </div>
                                            <div class="form-group">
                                                <label for="edit-duration">Duration(month)</label>
                                                <input type="number" class="form-control" name="pk_duration" required="" min="1" step="1" max="12">
                                            </div>
                                            <div class="form-group">
                                                <label for="edit-list-price">Price</label >
                                                <input type="number" class="form-control" name="pk_price" required="" min="1" step="1">
                                            </div>
                                            <div class="form-group">
                                                <label for="edit-list-price">Sale Price</label>
                                                <input type="number" class="form-control" name="pk_saleprice" required="" min="1"step="1">
                                            </div>

                                            <div class="form-group">
                                                <label for="pk_statusSale">Status Sale</label>
                                                <select class="form-select" name="pk_statusSale" >
                                                    <option value="1" selected="">Active</option>
                                                    <option value="0">Inactive</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="pk_status">Status</label>
                                                <select class="form-select" name="pk_status" >
                                                    <option value="1" selected="">Active</option>
                                                    <option value="0">Inactive</option>
                                                </select>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Save</button>
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>



                    </div>
                </div>
            </div>
        </div>





        <jsp:include page="../../components/footer.jsp"></jsp:include>
    </body>

</html>
