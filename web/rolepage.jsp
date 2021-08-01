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
        <link rel="stylesheet" href="/employee/cssrolelist/bootstrap.min.css">
        <link rel="stylesheet" href="/employee/cssrolelist/main.css">
    </head>
    <body>
        <header>
            <nav class="container navbar navbar-expand-lg">
                <div class="container-fluid">
                    <a class="navbar-brand fs-2 text-uppercase" href="#">
                        <span>User</span> List
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
                        <i class="fas fa-bars fa-lg"></i>
                    </button>
                    <div class="collapse navbar-collapse" id="navbar">
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                            <li class="nav-item ">
                                <a href="#" class="nav-link">Admin Panel</a>
                            </li>
                            <li class="nav-item ">
                                <a href="#" class="nav-link">Home</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="employeeList" role="button" data-bs-toggle="dropdown" aria-expanded="false">Employee List&nbsp;</a>
                                <ul class="dropdown-menu" aria-labelledby="employeeList">
                                    <li><a class="dropdown-item" href="#">Item 1</a></li>
                                    <li><a class="dropdown-item" href="#">Item 2</a></li>
                                    <li><a class="dropdown-item" href="#">Item 3</a></li>
                                </ul>
                            </li>
                            <li class="nav-item ">
                                <a href="#" class="nav-link">Logout</a>
                            </li>
                            <li class="nav-item ">
                                <a href="#" class="nav-link">Contact</a>
                            </li>
                            <li class="nav-item ">
                                <a href="#" class="nav-link">External</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <main class="container py-5">
            <div class="card mx-auto">
                <div class="card-header d-flex justify-content-between">
                    <h4 class="m-0">
                        User <span class="fw-bold">Managment</span>
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

                                        <select class="form-select">
                                            <c:forEach var="rp" items="${rsd.findPermissionIdByRoleId(role.id)}">
                                                <option value="select">${rp.permissionName}</option>
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
                                        <a href="#"><i class="fas fa-cog fa-lg me-2 edit"></i></a>
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