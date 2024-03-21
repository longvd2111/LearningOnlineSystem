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
            <c:if test="${sessionScope.acc != null}">

                <div class="row">
                    <div class="col-md-4 item-photo">
                        <img style="max-width:100%;" src=${detail.image}
                             </div>
                        <div class="col-md-5" style="border:0px solid gray">
                            <!-- Product title and seller -->
                            <h3>${detail.course_name}</h3>
                            <p>
                                <small>
                                    ${detail.tagline}
                                </small>
                            </p>

                            <div class="form-group">
                                <label for="category">Package</label>
                                <select class="form-control" name="name"  id="name" onchange="OnclickName()"> 
                                    <c:forEach items="${price}" var="u">                                  
                                        <option value="${u.duration}" ${u.name eq selectedUserCourse ? 'selected' : ''}>${u.name}</option>
                                    </c:forEach>
                                </select>
                            </div> 
                            <br/>

                            <h4>Price:</h4>
                            <p>

                                ${pricepackage.price}$

                            </p>  

                            <h4>Price Sale:</h4>
                            <p>

                                ${pricepackage.sale_price}$

                            </p>

                            <!-- Purchase buttons -->

                            <c:if test="${isRegisted == false}">
                                <div class="">
                                    <form action="CourseRegister" method="post">
                                        <input type="hidden" name="courseid" value="${detail.course_id}">
                                        <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Register</button>
                                    </form>
                                    <div class="col-md-9">

                                    </div>   
                                </div>
                            </c:if>
                            <c:if test="${isRegisted ==true}">
                                <a class="btn btn-success" href="CourseLearn?courseId=${detail.course_id}"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Learning</a>
                            </c:if>

                        </div>

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

            <c:if test="${sessionScope.acc == null}">
                <div class="row">
                    <div class="col-md-4 item-photo">
                        <img style="max-width:100%;" src=${detail.image}>
                             </div>
                        <div class="col-md-5" style="border:0px solid gray">
                            <!-- Product title and seller -->
                            <h3>${detail.course_name}</h3>
                            <p>
                                <small>
                                    ${detail.tagline}
                                </small>
                            </p>

                            <div class="form-group">
                                <label for="category">Package</label>
                                <select class="form-control" name="name"  id="name" onchange="OnclickName()">   
                                    <c:forEach items="${price}" var="u">                                  
                                        <option value="${u.duration}" ${u.name eq selectedUserCourse ? 'selected' : ''}>${u.name}</option>
                                    </c:forEach>
                                </select>
                            </div> 
                            <br/>
                            <div>
                                <h4>Price:</h4>
                                <p>

                                    ${pricepackage.price}$

                                </p>

                                <h4>Sale Price:</h4>
                                <p>
                            </div>


                            ${pricepackage.sale_price}$

                            </p>

                            <!-- Purchase buttons -->
                            <div class="">
                                <form class ="form-signin" action="login" method="post">                      
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
            <script>
                function OnclickName() {
                    var courseId = getUrlParameter('courseid');
                    var selectedPrice = document.getElementById('name').value;

                    var newUrl = 'price?courseid=' + courseId + '&price=' + selectedPrice;

                    window.location.href = newUrl;

                    localStorage.setItem("selectedName", selectedPrice);
                }

                function getUrlParameter(name) {
                    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
                    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
                    var results = regex.exec(location.search);
                    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
                }
                ;

                window.onload = function () {
                    var storedName = localStorage.getItem("selectedName");
                    if (storedName) {
                        document.getElementById("name").value = storedName;
                    }
                };

            </script>                                                           




            <jsp:include page="components/footer.jsp"></jsp:include>
    </body>
</html>