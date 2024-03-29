<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Detail</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .item-photo img {
                max-width: 100%;
                height: auto;
                display: block;
            }

            ul > li {
                margin-right: 25px;
                font-weight: lighter;
                cursor: pointer;
            }
            li.active {
                border-bottom: 3px solid silver;
            }
            .item-photo {
                display: flex;
                justify-content: center;
                align-items: center;
                border-right: 1px solid #f6f6f6;
            }
            .menu-items {
                list-style-type: none;
                font-size: 11px;
                display: inline-flex;
                margin-bottom: 0;
                margin-top: 20px;
            }
            .btn-success {
                width: 100%;
                border-radius: 0;
            }
            .section {
                width: 100%;
                margin-left: -15px;
                padding: 2px;
                padding-left: 15px;
                padding-right: 15px;
                background: #f8f9f9;
            }
            .title-price {
                margin-top: 30px;
                margin-bottom: 0;
                color: black;
            }
            .title-attr {
                margin-top: 0;
                margin-bottom: 0;
                color: black;
            }
            .btn-minus {
                cursor: pointer;
                font-size: 7px;
                display: flex;
                align-items: center;
                padding: 5px;
                padding-left: 10px;
                padding-right: 10px;
                border: 1px solid gray;
                border-radius: 2px;
                border-right: 0;
            }
            .btn-plus {
                cursor: pointer;
                font-size: 7px;
                display: flex;
                align-items: center;
                padding: 5px;
                padding-left: 10px;
                padding-right: 10px;
                border: 1px solid gray;
                border-radius: 2px;
                border-left: 0;
            }
            div.section > div {
                width: 100%;
                display: inline-flex;
            }
            div.section > div > input {
                margin: 0;
                padding-left: 5px;
                font-size: 10px;
                padding-right: 5px;
                max-width: 18%;
                text-align: center;
            }
            .attr, .attr2 {
                cursor: pointer;
                margin-right: 5px;
                height: 20px;
                font-size: 10px;
                padding: 2px;
                border: 1px solid gray;
                border-radius: 2px;
            }
            .attr.active, .attr2.active {
                border: 1px solid orange;
            }
            @media (max-width: 426px) {
                .container {
                    margin-top: 0 !important;
                }
                .container > .row {
                    padding: 0 !important;
                }
                .container > .row > .col-xs-12.col-sm-5 {
                    padding-right: 0;
                }
                .container > .row > .col-xs-12.col-sm-9 > div > p {
                    padding-left: 0 !important;
                    padding-right: 0 !important;
                }
                .container > .row > .col-xs-12.col-sm-9 > div > ul {
                    padding-left: 10px !important;
                }
                .section {
                    width: 104%;
                }
                .menu-items {
                    padding-left: 0;
                }
            }
        </style>
    </head>
    <body>
        <jsp:include page="components/header.jsp"></jsp:include>
            <div class="container">
                <div class="row" >
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="Home">Home</a></li>
                                <li class="breadcrumb-item " aria-current="#">Course Details</li>
                                
                            </ol>
                        </nav>
                    </div>
                </div>
                
    <div class="row">
        <div class="col-md-3">
            
            <form action="searchCourse" method="get">
                <div class="form-group">
                    <label for="search">Search course</label>
                    <input type="text" name="courseKeyword" class="form-control" id="courseKeyword" placeholder="Search course..." value="${courseKeyword}">
                </div>

                <div class="form-group">
                    <label for="cateID">Category</label>
                    <select class="form-control" name="cateID" id="cateID">
                        <option value="">Show All</option>
                        <c:forEach items="${listCate}" var="cate">
                            <option value="${cate.category_id}" ${cateID == cate.category_id ? 'selected' : ''}>${cate.category_name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="filter">Short By</label>
                    <select name="filter" id="filter" class="form-control">
                        <option <c:if test="${filter =='latest'}"> selected=""</c:if> value="latest">Latest</option>
                        <option <c:if test="${filter=='asc'}"> selected=""</c:if> value="asc">Price: low to high</option>
                        <option <c:if test="${filter=='desc'}"> selected=""</c:if>value="desc">Price: high to low</option>
                    </select>
                </div>

                <div class="form-group">
                    <label class="form-check-label" for="sale">On sale</label>
                    <input class="form-check-input" name="sale" type="checkbox" value="1" id="sale" ${sale == 1 ? 'checked' : ''}>
                </div>

                <input type="text" name="page" value="1" hidden>
                <button type="submit" class="btn btn-primary mb-2">Filter</button>
            </form>

        </div>


        <div class="col-md-9">
            <c:if test="${sessionScope.acc != null}">
                <div class="row">
                    <div class="col-md-4 item-photo" style="max-width: 100%">
                        <img  src="././img/course/${detail.image}" class="img-fluid rounded-start" style="max-width: 100%;height: auto">
                    </div>
                    <div class="col-md-5" style="border:0px solid gray">
                        <!-- Product title and seller -->
                        <h3>${detail.course_name}</h3>
                        <p>
                            <small>${detail.tagline}</small>
                        </p>

                        <!-- Purchase buttons -->
                        <c:if test="${isRegisted == false}">
                            <div class="form-group">
                                <label for="category">Package</label>
                                <select class="form-control" name="name" id="name" onchange="OnclickName()">   
                                    <option value="-1" selected disabled>Choose Package</option>
                                    <c:forEach items="${price}" var="u">                                  
                                        <option value="${u.duration}" ${u.name eq selectedUserCourse ? 'selected' : ''}>${u.name}</option>
                                    </c:forEach>
                                </select>
                            </div> 
                            <br/>
                           <div>
                                <%
                                    int price = (int) request.getAttribute("Strprice");
                                    if (price != 1) {
                                %>
                                        <h4>Price:</h4>
                                        <p>${pricepackage.price}$</p>
                                        <h4>Sale Price:</h4>
                                        <p>${pricepackage.sale_price}$</p>
                                <% } %>
                            </div>
                            <div class="">
                                <!-- Modal -->
                                    <div id="confirmModal" class="modal fade" role="dialog">
                                      <div class="modal-dialog">
                                        <!-- Modal content-->
                                        <div class="modal-content">
                                          <div class="modal-header">                                            
                                            <h4 class="modal-title">Confirm registration</h4>
                                          </div>
                                          <div class="modal-body">
                                            <p>Do you want to register for ${detail.course_name}?</p>
                                            <p>The price of the course is: ${pricepackage.price}$ Currently discounted: ${pricepackage.sale_price}$</p>
                                          </div>
                                          <div class="modal-footer">
                                            <button type="button" class="btn btn-danger" id="noButton">No</button>
                                            <button type="button" class="btn btn-primary" id="confirmYes">Yes</button>
                                          </div>
                                        </div>
                                      </div>
                                    </div>

                                    <!-- Button to trigger modal -->
                                    <form id="registerForm" action="CourseRegister" method="post">
                                        <input type="hidden" name="courseid" value="${detail.course_id}">
                                        <button type="button" class="btn btn-success" id="registerButton"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Register</button>
                                    </form>
                                <div class="col-md-9"></div>   
                            </div>
                        </c:if>
                        <c:if test="${isRegisted == true}">
                            <a class="btn btn-success" href="CourseLearn?courseId=${detail.course_id}"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Learning</a>
                        </c:if>
                    </div>
                    <div class="col-md-9">
                        <div class="tab-content">
                            <div class="tab-pane fade show active">
                                <div style="width:100%;border-top:1px solid silver;padding:15px;">
                                    <small>
                                        <ul>${detail.description}</ul>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${sessionScope.acc == null}">
                <div class="row">
                    <div class="col-md-4 item-photo">
                        <img  src="././img/course/${detail.image}" class="img-fluid rounded-start" style="max-width: 100%">
                    </div>
                    <div class="col-md-5" style="border:0px solid gray">
                        <!-- Product title and seller -->
                        <h3>${detail.course_name}</h3>
                        <p><small>${detail.tagline}</small></p>
                        <div class="form-group">
                            <label for="category">Package</label>
                            <select class="form-control" name="name" id="name" onchange="OnclickName()">   
                                <option value="-1" selected disabled>Choose Package</option>
                                <c:forEach items="${price}" var="u">                                  
                                    <option value="${u.duration}" ${u.name eq selectedUserCourse ? 'selected' : ''}>${u.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <br/>
                        <div>
                            <%
                                int price = (int) request.getAttribute("Strprice");
                                if (price != 1) {
                            %>
                                    <h4>Price:</h4>
                                    <p>${pricepackage.price}$</p>
                                    <h4>Sale Price:</h4>
                                    <p>${pricepackage.sale_price}$</p>
                            <% } %>
                        </div>
                        
                        <!-- Purchase buttons -->
                        <div class="">
                            <form class="form-signin" action="Login.jsp" method="post">                      
                                <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Register</button>

                                </form>

                            </div>
                        </div>

                        <div class="col-md-9">
                            <div class="tab-content">
                                <div class="tab-pane fade show active">
                                    <div style="width:100%;border-top:1px solid silver;padding:15px;">

                                        <small>
                                            <ul>
                                                ${detail.description}
                                            </ul>
                                        </small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                
            </c:if>
</div>
                
</div> 
</div>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function(){
        $("#noButton").click(function(){
            $("#confirmModal").modal("hide");
        });
    });
</script>    
            
            <script>
                var packageChanged = false;

                function OnclickName() {
                    var courseId = getUrlParameter('courseid');
                    var selectedPrice = document.getElementById('name').value;

                    if (selectedPrice !== "") {
                        packageChanged = true;
                        localStorage.setItem("selectedName", selectedPrice);
                    }

                    var newUrl = 'CourseDetails?courseid=' + courseId + '&price=' + selectedPrice;
                    window.location.href = newUrl;
                }

                function getUrlParameter(name) {
                    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
                    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
                    var results = regex.exec(location.search);
                    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
                }

                window.onload = function () {
                    var savedSelectedName = localStorage.getItem("selectedName");
                    if (savedSelectedName) {
                        document.getElementById('name').value = savedSelectedName;
                        packageChanged = true;
                    } else {
                        document.getElementById('name').value = "-1"; 
                    }
                };

            </script>                                                           

            <script>
document.addEventListener("DOMContentLoaded", function() {
    // Show modal when register button is clicked
    document.getElementById("registerButton").addEventListener("click", function() {
        $('#confirmModal').modal('show');
    });

    // Handle registration confirmation
    document.getElementById("confirmYes").addEventListener("click", function() {
        // Submit the form when "Yes" is clicked
        document.getElementById("registerForm").submit();
    });
});
</script>



            <jsp:include page="components/footer.jsp"></jsp:include>
    </body>
</html>