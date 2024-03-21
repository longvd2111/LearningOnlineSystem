S<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.util.List"%>

<%@page import ="java.util.ArrayList"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


        <!------ Include the above in your HEAD tag ---------->
          <link href="css/styles.css" rel="stylesheet" type="text/css"/>
          
        <script>


            function filterSubcategories() {
            var categorySelect = document.getElementById("category");
                    var selectedCategoryId;
                    if (categorySelect == "null") {
            selectedCategoryId = 0;
            } else{
            selectedCategoryId = categorySelect.value;
            }
            var subcategorySelect = document.getElementById("subcategory");
                    console.log(selectedCategoryId);
                    var listSubCate = [
            <c:forEach items="${listSubCate}" var="subCate" varStatus="status">
                    {
                    category_id: ${subCate.category_id},
                            sub_category_id: ${subCate.sub_category_id},
                            sub_category_name: "${subCate.sub_category_name}"
                    }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
                    ];
                    // Clear all current options in the subcategory select

                    // Add a "Show All" option
                    var defaultOption = document.createElement("option");
                    subcategorySelect.innerHTML = "";
                    var defaultOption = document.createElement('option');
                    defaultOption.value = '0';
                    defaultOption.text = 'Show All';
                    subcategorySelect.add(defaultOption);
                    // Add subcategories based on the selected category
                    listSubCate.forEach(function (subCate) {
                    if (selectedCategoryId == 0) {
                    var option = document.createElement("option");
                            option.value = subCate.sub_category_id;
                            option.text = subCate.sub_category_name;
                            subcategorySelect.add(option);
                    } else {

                    if (subCate.category_id == selectedCategoryId) {
                    // Create an option for the subcategory if it matches the selected category id, or if no category is selected (Show All)
                    var option = document.createElement("option");
                            option.value = subCate.sub_category_id;
                            option.text = subCate.sub_category_name;
                            subcategorySelect.add(option);
                    }
                    }
                    });
            }
        </script>


    </head>
    <body>

        <jsp:include page="components/header.jsp"></jsp:include>

            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-sm-12">
                        <div class="card bg-light ">         
                            <form action="CategoryURL" class="form-inline">
                                <div>
                                    <label for="category" style="display: inline-block;">Category:</label>
                                    <select id="category" name="cateCId" onchange="filterSubcategories()" class="form-control mb-2 mr-sm-2" style="display: inline-block;">
                                        <option value="0">Show All</option> <!-- Thêm lựa chọn "Show All" -->
                                    <c:forEach items="${listCate}" var="cate">
                                        <c:set var="activeClass" value="${cate.category_id == cateIDNow ? 'active' : ''}" />
                                        <option value="${cate.category_id}" class="${activeClass}">${cate.category_name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div>
                                <label for="search" class="mb-2 mr-sm-2" style="display: inline-block; width: 150px;">Search course:</label>
                                <input type="text" class="form-control mb-2 mr-sm-2" id="search" name="search" placeholder="Search" style="display: inline-block; width: 200px;">
                            </div>
                            <button type="submit" class="btn btn-primary mb-2">Search</button>
                        </form>
                    </div>

                    <a href="CourseDetails?courseid=${FCourse.course_id}" class="card bg-light " style="text-decoration: none">
                        <div class="card-header bg-success text-white text-uppercase">Best Seller</div>
                        <div class="card-body">
                            <div>${FCourse.image}</div>
                            <h5 class="card-title">${FCourse.course_name}</h5>
                            <p class="card-text">${FCourse.tagline}</p>

                        </div>
                    </a>
                </div>

                <div class="col-md-8 col-sm-12">

                    <c:if test="${a == 0}">
                        <h2 style=" color: red ;text-decoration: none; "  >No course available</h2>
                    </c:if>    
                    <c:forEach items="${listCourse}" var="Course">


                        <div  class="card" style="flex-direction: row; text-decoration: none" >
                            <a href="CourseDetails?courseid=${Course.course_id}" class="col-md-4">

                                ${Course.image}

                            </a>
                            <div class="card-body col-md-8">

                                <a href="CourseDetails?courseid=${Course.course_id}" style="text-decoration: none">
                                    <h4 class="card-title">${Course.course_name}</h4>
                                    <p class="card-description">${Course.tagline}</p>
                                    <p>Created at: ${Course.created_at}</p>
                                </a>
                                
                                <div style="display: flex;z-index: 1">

                                    <div class="row col-md-4" style="flex-grow: 1;margin: 0">
                                        <label for="duration">Duration:</label>
                                        <select id="duration" style="height: 25px" onchange="updatePrice()">
                                            <c:forEach items="${listPrice}" var="price">
                                                <c:if test="${price.course_id == Course.course_id}">
                                                    <option value="${price.price}">${price.name}: ${price.price}$</option>

                                                </c:if>
                                            </c:forEach>
                                        </select>
                                        <label for="sale">Sale:</label>
                                        <select id="sale" style="height: 25px" onchange="updatePrice()">
                                            <option value="0">No Sale</option>
                                            <c:forEach items="${listCoupon}" var="coupon">
                                                <c:if test="${coupon.course_id == Course.course_id}">
                                                    <option value="${coupon.percent}">Sale: (${coupon.percent}%)</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <c:forEach items="${lwp}" var="lp">
                                        <c:if test="${lp.course_id == Course.course_id}">
                                            <div class="col-md-4" style="flex-grow: 1;">
                                                <p id="card-price">Lowest Price(3months+sale):${lp.lowest_price}$</p>
                                            </div>    
                                        </c:if>
                                    </c:forEach>



                                    <div class="col-md-4"style="flex-grow: 1;">
                                        <button id="registerButton" class="register-button" " >Register</button>
                                    </div>
                                </div>
                            </div>
                        </div>    
                    </c:forEach>

                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="CategoryURL?page=${pageIndex-1}&cateCId=${cateIDNow}&cateSubCId=${subCateIDNow}&search=${search}">Previous</a></li>

                            <c:forEach begin="1" end="${endPage}" var = "i">
                                <li class="page-item"><a class="page-link ${i == pageIndex ? 'active':'' }" href="CategoryURL?page=${i}&cateCId=${cateIDNow}&cateSubCId=${subCateIDNow}&search=${search}">${i}</a></li>
                                </c:forEach>
                            <li class="page-item"><a class="page-link" href="CategoryURL?page=${(pageIndex + 1 <= endPage) ? pageIndex + 1 : pageIndex}&cateCId=${cateIDNow}&cateSubCId=${subCateIDNow}&search=${search}">Next</a></li>
                        </ul>
                    </nav>  


                </div>



            </div>



            <!-- form đăng ký cho course -->
            <div id="registerModal" class="modal">
                <div class="modal-content">
                    <span class="close" id="closeModal">&times;</span>
                    <!-- Form đăng ký -->
                    <form form id="registrationForm" action="RegisterCourseServlet" method="post">
                        <!-- Thêm các trường thông tin cần thiết cho đăng ký -->
                        <label for="fullName">Full Name:</label>
                        <input type="text" id="fullName" name="fullName" required>

                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required>

                        <label for="mobile">Mobile:</label>
                        <input type="text" id="mobile" name="mobile" required>

                        <label for="gender">Gender:</label>
                        <select id="gender" name="gender" required>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                        <input type="hidden" id="courseId" name="courseId">
                        <button type="submit">Register</button>
                    </form>
                </div>
            </div>
            <script>
                        document.addEventListener('DOMContentLoaded', function () {
                        // Lấy các phần tử cần thiết
                        var registerButton = document.getElementById('registerButton');
                                var registerModal = document.getElementById('registerModal');
                                var closeModal = document.getElementById('closeModal');
                                var courseIdInput = document.getElementById('courseId');
                                registerButton.addEventListener('click', function () {
                                courseIdInput.value = "${detail.course_id}";
                                        registerModal.style.display = 'block';
                                });
                                window.onclick = function (event) {
                                if (event.target === registerModal) {
                                registerModal.style.display = 'none';
                                }
                                };
                                closeModal.addEventListener('click', function () {
                                registerModal.style.display = 'none';
                                });
                                var registrationForm = document.getElementById('registrationForm');
                                registrationForm.addEventListener('submit', function (event) {
                                event.preventDefault();
                                        registerModal.style.display = 'none';
                                });
                        });
            </script>
            <script>
                        document.addEventListener('DOMContentLoaded', function () {
                        var registrationForm = document.getElementById('registrationForm');
                                registrationForm.addEventListener('submit', function (event) {
                                event.preventDefault();
                                        // Lấy thông tin từ form
                                        var fullName = document.getElementById('fullName').value;
                                        var email = document.getElementById('email').value;
                                        var mobile = document.getElementById('mobile').value;
                                        var gender = document.getElementById('gender').value;
                                        // Lấy ID của khóa học từ trang JSP
                                        var courseId = "${detail.course_id}";
                                        // Gửi thông tin đăng ký tới Servlet
                                        var xhr = new XMLHttpRequest();
                                        xhr.open('POST', 'RegisterCourseServlet', true);
                                        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                                        xhr.onreadystatechange = function () {
                                        if (xhr.readyState == 4 && xhr.status == 200) {
                                        // Xử lý kết quả từ Servlet (có thể cập nhật giao diện ở đây)
                                        console.log(xhr.responseText);
                                                alert(xhr.responseText); // Hiển thị thông báo
                                        }
                                        };
                                        var params = "fullName=" + fullName + "&email=" + email + "&mobile=" + mobile + "&gender=" + gender + "&courseId=" + courseId;
                                        xhr.send(params);
                                        // Đóng modal sau khi gửi thông tin
                                        var registerModal = document.getElementById('registerModal');
                                        registerModal.style.display = 'none';
                                });
                        });
            </script>

            <jsp:include page="components/footer.jsp"></jsp:include>

    </body>
</html>

