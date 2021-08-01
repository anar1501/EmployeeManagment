<jsp:include page="header.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="csslogin/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="csslogin/fontawesome-all.min.css">
<link rel="stylesheet" type="text/css" href="csslogin/iofrm-style.css">
<link rel="stylesheet" type="text/css" href="csslogin/iofrm-theme17.css">

<div class="form-body without-side">
    <div class="website-logo">
        <a href="index.html">
            <div class="logo">
            </div>
        </a>
    </div>
    <div class="row">
        <div class="img-holder">
            <div class="bg"></div>
            <div class="info-holder">
                <img src="imageslogin/graphic3.svg" alt="">
            </div>
        </div>
        <div class="form-holder">
            <div class="form-content">
                <div class="form-items">
                    <h3>Login to account</h3>
                    <form action="check-login" method="post">
                        <c:if test="${error eq 'Email is incorrect!'}">
                            <c:out value="${error}"></c:out>
                        </c:if>
                        <input class="form-control" type="email" name="email" placeholder="E-mail Address" required>
                        <c:if test="${error eq 'Password is incorrect!'}">
                            <c:out value="${error}"></c:out>
                        </c:if>
                        <input class="form-control" type="password" name="password" placeholder="Password" required>
                        <div class="form-button">
                            <button id="submit" type="submit" class="ibtn">Login</button> <a href="forget-password">Forget password?</a>
                        </div>
                    </form>
                    <div class="other-links">
                        <div class="text">Or login with</div>
                        <a href="#"><i class="fab fa-facebook-f"></i>Facebook</a><a href="#"><i class="fab fa-google"></i>Google</a><a href="#"><i class="fab fa-linkedin-in"></i>Linkedin</a>
                    </div>
                    <div class="page-links">
                        <a href="register">Register new account</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="jslogin/jquery.min.js"></script>
<script src="jslogin/popper.min.js"></script>
<script src="jslogin/bootstrap.min.js"></script>
<script src="jslogin/main.js"></script>

<jsp:include page="footer.jsp" ></jsp:include>