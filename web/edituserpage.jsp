<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>


    <!DOCTYPE html>
    <html>
        <head>
            <title>Edit User</title>
            <link rel="stylesheet" href="/emplpyee/https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
            <link href="/employee/https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css rel=stylesheet">
            <link href="/employee/cssedituser/style.css" rel="stylesheet">
            <link rel="stylesheet" href="/employee/cssedituser/bootstrap.min.css">
            <link rel="stylesheet" href="/employee/cssedituser/main.css">
            <link href="/employee/https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400,500,600,700,800,900" rel=stylesheet>

        </head>
        <body>

            <div class="container" w-100>
                <table class="table table-bordered w-50 mr-auto ml-auto" >
                    <thead>
                        <tr class="bg-dark">
                            <th class="text-light">Name</th>
                            <th class="text-light">Date Created</th>
                            <th class="text-light">Role</th>
                            <th class="text-light">Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                    <form action="/employee/admin/save-edit-user" method="post">
                        <td>${user.name}</td>
                    <td>${user.createdDate}</td>
                    <td>
                        <input type="hidden" name="id-user" value="${user.id}">
                        <select name=role-id>
                            <c:forEach var="role" items="${roles}">
                                <c:if test="${role.id eq user.roleId}">
                                    <option value="${role.id}"  selected="${role.id}">${role.roleName}</option>
                                </c:if>
                                <c:if test="${role.id ne user.roleId}">
                                    <option value="${role.id}">${role.roleName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <c:if test="${user.status eq 1}">
                            <input data-id="${user.id}" class="aktiv" name="status" type="checkbox" data-onstyle="success" data-offstyle="danger" data-toggle="toggle" data-on="Active" data-off="InActive" value="${user.status}" checked/>
                        </c:if>
                        <c:if test="${user.status ne 1}">
                            <input data-id="${user.id}" class="aktiv" name="status" type="checkbox" data-onstyle="success" data-offstyle="danger" data-toggle="toggle" data-on="Active" data-off="InActive" value="${user.status}"/>
                        </c:if>
                    </td>
                    </tr>
                    </tbody>
            </table>
            <div style="display:flex; justify-content: center;">
                <input type=submit value=Submit name=submit class="btn btn-primary">
                <input type=submit value=Cancel name=cancel class="btn btn-danger">
                </form>
            </div>
        </div>
    </body>
</html>
<script src="/employee/jsedituser/edituser.js"></script>
<script src="/employee/https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>


<jsp:include page="footer.jsp" ></jsp:include>
