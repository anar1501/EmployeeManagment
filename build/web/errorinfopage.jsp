<jsp:include page="header.jsp"></jsp:include>


    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error!</title>
    <link rel="stylesheet" href="errorcss/bootstrap.min.css">
    <body style="padding-top: 100px;">
        <main class="d-flex flex-column align-items-center">
            <div class="text-center text-muted">
                <img src="errorimages/error-icon.png" alt="Error icon">
                <span class="h2 d-block">Something Went Wrong</span>
                <h2 style="color: red">${info1}</h2>
                <a href="${info2}" class="btn btn-primary mt-4">Resend Email</a>
            </div>
        </main>
    <jsp:include page="footer.jsp" ></jsp:include>
