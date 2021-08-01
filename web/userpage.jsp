<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User List</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/f9a01ea1da.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="/employee/cssuserlist/bootstrap.min.css">
        <link rel="stylesheet" href="/employee/cssuserlist/main.css">
    </head>
    <body>

        <main class="container py-5" style="margin: 90px auto">
            <div class="card mx-auto">
                <div class="card-header">
                    <h4 class="m-0 text-white">
                        User <span class="fw-bold">List</span>
                    </h4>
                </div>
                <div class="card-body table-responsive-sm">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Date Created</th>
                                <th>Role</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.name}</td>
                                    <td>${user.createdDate}</td>
                                    <c:if test="${user.roleName ne null}">
                                    <td style="color:blue">${user.roleName}</td>
                                    </c:if>
                                     <c:if test="${user.roleName eq null}">
                                    <td style="color:red">No Role</td>
                                    </c:if>
                                    <c:if test="${user.status eq 1}">
                                        <td style="color:green"><i class="fas fa-circle green"></i> Active</td>
                                    </c:if>
                                    <c:if test="${user.status ne 1}">
                                        <td style="color:red"><i class="fas fa-circle green"></i> Deactive</td>
                                    </c:if>

                                    <td>
                                        <a href="/employee/admin/edit-user?id=${user.id}"><i class="fas fa-cog fa-lg me-2 edit"></i></a>
                                        <a href="#"><i class="fas fa-times-circle fa-lg delete"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>


        <script src="/employee/jsuserlist/bootstrap.min.js"></script>
    </body>
</html>
<jsp:include page="footer.jsp" ></jsp:include>