<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>

    <link rel="stylesheet" href=forgetpasswordcss/forgot-password-design.css>

    <div class="navbar">
        <div >
            <a href="Home"><i class="fa fa-fw fa-home"></i> Home</a>
            <a href="Search"><i class="fa fa-fw fa-search"></i> Search</a> 
            <a href="Contact"><i class="fa fa-fw fa-envelope"></i> Contact</a>
        </div>
        <div>
            <a href="Register"><i class="fa fa-fw fa-user"></i>Register</a>
            <a href="Login"><i class="fa fa-fw fa-user"></i>Login</a>
        </div>
    </div>

    <div class="container">


        <form action=save-change-password class="forgot-password" method="post">
            <p>Please enter your new password to change for your account</p>
        <c:out value="${error}"></c:out>
            <input class="forgot-passwordinput" type="password" name="password" placeholder="Enter new password" required />
            <input class="forgot-passwordinput" type="password" name="passwordRepeat" placeholder="Confirm repeat password" required />
            <input class="forgot-passwordinput" type="hidden" name="id" value="${id}" placeholder="Confirm repeat password" required />
        <div class="forgot-passwordactions">
            <button type="submit" class="forgot-passwordbutton active">Sendout</button>
        </div>

    </form>

</div>

</body>
</html>

<jsp:include page="footer.jsp" ></jsp:include>