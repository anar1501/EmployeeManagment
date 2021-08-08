<%@page import="az.company.employee.dao.concrets.RolePermissionDaoManager"%>
<%@page import="az.company.employee.dao.abstracts.RolePermissionDaoService"%>
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Role List</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/f9a01ea1da.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="/employee/cssrolelist/bootstrap.min.css">
        <link rel="stylesheet" href="/employee/cssrolelist/main.css">
    </head>
    <body style="background-color: darkslategray;">
       

        <main class="container py-5">
            <div class="card mx-auto" style="margin-top: 150px;">
                <div class="card-header d-flex justify-content-between">
                    <h4 class="m-0">
                        Role <span class="fw-bold">List</span>
                    </h4>
                    <button class="btn btn-light btn-sm" role="button"><i class="fas fa-plus-circle"></i> Add New Role</button>
                </div>
                <div class="card-body table-responsive-sm">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th style="width: 25%;">Name</th>
                                <th style="width: 35%;">Permission of Role</th>
                                <th style="width: 25%;">Status</th>
                                <th style="width: 15%;">Action</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="role" items="${roles}">
                                <tr>

                                    <td>${role.name}</td>

                                    <td>

                                        <select name="role-permission" class="form-select">
                                            <c:forEach var="rp"  items="${rds.findPermissionIdByRoleId(role.id)}">
                                                <option value="${rp.permissionName}">${rp.permissionName}</option>
                                            </c:forEach>
                                        </select>

                                    </td>

                                    <c:if test="${role.status eq 1}">
                                        <td><i class="fas fa-circle green"></i> Active</td>
                                    </c:if>
                                    <c:if test="${role.status ne 1}">
                                        <td><i class="fas fa-circle green"></i> Deactive</td>
                                    </c:if>
                                    <td>
                                        <a href="/employee/admin/edit-role?id=${role.id}"><i class="fas fa-cog fa-lg me-2 edit"></i></a>
                                        <a href="#"><i class="fas fa-times-circle fa-lg delete"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </main>


        <script src="/employee/jsrolelist/bootstrap.min.js"></script>
    </body>
</html>
<jsp:include page="footer.jsp" ></jsp:include>