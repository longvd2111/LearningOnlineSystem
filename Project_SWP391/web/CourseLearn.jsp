<%-- 
    Document   : CourseLearn
    Created on : Feb 26, 2024, 11:07:52 PM
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>



        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>

        <style>
            img {
                max-width: 100%;
                height: auto;
            }

            .language-python {
                display: block;
                background-color: #f4f4f4;
                color: #008000;
                padding: 10px;
                margin: 10px 0;
                font-size: 14px;
                border-radius: 5px;
                overflow-x: auto;
                font-family: 'Courier New', monospace;
            }
            .lesson-item ul {
                list-style: none;
                padding: 0;
            }
            .lesson-item ul li {
                padding: 10px 20px;
                border-top: 1px solid #dee2e6;
                font-size: 16px;
            }
            .lesson-item ul li:hover{
                background-color: #d1d7dc;
            }

            .lesson-item ul li a {
                color: #343a40;
                text-decoration: none;
            }
            .accordion-body{
                padding: 0;
            }
            button {
                color: black;
                background-color: lightgray;

            }
            .accordion {
                max-height: 80vh; /* Đặt chiều cao tối đa cho accordion, có thể điều chỉnh tùy theo nhu cầu */
                overflow-y: auto; /* Tạo thanh cuộn nếu nội dung vượt quá chiều cao */
            }
            body{
                height: 100vh;
            }
        </style>
        <div class="navbar navbar-expand-md bg-dark text-white"">
            <div class="container-fluid">

                <a class="navbar-brand active col-md-2 text-white"  href="Home">Online Course</a>


                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <h3 class="navbar-brand active col-md-2 text-white" >${course.course_name}</h3>
                    </li>
                    


                </ul>
                <h7 style="padding: 0 20px">Your process: ${doneLesson}/${totalLesson}</h7>

            </div>


        </div>

        <div class="container-fluid">
            <div class="row g-0">
                <div class="col-lg-9" style="">
                    
                    <iFrame src="https://www.youtube.com/embed/${lessonContent.content}" width="100%" height="600vh" allowfullscreen></iFrame>
                    
                    <div class="container-fluid"><div class="col-lg-10 offset-lg-1" >${lessonContent.content_text}</div></div>
                    <a href="videoDone?lessonId=${lessonContent.lesson_id}&courseId=${course.course_id}" class="btn  mb-2 float-end ${1 == lessonContent.is_archived ? 'btn-primary disabled' : 'btn-outline-dark'}">Done</a>
                </div>
                <div class="col-lg-3">

                    <div class="accordion accordion-flush" id="accordionExample" style="max-height: 100vh;position: fixed;top: 58px;right: 0;width: 25vw">
                        <c:forEach items="${listChapter}" var="chapter">
                            <div class="accordion-item lesson-item">
                                <h2 class="accordion-header" style="border-top: 1px solid #d1d7dc;">
                                    <button class="accordion-button ${chapterVideoId == chapter.chapter_id ? '' : 'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${chapter.chapter_id}" aria-controls="collapsecollapse${chapter.chapter_id}" aria-expanded="${chapterVideoId == chapter.chapter_id ? 'true' : 'false'}" style="color: black; background-color: #f7f9fa;">
                                        <h5><strong>${chapter.chapter_name}</strong> </h5>
                                    </button>
                                </h2>
                                <div id="collapse${chapter.chapter_id}" class="accordion-collapse collapse ${chapterVideoId == chapter.chapter_id ? 'show' : ''}">
                                    <div class="accordion-body" style="padding: 0">
                                        <ul>
                                            <c:forEach items="${chapter.listLesson}" var="lesson">
                                                <li style="${lessonContent.lesson_id == lesson.lesson_id ? 'background-color:#d1d7dc;' :''}"><a  href="CourseLearn?courseId=${course.course_id}&lessonId=${lesson.lesson_id}">${lesson.lesson_name}  ${1 == lesson.is_archived ? '(DONE)' : ''}</a></li>

                                                <!-- Add more parts as needed -->
                                            </c:forEach>
                                        </ul>

                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.getElementById('videoDone').addEventListener('ended', function () {
                // Gửi yêu cầu đến máy chủ khi video kết thúc
                var xhr = new XMLHttpRequest();
                xhr.open('GET', '/Project_SWP391/videoDone?lessonId=${lessonContent.lesson_id}', true);
                xhr.send();
            });
        </script>
    </body>
</html>
