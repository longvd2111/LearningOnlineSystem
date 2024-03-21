<%-- 
    Document   : TestYoutube
    Created on : Feb 19, 2024, 2:19:15 PM
    Author     : caomi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"></jsp:include>
        <iframe width="560" height="315" src="https://www.youtube.com/embed/4BUKaazoYyg?si=C8zuXiJd1tP-Bsu4;start=80" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
        <iframe width="560" height="315" src="https://www.youtube.com/embed/4BUKaazoYyg?si=pSu8lfgnN2-p0TA2&amp;start=80&amp;modestbranding=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

        <video controls width="640" height="360">
            <source src="file:///C:/video/001 Course Overview and What you'll Learn in this Course.mp4" type="video/mp4">
            Your browser does not support the video tag.
        </video>

    </body>
</html>
