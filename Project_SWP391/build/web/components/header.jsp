<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



        <!--        link bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>



        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    </head>
    <body class="body" >
        <!-- comment -->

        <!-- comment -->

                
                

        <div class="navbar navbar-expand-md bg-white text-dark">
            <div class="container"><a class="navbar-brand text-dark active col-md-2 "  href="Home">Online Course</a>
                <form class="d-flex col-md-6" action="">

                    <input class="form-control mr-auto" type="search" placeholder="Search">
                    <button class="btn btn-outline-info" type="send">Search</button>

                </form>
                </div>    
                <!-- tạo 1 nút menu khi màn hình nhỏ -->
                <button class="navbar-toggler col-md-4" data-bs-toggle="collapse"
                        data-bs-target="#nav" aria-controls="nav">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse d-flex navbar-collapse col-md-4" id="nav">
                    <div class="navbar-nav">
                        <ul class="navbar-nav header__list">
                            <li class="nav-item"><a class="nav-link" href="CourseListURL">Course</a></li>
                            <li class="nav-item"><a class="nav-link" href="BlogList2">Blog</a></li>

                            <c:if test="${sessionScope.acc != null}">
                                <!-- Dropdown button -->
                                <div class="dropdown">
                                    <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                                        <c:if test="${userRole.role_id==1}">Admin</c:if>
                                        <c:if test="${userRole.role_id==2}">Marketing</c:if>
                                        <c:if test="${userRole.role_id==3}">Sale</c:if>
                                        <c:if test="${userRole.role_id==4}">Expert</c:if>
                                        <c:if test="${userRole.role_id==5}">User</c:if>
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <c:if test="${userRole.role_id==1||userRole.role_id==2}">
                                        <li><a class="dropdown-item" href="slider" >Edit Slider</a></li>
                                        <li><a class="dropdown-item" href="BlogManager" >Blog Manager</a></li>
                                        </c:if>
                                        <li><a class="dropdown-item" href="manage?user_id=${acc.user_id}">Manage Profile</a></li>
                                    <c:if test="${userRole.role_id==1||userRole.role_id==3}">
                                        <li><a class="dropdown-item" href="RegistrationList" >Registration List</a></li>
                                        </c:if>
                                        <li><a class="dropdown-item" href="MyCourse2" >My Course</a></li>
                                            <c:if test="${userRole.role_id==1||userRole.role_id==4}">
                                            <li><a class="dropdown-item" href="SubjectsList" >Manage Course Content</a></li>
                                            </c:if>
                                        <li><a class="dropdown-item" href="logout" >Log Out</a></li>
                                    </ul>

                                </div>

                                <!-- --------- -->                                

                        </div>           
                        </ul>
                    </c:if>

                    <c:if test="${sessionScope.acc == null}">
                        <li class="nav-item">
                            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#loginModal">Login</button>
                            <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header text-center">
                                            <h5 class="modal-title text-center" id="loginModalLabel">Login</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>                                                
                                        </div>
                                        <div class="modal-body">
                                            <form class ="form-signin" action="login" method="post">
                                                <input name="email"  type="text" id="inputEmail" class="form-control" placeholder="Email" required="" autofocus=""><br/>
                                                <input name="pass"  type="password" id="inputPassword" class="form-control" placeholder="Password" required=""><br/>
                                                <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i>Login</button>
                                                <button class="btn btn-block" type="button"><a href="ForgotPassword.jsp"> Forgot password? </a></button>
                                            </form>   
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="nav-item">
                            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#signupModal">Sign Up</button>
                            <div class="modal fade" id="signupModal" tabindex="-1" aria-labelledby="signupModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header text-center">
                                            <h5 class="modal-title text-center" id="loginModalLabel">Sign Up</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>                                                
                                        </div>
                                        <div class="modal-body">
                                            <form action="signup" method="post" class="form-signup">
                                                <input name="email" type="text"  class="form-control" placeholder="Email"><br/>
                                                <input name="user_name" type="text"  class="form-control" placeholder="User Name"><br/>
                                                <input name="full_name" type="text"  class="form-control" placeholder="Full name"><br/>
                                                <input name="phone_number" type="text"  class="form-control" placeholder="Phone number"><br/>
                                                <input name="created_at" type="date"  class="form-control" placeholder="Phone number"><br/>
                                                <select id="gender" name="gender" class="form-control" required>
                                                    <option value="" disabled selected hidden>Choose gender</option>
                                                    <option value="0">Male</option>
                                                    <option value="1">Female</option>
                                                </select><br/>
                                                <input name="pass" type="password" id="user-pass" class="form-control" placeholder="Password" required ><br/>
                                                <input name="repass" type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" required ><br/>                                      

                                                <button class="btn btn-primary btn-block" type="submit">Sign Up</button><br>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:if>

                    </ul>        
                </div>           


            </div>

        


    </div>                   
</body>
</html>