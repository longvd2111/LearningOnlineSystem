<%-- 
    Document   : BlogList
    Created on : Jan 15, 2024, 12:54:38 PM
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
         <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <!-- Link bootstrap Css -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <!-- Link bootstrap Js -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
  <script>
    document.addEventListener("DOMContentLoaded", function() {
      const headers = document.querySelectorAll(".sortable-table th");
      headers.forEach(header => {
        header.addEventListener("click", function() {
          const columnIdx = header.cellIndex;
          const tableBody = header.closest(".table").querySelector("tbody");
          const rows = Array.from(tableBody.querySelectorAll("tr"));
          const isNumeric = (str) => !isNaN(parseFloat(str)) && isFinite(str);
          
          rows.sort((rowA, rowB) => {
            const cellA = rowA.cells[columnIdx].innerText.trim();
            const cellB = rowB.cells[columnIdx].innerText.trim();
            if (isNumeric(cellA) && isNumeric(cellB)) {
              return parseFloat(cellA) - parseFloat(cellB);
            } else {
              return cellA.localeCompare(cellB);
            }
          });
          
          tableBody.innerHTML = "";
          rows.forEach(row => {
            tableBody.appendChild(row);
          });
        });
      });
    });
  </script>
        
    <style>
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}
</style>    
    </head>
    <body>
        <jsp:include page="components/header.jsp"></jsp:include>
            <br>
            <div class="container">
                <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="Home">Home</a></li>
                                <li class="breadcrumb-item active" aria-current=""><a href="RegistrationList">List Registration</a></li>
                                
                            </ol>
                        </nav>
                <div class="row">
                    <div class="col-sm-3">
                        <form action="/Project_SWP391/RegistrationSearch" method="get">
                            <div class="form-group">
                                
                                <label for="search">Search subject</label>
                                <input type="text" name="subject"class="form-control" id="subject" placeholder="Search subject..." oninput="updateLink()" value="${subject}">
                            </div>
                            
                            <div class="form-group">
                                <label for="search">Search email</label>
                                <input type="text" name="email"class="form-control" id="email" placeholder="Search email..." oninput="updateLink()" value="${email}">
                            </div>
                            
                        <div class="form-group">
                            <label for="category">Status</label>
                            <select class="form-control" name="status"id="status" onchange="OnclickStatus()">
                                <option value="-1" ${status == -1 ? 'selected' : ''}>All</option>
                                <option value="0" ${status == 0 ? 'selected' : ''}>Paid</option>
                                <option value="1" ${status == 1 ? 'selected' : ''}>Unpaid</option>

                            </select>
                        </div>
                                
                        <div class="form-group">
                            <label for="category">Subject</label>
                            <select class="form-control" name="subject" id="subjectRegistration" onchange="OnclickSubject()">
                                <option value="-1" ${cid == -1 ? 'selected' : ''}>All</option>
                                <c:forEach items="${listCourse}" var="c">
                                    <option value="${c.course_id}" ${c.course_name eq selectedCourse ? 'selected' : ''}>${c.course_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                                
                        <br/>        
                        <input type="text" name="page"value="1" hidden>
                        <button type="submit" class="btn btn-primary mb-2">Search</button>
                        </form>                    
                    </div>
  
<script>
   function OnclickStatus() {
    var selectedOption = document.getElementById("status").value;
    localStorage.setItem("selectedStatus", selectedOption);
    window.location.href = "status?status=" + selectedOption;
}

// Function cho dropdown join_time
function OnclickJoinTime() {
    var selectedOption = document.getElementById("join_time").value;
    localStorage.setItem("selectedJoinTime", selectedOption);
    window.location.href = "jointime?join_time=" + selectedOption;
}

// Function cho dropdown selectOption
function redirectToPage() {
    var selectedOption = document.getElementById("selectOption").value;
    localStorage.setItem("selectedTimeTo", selectedOption);
    window.location.href = "timeTo?timeTo=" + selectedOption;
}

function OnclickSubject() {
    var selectedOption = document.getElementById("subjectRegistration").value;
    localStorage.setItem("selectedSubject", selectedOption);
    // Thực hiện hành động mong muốn với selectedSubject
    window.location.href = "subject?cid=" + selectedOption;
}

// Hàm được gọi khi trang được tải lại
window.onload = function() {
    var storedStatus = localStorage.getItem("selectedStatus");
    if (storedStatus) {
        document.getElementById("status").value = storedStatus;
    }
    
    var storedSubject = localStorage.getItem("selectedSubject");
    if (storedSubject) {
        document.getElementById("subjectRegistration").value = storedSubject;
    }
};
</script> 



                                
<style>
    .card {
        border: 1px solid #ddd;
        border-radius: 8px;
        overflow: hidden;
        margin-bottom: 20px;
    }

    .row-g-0 {
        display: flex;
        align-items: center;
    }

    .col-md-3 {
        flex: 0 0 auto;
    }

    .col-md-3 img {
        max-height: 150px;
        width: 100%;
        height: auto;
        border-radius: 8px 0 0 8px;
    }

    .col-md-9 {
        padding: 15px;
    }

    .card-body {
        display: flex;
        flex-direction: column;
        height: 100%;
    }

    .card-title {
        font-size: 1.2rem;
        margin-bottom: 8px;
    }

    .card-text {
        margin-bottom: 8px;
    }

    .text-body-secondary {
        color: #888;
    }

    .btn-primary {
        background-color: #007bff;
        color: #fff;
        border: 1px solid #007bff;
        padding: 6px 12px;
        border-radius: 4px;
        transition: background-color 0.3s;
        align-self: flex-start;
        text-decoration: none;
    }

    .btn-primary:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }
    
</style>    
                <div class="col-sm-9">
                    <div class="row">
                        
                        <h1>Manager Registration List</h1>
                        <h5>Click on the title to sort the table.</h5>
                        <button type="button" class=" col-sm-2 btn btn-primary" onclick="redirectToAddProduct()">Add new users' registrations</button>
                        <br>
                        <table id="customers" class="table table-striped sortable-table">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Email</th>
                                    <th>Regitration Time</th>
                                    <th>Subject</th>
                                    <th>Package</th>
                                    <th>Total Cost</th>
                                    <th>Status</th>
                                    <th>Valid From</th>
                                    <th>Valid To</th>
                                    <th>Button</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listRegistration}" var="p">
                                <tr>
                                    <td>${p.user_id}</td>
                                    <td>${p.email}</td>
                                    <td>${p.join_time}</td>
                                    <td>${p.name}</td>
                                    <td>${p.pack}</td>
                                    <td>${p.price}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.is_archived == 0}">
                                                Paid
                                            </c:when>
                                            <c:otherwise>
                                                Unpaid
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${p.join_time}</td>
                                    <td>${p.timeTo}</td>
                                    <td>
                                        <button type="button" class="btn btn-danger me-2" onclick="redirectToServlet(${p.course_id}, ${p.user_id}, ${p.price_package_id}, ${p.is_archived})">View</button>
                                        <button type="button" class="btn btn-success" onclick="deleteFunction(${p.course_id}, ${p.user_id})">Delete </button>
                                    </td>

                                </tr>
                                
                               
                                </c:forEach>
                            </tbody>
                            
                        </table>
                        
                        
                     
                    </div>
                    <div class="endpage">
                        <c:forEach begin="1" end="${endP}" var="i">
                            <a class="${tag == i ? 'active' : ''}" href="RegistrationList?index=${i}">${i}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
                                
        <jsp:include page="components/footer.jsp"></jsp:include>
        <style>
            .endpage {
                display: flex;
                gap: 10px; 
                margin-bottom: 20px;
            }

            .endpage a {
                padding: 5px;
                border: 1px solid #ccc;
                text-decoration: none;
                color: #333;
                width: 30px; 
                height: 30px; 
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .endpage a.active {
                background-color: #007bff;
                color: green;
                
            }
        </style>
                                
        <script>
    function redirectToServlet(courseId, userId, price_package_id, status) {
        window.location.href = 'EditRegistration?course_id=' + courseId + '&user_id=' + userId+'&price_package_id=' + price_package_id+'&status='+status;
    }
    
    function deleteFunction(courseId, userId) {
        window.location.href = 'deleteUserCourse?course_id=' + courseId + '&user_id=' + userId;
    }
    
    function redirectToAddProduct(){
        window.location.href = "selectCourse";
    }
</script>
           
    </body>
</html>
