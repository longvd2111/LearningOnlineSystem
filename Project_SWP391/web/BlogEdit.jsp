<%-- 
    Document   : BlogEdit
    Created on : Jan 29, 2024, 9:37:20 PM
    Author     : caomi
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.util.List"%>

<%@page import ="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="ckeditor5-41.0.0-fawpjqxdt1w9/build/ckeditor.js"></script>
        <title>JSP Page</title>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <style>
            .ck-editor__editable_inline:not(.ck-comment__input ) {
                min-height: 100px;
                overflow-y: auto;
            }
        </style>
        <jsp:include page="components/header.jsp"></jsp:include>
            <div class="container">
                <div class="row" >

                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="Home">Home</a></li>
                                <li class="breadcrumb-item" ><a href="BlogManager">Blog Manager</a></li>
                                <li class="breadcrumb-item active" aria-current="#">Edit Post</li>

                            </ol>
                        </nav>
                    </div>

                </div>
                <div class="col-md-10 offset-md-1">
                    <br>
                    <h1>Edit Post</h1>
                    <div class="d-flex" style="display: flex;justify-content: space-between">
                        <a class="btn btn-primary mb-2" href="CreatePost">Create New</a>

                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#DeleteModal">
                            Delete
                        </button>

                        <div class="modal fade" id="DeleteModal" tabindex="-1" aria-labelledby="DeleteModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Delete this post?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <a class="btn btn-danger mb-2" href="BlogDelete?post_id=${post.post_id}">Delete</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <h3 style="color:red;" >${infor}</h3>
                <br>
                <form action="BlogEdit" method="post">
                    <input type="number" name="post_id" value="${post.post_id}" hidden>
                    <div class="row mb-3 align-items-center">
                        <div class="col-2">
                            <label for="title" class="col-form-label">Title:</label>
                        </div>
                        <div class="col-9">
                            <input type="text" id="title" name="title" class="form-control" placeholder="Title..." value="${post.title}" required>
                        </div>

                    </div>
                    <div class="row mb-3 align-items-center">
                        <div class="col-2">
                            <label for="thumbnail" class="col-form-label">Thumbnail:</label>
                        </div>
                        <div class="col-9">
                            <textarea name="thumbnail" id="imageP" style="height:100px;">${post.thumbnail}</textarea>
                        </div>
                    </div>

                    <div class="row mb-3 align-items-center">
                        <div class="col-2">
                            <label for="category" class="col-form-label">Category:</label>
                        </div>
                        <div class="col-5">
                            <select class="form-control" name="cateId"id="category">

                                <c:forEach items="${listCategory}" var="cate">
                                    <option value="${cate.category_id}" ${post.category_post_id == cate.category_id ? 'selected' : ''}>${cate.category_name}</option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="col-4">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="active" name="active" id="active" ${post.is_feature == 1 ? 'checked' : ''}>
                                <label class="form-check-label" for="active">
                                    Active
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3 align-items-center">
                        <div class="col-2">
                            <label for="sub_content" class="col-form-label">Sub title:</label>
                        </div>
                        <div class="col-9">
                            <input type="text" id="sub_content" class="form-control" name="sub_content" placeholder="Sub title..." value="${post.sub_content}" required>
                        </div>
                    </div>
                    <div class="row mb-3 ">
                        <div class="col-2">
                            <label for="editor1" class="col-form-label">Content:</label>
                        </div>
                        <div class="col-9">

                            <textarea name="editor1" style="height: 400px;"  id="editor1" required>${post.content}</textarea>

                        </div>
                    </div>


                    <button type="submit" class="btn btn-primary mb-2 float-end">Save</button>
                </form>

            </div>
        </div>
        <script>



            ClassicEditor
                    .create(document.querySelector('#editor1'));
            ClassicEditor
                    .create(document.querySelector('#imageP'), {
                        toolbar: ['imageInsert']
                    });
        </script>
    </body>
</html>
