<div><!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Manager Profile</title>
        <script src="ckeditor5-41.0.0-fawpjqxdt1w9/build/ckeditor.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <jsp:include page="components/header.jsp"></jsp:include>
            <section class="bg-light">
                <div class="container">
                    <div id ="logreg-form">
                        <div class="row">
                            <div class="col">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="Home">Home</a></li>
                                        <li class="breadcrumb-item active" aria-current=""><a href="manage?user_id=${acc.user_id}">Manager Profile</a></li>

                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 mb-4 mb-sm-5">
                            <div class="card card-style1 border-0">
                                <div class="card-body p-1-9 p-sm-2-3 p-md-6 p-lg-7">
                                    <div class="row align-items-center">
                                        <div class="col-lg-6 mb-4 mb-lg-0" style="max-width: 100%; height: 100%;">
                                            ${user.image}
                                        </div>
                                        <div class="col-lg-6 px-xl-10">
                                            <div class="bg-secondary d-lg-inline-block py-1-9 px-1-9 px-sm-6 mb-1-9 rounded" style="margin-top: 0;">
                                                <h3 class="h2 text-white mb-0">${user.fullname}</h3>
                                            </div>
                                            <ul class="list-unstyled mb-1-9">
                                                <li class="mb-2 mb-xl-3 display-28"><span class="display-26 text-secondary me-2 font-weight-600">Username:</span> ${user.user_name}</li>
                                                <li class="mb-2 mb-xl-3 display-28"><span class="display-26 text-secondary me-2 font-weight-600">Email:</span> ${user.email}</li>
                                                <li class="mb-2 mb-xl-3 display-28"><span class="display-26 text-secondary me-2 font-weight-600">Phone:</span> ${user.phone}</li>
                                                <li class="mb-2 mb-xl-3 display-28"><span class="display-26 text-secondary me-2 font-weight-600">Gender:</span> ${user.gender == 1 ? 'Man' : 'Woman'}</li>
                                                <li class="mb-2 mb-xl-3 display-28"><span class="display-26 text-secondary me-2 font-weight-600">Status:</span> ${user.status == 1 ? 'Active' : 'Deactive'}</li>
                                                <li class="mb-2 mb-xl-3 display-28"><span class="display-26 text-secondary me-2 font-weight-600">Crated at:</span> ${user.create_at}</li>

                                            </ul>
                                            <div class="row justify-content-end">
                                                <div class="col-auto">
                                                    <button onclick="openPopup()" class="btn btn-success py-1-6 px-1-3 px-sm-3 mb-1 rounded" data-bs-toggle="modal" data-bs-target="#changeModal">
                                                        Change Password
                                                    </button>
                                                </div>
                                                <div class="col-auto">
                                                    <button class="btn btn-success py-1-6 px-1-3 px-sm-3 mb-1 rounded" data-bs-toggle="modal" data-bs-target="#manageModal">
                                                        Edit Profile
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </section>

        <!-- popup manage profile -->
        <div class="modal fade" id="manageModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title text-center" id="loginModalLabel">Manage Profile</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>                                                
                    </div>
                    <div class="modal-body">
                        <form class ="form-signin" action="manage" method="post">
                            <input value="${user.user_id}" name="userId" type="hidden">
                            Email: <input value="${user.email}" name="email"  type="text" id="inputEmail" class="form-control" readonly disabled required><br/>
                            Username: <input value="${user.user_name}" name="user_name"  type="text" id="username" class="form-control" placeholder="Username" required="" autofocus=""><br/>

                            Full name: <input value="${user.fullname}" name="full_name"  type="text" id="fullname" class="form-control" placeholder="Full Name" required="" autofocus=""><br/>
                            Phone: <input value="${user.phone}" name="phone_number"  type="text" id="phonenumber" class="form-control" placeholder="Phone number" required=""><br/>
                            Gender: <select id="gender" name="gender" class="form-control" required>
                                <option value="" disabled selected hidden>Choose Gender</option>
                                <option value="1">Male</option>
                                <option value="0">Female</option>
                            </select><br/>
                            <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i>Save</button>

                        </form>   
                    </div>
                </div>
            </div>
        </div>  

        <!-- success profile -->                            
        <div class="modal fade" id="successchangeProfile" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title text-center" id="loginModalLabel">Edit profile</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>                                                
                    </div>
                    <div class="modal-body">
                        <p>Profile was changed successfully</p>
                    </div>
                </div>
            </div>
        </div> 


        <!-- success password -->                            
        <div class="modal fade" id="successchangeModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title text-center" id="loginModalLabel">Password was changed successfully</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>                                                
                    </div>
                </div>
            </div>
        </div>     
        <!-- popup Change Password -->
        <div class="modal fade" id="changeModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h5 class="modal-title text-center" id="loginModalLabel">Change Password</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>                                                
                    </div>
                    <div class="modal-body">
                        <form class ="form-signin" action="changePassword" method="post">
                            <input value="${user.user_id}" name="userId" type="hidden">
                            <input name="userId"  type="hidden" id="inputEmail" class="form-control" value=""><br/>
                            <input name="oldPassword"  type="password" id="oldPassword" class="form-control" placeholder="Old password" required="" autofocus=""><br/>
                            <input name="newPassword"  type="password" id="newPassword" class="form-control" placeholder="New Password" required="" autofocus=""><br/>
                            <input name="rePassword"  type="password" id="rePassword" class="form-control" placeholder="Re-enter Password" required=""><br/>
                            <button  class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i>Change Password</button>

                        </form>   
                    </div>
                </div>
            </div>
        </div>

        <script>

            ClassicEditor
                    .create(document.querySelector('#imageP'), {
                        toolbar: ['imageInsert']
                    });
        </script>

        <jsp:include page="components/footer.jsp"></jsp:include>

        <% 
           Boolean managerchanged = (Boolean) session.getAttribute("managerchanged");
           if(managerchanged != null && managerchanged) {
        %>
        <script>
            $(document).ready(function () {
                $('#successchangeProfile').modal('show');
            });
        </script>
        <%
           session.removeAttribute("managerchanged");
           }
        %>    


        <% 
           Boolean passwordChanged = (Boolean) session.getAttribute("passwordChanged");
           if(passwordChanged != null && passwordChanged) {
        %>
        <script>
            $(document).ready(function () {
                $('#successchangeModal').modal('show');
            });
        </script>
        <%
           session.removeAttribute("passwordChanged");
           }
        %>


        <style>
            
            .card-style1 {
                box-shadow: 0px 0px 10px 0px rgb(89 75 128 / 9%);
            }
            .border-0 {
                border: 0!important;
            }
            .card {
                position: relative;
                display: flex;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 1px solid rgba(0,0,0,.125);
                border-radius: 0.25rem;
            }

            section {
                padding: 50px 0;

                background: #fff;
            }
            .mb-2-3, .my-2-3 {
                margin-bottom: 2.3rem;
            }

            .section-title {
                font-weight: 600;
                letter-spacing: 2px;
                text-transform: uppercase;
                margin-bottom: 10px;
                position: relative;
                display: inline-block;
            }
            .text-primary {
                color: #ceaa4d !important;
            }
            .text-secondary {
                color: #15395A !important;
            }
            .font-weight-600 {
                font-weight: 600;
            }
            .display-26 {
                font-size: 1.3rem;
            }

            @media screen and (min-width: 992px){
                .p-lg-7 {
                    padding: 4rem;
                }
            }
            @media screen and (min-width: 768px){
                .p-md-6 {
                    padding: 3.5rem;
                }
            }
            @media screen and (min-width: 576px){
                .p-sm-2-3 {
                    padding: 2.3rem;
                }
            }
            .p-1-9 {
                padding: 1.9rem;
            }

            .bg-secondary {
                background: #15395A !important;
            }
            @media screen and (min-width: 576px){
                .pe-sm-6, .px-sm-6 {
                    padding-right: 3.5rem;
                }
            }
            @media screen and (min-width: 576px){
                .ps-sm-6, .px-sm-6 {
                    padding-left: 3.5rem;
                }
            }
            .pe-1-9, .px-1-9 {
                padding-right: 1.9rem;
            }
            .ps-1-9, .px-1-9 {
                padding-left: 1.9rem;
            }
            .pb-1-9, .py-1-9 {
                padding-bottom: 1.9rem;
            }
            .pt-1-9, .py-1-9 {
                padding-top: 1.9rem;
            }
            .mb-1-9, .my-1-9 {
                margin-bottom: 1.9rem;
            }
            @media (min-width: 992px){
                .d-lg-inline-block {
                    display: inline-block!important;
                }
            }
            .rounded {
                border-radius: 0.25rem!important;
            }

        </style>
    </body>
</html>





</div>
