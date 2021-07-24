<jsp:include page="header.jsp"></jsp:include>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/employee/insertcss/insert.css">

    <div class="main-div">
        <body class="bck-image">
            <form action="employee-save" method="post">
                <div class="form-group row">
                    <label for="Name" class="col-sm-2 col-form-label">Name:</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" class="form-control" id="Name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="Surname" class="col-sm-2 col-form-label">Surname:</label>
                    <div class="col-sm-10">
                        <input type="text" name="surname" class="form-control" id="Surname">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="Age" class="col-sm-2 col-form-label">Age:</label>
                    <div class="col-sm-10">
                        <input type="text" name="age" class="form-control" id="Age">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="Salary" class="col-sm-2 col-form-label">Salary:</label>
                    <div class="col-sm-10">
                        <input type="text" name="salary" class="form-control" id="Salary">
                    </div>
                </div>
                <button class="btn btn-success" name="submit">Submit</button>
                <button class="btn btn-danger" name="cancel">Cancel</button>
            </form> 

    </div>
</div>
</body>
<jsp:include page="footer.jsp" ></jsp:include>