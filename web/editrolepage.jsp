<jsp:include page="header.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Servlet EditRole</title>
<link rel="stylesheet" href="/employee/csseditrole/editrole.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<body style="background-color: darkslategray;">
    <form action="/employee/admin/save-edit-role" method="post">
        <input type="hidden" name="role-id" value="${role.id}">
        <div class="w-50 main-div">
            <div class="header-div p-3 "><span class="header-span">Role <b>List</b></span><button class="header-button"><i style="color: #576689;margin-right: 5px;" class="fas fa-plus-circle "></i>Add
                    New Role</button></div>
            <div class="table-div">
                <table class="table table-stripped table-hover">
                    <tr>
                        <th>Name</th>
                        <th>Permission</th>
                        <th>Status</th>
                        <th>Add Permission</th>
                    </tr>
                    <tr>
                        <td>${role.name}</td>

                        <td>

                            <select name="permission-id">
                                <c:forEach var="rp" items="${rds.findPermissionIdByRoleId(role.id)}">
                                    <option value="${rp.permissionId}">${rp.permissionName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <c:if test="${role.status eq 1}">
                                <label class="switch">
                                    <input name="status-id" type="checkbox" value="${role.status}" checked>
                                    <span class="slider round"></span>
                                </label>
                            </c:if>
                            <c:if test="${role.status ne 1}">
                                <label class="switch">
                                    <input name="status-id" type="checkbox" value="${role.status}">
                                    <span class="slider round"></span>
                                </label>
                            </c:if>
                        </td>
                        <td>
                            <div class="multiselect">
                                <div class="selectBox" onclick="showCheckboxes()">
                                    <select>
                                        <option>Select</option>
                                    </select>
                                    <div class="overSelect"></div>
                                </div>
                                <div id="checkboxes">
                                    <c:forEach var="p" items="${permissions}">
                                        <label for="one">
                                            <input type="checkbox" name="p-id" value="${p.id}" id="one">${p.pageName}</label>
                                        </c:forEach>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <footer class="footer">
                <div style="float: right;" class="footer-div p-2">
                    <button name="submit" class="btn btn-primary m-1">Submit</button>
                    <button name="cancel" class="btn btn-danger m-1">Cancel</button>
                </div>
            </footer>
        </div>
    </form>

<script src="/employee/jseditrole/editrole.js"></script>
</body>
<jsp:include page="footer.jsp" ></jsp:include>