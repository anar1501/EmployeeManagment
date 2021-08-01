<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>


    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error!</title>
    <link rel="stylesheet" href="errorcss/bootstrap.min.css">
    <body style="padding-top: 100px;">
        <main class="d-flex flex-column align-items-center">
            <div class="text-center text-muted">
                
                <img src="../errorimages/errorimg.png" alt="Error icon">
                <span class="h2 d-block">Something Went Wrong</span>
                
            <c:choose>
                <c:when test="${infoex eq 'Confirmation code is expired!'}">
                    <h2 style="color: red">${infoex}</h2>
                    <a href="${infos}" class="btn btn-primary mt-4">Resend Email</a>
                </c:when>
                <c:when test="${infoex eq 'Your account is not confirmed!'}">
                    <h2 style="color: red">${infoex}</h2>
                    <a href="${infos}" class="btn btn-primary mt-4">Resend Email</a>
                </c:when>
                <c:when test="${infoex eq 'Forget Password Confirmation code is expired!'}">
                    <h2 style="color: red">${infoex}</h2>
                    <a href="${infos}" class="btn btn-primary mt-4">Resend Email</a>
                </c:when>
                <c:otherwise>
                    <h2 style="color: red">${infoe}</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
    <jsp:include page="footer.jsp" ></jsp:include>
