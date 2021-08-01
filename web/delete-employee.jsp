<jsp:include page="header.jsp"></jsp:include>

    <!DOCTYPE html>
    <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="preconnect" href="https://fonts.googleapis.com">
            <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
            <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
            <title>Document</title>
            <style>
                body {
                    background-color: #e1afff;
                }

                .main-div {
                    width: 1000px;
                    height: 400px;
                    background-color: #af4bff;
                    border-radius: 30px;
                    margin: auto;
                    margin-top: 10%;
                    display: flex;
                }

                .armimg {
                    width: 350px;
                    position: relative;
                    top: 20%;
                }

                .button-div {
                    display: flex;
                }

                .yes,
                .no {
                    border: 1px solid white;
                    width: 200px;
                    height: 50px;
                    margin: 20px;
                    border-radius: 10px;
                    background-color: #af4bff;
                    color: white;
                    font-size: 20px;
                }
                .yes,.no:hover{
                    background-color: white;
                    color: #af4bff;
                    cursor: pointer;
                }
                .headertext{
                    font-size: 2.5rem;
                    font-family: 'Poppins', sans-serif;
                    color: white;
                }
                .paragraph{
                    font-size: 1.5rem;
                    font-family: 'Poppins', sans-serif;
                    color: white;
                }
                .right-div{
                    padding-top: 3%;
                }
            </style>
        </head>

        <body>

            <div class="main-div">

                <div>
                    <img src="../test/arm.png" alt="" class="armimg"> 
                </div>

                <div class="right-div">
                    <h3 class="headertext">Hey,Wait!!</h3>
                    <p class="paragraph">Are you sure,you want to leave without confirmg?</p>
                    <div class="button-div">
                        <form action="delete-employee-save" method="post">
                            <input type="hidden" name="id" value="${employee.id}">
                        <button class="yes" name="submit">Yes</button>
                        <button class="no" name="cancel">No</button>
                    </form>
                </div>
            </div>
        </div>


    </body>

</html>
<jsp:include page="footer.jsp" ></jsp:include>