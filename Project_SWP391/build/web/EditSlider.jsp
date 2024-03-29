<%-- 
    Document   : SignUp
    Created on : Jan 15, 2024, 3:56:37 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <script src="ckeditor5-41.0.0-fawpjqxdt1w9/build/ckeditor.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <title>EditSlider Form</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"></jsp:include>
                       <div class="col-md-10 offset-md-1">
                    <br>
                    <h1>Edit Slider</h1>
                    

                <h3 style="color:red;" >${infor}</h3>
                <br>
                <form action="editSlider" method="post">
                    <input type="number" name="id" value="${detail.slider_id}" hidden>
                    <div class="row mb-3 align-items-center">
                        <div class="col-2">
                            <label for="title" class="col-form-label">Title:</label>
                        </div>
                        <div class="col-9">
                            <input type="text" id="title" name="title" class="form-control" placeholder="Title..." value="${detail.title}" required>
                        </div>

                    </div>
                    <div class="row mb-3 align-items-center">
                        <div class="col-2">
                            <label for="thumbnail" class="col-form-label">Back Link</label>
                        </div>
                        <div class="col-9">
                            <input type="text" id="thumbnail" class="form-control" name="backlink" placeholder="Back link..." value="${detail.backlink}" required>
                        </div>
                    </div>

                    <div class="row mb-3 align-items-center">
                        <div class="col-2">
                            <label for="category" class="col-form-label">Status</label>
                        </div>
                        <div class="col-5">
                            <select id="status" name="status" class="form-control" required>
                                <option value="" disabled selected hidden>Choose status</option>
                                <option value="0">Hidden</option>
                                <option value="1">Show</option>
                            </select><br/>
                        </div>
                       
                    </div>
                    <div class="row mb-3 align-items-center">
                        <div class="col-2">
                            <label for="sub_content" class="col-form-label">Note:</label>
                        </div>
                        <div class="col-9">
                            <input type="text" id="sub_content" class="form-control" name="note" placeholder="Note..." value="${detail.notes}" required>
                        </div>
                    </div>
                    <div class="row mb-3 ">
                        <div class="col-2">
                            <label for="editor1" class="col-form-label">Image</label>
                        </div>
                        <div class="col-9">

                            <textarea name="editor1" style="height: 400px;"  id="editor1" required>${detail.image}</textarea>

                        </div>
                    </div>


                    <button type="submit" class="btn btn-primary mb-2">Save</button>
                </form>
               
            </div>
        <br/>
        <script>



            ClassicEditor
                    .create(document.querySelector('#editor1'))
        </script>
    </body>
</html>
