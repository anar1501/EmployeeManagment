<jsp:include page="header.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Success</title>
    <link rel="stylesheet" href="successcss/bootstrap.min.css">
    <link rel="stylesheet" href="successcss/thanks-page.css">

    
    <main id="thank-you" class="d-flex flex-column align-items-center justify-content-center" style="padding: 10%">
        <div class="text-center">
            <span class="display-1">Thank You!</span>
            <p class="fs-5 mb-5">for registration in <span class="fw-bold">Employee <a href="/employee/home" class="blue-link">Managment</a></span></p>
            <img src="successimages/mail-sent.svg" alt="Email sent icon">
            <h2 style="color:green">${info2}</h2>
            <div class="mt-5">
                <a href="/employee/home" class="btn btn-blue me-4">Homepage</a>
                <a href="#" class="btn btn-blue">Visit the Blog</a>
            </div>
        </div>
    </main>

<jsp:include page="footer.jsp" ></jsp:include>